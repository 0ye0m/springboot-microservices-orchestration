# Spring Boot Microservices Orchestration Demo

A practical microservices project built using Spring Boot to understand orchestration between multiple services.

This project demonstrates how an Order Service coordinates with Inventory Service and Payment Service to complete an order workflow.

---

# Architecture

```text
Client
   |
   v
Order Service (Orchestrator)
   |
   |----> Inventory Service
   |
   |----> Payment Service
```

---

# Microservices Used

| Service | Description | Port |
|---|---|---|
| Order Service | Main orchestrator controlling workflow | 8080 |
| Inventory Service | Checks product availability | 8081 |
| Payment Service | Handles payment processing | 8082 |

---

# Project Objective

The goal of this project was to learn:

- Microservices architecture
- Service-to-service communication
- Orchestration pattern
- REST API communication
- Spring Boot fundamentals
- DTO usage
- Inter-service workflow handling
- Mockito unit testing

---

# What I Implemented

## Inventory Service
- Checks whether a product is available
- Returns inventory status response

### Example API
```http
GET /inventory/{productId}
```

---

## Payment Service
- Simulates payment processing
- Returns payment success or failure status

### Example API
```http
POST /payment
```

Request:
```json
{
  "amount": 500
}
```

---

## Order Service (Orchestrator)
The main service responsible for:

1. Receiving order request
2. Calling Inventory Service
3. Verifying stock availability
4. Calling Payment Service
5. Returning final order status

This service demonstrates the orchestration pattern in microservices.

### Example API
```http
POST /orders
```

Request:
```json
{
  "productId": 1,
  "amount": 500
}
```

---

# Technologies Used

- Java
- Spring Boot
- REST APIs
- RestTemplate
- Maven
- Lombok
- Mockito
- JUnit 5

---

# Concepts Learned

Through this project, I learned:

## Microservices Communication
How services communicate using REST APIs.

## Orchestration Pattern
How one central service controls the complete business workflow.

## DTOs
How data is transferred between services using DTO classes.

## RestTemplate
How to call external APIs from Spring Boot applications.

## Unit Testing with Mockito
How to mock dependencies and test orchestration logic without running actual services.

## Mocking
How fake responses can be used to isolate business logic during testing.

---

# Mockito Test Cases Added

Unit tests were added for Order Service to test:

- Successful order placement
- Product out of stock scenario
- Payment failure scenario

Mocking was used to simulate responses from:
- Inventory Service
- Payment Service

---

# Project Flow

```text
1. Client places order
2. Order Service checks inventory
3. Inventory Service responds
4. Order Service processes payment
5. Payment Service responds
6. Final order response returned
```

---

# Sample Success Response

```text
ORDER SUCCESS
```

---

# Future Improvements

Possible future enhancements:

- OpenFeign Client
- Eureka Server
- API Gateway
- Kafka Integration
- Saga Pattern
- Docker
- Kubernetes
- Circuit Breaker (Resilience4j)

---

# Key Learning Outcome

This project helped me understand how real-world backend systems coordinate multiple services to complete a business transaction using orchestration in microservices architecture.

---

# Author

Om