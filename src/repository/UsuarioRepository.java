package repository;

import model.Usuario;
import model.Perfil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciamento de usuários no sistema.
 */
public class UsuarioRepository {
    // Lista para armazenar usuários
    private List<Usuario> usuarios;

    // Construtor
    public UsuarioRepository() {
        this.usuarios = new ArrayList<>();
    }

    /**
     * Adiciona um novo usuário ao repositório
     * @param usuario Usuário a ser adicionado
     * @return boolean indicando se a adição foi bem-sucedida
     */
    public boolean adicionar(Usuario usuario) {
        // Verifica se já existe usuário com o mesmo CPF
        if (buscarPorCPF(usuario.getCpf()).isPresent()) {
            return false;
        }
        return usuarios.add(usuario);
    }

    /**
     * Busca usuário por CPF
     * @param cpf CPF do usuário
     * @return Optional com o usuário encontrado
     */
    public Optional<Usuario> buscarPorCPF(String cpf) {
        return usuarios.stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst();
    }

    /**
     * Busca usuário por login
     * @param login Login do usuário
     * @return Optional com o usuário encontrado
     */
    public Optional<Usuario> buscarPorLogin(String login) {
        return usuarios.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }

    /**
     * Lista todos os usuários
     * @return Lista de todos os usuários
     */
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    /**
     * Lista usuários por perfil
     * @param perfil Perfil a ser filtrado
     * @return Lista de usuários com o perfil especificado
     */
    public List<Usuario> listarPorPerfil(Perfil perfil) {
        return usuarios.stream()
                .filter(u -> u.getPerfil() == perfil)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza um usuário existente
     * @param usuarioAtualizado Usuário com informações atualizadas
     * @return boolean indicando se a atualização foi bem-sucedida
     */
    public boolean atualizar(Usuario usuarioAtualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuarioAtualizado.getId())) {
                usuarios.set(i, usuarioAtualizado);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um usuário pelo CPF
     * @param cpf CPF do usuário a ser removido
     * @return boolean indicando se a remoção foi bem-sucedida
     */
    public boolean remover(String cpf) {
        return usuarios.removeIf(u -> u.getCpf().equals(cpf));
    }

    /**
     * Verifica se o login e senha correspondem a um usuário
     * @param login Login do usuário
     * @param senha Senha do usuário
     * @return Optional com o usuário autenticado
     */
    public Optional<Usuario> autenticar(String login, String senha) {
        return usuarios.stream()
                .filter(u -> u.getLogin().equals(login) && senhaCorresponde(u, senha))
                .findFirst();
    }

    /**
     * Método privado para verificar correspondência de senha
     * @param usuario Usuário a ser verificado
     * @param senhaDigitada Senha digitada para comparação
     * @return boolean indicando se a senha está correta
     */
    private boolean senhaCorresponde(Usuario usuario, String senhaDigitada) {
        // Na prática, use hash de senha
        return usuario.getSenha().equals(senhaDigitada);
    }

    /**
     * Retorna o número total de usuários
     * @return Quantidade de usuários
     */
    public int quantidadeUsuarios() {
        return usuarios.size();
    }
}
