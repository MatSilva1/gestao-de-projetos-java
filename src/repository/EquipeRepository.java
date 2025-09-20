package repository;

import model.Equipe;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciamento de equipes no sistema.
 */
public class EquipeRepository {
    // Lista para armazenar equipes
    private List<Equipe> equipes;

    // Construtor
    public EquipeRepository() {
        this.equipes = new ArrayList<>();
    }

    /**
     * Adiciona uma nova equipe ao repositório
     * @param equipe Equipe a ser adicionada
     * @return boolean indicando se a adição foi bem-sucedida
     */
    public boolean adicionar(Equipe equipe) {
        // Verifica se já existe equipe com o mesmo nome
        if (buscarPorNome(equipe.getNome()).isPresent()) {
            return false;
        }
        return equipes.add(equipe);
    }

    /**
     * Busca equipe por nome
     * @param nome Nome da equipe
     * @return Optional com a equipe encontrada
     */
    public Optional<Equipe> buscarPorNome(String nome) {
        return equipes.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    /**
     * Busca equipe por ID
     * @param id ID da equipe
     * @return Optional com a equipe encontrada
     */
    public Optional<Equipe> buscarPorId(String id) {
        return equipes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    /**
     * Lista todas as equipes
     * @return Lista de todas as equipes
     */
    public List<Equipe> listarTodas() {
        return new ArrayList<>(equipes);
    }

    /**
     * Lista equipes que contêm um determinado membro
     * @param membro Usuário a ser buscado
     * @return Lista de equipes que contêm o membro
     */
    public List<Equipe> listarPorMembro(Usuario membro) {
        return equipes.stream()
                .filter(e -> e.contemMembro(membro))
                .collect(Collectors.toList());
    }

    /**
     * Atualiza uma equipe existente
     * @param equipeAtualizada Equipe com informações atualizadas
     * @return boolean indicando se a atualização foi bem-sucedida
     */
    public boolean atualizar(Equipe equipeAtualizada) {
        for (int i = 0; i < equipes.size(); i++) {
            if (equipes.get(i).getId().equals(equipeAtualizada.getId())) {
                equipes.set(i, equipeAtualizada);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove uma equipe pelo nome
     * @param nome Nome da equipe a ser removida
     * @return boolean indicando se a remoção foi bem-sucedida
     */
    public boolean remover(String nome) {
        return equipes.removeIf(e -> e.getNome().equalsIgnoreCase(nome));
    }

    /**
     * Conta o número de equipes
     * @return Quantidade total de equipes
     */
    public int quantidadeEquipes() {
        return equipes.size();
    }

    /**
     * Lista equipes ordenadas por quantidade de membros
     * @return Lista de equipes ordenadas por tamanho
     */
    public List<Equipe> listarPorTamanho() {
        return equipes.stream()
                .sorted((e1, e2) -> Integer.compare(e2.quantidadeMembros(), e1.quantidadeMembros()))
                .collect(Collectors.toList());
    }

    /**
     * Verifica se um membro pode ser adicionado a uma equipe
     * @param equipe Equipe para verificação
     * @param membro Membro a ser adicionado
     * @return boolean indicando se o membro pode ser adicionado
     */
    public boolean podAdicionarMembro(Equipe equipe, Usuario membro) {
        // Exemplo de regra: limite máximo de 10 membros por equipe
        return equipe.quantidadeMembros() < 10;
    }

    /**
     * Adiciona um membro a uma equipe
     * @param nomeEquipe Nome da equipe
     * @param membro Membro a ser adicionado
     * @return boolean indicando se a adição foi bem-sucedida
     */
    public boolean adicionarMembroNaEquipe(String nomeEquipe, Usuario membro) {
        Optional<Equipe> equipeOptional = buscarPorNome(nomeEquipe);

        if (equipeOptional.isPresent()) {
            Equipe equipe = equipeOptional.get();
            if (podAdicionarMembro(equipe, membro)) {
                equipe.adicionarMembro(membro);
                return true;
            }
        }

        return false;
    }
}
