# Sobre o projeto

Commerce API é uma aplicação backend de e-commerce desenvolvida com **Spring Boot**, com foco em API REST, mapeamento objeto-relacional com JPA/Hibernate e **segurança com OAuth2 + JWT**.

A proposta é simular um pequeno sistema de vendas, onde é possível consultar o catálogo de produtos e categorias, realizar pedidos e controlar o acesso aos recursos conforme o perfil do usuário.

Principais funcionalidades:

- Listagem paginada de produtos, com busca por nome, e listagem de categorias (acesso público)
- CRUD de produtos, restrito a usuários com perfil **ADMIN**
- Criação de pedidos por usuários autenticados com perfil **CLIENT**
- Consulta de pedidos, permitida ao dono do pedido ou a um **ADMIN**
- Consulta dos dados do usuário logado (`/users/me`)
- Autenticação via OAuth2 (Authorization Server próprio) com grant customizado de *password* e tokens JWT
- Tratamento padronizado de erros e validação de dados com Bean Validation
- Configuração de CORS via variável de ambiente

## Modelo conceitual
![Modelo Conceitual](https://github.com/Vitor247/assets/blob/main/commerce-api/commerce-model.png)

Relações exploradas:

- Um para muitos (1:N): usuário → pedidos
- Muitos para muitos (N:N): produtos ↔ categorias e usuários ↔ perfis (roles)
- Um para um (1:1): pedido ↔ pagamento
- Associação com atributos extras (`OrderItem`, com chave composta): pedido ↔ produto, com quantidade e preço

## Endpoints

| Método | Rota | Acesso |
|--------|------|--------|
| POST | `/oauth2/token` | Público (login) |
| GET | `/categories` | Público |
| GET | `/products` (paginado, param. `name`) | Público |
| GET | `/products/{id}` | Público |
| POST | `/products` | ADMIN |
| PUT | `/products/{id}` | ADMIN |
| DELETE | `/products/{id}` | ADMIN |
| POST | `/orders` | CLIENT |
| GET | `/orders/{id}` | ADMIN ou dono do pedido |
| GET | `/users/me` | ADMIN ou CLIENT |

## Tecnologias utilizadas
- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA / Hibernate
- Spring Security, OAuth2 Authorization Server e Resource Server (JWT)
- Bean Validation
- Maven
- H2 Database (perfil de teste)
- PostgreSQL (perfis de desenvolvimento e produção)
- Docker / Docker Compose
- Postman

## Perfis de execução

| Perfil | Banco de dados | Observações |
|--------|----------------|-------------|
| `test` (padrão) | H2 em memória | Console em `/h2-console`, dados iniciais carregados via `import.sql` |
| `dev` | PostgreSQL local (porta 5433) | Suba o banco com o `docker-compose.yml` |
| `prod` | PostgreSQL configurado por variáveis de ambiente | `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` |

Variáveis de ambiente opcionais: `APP_PROFILE`, `CLIENT_ID`, `CLIENT_SECRET`, `JWT_DURATION` e `CORS_ORIGINS`.

## Como executar o projeto

Pré-requisitos: Java 21

```bash
# clonar repositório
git clone https://github.com/Vitor247/commerce-api

# entrar na pasta do projeto
cd commerce-api

# executar o projeto (perfil test, com H2 em memória)
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

Para rodar com PostgreSQL (perfil `dev`):

```bash
# subir o PostgreSQL e o pgAdmin
docker compose up -d

# executar com o perfil dev
APP_PROFILE=dev ./mvnw spring-boot:run
```

## Testando a API

Importe no [Postman](https://www.postman.com/) a coleção `Commerce.postman_collection.json` e o ambiente `Commerce OAuth2 JWT password.postman_environment.json`, ambos na raiz do projeto. Faça login em `/oauth2/token` para obter o token e utilize-o nas demais requisições.

O projeto também inclui um `index.html` simples, que consome o endpoint `/products` para demonstrar um CRUD de produtos no front-end.


# Autor

Vitor Camilo Inácio

[linkedin](https://www.linkedin.com/in/vitorcamilo-dev)
