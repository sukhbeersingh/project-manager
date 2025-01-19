# Project Manager Backend

## Overview
The Project Manager Backend is a Java-based application designed to manage and track various projects. It provides RESTful APIs to handle project-related operations.

## Architecture

### Layers
1. **Controller Layer**: Handles HTTP requests and responses.
2. **Service Layer**: Contains business logic.
3. **Repository Layer**: Manages data persistence and retrieval.

### Technologies
- **Spring Boot**: Framework for building the application.
- **Hibernate**: ORM tool for database interactions.
- **H2 Database**: In-memory database for development and testing.

## Endpoints

### Project Endpoints
- `GET /projects`: Retrieve all projects.
- `POST /projects`: Create a new project.
- `GET /projects/{id}`: Retrieve a project by ID.
- `PUT /projects/{id}`: Update a project by ID.
- `DELETE /projects/{id}`: Delete a project by ID.

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven

### Running the Application
1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn spring-boot:run`.

## Contributing
1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Submit a pull request.

## License
This project is licensed under the MIT License.