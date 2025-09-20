package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe que representa uma Equipe no Sistema de Gestão de Projetos e Equipes.
 */
public class Equipe {
    // Atributos privados
    private String id;
    private String nome;
    private String descricao;
    private List<Usuario> membros;
    private LocalDateTime dataCriacao;

    // Construtor
    public Equipe(String nome, String descricao) {
        this.id = UUID.randomUUID().toString(); // Gera ID único
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>(); // Inicializa lista de membros
        this.dataCriacao = LocalDateTime.now(); // Registra data de criação
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Usuario> getMembros() {
        return new ArrayList<>(membros); // Retorna cópia para evitar modificação direta
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Métodos para gerenciamento de membros
    public void adicionarMembro(Usuario usuario) {
        if (!membros.contains(usuario)) {
            membros.add(usuario);
        }
    }

    public void removerMembro(Usuario usuario) {
        membros.remove(usuario);
    }

    // Método para verificar se usuário é membro
    public boolean contemMembro(Usuario usuario) {
        return membros.contains(usuario);
    }

    // Método para contar membros
    public int quantidadeMembros() {
        return membros.size();
    }

    // Método para listar gerentes na equipe
    public List<Usuario> listarGerentes() {
        return membros.stream()
                .filter(usuario -> usuario.getPerfil() == Perfil.GERENTE)
                .toList();
    }

    // Sobrescrita do método toString
    @Override
    public String toString() {
        return "Equipe{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", quantidadeMembros=" + quantidadeMembros() +
                ", dataCriacao=" + dataCriacao +
                '}';
    }

    // Método equals para comparação
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipe equipe = (Equipe) o;
        return id.equals(equipe.id);
    }

    // Método hashCode para uso em coleções
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
