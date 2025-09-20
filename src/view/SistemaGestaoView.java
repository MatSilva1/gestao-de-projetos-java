package view;

import model.Usuario;
import model.Perfil;
import model.Projeto;
import model.Equipe;
import service.UsuarioService;
import service.ProjetoService;
import service.EquipeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class SistemaGestaoView {
    private Scanner scanner;
    private UsuarioService usuarioService;
    private ProjetoService projetoService;
    private EquipeService equipeService;
    private Usuario usuarioLogado;

    public SistemaGestaoView() {
        this.scanner = new Scanner(System.in);
        this.usuarioService = new UsuarioService();
        this.projetoService = new ProjetoService();
        this.equipeService = new EquipeService();
    }

    public void iniciar() {
        while (true) {
            if (usuarioLogado == null) {
                menuLogin();
            } else {
                menuPrincipal();
            }
        }
    }

    private void menuLogin() {
        System.out.println("\n--- SISTEMA DE GESTÃO ---");
        System.out.println("1 - Fazer Login");
        System.out.println("2 - Cadastrar Novo Usuário");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (opcao) {
            case 1:
                realizarLogin();
                break;
            case 2:
                cadastrarNovoUsuario();
                break;
            case 0:
                System.out.println("Encerrando o sistema...");
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void realizarLogin() {
        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        try {
            usuarioLogado = usuarioService.autenticar(login, senha)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário ou senha inválidos"));
            System.out.println("Bem-vindo, " + usuarioLogado.getNomeCompleto() + "!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarNovoUsuario() {
        try {
            System.out.print("Nome Completo: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();
            System.out.print("Login: ");
            String login = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            // Escolha de perfil
            System.out.println("Selecione o Perfil:");
            for (Perfil perfil : Perfil.values()) {
                System.out.println(perfil.ordinal() + " - " + perfil);
            }
            int perfilEscolhido = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            Usuario novoUsuario = new Usuario(
                    nome,
                    cpf,
                    email,
                    cargo,
                    login,
                    senha,
                    Perfil.values()[perfilEscolhido]
            );

            if (usuarioService.cadastrarUsuario(novoUsuario)) {
                System.out.println("Usuário cadastrado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private void menuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1 - Gerenciar Usuários");
        System.out.println("2 - Gerenciar Projetos");
        System.out.println("3 - Gerenciar Equipes");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (opcao) {
            case 1:
                menuGerenciarUsuarios();
                break;
            case 2:
                menuGerenciarProjetos();
                break;
            case 3:
                menuGerenciarEquipes();
                break;
            case 4:
                usuarioLogado = null;
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void menuGerenciarUsuarios() {
        System.out.println("\n--- GERENCIAR USUÁRIOS ---");
        System.out.println("1 - Listar Usuários");
        System.out.println("2 - Buscar Usuário");
        System.out.println("0 - Voltar");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (opcao) {
            case 1:
                listarUsuarios();
                break;
            case 2:
                // Implementar busca de usuário
                break;
            case 0:
                return;
        }
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        usuarios.forEach(System.out::println);
    }

    private void menuGerenciarProjetos() {
        System.out.println("\n--- GERENCIAR PROJETOS ---");
        System.out.println("1 - Cadastrar Novo Projeto");
        System.out.println("2 - Listar Projetos");
        System.out.println("3 - Listar Projetos por Status");
        System.out.println("4 - Listar Projetos Atrasados");
        System.out.println("5 - Atualizar Status do Projeto");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (opcao) {
            case 1:
                cadastrarNovoProjeto();
                break;
            case 2:
                listarProjetos();
                break;
            case 3:
                listarProjetosPorStatus();
                break;
            case 4:
                listarProjetosAtrasados();
                break;
            case 5:
                atualizarStatusProjeto();
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarNovoProjeto() {
        try {
            System.out.print("Nome do Projeto: ");
            String nome = scanner.nextLine();

            System.out.print("Descrição do Projeto: ");
            String descricao = scanner.nextLine();

            System.out.print("Data de Início (AAAA-MM-DD): ");
            LocalDate dataInicio = LocalDate.parse(scanner.nextLine());

            System.out.print("Data de Término Prevista (AAAA-MM-DD): ");
            LocalDate dataTermino = LocalDate.parse(scanner.nextLine());

            // Selecionar gerente
            List<Usuario> gerentes = usuarioService.listarUsuariosPorPerfil(Perfil.GERENTE);
            System.out.println("Selecione o Gerente:");
            for (int i = 0; i < gerentes.size(); i++) {
                System.out.println(i + " - " + gerentes.get(i).getNomeCompleto());
            }
            int gerenteIndex = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            Projeto novoProjeto = new Projeto(
                    nome,
                    descricao,
                    dataInicio,
                    dataTermino,
                    gerentes.get(gerenteIndex)
            );

            if (projetoService.cadastrarProjeto(novoProjeto)) {
                System.out.println("Projeto cadastrado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar projeto: " + e.getMessage());
        }
    }

    private void listarProjetos() {
        List<Projeto> projetos = projetoService.listarProjetosPorStatus(null);
        projetos.forEach(System.out::println);
    }

    private void listarProjetosPorStatus() {
        System.out.println("Selecione o Status:");
        for (Projeto.Status status : Projeto.Status.values()) {
            System.out.println(status.ordinal() + " - " + status);
        }
        int statusIndex = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        List<Projeto> projetos = projetoService.listarProjetosPorStatus(
                Projeto.Status.values()[statusIndex]
        );
        projetos.forEach(System.out::println);
    }

    private void listarProjetosAtrasados() {
        List<Projeto> projetosAtrasados = projetoService.listarProjetosAtrasados();
        if (projetosAtrasados.isEmpty()) {
            System.out.println("Não há projetos atrasados.");
        } else {
            projetosAtrasados.forEach(System.out::println);
        }
    }

    private void atualizarStatusProjeto() {
        try {
            System.out.print("Nome do Projeto: ");
            String nomeProjeto = scanner.nextLine();

            System.out.println("Selecione o Novo Status:");
            for (Projeto.Status status : Projeto.Status.values()) {
                System.out.println(status.ordinal() + " - " + status);
            }
            int statusIndex = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (projetoService.atualizarStatusProjeto(
                    nomeProjeto,
                    Projeto.Status.values()[statusIndex]
            )) {
                System.out.println("Status do projeto atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar status do projeto.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void menuGerenciarEquipes() {
        System.out.println("\n--- GERENCIAR EQUIPES ---");
        System.out.println("1 - Cadastrar Nova Equipe");
        System.out.println("2 - Listar Equipes");
        System.out.println("3 - Adicionar Membro à Equipe");
        System.out.println("4 - Remover Membro da Equipe");
        System.out.println("5 - Listar Equipes por Tamanho");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        switch (opcao) {
            case 1:
                cadastrarNovaEquipe();
                break;
            case 2:
                listarEquipes();
                break;
            case 3:
                adicionarMembroEquipe();
                break;
            case 4:
                removerMembroEquipe();
                break;
            case 5:
                listarEquipesPorTamanho();
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarNovaEquipe() {
        try {
            System.out.print("Nome da Equipe: ");
            String nome = scanner.nextLine();

            System.out.print("Descrição da Equipe: ");
            String descricao = scanner.nextLine();

            Equipe novaEquipe = new Equipe(nome, descricao);

            if (equipeService.cadastrarEquipe(novaEquipe)) {
                System.out.println("Equipe cadastrada com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar equipe: " + e.getMessage());
        }
    }

    private void listarEquipes() {
        List<Equipe> equipes = equipeService.listarTodasEquipes();
        equipes.forEach(System.out::println);
    }

    private void adicionarMembroEquipe() {
        try {
            System.out.print("Nome da Equipe: ");
            String nomeEquipe = scanner.nextLine();

            List<Usuario> usuarios = usuarioService.listarUsuarios();
            System.out.println("Selecione o Usuário:");
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println(i + " - " + usuarios.get(i).getNomeCompleto());
            }
            int usuarioIndex = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (equipeService.adicionarMembroNaEquipe(
                    nomeEquipe,
                    usuarios.get(usuarioIndex)
            )) {
                System.out.println("Membro adicionado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerMembroEquipe() {
        try {
            System.out.print("Nome da Equipe: ");
            String nomeEquipe = scanner.nextLine();

            List<Usuario> usuarios = usuarioService.listarUsuarios();
            System.out.println("Selecione o Usuário:");
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println(i + " - " + usuarios.get(i).getNomeCompleto());
            }
            int usuarioIndex = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (equipeService.removerMembroDaEquipe(
                    nomeEquipe,
                    usuarios.get(usuarioIndex)
            )) {
                System.out.println("Membro removido com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarEquipesPorTamanho() {
        List<Equipe> equipes = equipeService.listarEquipesPorTamanho();
        equipes.forEach(equipe ->
                System.out.println(
                        "Equipe: " + equipe.getNome() +
                                " - Membros: " + equipe.quantidadeMembros()
                )
        );
    }


    public static void main(String[] args) {
        new SistemaGestaoView().iniciar();
    }
}
