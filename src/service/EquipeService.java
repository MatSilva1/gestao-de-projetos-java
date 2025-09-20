package service;

import model.Equipe;
import model.Usuario;
import repository.EquipeRepository;

import java.util.List;
import java.util.Optional;


public class EquipeService {
    // Repositório de equipes
    private EquipeRepository equipeRepository;

    // Construtor
    public EquipeService() {
        this.equipeRepository = new EquipeRepository();
    }

    public boolean cadastrarEquipe(Equipe equipe) {
        // Validações
        validarDadosEquipe(equipe);

        // Verifica se equipe com mesmo nome já existe
        if (equipeRepository.buscarPorNome(equipe.getNome()).isPresent()) {
            throw new IllegalArgumentException("Equipe com este nome já existe");
        }

        // Adiciona equipe
        return equipeRepository.adicionar(equipe);
    }

    private void validarDadosEquipe(Equipe equipe) {
        // Validação de nome
        if (equipe.getNome() == null || equipe.getNome().trim().length() < 3) {
            throw new IllegalArgumentException("Nome da equipe inválido");
        }

        // Validação de descrição
        if (equipe.getDescricao() == null || equipe.getDescricao().trim().length() < 10) {
            throw new IllegalArgumentException("Descrição da equipe muito curta");
        }
    }

    public boolean adicionarMembroNaEquipe(String nomeEquipe, Usuario membro) {
        Optional<Equipe> equipeOptional = equipeRepository.buscarPorNome(nomeEquipe);

        if (equipeOptional.isPresent()) {
            Equipe equipe = equipeOptional.get();

            // Verifica se o membro já está na equipe
            if (equipe.contemMembro(membro)) {
                throw new IllegalArgumentException("Usuário já é membro da equipe");
            }

            // Verifica limite de membros
            if (!equipeRepository.podAdicionarMembro(equipe, membro)) {
                throw new IllegalArgumentException("Limite de membros atingido");
            }

            // Adiciona membro
            equipe.adicionarMembro(membro);
            return equipeRepository.atualizar(equipe);
        }

        throw new IllegalArgumentException("Equipe não encontrada");
    }

    public boolean removerMembroDaEquipe(String nomeEquipe, Usuario membro) {
        Optional<Equipe> equipeOptional = equipeRepository.buscarPorNome(nomeEquipe);

        if (equipeOptional.isPresent()) {
            Equipe equipe = equipeOptional.get();

            // Verifica se o membro está na equipe
            if (!equipe.contemMembro(membro)) {
                throw new IllegalArgumentException("Usuário não é membro da equipe");
            }

            // Remove membro
            equipe.removerMembro(membro);
            return equipeRepository.atualizar(equipe);
        }

        throw new IllegalArgumentException("Equipe não encontrada");
    }

    public List<Equipe> listarEquipesPorMembro(Usuario membro) {
        return equipeRepository.listarPorMembro(membro);
    }

    public List<Equipe> listarTodasEquipes() {
        return equipeRepository.listarTodas();
    }

    public List<Equipe> listarEquipesPorTamanho() {
        return equipeRepository.listarPorTamanho();
    }

    public int quantidadeEquipes() {
        return equipeRepository.quantidadeEquipes();
    }

    public boolean atualizarEquipe(Equipe equipe) {
        // Validações
        validarDadosEquipe(equipe);

        return equipeRepository.atualizar(equipe);
    }

    public boolean removerEquipe(String nomeEquipe) {
        return equipeRepository.remover(nomeEquipe);
    }
}
