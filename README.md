# Hello-Service

A minimal Spring Boot microservice designed for daily learning and portfolio development.  
This project showcases incremental full-stack backend progress — built with Java 17, Spring Boot 3, and tested using JUnit & MockMvc.  
Docker and H2 database integration included.

---

## 📆 Progress Overview

| Day   | What Was Built                              |
| ----- | ------------------------------------------- |
| Day 1 | Spring Boot base project + Dockerfile       |
| Day 2 | `/echo` endpoint + JUnit tests              |
| Day 3 | User CRUD API using Spring Data JPA + H2 DB |

---

## 🚀 API Endpoints

### ✅ Day 1 – `GET /hello`

- Simple Spring Boot controller.
- **Returns**: `"Hello from Spring Boot!"`

---

### 🔁 Day 2 – `POST /echo`

- Accepts plain text and echoes it back.
- **URL**: `/echo`
- **Method**: `POST`
- **Body**: `your text`
- **Response**: `"Echo: your text"`

---

### 👥 Day 3 – User CRUD API

#### `POST /users`

- **Creates** a user.
- **Body**:
  ```json
  {
    "name": "Simon",
    "email": "simon@example.com"
  }
  ```
