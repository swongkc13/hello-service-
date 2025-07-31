# Hello-Service

A minimal Spring Boot microservice designed for daily learning and portfolio development. This project showcases incremental full-stack backend progress — built with Java 17, Spring Boot 3, and tested using JUnit & MockMvc. Includes Docker support and a transition from in-memory H2 to a PostgreSQL container using Docker Compose.

## 📆 Progress Overview

| Day | What Was Built |
|-----|----------------|
| Day 1 | Spring Boot base project + Dockerfile setup |
| Day 2 | /echo endpoint + unit tests |
| Day 3 | User CRUD API using Spring Data JPA + H2 DB |
| Day 4 | PostgreSQL integration via Docker Compose |
| Day 5 | Verified PostgreSQL persistence and deployment |
| Day 6 | Input validation using @NotBlank, error handler, JSON UX |
| Day 7 | GET /users/{id} with Optional + 404 handling |
| Day 8 | DELETE /users/{id} with safe delete + not found response |

## 🚀 API Endpoints

### ✅ Day 1 – GET /hello
- **Description**: Basic Spring Boot health check route.
- **Response**: `Hello from Spring Boot!`

### 🔁 Day 2 – POST /echo
- **Description**: Accepts plain text and echoes it back.
- **URL**: /echo
- **Method**: POST
- **Body**: `your message`
- **Response**: `Echo: your message`

### 👥 Day 3 – User CRUD API

#### POST /users
- **Description**: Create a new user.
- **Body**:
  ```json
  {
    "name": "Simon",
    "email": "simon@example.com"
  }
  ```

#### GET /users
- **Description**: List all users.

#### GET /users/{id}
- **Description**: Retrieve user by ID.
- **Response**: Returns 404 Not Found if user does not exist.

## 🐳 Day 4 – PostgreSQL Integration via Docker
- **Description**: Switched from H2 to PostgreSQL. Added docker-compose.yml for containerized DB.
- **Spring Boot Configuration**:
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/hello_service
  spring.datasource.username=postgres
  spring.datasource.password=postgres
  spring.jpa.hibernate.ddl-auto=update
  ```
- **Start PostgreSQL locally**:
  ```
  docker-compose down -v && docker-compose up --build
  ```
  Ensure port 5432 is available or stop other DB services first.

## 🧪 Day 5 – Test Database Persistence

Tested user creation and retrieval using curl:
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Simon", "email": "simon@example.com"}'

curl http://localhost:8080/users
```
**Expected Output**:
```json
[
  {
    "id": 1,
    "name": "Simon",
    "email": "simon@example.com"
  }
]
```

## ✅ Day 6 – Validation and Clean JSON Output
- **Validation Annotations**:
  ```java
  @NotBlank(message = "Name is required")
  @Email(message = "Email is required")
  ```
- **Error Handling with JSON**:
  ```json
  {
    "errors": [
      "Name is required",
      "Email is required"
    ],
    "status": 400
  }
  ```
- **JSON Formatting**:
  ```
  spring.jackson.serialization.indent-output=true
  ```

## 🔍 Day 7 – GET /users/{id} with Optional + 404 Handling
- **Description**: Introduced Optional<User> in UserService.
- **Handling**: Used `.map(ResponseEntity::ok).orElseGet(...)` for cleaner response handling.
- **404 Response**:
  ```bash
  curl -i http://localhost:8080/users/9999
  ```
  **Response**:
  ```
  HTTP/1.1 404 Not Found
  ```

## 🗑️ Day 8 – DELETE /users/{id} with 404 Handling
- **Description**: Added support to safely delete users.
- **Scenarios**:
  - **✅ Successful Deletion**:
    ```bash
    curl -X DELETE http://localhost:8080/users/1
    ```
    **Response**:
    ```json
    {
      "message": "User deleted successfully"
    }
    ```
  - **❌ Non-existent User**:
    ```bash
    curl -X DELETE http://localhost:8080/users/99999
    ```
    **Response**:
    ```json
    {
      "error": "User not found"
    }
    ```

## 🔐 Day 9 – Externalized Environment Configuration with .env

- **Goal**: Avoid hardcoding secrets (like DB passwords) in `application.properties`.
- **What was done**:
  - Introduced `.env` file to hold database credentials securely.
  - Used `spring.datasource.url` and `spring.datasource.password` with `${}` syntax.
  - Updated `docker-compose.yml` to match `.env` credentials.
  - Verified `.env` variables load using Spring Boot’s `application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:${POSTGRES_PORT}/hello_service
    spring.datasource.username=${POSTGRES_USER}
    spring.datasource.password=${POSTGRES_PASSWORD}
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jackson.serialization.indent-output=true
    ```

- **Security Note**:
  - `.env` file is excluded from Git (`.gitignore`).
  - Shared `.env.example` for safe public reference.

- ✅ **Tested**:
  - Created new user with:
    ```bash
    curl -X POST http://localhost:8080/users \
      -H "Content-Type: application/json" \
      -d '{"name": "EnvTest", "email": "env@test.com"}'
    ```
  - Fetched users:
    ```bash
    curl http://localhost:8080/users
    ```

- **Expected Output**:
  ```json
  [
    {
      "id": 1,
      "name": "EnvTest",
      "email": "env@test.com"
    }
  ]


## ❤️‍🩹 Day 10 – Health Check Endpoint
	•	✅ Added a lightweight /health endpoint.
	•	🔁 Returns HTTP 200 OK with a simple JSON payload:

curl http://localhost:8080/health

Response:

{
  "status": "UP"
}

### 📦 Useful for:
	•	Docker container health checks
	•	Kubernetes liveness/readiness probes
	•	CI/CD pipeline checks
	•	Basic monitoring without hitting the database

⸻

---

📊 Day 11 – Add Spring Boot Actuator (Metrics Ready)

- Added `spring-boot-starter-actuator` to `pom.xml`.
- Exposed all management endpoints in `application.properties`:
  ```properties
  management.endpoints.web.exposure.include=*
  ```

- Restarted the app and verified operational metrics:

Example:

```bash
curl http://localhost:8080/actuator
```

```json
{
  "_links": {
    "health": { "href": "http://localhost:8080/actuator/health" },
    "metrics": { "href": "http://localhost:8080/actuator/metrics" },
    "loggers": { "href": "http://localhost:8080/actuator/loggers" }
  }
}
```
⸻

### :floppy_disk: Day 12 – Persistent PostgreSQL Data with Docker Volumes
	*	Configured a named Docker volume to persist PostgreSQL data across container restarts.
	*	Used the following in docker-compose.yml:

volumes:
  - postgres-data:/var/lib/postgresql/data


	*	Tested persistence:
		1.	Created a user: POST /users
		2.	Restarted the container: docker-compose down && docker-compose up --build
		3.	Verified user data still exists: GET /users

:heavy_check_mark: This confirms data is stored outside the container in a durable Docker-managed volume.

## 🚀 Day 13 – Deploy to Render

- ✅ Successfully deployed the Spring Boot application to **Render** using:
  - Docker deployment option
  - PostgreSQL database provisioned through Render

- 🔐 Render Environment Variables:
  - `SPRING_PROFILES_ACTIVE=prod`
  - `SPRING_DATASOURCE_URL`
  - `SPRING_DATASOURCE_USERNAME`
  - `SPRING_DATASOURCE_PASSWORD`

- ✅ Externalized all secrets and sensitive values via `.env` and Render dashboard

- 🛠️ Application now builds via Dockerfile and runs using `java -jar app.jar`

- 📦 Dockerfile tested for both local and cloud deployments

- ✅ Verified full CRUD functionality via deployed Render URL:
  ```bash
  curl https://your-app-name.onrender.com/users

  ## 🧹 Day 14 – Weekly Reflection + Cleanup

- 🧾 Reviewed and updated all previous progress entries in `README.md`
- ✅ Pushed `.env.example` to the repo to help others set up local development securely
- 🧪 Verified profile-specific config works:
  - `application.properties` – for Render (production) deployment
  - `application-local.properties` – for local dev with Docker Compose PostgreSQL

- 🐳 Docker Compose PostgreSQL still persists data correctly using:
  ```yaml
  volumes:
    - postgres-data:/var/lib/postgresql/data
- 🔍 Tested database persistence by restarting the container and verifying user data remains


⸻

## 🧪 Running Unit Tests

Run tests with:
```bash
mvn clean test
```
- Uses `@WebMvcTest` and `MockMvc` for controller-layer testing.

## ✅ Tech Stack
- **Java 17**
- **Spring Boot 3**
- **Spring Web + Spring Data JPA**
- **Hibernate Validator**
- **PostgreSQL (via Docker)**
- **Docker Compose**
- **Jackson (for pretty JSON)**
- **Maven**
- **JUnit 5 + MockMvc**



