# idarati API

idarati API is a powerful tool designed to efficiently manage and streamline administrative processes, including document management, condition handling, and more. It offers a robust backend solution for modern administrative needs.

## Key Features
- **Procedure Management**: Easily define and manage various administrative procedures, including associated documents, conditions, costs, and timelines.
- **Document Handling**: Organize, track, and retrieve documents essential to each procedure.
- **Condition Management**: Specify and enforce conditions that must be met for different procedures.
- **Administration and Branch Management**: Oversee various administrative units and their corresponding branches (antennes).

## Technology Stack
- **Spring Boot 3.3.2**: Comprehensive framework for building Java applications.
  - **Data JPA**: Simplifies database operations using JPA.
  - **Data REST**: Automatically exposes Spring Data repositories as REST endpoints.
  - **Web Starter**: Supports web applications and RESTful services.
- **PostgreSQL**: Reliable and advanced open-source relational database.
- **Lombok**: Reduces boilerplate code through powerful annotations.
- **Liquibase**: Tracks and manages database changes and version control.
- **Springdoc OpenAPI 2.6.0**: Automatically generates interactive API documentation.
- **MapStruct 1.6.0.RC1**: Simplifies the mapping between different Java beans.

## Getting Started
To set up the project locally, clone the repository and navigate to the project directory:

```bash
git clone https://github.com/malakhbiyel/idarati.git
cd idarati
```

Build and run the application:

```bash
mvn clean install
mvn spring-boot:run
```

## Usage

### API Endpoints
Here are some of the key endpoints provided by the Idarati API:

- **GET /api/procedures**: Retrieve a list of all procedures.
- **POST /api/procedures**: Create a new procedure.
- **GET /api/documents/{id}**: Retrieve detailed information about a specific document.
- **POST /api/documents**: Create a new document entry.
- **GET /api/conditions/{id}**: Retrieve detailed information about a specific condition.
- **DELETE /api/dossiers/{id}**: Delete a dossier by its unique ID.

## Contribution Guidelines
Contributions are highly appreciated! To contribute:

1. Fork the repository.
2. Create a new branch with your feature or bug fix.
3. Submit a pull request with a detailed description of the changes.



