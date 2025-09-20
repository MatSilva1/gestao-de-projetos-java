package model;

import java.util.UUID;
import java.time.LocalDateTime;


public class Usuario {
    private String id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private Perfil perfil;
    private LocalDateTime dataCriacao;

    // Construtor
    public Usuario(String nomeCompleto, String cpf, String email,
                   String cargo, String login, String senha, Perfil perfil) {
        this.id = UUID.randomUUID().toString(); // Gera ID único
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.dataCriacao = LocalDateTime.now(); // Registra data de criação
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public String getLogin() {
        return login;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    // Setters para campos que podem ser atualizados
    public void setEmail(String email) {
        this.email = email;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    // Método para validar CPF (implementação simples)
    public boolean validarCPF() {
        // Remove caracteres não numéricos
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        // Verifica se tem 11 dígitos
        return cpfLimpo.length() == 11;
    }

    // Método toString para representação em texto
    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", cargo='" + cargo + '\'' +
                ", perfil=" + perfil +
                '}';
    }

    public boolean verificarSenha(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }
}
