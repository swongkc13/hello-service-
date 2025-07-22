# Hello-Service

A minimal Spring Boot microservice exposing REST endpoints and tested with JUnit & MockMvc.

## 🚀 Endpoints (Day 1 & Day 2)

### ✅ GET /hello
_Implemented on **Day 1**_
- **Returns**: `"Hello from Spring Boot!"`

### 🔁 POST /echo
_Implemented on **Day 2**_
- **URL**: `/echo`
- **Method**: `POST`
- **Content-Type**: `text/plain`
- **Body**: `your message`
- **Response**: `"Echo: your message"`

## 🧪 Tests (Day 2)
- Written using `@WebMvcTest` and `MockMvc`
- Validates both endpoints using unit tests
- Run with `mvn clean test`

## 🐳 Docker Instructions (Day 1)

### Build JAR
```bash
mvn clean package```