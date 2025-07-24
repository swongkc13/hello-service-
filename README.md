# Hello-Service

A minimal Spring Boot microservice designed for daily learning and portfolio development.
This project showcases incremental full-stack backend progress — built with **Java 17**, **Spring Boot 3**, and tested using **JUnit & MockMvc**.
Includes Docker support and a transition from **in-memory H2** to a **PostgreSQL container** using `docker-compose`.

---

## 📆 Progress Overview

| Day   | What Was Built                                 |
| ----- | ---------------------------------------------- |
| Day 1 | Spring Boot base project + Dockerfile setup    |
| Day 2 | `/echo` endpoint + unit tests                  |
| Day 3 | User CRUD API using Spring Data JPA + H2 DB    |
| Day 4 | PostgreSQL integration via Docker Compose      |
| Day 5 | Verified PostgreSQL persistence and deployment |

---

## 🚀 API Endpoints

### ✅ Day 1 – `GET /hello`

* Basic Spring Boot health check route.
* **Returns**: `"Hello from Spring Boot!"`

---

### 🔁 Day 2 – `POST /echo`

* Accepts plain text and echoes it back.
* **URL**: `/echo`
* **Method**: `POST`
* **Body**: `your message`
* **Response**: `"Echo: your message"`

---

### 👥 Day 3 – User CRUD API

#### `POST /users`

* Create a new user.
* **Body**:

  ```json
  {
    "name": "Simon",
    "email": "simon@example.com"
  }
  ```

#### `GET /users`

* List all users.

#### `GET /users/{id}`

* Retrieve user by ID.

---

## 🐳 Day 4 – PostgreSQL Integration via Docker

* Switched from H2 to PostgreSQL.
* Added `docker-compose.yml` for containerized DB.
* Updated Spring Boot configuration in `application.properties`:

  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/hello_service
  spring.datasource.username=postgres
  spring.datasource.password=postgres
  spring.jpa.hibernate.ddl-auto=update
  ```

### ✅ How to start PostgreSQL locally

```bash
docker-compose down -v && docker-compose up --build
```

> *Make sure port `5432` is available or stop other DB services.*

---

## 🧪 Day 5 – Test Database Persistence

* Confirmed Docker container DB works.
* Used `curl` to test data storage:

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Simon", "email": "simon@example.com"}'

curl http://localhost:8080/users
```

**Expected Output**:

```json
[{"id":1,"name":"Simon","email":"simon@example.com"}]
```

---

## 🤪 Running Unit Tests

Run unit tests with:

```bash
mvn clean test
```

Tests use `@WebMvcTest` and `MockMvc`.

---

## ✅ Tech Stack

* Java 17
* Spring Boot 3
* Maven
* PostgreSQL (via Docker)
* Docker Compose
* JUnit 5 + MockMvc
