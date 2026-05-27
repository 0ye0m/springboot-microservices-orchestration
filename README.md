# Spring Boot Microservices Orchestration System

A production-style microservices backend project built using Spring Boot to demonstrate orchestration between multiple services such as Order Service, Inventory Service, and Payment Service.

This project simulates a real-world e-commerce workflow where orders are processed through inventory verification, payment processing, and stock management.

---

# Microservices Architecture

```text
                    +-------------------+
                    |      CLIENT       |
                    +-------------------+
                              |
                              v
                +--------------------------+
                |      ORDER SERVICE       |
                |      (Orchestrator)      |
                +--------------------------+
                     |               |
                     |               |
                     v               v
         +----------------+   +----------------+
         | INVENTORY SRV  |   | PAYMENT SRV    |
         +----------------+   +----------------+
```

---

# Services Overview

| Service | Port | Responsibility |
|---|---|---|
| Order Service | 8080 | Orchestrates complete order workflow |
| Inventory Service | 8081 | Manages products and stock |
| Payment Service | 8082 | Handles payment processing |

---

# Project Objective

The goal of this project was to learn and implement:

- Microservices architecture
- Orchestration pattern
- Inter-service communication
- Layered backend architecture
- Spring Boot REST APIs
- Hibernate & JPA
- MySQL integration
- DTO pattern
- Validation
- Global exception handling
- Workflow management
- Mockito unit testing

---

# Technologies Used

## Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven

## Database
- MySQL

## Testing
- JUnit 5
- Mockito

## Utilities
- Lombok

---

# Project Structure

```text
springboot-microservices-orchestration-demo
│
├── order-service
│
├── inventory-service
│
├── payment-service
│
└── README.md
```

---

# Order Service

## Responsibilities

The Order Service acts as the orchestrator and controls the complete business workflow.

### Workflow
1. Receives order request
2. Saves order as PENDING
3. Calls Inventory Service
4. Verifies product availability
5. Calls Payment Service
6. Updates order status
7. Returns final response

---

## Features Implemented

- Layered Architecture
- Hibernate & JPA
- MySQL Integration
- DTOs
- Validation
- Global Exception Handling
- Order Status Tracking
- Workflow Orchestration
- Mockito Unit Testing

---

## Order Status Workflow

```text
PENDING -> SUCCESS
PENDING -> FAILED
```

---

## APIs

### Place Order

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

Response:

```json
{
  "orderId": 1,
  "message": "Order placed successfully",
  "status": "SUCCESS",
  "timestamp": "2026-05-27T10:20:00"
}
```

---

# Inventory Service

## Responsibilities

The Inventory Service manages products and stock availability.

---

## Features Implemented

- Product Management APIs
- Stock Management
- Reduce Stock Logic
- Layered Architecture
- Hibernate & JPA
- MySQL Integration
- Validation
- Exception Handling
- Transaction Management

---

## Inventory Workflow

```text
Product Added
      ↓
Inventory Checked
      ↓
Stock Reduced After Order
```

---

## APIs

### Add Product

```http
POST /inventory/products
```

Request:

```json
{
  "productId": 1,
  "productName": "Laptop",
  "quantity": 10
}
```

---

### Get All Products

```http
GET /inventory/products
```

---

### Get Product

```http
GET /inventory/products/{productId}
```

---

### Update Product

```http
PUT /inventory/products/{productId}
```

---

### Delete Product

```http
DELETE /inventory/products/{productId}
```

---

### Check Inventory

```http
GET /inventory/{productId}
```

---

### Reduce Stock

```http
PUT /inventory/reduce/{productId}
```

---

# Payment Service

## Responsibilities

The Payment Service handles payment transactions and payment history management.

---

## Features Implemented

- Payment Processing APIs
- Payment Transaction Storage
- Payment Status Tracking
- Layered Architecture
- Hibernate & JPA
- MySQL Integration
- DTO Pattern
- Validation
- Global Exception Handling

---

## Payment Workflow

```text
Payment Requested
       ↓
Payment Processed
       ↓
SUCCESS / FAILED
```

---

## Payment Status

```text
PENDING
SUCCESS
FAILED
```

---

## APIs

### Make Payment

```http
POST /payments
```

Request:

```json
{
  "orderId": 1,
  "amount": 500,
  "paymentMethod": "UPI"
}
```

---

### Get Payment

```http
GET /payments/{paymentId}
```

---

### Get All Payments

```http
GET /payments
```

---

### Delete Payment

```http
DELETE /payments/{paymentId}
```

---

# Layered Architecture Used

All services follow professional layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

---

# Important Concepts Implemented

## 1. Orchestration Pattern

The Order Service centrally controls communication between Inventory and Payment services.

---

## 2. DTO Pattern

DTOs are used for request and response transfer between layers and services.

---

## 3. Validation

Request validation implemented using:

- @Valid
- @Positive
- @NotBlank

---

## 4. Global Exception Handling

Centralized exception handling implemented using:

```java
@RestControllerAdvice
```

---

## 5. JPA & Hibernate

Used for ORM and database interaction.

---

## 6. Transaction Management

Implemented using:

```java
@Transactional
```

for safe stock reduction operations.

---

## 7. Mockito Unit Testing

Mockito was used to test orchestration logic in Order Service by mocking external microservices.

---

# Database Design

## Order Service Database

### orders table

| Column | Description |
|---|---|
| id | Order ID |
| product_id | Product identifier |
| amount | Order amount |
| status | Order status |
| created_at | Order timestamp |

---

## Inventory Service Database

### products table

| Column | Description |
|---|---|
| product_id | Product ID |
| product_name | Product name |
| quantity | Available stock |

---

## Payment Service Database

### payments table

| Column | Description |
|---|---|
| payment_id | Payment ID |
| order_id | Related order |
| amount | Payment amount |
| payment_method | Payment type |
| status | Payment status |
| transaction_time | Payment timestamp |

---

# What I Learned

Through this project, I learned:

- How microservices communicate
- How orchestration works
- Real backend architecture design
- Layered architecture implementation
- Hibernate & JPA integration
- DTO usage
- Validation techniques
- Exception handling
- Workflow state management
- REST API design
- Unit testing with Mockito
- Transaction management
- Domain separation in microservices

---

# Future Improvements

The following enterprise-level improvements can be added next:

- OpenFeign Client
- Eureka Server
- API Gateway
- Circuit Breaker (Resilience4j)
- Kafka
- Saga Pattern
- Docker
- Kubernetes
- JWT Authentication
- Role-Based Authorization
- CI/CD Pipelines

---

# Key Learning Outcome

This project helped me understand how real-world distributed backend systems coordinate multiple microservices using orchestration and workflow management patterns.

It also provided hands-on experience with enterprise backend development practices using Spring Boot.

---

# Author

Om