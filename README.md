Hello-Service

A minimal Spring Boot microservice designed for daily learning and portfolio development.
This project showcases incremental full-stack backend progress — built with Java 17, Spring Boot 3, and tested using JUnit & MockMvc.
Includes Docker support and a transition from in-memory H2 to a PostgreSQL container using docker-compose.

⸻

📆 Progress Overview

Day	What Was Built
Day 1	Spring Boot base project + Dockerfile setup
Day 2	/echo endpoint + unit tests
Day 3	User CRUD API using Spring Data JPA + H2 DB
Day 4	PostgreSQL integration via Docker Compose
Day 5	Verified PostgreSQL persistence and deployment
Day 6	Input validation using @NotBlank, error handler, JSON UX
Day 7	GET /users/{id} with Optional + 404 handling
Day 8	DELETE /users/{id} with safe delete + not found response


⸻

🚀 API Endpoints

✅ Day 1 – GET /hello
	•	Basic Spring Boot health check route.
	•	Returns:

Hello from Spring Boot!



⸻

🔁 Day 2 – POST /echo
	•	Accepts plain text and echoes it back.
	•	URL: /echo
	•	Method: POST
	•	Body:

your message


	•	Response:

Echo: your message



⸻

👥 Day 3 – User CRUD API

POST /users
	•	Create a new user.
	•	Body:

{
  "name": "Simon",
  "email": "simon@example.com"
}



GET /users
	•	List all users.

GET /users/{id}
	•	Retrieve user by ID.
	•	Returns 404 Not Found if user does not exist.

⸻

🐳 Day 4 – PostgreSQL Integration via Docker
	•	Switched from H2 to PostgreSQL.
	•	Added docker-compose.yml for containerized DB.
	•	Updated Spring Boot configuration in application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/hello_service
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update



✅ Start PostgreSQL locally:

docker-compose down -v && docker-compose up --build

Make sure port 5432 is available or stop other DB services first.

⸻

🧪 Day 5 – Test Database Persistence

Tested user creation and retrieval using curl:

curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Simon", "email": "simon@example.com"}'

curl http://localhost:8080/users

Expected Output:

[
  {
    "id": 1,
    "name": "Simon",
    "email": "simon@example.com"
  }
]


⸻

✅ Day 6 – Validation and Clean JSON Output
	•	Added validation annotations:

@NotBlank(message = "Name is required")
@Email(message = "Email is required")


	•	Used BindingResult to handle and return JSON errors:

{
  "errors": [
    "Name is required",
    "Email is required"
  ],
  "status": 400
}


	•	Enabled clean JSON formatting:

spring.jackson.serialization.indent-output=true



⸻

🔍 Day 7 – GET /users/{id} with Optional + 404 Handling
	•	Introduced Optional<User> in UserService.
	•	Used .map(ResponseEntity::ok).orElseGet(...) for cleaner response handling.
	•	Returned 404 Not Found when user is not found:

curl -i http://localhost:8080/users/9999

Response:

HTTP/1.1 404 Not Found


⸻

🗑️ Day 8 – DELETE /users/{id} with 404 Handling
	•	Added support to safely delete users.
	•	Handles 2 scenarios:
	•	✅ Successful deletion:

curl -X DELETE http://localhost:8080/users/1

Response:

{
  "message": "User deleted successfully"
}


	•	❌ Non-existent user:

curl -X DELETE http://localhost:8080/users/99999

Response:

{
  "error": "User not found"
}



⸻

🧪 Running Unit Tests

Run tests with:

mvn clean test

	•	Uses @WebMvcTest and MockMvc for controller-layer testing.

⸻

✅ Tech Stack
	•	Java 17
	•	Spring Boot 3
	•	Spring Web + Spring Data JPA
	•	Hibernate Validator
	•	PostgreSQL (via Docker)
	•	Docker Compose
	•	Jackson (for pretty JSON)
	•	Maven
	•	JUnit 5 + MockMvc

⸻
