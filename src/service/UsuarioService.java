package service;

import model.Usuario;
import model.Perfil;
import repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Serviço para gerenciamento de usuários,
 * contendo regras de negócio e validações.
 */
public class UsuarioService {
    // Repositório de usuários
    private UsuarioRepository usuarioRepository;

    // Construtor
    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    /**
     * Método para cadastrar novo usuário
     * @param usuario Usuário a ser cadastrado
     * @return boolean indicando sucesso no cadastro
     * @throws IllegalArgumentException em caso de dados inválidos
     */
    public boolean cadastrarUsuario(Usuario usuario) {
        // Validações
        validarDadosUsuario(usuario);

        // Verifica se CPF já está cadastrado
        if (usuarioRepository.buscarPorCPF(usuario.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        // Verifica se login já existe
        if (usuarioRepository.buscarPorLogin(usuario.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Login já existe");
        }

        // Adiciona usuário
        return usuarioRepository.adicionar(usuario);
    }

    /**
     * Método para validar dados do usuário
     * @param usuario Usuário a ser validado
     * @throws IllegalArgumentException em caso de dados inválidos
     */
    private void validarDadosUsuario(Usuario usuario) {
        // Validação de nome
        if (usuario.getNomeCompleto() == null || usuario.getNomeCompleto().trim().length() < 3) {
            throw new IllegalArgumentException("Nome inválido");
        }

        // Validação de CPF
        validarCPF(usuario.getCpf());

        // Validação de email
        validarEmail(usuario.getEmail());

        // Validação de login
        validarLogin(usuario.getLogin());

        // Validação de senha
        validarSenha(usuario.getSenha());
    }

    /**
     * Validação de CPF
     * @param cpf CPF a ser validado
     */
    private void validarCPF(String cpf) {
        // Remove caracteres não numéricos
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        // Verifica se tem 11 dígitos
        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF inválido");
        }

        // Implementar algoritmo de validação de CPF
        // (pode ser adicionada lógica mais complexa)
    }

    /**
     * Validação de email
     * @param email Email a ser validado
     */
    private void validarEmail(String email) {
        // Regex para validação de email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (email == null || !Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    /**
     * Validação de login
     * @param login Login a ser validado
     */
    private void validarLogin(String login) {
        if (login == null || login.length() < 4) {
            throw new IllegalArgumentException("Login deve ter no mínimo 4 caracteres");
        }
    }

    /**
     * Validação de senha
     * @param senha Senha a ser validada
     */
    private void validarSenha(String senha) {
        if (senha == null || senha.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter no mínimo 6 caracteres");
        }
    }

    /**
     * Método de autenticação de usuário
     * @param login Login do usuário
     * @param senha Senha do usuário
     * @return Optional com usuário autenticado
     */
    public Optional<Usuario> autenticar(String login, String senha) {
        return usuarioRepository.autenticar(login, senha);
    }

    /**
     * Listar todos os usuários
     * @return Lista de usuários
     */
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarTodos();
    }

    /**
     * Listar usuários por perfil
     * @param perfil Perfil a ser filtrado
     * @return Lista de usuários do perfil
     */
    public List<Usuario> listarUsuariosPorPerfil(Perfil perfil) {
        return usuarioRepository.listarPorPerfil(perfil);
    }

    /**
     * Atualizar usuário
     * @param usuario Usuário a ser atualizado
     * @return boolean indicando sucesso na atualização
     */
    public boolean atualizarUsuario(Usuario usuario) {
        // Validações
        validarDadosUsuario(usuario);

        return usuarioRepository.atualizar(usuario);
    }

    /**
     * Remover usuário
     * @param cpf CPF do usuário a ser removido
     * @return boolean indicando sucesso na remoção
     */
    public boolean removerUsuario(String cpf) {
        return usuarioRepository.remover(cpf);
    }

    /**
     * Quantidade total de usuários
     * @return Número de usuários cadastrados
     */
    public int quantidadeUsuarios() {
        return usuarioRepository.quantidadeUsuarios();
    }
}
