package service;

import model.Projeto;
import model.Usuario;
import repository.ProjetoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciamento de projetos,
 * contendo regras de negócio e validações.
 */
public class ProjetoService {
    // Repositório de projetos
    private ProjetoRepository projetoRepository;

    // Construtor
    public ProjetoService() {
        this.projetoRepository = new ProjetoRepository();
    }

    /**
     * Método para cadastrar novo projeto
     * @param projeto Projeto a ser cadastrado
     * @return boolean indicando sucesso no cadastro
     * @throws IllegalArgumentException em caso de dados inválidos
     */
    public boolean cadastrarProjeto(Projeto projeto) {
        // Validações
        validarDadosProjeto(projeto);

        // Verifica se projeto com mesmo nome já existe
        if (projetoRepository.buscarPorNome(projeto.getNome()).isPresent()) {
            throw new IllegalArgumentException("Projeto com este nome já existe");
        }

        // Adiciona projeto
        return projetoRepository.adicionar(projeto);
    }

    /**
     * Método para validar dados do projeto
     * @param projeto Projeto a ser validado
     * @throws IllegalArgumentException em caso de dados inválidos
     */
    private void validarDadosProjeto(Projeto projeto) {
        // Validação de nome
        if (projeto.getNome() == null || projeto.getNome().trim().length() < 3) {
            throw new IllegalArgumentException("Nome do projeto inválido");
        }

        // Validação de descrição
        if (projeto.getDescricao() == null || projeto.getDescricao().trim().length() < 10) {
            throw new IllegalArgumentException("Descrição do projeto muito curta");
        }

        // Validação de datas
        validarDatas(projeto.getDataInicio(), projeto.getDataTerminoPrevista());

        // Validação de gerente
        if (projeto.getGerente() == null) {
            throw new IllegalArgumentException("Gerente do projeto não definido");
        }
    }

    /**
     * Validação de datas do projeto
     * @param dataInicio Data de início do projeto
     * @param dataTermino Data de término prevista
     */
    private void validarDatas(LocalDate dataInicio, LocalDate dataTermino) {
        if (dataInicio == null || dataTermino == null) {
            throw new IllegalArgumentException("Datas do projeto não podem ser nulas");
        }

        if (dataInicio.isAfter(dataTermino)) {
            throw new IllegalArgumentException("Data de início não pode ser posterior à data de término");
        }

        if (dataInicio.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de início não pode ser no passado");
        }
    }

    /**
     * Atualizar status do projeto
     * @param nomeProjeto Nome do projeto
     * @param novoStatus Novo status do projeto
     * @return boolean indicando sucesso na atualização
     */
    public boolean atualizarStatusProjeto(String nomeProjeto, Projeto.Status novoStatus) {
        Optional<Projeto> projetoOptional = projetoRepository.buscarPorNome(nomeProjeto);

        if (projetoOptional.isPresent()) {
            Projeto projeto = projetoOptional.get();
            projeto.setStatus(novoStatus);
            return projetoRepository.atualizar(projeto);
        }

        return false;
    }

    /**
     * Listar projetos de um gerente
     * @param gerente Gerente responsável pelos projetos
     * @return Lista de projetos do gerente
     */
    public List<Projeto> listarProjetosPorGerente(Usuario gerente) {
        return projetoRepository.listarPorGerente(gerente);
    }

    /**
     * Listar projetos por status
     * @param status Status dos projetos
     * @return Lista de projetos com o status informado
     */
    public List<Projeto> listarProjetosPorStatus(Projeto.Status status) {
        return projetoRepository.listarPorStatus(status);
    }

    /**
     * Listar projetos em um intervalo de datas
     * @param dataInicio Data inicial
     * @param dataFim Data final
     * @return Lista de projetos no intervalo
     */
    public List<Projeto> listarProjetosNoIntervalo(LocalDate dataInicio, LocalDate dataFim) {
        return projetoRepository.listarPorIntervaloData(dataInicio, dataFim);
    }

    /**
     * Listar projetos atrasados
     * @return Lista de projetos atrasados
     */
    public List<Projeto> listarProjetosAtrasados() {
        return projetoRepository.listarProjetosAtrasados();
    }

    /**
     * Quantidade total de projetos
     * @return Número de projetos cadastrados
     */
    public int quantidadeProjetos() {
        return projetoRepository.quantidadeProjetos();
    }

    /**
     * Quantidade de projetos por status
     * @param status Status dos projetos
     * @return Número de projetos com o status informado
     */
    public int quantidadeProjetosPorStatus(Projeto.Status status) {
        return projetoRepository.contarProjetosPorStatus(status);
    }

    /**
     * Remover projeto
     * @param nomeProjeto Nome do projeto a ser removido
     * @return boolean indicando sucesso na remoção
     */
    public boolean removerProjeto(String nomeProjeto) {
        return projetoRepository.remover(nomeProjeto);
    }
}
