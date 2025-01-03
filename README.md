# spring boot course management system jpa hibernate

## Description
This project is a Spring Boot application designed to demonstrate database interaction using Spring Data JPA. It includes models, repositories, services, and controllers for managing entities like authors, courses, lectures, resources, and sections.

## Features
- **Database Integration:** Fully integrated with a relational database using Spring Data JPA.
- **RESTful APIs:** Provides APIs for managing different entities.
- **Exception Handling:** Global exception handling with custom error responses.
- **Embedded Components:** Uses embedded objects for complex data structures.
- **Configuration:** Configurable via `application.yml`.

## Project Structure
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com.example.jpa/
│   │       ├── config/         # Configuration classes
│   │       ├── Controller/     # REST API controllers
│   │       ├── embedded/       # Embedded object models
│   │       ├── exception/      # Exception handling
│   │       ├── models/         # Entity classes
│   │       ├── repositories/   # Repository interfaces
│   │       ├── Service/        # Business logic services
│   │       └── JpaApplication.java # Main application class
│   └── resources/
│       ├── application.yml     # Configuration file
│       ├── static/             # Static resources
│       └── templates/          # Templates (if applicable)
└── test/
    └── java/
        └── com.example.jpa/
            └── JpaApplicationTests.java
```

## Prerequisites
- **JDK:** 17
- **Maven:** Build and dependency management
- **Database:** PostgreSQL (default, configurable in `application.yml`)

## Getting Started

### Clone the Repository
```bash
git clone <repository-url>
cd <project-directory>
```

### Build the Project
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

### Test the APIs
Use tools like [Postman](https://www.postman.com/) to test the API endpoints.

## Configuration
Update `src/main/resources/application.yml` to configure:
- Database connection
- Server port
- Other application-specific settings

## API Endpoints
- **Authors:**
  - `GET /authors`
  - `POST /authors`
  - `PUT /authors/{id}`
  - `DELETE /authors/{id}`
- **Courses:** Similar endpoints as above
- **Lectures, Resources, Sections:** Similar structure

## Sample API Response
Here is a sample response for fetching all authors:
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "books": ["Java Fundamentals", "Spring Boot Essentials"]
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "books": ["Database Systems"]
  }
]
```

## Exception Handling
All exceptions are handled globally using `GlobalExceptionHandler`. Custom error messages are provided via `ApiError` class.

## Testing
Run the unit tests using:
```bash
mvn test
```

## Contribution
Contributions are welcome! Please fork the repository and create a pull request.
















