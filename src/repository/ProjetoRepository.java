package repository;

import model.Projeto;
import model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciamento de projetos no sistema.
 */
public class ProjetoRepository {
    // Lista para armazenar projetos
    private List<Projeto> projetos;

    // Construtor
    public ProjetoRepository() {
        this.projetos = new ArrayList<>();
    }

    /**
     * Adiciona um novo projeto ao repositório
     * @param projeto Projeto a ser adicionado
     * @return boolean indicando se a adição foi bem-sucedida
     */
    public boolean adicionar(Projeto projeto) {
        // Verifica se já existe projeto com o mesmo nome
        if (buscarPorNome(projeto.getNome()).isPresent()) {
            return false;
        }
        return projetos.add(projeto);
    }

    /**
     * Busca projeto por nome
     * @param nome Nome do projeto
     * @return Optional com o projeto encontrado
     */
    public Optional<Projeto> buscarPorNome(String nome) {
        return projetos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    /**
     * Busca projeto por ID
     * @param id ID do projeto
     * @return Optional com o projeto encontrado
     */
    public Optional<Projeto> buscarPorId(String id) {
        return projetos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    /**
     * Lista todos os projetos
     * @return Lista de todos os projetos
     */
    public List<Projeto> listarTodos() {
        return new ArrayList<>(projetos);
    }

    /**
     * Lista projetos por status
     * @param status Status a ser filtrado
     * @return Lista de projetos com o status especificado
     */
    public List<Projeto> listarPorStatus(Projeto.Status status) {
        return projetos.stream()
                .filter(p -> p.getStatus() == status)
                .collect(Collectors.toList());
    }

    /**
     * Lista projetos de um gerente específico
     * @param gerente Gerente responsável pelos projetos
     * @return Lista de projetos do gerente
     */
    public List<Projeto> listarPorGerente(Usuario gerente) {
        return projetos.stream()
                .filter(p -> p.getGerente().equals(gerente))
                .collect(Collectors.toList());
    }

    /**
     * Lista projetos em um intervalo de datas
     * @param dataInicio Data de início
     * @param dataFim Data de término
     * @return Lista de projetos no intervalo
     */
    public List<Projeto> listarPorIntervaloData(LocalDate dataInicio, LocalDate dataFim) {
        return projetos.stream()
                .filter(p -> !p.getDataInicio().isBefore(dataInicio) &&
                        !p.getDataTerminoPrevista().isAfter(dataFim))
                .collect(Collectors.toList());
    }

    /**
     * Atualiza um projeto existente
     * @param projetoAtualizado Projeto com informações atualizadas
     * @return boolean indicando se a atualização foi bem-sucedida
     */
    public boolean atualizar(Projeto projetoAtualizado) {
        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getId().equals(projetoAtualizado.getId())) {
                projetos.set(i, projetoAtualizado);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um projeto pelo nome
     * @param nome Nome do projeto a ser removido
     * @return boolean indicando se a remoção foi bem-sucedida
     */
    public boolean remover(String nome) {
        return projetos.removeIf(p -> p.getNome().equalsIgnoreCase(nome));
    }

    /**
     * Conta o número de projetos
     * @return Quantidade total de projetos
     */
    public int quantidadeProjetos() {
        return projetos.size();
    }

    /**
     * Conta projetos por status
     * @param status Status a ser contado
     * @return Quantidade de projetos com o status especificado
     */
    public int contarProjetosPorStatus(Projeto.Status status) {
        return (int) projetos.stream()
                .filter(p -> p.getStatus() == status)
                .count();
    }

    /**
     * Verifica projetos atrasados
     * @return Lista de projetos atrasados
     */
    public List<Projeto> listarProjetosAtrasados() {
        return projetos.stream()
                .filter(Projeto::estaAtrasado)
                .collect(Collectors.toList());
    }
}
