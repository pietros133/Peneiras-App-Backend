# Peneiras App — Backend

Backend of **Peneiras**, a platform designed to connect **football players and clubs**, making it easier for athletes to discover and participate in football tryouts.

The platform allows **clubs to publish tryouts** and **athletes to find opportunities near their location**, centralizing tryout information and facilitating connections between players and clubs.

The backend is responsible for providing the API, implementing business rules, persisting data, and handling application security.

---

## Objective

The main goal of **Peneiras** is to make it easier for athletes to access football opportunities.

### For athletes

* Find tryouts published by football clubs.
* View tryout information.
* Find opportunities based on their location.
* Register for tryouts.

### For clubs

* Publish tryouts.
* Manage published tryouts.
* Provide information to interested athletes.

---

## Architecture

The project follows a layered architecture, separating responsibilities between the different components of the application.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

In addition to these layers, the project contains dedicated structures for:

* DTOs
* Entities
* Security
* Exception handling
* External integrations
* Application configuration

---

## Technologies

### Backend

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **Spring Security**
* **PostgreSQL**
* **Maven**

### Frontend

* **Flutter**

The mobile application is developed separately using Flutter and consumes the REST API provided by this backend.

---

## Project Structure

```text
src/main/java/peneiras_app/
│
├── config/          # Application configuration
├── controller/      # API endpoints
├── dto/             # Data Transfer Objects
├── entity/          # Database entities
├── exception/       # Exception handling
├── integration/     # External service integrations
├── repository/      # Data access
├── security/        # Security configuration
├── service/         # Business logic
│
└── PeneirasAppDeveloptmentApplication.java
```

---

## API

The application provides a REST API responsible for communication between the Flutter mobile application and the backend.

The main application flow follows this structure:

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

## ViaCEP Integration

The backend integrates with **ViaCEP** to retrieve and populate address information using a Brazilian ZIP code (CEP).

The integration provides information such as:

* ZIP code (CEP)
* Street
* Complement
* Neighborhood
* City
* State

---

## Database

The project uses **PostgreSQL** as its relational database.

Entity persistence is handled using **Spring Data JPA**.

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

## Security

The application has a dedicated security layer using **Spring Security**, responsible for authentication and authorization.

The security structure is located at:

```text
src/main/java/peneiras_app/security/
```

---

## How to Run

### Prerequisites

Before running the project, make sure you have:

* Java 17+
* Maven
* PostgreSQL
* Git

### 1. Clone the repository

```bash
git clone <REPOSITORY_URL>
```

### 2. Navigate to the project directory

```bash
cd Peneiras-App-Backend
```

### 3. Configure the database

Create a PostgreSQL database for the application and configure the database credentials in the Spring Boot configuration file.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/peneiras
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Run the application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or:

```bash
mvn spring-boot:run
```

The API will be available locally on the port configured in the application.

---

## Frontend

The **Peneiras** frontend was developed using **Flutter**.

The mobile application communicates with this backend through the REST API.

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

## Main Technologies

```text
Java 17
Spring Boot
PostgreSQL
Flutter
```
