package model;

import java.time.LocalDate;
import java.util.UUID;


public class Projeto {
    private String id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private Status status;
    private Usuario gerente;

    public enum Status {
        PLANEJADO,
        EM_ANDAMENTO,
        CONCLUIDO,
        CANCELADO
    }

    // Construtor
    public Projeto(String nome, String descricao, LocalDate dataInicio,
                   LocalDate dataTerminoPrevista, Usuario gerente) {
        this.id = UUID.randomUUID().toString(); // Gera ID único
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.gerente = gerente;
        this.status = Status.PLANEJADO; // Status inicial
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTerminoPrevista() {
        return dataTerminoPrevista;
    }

    public Status getStatus() {
        return status;
    }

    public Usuario getGerente() {
        return gerente;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataTerminoPrevista(LocalDate dataTerminoPrevista) {
        this.dataTerminoPrevista = dataTerminoPrevista;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setGerente(Usuario gerente) {
        this.gerente = gerente;
    }

    // Método para verificar se o projeto está atrasado
    public boolean estaAtrasado() {
        return status != Status.CONCLUIDO &&
                LocalDate.now().isAfter(dataTerminoPrevista);
    }

    // Método para calcular duração prevista do projeto
    public long duracaoPrevista() {
        return java.time.temporal.ChronoUnit.DAYS.between(dataInicio, dataTerminoPrevista);
    }

    // Sobrescrita do método toString
    @Override
    public String toString() {
        return "Projeto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", status=" + status +
                ", gerente=" + gerente.getNomeCompleto() +
                ", dataInicio=" + dataInicio +
                ", dataTerminoPrevista=" + dataTerminoPrevista +
                '}';
    }
}
