# 🍽️ Cozinha Inteligente

Sistema de gestão de restaurantes desenvolvido em Java (versão 21) com Spring Boot com gerenciamento de usuários.

## 🎯 Principais Características

- **API RESTful** completa e documentada
- **Autenticação JWT** com tokens seguros
- **Arquitetura em camadas** bem definida
- **Banco de dados relacional** com JPA/Hibernate
- **Documentação automática** com Swagger/OpenAPI
- **Validação de dados**
- **Tratamento de erros**

## 🚀 Tecnologias Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.5.0**
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM
- **JWT (JSON Web Tokens)** - Autenticação stateless
- **ModelMapper** - Mapeamento de objetos
- **Maven** - Gerenciamento de dependências

### Banco de Dados
- **MySQL**

### Documentação e Ferramentas
- **Swagger/OpenAPI 3** - Documentação da API
- **Lombok** - Redução de boilerplate
- **Bean Validation** - Validação de dados

## 🏗️ Arquitetura

O projeto segue uma **arquitetura em camadas**:

```
┌─────────────────────────────────────┐
│           Controllers               │  ← Camada de Apresentação
├─────────────────────────────────────┤
│            Services                 │  ← Camada de Negócio
├─────────────────────────────────────┤
│          Repositories               │  ← Camada de Acesso a Dados
├─────────────────────────────────────┤
│            Entities                 │  ← Camada de Persistência
└─────────────────────────────────────┘
```

### Componentes Principais

- **Controllers**: Endpoints REST e manipulação de requisições HTTP
- **Services**: Lógica de negócio e regras da aplicação
- **Repositories**: Acesso e manipulação de dados
- **Entities**: Modelos de dados/entidades JPA
- **DTOs**: Objetos de transferência de dados
- **Security**: Configurações de segurança e JWT
- **Config**: Configurações gerais da aplicação

## ⚡ Funcionalidades

### 👤 Gestão de Usuários
- Cadastro de novos usuários
- Autenticação com login/senha
- Alteração de dados dos usuários
- Alteração de senha do usuário

## 🛠️ Endpoints da API

### 🔓 Autenticação
```http
POST   /login              # Autenticação de usuário
```

### 👥 Usuários
```http
POST    /users                  # Cadastrar novo usuário
PUT     /users/{id}             # Atualizar usuário
DELETE  /users                  # Deletar usuário
PATCH   /users/{id}/password    # Alteração de senha
```

### 📋 Documentação
```http
GET    /swagger-ui.html    # Interface Swagger
GET    /v3/api-docs        # Documentação OpenAPI JSON
```

### Exemplos de Requests

#### Login
```json
POST /login
Content-Type: application/json

{
    "login": "usuario@email.com",
    "password": "senha123"
}
```

#### Cadastro de Usuário
```json
POST /users
Content-Type: application/json

{
    "name": "João Silva",
    "email": "joao@email.com",
    "login": "joao",
    "password": "senha123",
    "userType": "CUSTOMER",
    "address": {
        "street": "Rua das Flores",
        "number": "123",
        "complement": "Apto 45",
        "neighborhood": "Centro",
        "city": "São Paulo",
        "state": "SP",
        "zipCode": "01234-567",
        "country": "Brasil"
    }
}
```

## ⚙️ Configuração e Instalação

### Pré-requisitos

- **Java 21** instalado
- **Maven 3.6+** instalado
- **MySQL 8.0+** instalado e configurado
- **Git** para clonar o repositório

### 1. Clone o Repositório

```bash
git clone https://github.com/mjuli/cozinha-inteligente.git
cd cozinha-inteligente
```

## 🚀 Execução

### Docker Compose

```bash
# Build e execução
docker compose build
docker compose up -d
```

## 🔐 Autenticação

### Fluxo de Autenticação

1. **Login**: `POST /login` com credenciais
2. **Recebimento do Token**: JWT retornado na resposta
3. **Uso do Token**: Incluir em todas as requisições autenticadas

### Exemplo de Uso

```bash
# 1. Login
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"login":"usuario","password":"senha"}'

# Resposta:
# {"data":"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."}

# 2. Usar o token nas próximas requisições
curl -X GET http://localhost:8080/users \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

## 📚 Documentação da API

### Swagger UI
Acesse: `http://localhost:8080/swagger-ui.html`

### OpenAPI JSON
Acesse: `http://localhost:8080/v3/api-docs`

A documentação interativa permite:
- Visualizar todos os endpoints
- Testar requisições diretamente
- Ver exemplos de request/response
- Entender os modelos de dados

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── br/com/fiap/cozinha_inteligente/
│   │       ├── CozinhaInteligenteApplication.java
│   │       ├── controllers/           # Controllers REST
│   │       │   ├── AuthenticationController.java
│   │       │   └── UserController.java
│   │       ├── services/              # Lógica de negócio
│   │       │   ├── AuthenticationService.java
│   │       │   ├── TokenService.java
│   │       │   └── AuthenticationService.java
│   │       ├── repositories/          # Acesso a dados
│   │       │   └── UserRepository.java
│   │       ├── entities/              # Entidades JPA
│   │       │   ├── User.java
│   │       │   └── Address.java
│   │       ├── dtos/                  # Data Transfer Objects
│   │       │   ├── AuthenticationDataDTO.java
│   │       │   ├── TokenJWTDataDTO.java
│   │       │   ├── UpdatePasswordRequestDTO.java
│   │       │   ├── UserInfoDTO.java
│   │       │   └── UserRequestDTO.java
│   │       ├── security/                # Configurações
│   │       │   ├── SecurityConfigurations.java
│   │       │   └── SecurityFilter.java
│   │       ├── config/    
│   │       │   ├── ModelMapperConfig.java
│   │       │   └── SpringDocConfig.java
│   │       ├── exceptions/            # Tratamento de exceções
│   │       └── enums/                 # Enumeradores
│   └── resources/
│       ├── application.yml            # Configurações da aplicação
│       └── application-docker.yml     # Configurações do docker
└── test/                              # Testes unitários e integração
```

## 👨‍💻 Autor

**Maria Júlia** - [@mjuli](https://github.com/mjuli)
