# Peneiras App — Backend

Backend da aplicação **Peneiras**, uma plataforma desenvolvida para aproximar **atletas e clubes de futebol**, facilitando a descoberta e participação em peneiras esportivas.

A plataforma permite que **clubes publiquem peneiras** e que **atletas encontrem oportunidades próximas à sua localização**, centralizando informações sobre as avaliações e facilitando a conexão entre jogadores e clubes.

O backend é responsável por disponibilizar a API, implementar as regras de negócio, realizar a persistência dos dados e controlar a segurança da aplicação.

---

## Objetivo

O **Peneiras** tem como objetivo facilitar o acesso de atletas a oportunidades no futebol.

### Para atletas

* Encontrar peneiras publicadas por clubes.
* Consultar informações das peneiras.
* Encontrar oportunidades de acordo com sua localização.
* Realizar sua inscrição nas peneiras.

### Para clubes

* Publicar peneiras.
* Gerenciar as peneiras cadastradas.
* Disponibilizar informações para os atletas interessados.

---

## Arquitetura

O projeto foi desenvolvido utilizando uma arquitetura organizada em camadas, separando responsabilidades entre os diferentes componentes da aplicação.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

Além dessas camadas, o projeto possui estruturas específicas para:

* DTOs
* Entidades
* Segurança
* Tratamento de exceções
* Integrações externas
* Configurações da aplicação

---

## Tecnologias

### Backend

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **Spring Security**
* **PostgreSQL**
* **Maven**

### Frontend

* **Flutter**

O aplicativo mobile é desenvolvido separadamente em Flutter e consome a API REST disponibilizada por este backend.

---

## Estrutura do projeto

```text
src/main/java/peneiras_app/
│
├── config/          # Configurações da aplicação
├── controller/      # Endpoints da API
├── dto/             # Data Transfer Objects
├── entity/          # Entidades do banco de dados
├── exception/       # Tratamento de exceções
├── integration/     # Integrações com serviços externos
├── repository/      # Acesso aos dados
├── security/        # Configurações de segurança
├── service/         # Regras de negócio
│
└── PeneirasAppDeveloptmentApplication.java
```

---

## API

A aplicação disponibiliza uma API REST responsável pela comunicação entre o aplicativo Flutter e o backend.

O fluxo principal da aplicação segue o modelo:

```text
Flutter
   │
   │ HTTP / REST
   ▼
Spring Boot API
   │
   ├── Controllers
   ├── Services
   ├── Repositories
   │
   ▼
PostgreSQL
```

---

## Integração com ViaCEP

O backend utiliza a API do **ViaCEP** para consulta e preenchimento de informações relacionadas a endereços através do CEP.

A integração permite obter dados como:

* CEP
* Logradouro
* Complemento
* Bairro
* Cidade
* Estado

---

## Banco de dados

O projeto utiliza **PostgreSQL** como banco de dados relacional.

A persistência das entidades é realizada utilizando **Spring Data JPA**.

```text
Spring Boot
     │
     ▼
Spring Data JPA
     │
     ▼
PostgreSQL
```

---

## Segurança

A aplicação possui uma camada dedicada à segurança através do **Spring Security**, responsável pelo controle de autenticação e autorização da API.

A estrutura de segurança está localizada em:

```text
src/main/java/peneiras_app/security/
```

---

## Como executar o projeto

### Pré-requisitos

Antes de executar o projeto, certifique-se de possuir:

* Java 17+
* Maven
* PostgreSQL
* Git

### 1. Clone o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

### 2. Entre na pasta do projeto

```bash
cd Peneiras-App-Backend
```

### 3. Configure o banco de dados

Crie um banco PostgreSQL para a aplicação e configure as credenciais no arquivo de configuração do Spring Boot.

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/peneiras
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Execute a aplicação

Com Maven:

```bash
./mvnw spring-boot:run
```

Ou:

```bash
mvn spring-boot:run
```

A API estará disponível localmente na porta configurada pela aplicação.

---

## Frontend

O frontend da aplicação **Peneiras** foi desenvolvido utilizando **Flutter**.

O aplicativo se comunica com este backend através da API REST.

```text
┌─────────────────┐
│     Flutter     │
│    Mobile App   │
└────────┬────────┘
         │
         │ REST API
         ▼
┌─────────────────┐
│   Spring Boot   │
│     Backend     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   PostgreSQL    │
└─────────────────┘
```

---

### Tecnologias principais

```text
Java 17
Spring Boot
PostgreSQL
Flutter
```
