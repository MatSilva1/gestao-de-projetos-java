# Sistema de Gestão de Projetos em Java

Um sistema completo de gestão de projetos desenvolvido em Java, projetado para facilitar o gerenciamento de equipes, projetos e usuários em organizações de pequeno e médio porte.

## 📋 Índice

- [Visão Geral](#visão-geral)
- [Características Principais](#características-principais)
- [Requisitos do Sistema](#requisitos-do-sistema)
- [Instalação e Configuração](#instalação-e-configuração)
- [Como Usar](#como-usar)
- [Arquitetura do Sistema](#arquitetura-do-sistema)
- [Funcionalidades Detalhadas](#funcionalidades-detalhadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Exemplos de Uso](#exemplos-de-uso)

## 🎯 Visão Geral

O Sistema de Gestão de Projetos é uma aplicação Java robusta que oferece uma solução completa para o gerenciamento de projetos, equipes e usuários. Desenvolvido com foco na simplicidade e eficiência, o sistema permite que organizações controlem o ciclo de vida completo de seus projetos, desde o planejamento inicial até a conclusão.

### Principais Benefícios

- **Centralização**: Todas as informações de projetos, equipes e usuários em um local único
- **Visibilidade**: Acompanhamento em tempo real do status dos projetos
- **Colaboração**: Ferramentas para facilitar o trabalho em equipe
- **Controle**: Sistema de perfis para diferentes níveis de acesso
- **Simplicidade**: Interface intuitiva e fácil de usar

## ✨ Características Principais

### Gestão de Usuários
- Cadastro e autenticação de usuários
- Sistema de perfis (Gerente e Usuário)
- Controle de acesso baseado em permissões

### Gestão de Projetos
- Criação e acompanhamento de projetos
- Definição de datas de início e término
- Monitoramento de status (Em Andamento, Concluído, Atrasado)
- Identificação automática de projetos atrasados
- Atribuição de gerentes responsáveis

### Gestão de Equipes
- Formação e gerenciamento de equipes
- Adição e remoção de membros
- Organização por tamanho e capacidade
- Visualização da estrutura organizacional

## 🔧 Requisitos do Sistema

### Requisitos Mínimos
- **Java**: JDK 8 ou superior
- **Sistema Operacional**: Windows, macOS, Linux
- **Memória RAM**: 512 MB mínimo (1 GB recomendado)
- **Espaço em Disco**: 50 MB para a aplicação

### Dependências
- Java Runtime Environment (JRE) ou Java Development Kit (JDK)
- Nenhuma dependência externa adicional necessária

## 🚀 Instalação e Configuração

### Passo 1: Verificar Instalação do Java
```bash
java -version
```

Se o Java não estiver instalado, baixe e instale a partir do [site oficial da Oracle](https://www.oracle.com/java/technologies/downloads/).

### Passo 2: Clonar o Repositório
```bash
git clone https://github.com/MatSilva1/gestao-de-projetos-java.git
cd gestao-de-projetos-java
```

### Passo 3: Compilar o Projeto
```bash
javac -d bin src/**/*.java
```

### Passo 4: Executar a Aplicação
```bash
java -cp bin view.SistemaGestaoView
```

Alternativamente, você pode executar diretamente o arquivo principal:
```bash
cd src
java view.SistemaGestaoView
```

## 📖 Como Usar

### Primeiro Acesso

1. **Iniciar a Aplicação**: Execute o comando de inicialização
2. **Cadastrar Primeiro Usuário**: Selecione a opção "Cadastrar Novo Usuário"
3. **Fazer Login**: Use as credenciais criadas para acessar o sistema
4. **Explorar Funcionalidades**: Navegue pelos menus para conhecer as opções disponíveis

### Fluxo Básico de Uso

1. **Cadastro de Usuários**: Registre todos os membros da equipe
2. **Criação de Projetos**: Defina projetos com datas e responsáveis
3. **Formação de Equipes**: Organize os usuários em equipes de trabalho
4. **Monitoramento**: Acompanhe o progresso e status dos projetos

## 🏗️ Arquitetura do Sistema

O sistema segue uma arquitetura em camadas baseada no padrão MVC (Model-View-Controller), organizada da seguinte forma:

### Camada Model (Modelo)
Responsável pela representação dos dados e regras de negócio:
- `Usuario.java`: Representa os usuários do sistema
- `Projeto.java`: Modela os projetos e suas propriedades
- `Equipe.java`: Define a estrutura das equipes
- `Perfil.java`: Enumera os tipos de perfil de usuário

### Camada View (Visão)
Interface com o usuário:
- `SistemaGestaoView.java`: Interface principal do sistema via console

### Camada Service (Serviço)
Lógica de negócio e operações:
- `UsuarioService.java`: Operações relacionadas a usuários
- `ProjetoService.java`: Gerenciamento de projetos
- `EquipeService.java`: Administração de equipes

### Camada Repository (Repositório)
Persistência e acesso aos dados:
- `UsuarioRepository.java`: Armazenamento de dados de usuários
- `ProjetoRepository.java`: Persistência de informações de projetos
- `EquipeRepository.java`: Gerenciamento de dados de equipes

### Diagrama de Arquitetura

```
┌─────────────────┐
│      View       │
│ SistemaGestao   │
│     View        │
└─────────┬───────┘
          │
┌─────────▼───────┐
│    Services     │
│ Usuario│Projeto │
│   │Equipe       │
└─────────┬───────┘
          │
┌─────────▼───────┐
│  Repositories   │
│ Usuario│Projeto │
│   │Equipe       │
└─────────┬───────┘
          │
┌─────────▼───────┐
│     Models      │
│ Usuario│Projeto │
│ Equipe│Perfil   │
└─────────────────┘
```

## 🔍 Funcionalidades Detalhadas

### Sistema de Autenticação

O sistema implementa um mecanismo de autenticação baseado em login e senha, com diferentes níveis de acesso conforme o perfil do usuário.

**Perfis Disponíveis:**
- **GERENTE**: Acesso completo a todas as funcionalidades
- **USUARIO**: Acesso limitado a funcionalidades básicas

### Gerenciamento de Projetos

#### Criação de Projetos
Os projetos podem ser criados com as seguintes informações:
- Nome do projeto
- Descrição detalhada
- Data de início
- Data de término prevista
- Gerente responsável

#### Status de Projetos
O sistema monitora automaticamente o status dos projetos:
- **EM_ANDAMENTO**: Projetos ativos dentro do prazo
- **CONCLUIDO**: Projetos finalizados
- **ATRASADO**: Projetos que ultrapassaram a data de término

#### Monitoramento de Prazos
O sistema identifica automaticamente projetos atrasados comparando a data atual com a data de término prevista.

### Gestão de Equipes

#### Formação de Equipes
- Criação de equipes com nome e descrição
- Adição de membros existentes no sistema
- Remoção de membros quando necessário

#### Organização
- Listagem de equipes por tamanho
- Visualização da quantidade de membros
- Estrutura hierárquica clara

## 📁 Estrutura do Projeto

```
gestao-de-projetos-java/
├── src/
│   ├── model/
│   │   ├── Equipe.java
│   │   ├── Perfil.java
│   │   ├── Projeto.java
│   │   └── Usuario.java
│   ├── repository/
│   │   ├── EquipeRepository.java
│   │   ├── ProjetoRepository.java
│   │   └── UsuarioRepository.java
│   ├── service/
│   │   ├── EquipeService.java
│   │   ├── ProjetoService.java
│   │   └── UsuarioService.java
│   └── view/
│       └── SistemaGestaoView.java
├── .gitignore
├── GestaoDeProjetos.iml
└── README.md
```

### Descrição dos Diretórios

- **src/model/**: Classes que representam as entidades do domínio
- **src/repository/**: Classes responsáveis pelo armazenamento e recuperação de dados
- **src/service/**: Classes que implementam a lógica de negócio
- **src/view/**: Interface do usuário e controle de fluxo da aplicação

## 💡 Exemplos de Uso

### Exemplo 1: Cadastro de Usuário
```java
// Criação de um novo usuário
Usuario novoUsuario = new Usuario(
    "João Silva",
    "123.456.789-00",
    "joao@empresa.com",
    "Desenvolvedor",
    "joao.silva",
    "senha123",
    Perfil.USUARIO
);

// Cadastro através do serviço
UsuarioService usuarioService = new UsuarioService();
boolean sucesso = usuarioService.cadastrarUsuario(novoUsuario);
```

### Exemplo 2: Criação de Projeto
```java
// Criação de um novo projeto
Projeto novoProjeto = new Projeto(
    "Sistema de Vendas",
    "Desenvolvimento de sistema para controle de vendas",
    LocalDate.of(2024, 1, 15),
    LocalDate.of(2024, 6, 30),
    gerente
);

// Cadastro através do serviço
ProjetoService projetoService = new ProjetoService();
boolean sucesso = projetoService.cadastrarProjeto(novoProjeto);
```

### Exemplo 3: Formação de Equipe
```java
// Criação de uma nova equipe
Equipe novaEquipe = new Equipe(
    "Equipe de Desenvolvimento",
    "Responsável pelo desenvolvimento de software"
);

// Adição de membros
EquipeService equipeService = new EquipeService();
equipeService.cadastrarEquipe(novaEquipe);
equipeService.adicionarMembroNaEquipe("Equipe de Desenvolvimento", usuario1);
equipeService.adicionarMembroNaEquipe("Equipe de Desenvolvimento", usuario2);
```

### Tecnologias Utilizadas
- **Java**: Linguagem de programação principal
- **Git**: Controle de versão
- **GitHub**: Hospedagem do código e colaboração

---

**Última atualização**: Setembro 2024  
**Versão do README**: 1.0  
**Versão do Software**: 1.0.0


