**Project Overview**
- **Purpose**: A small microservices demo containing three Spring Boot services: Inventory, Order, and Payment. It demonstrates basic service separation, simple DTO-based communication, and local orchestration.
- **Location**: This workspace contains `inventory-service`, `order-service`, and `payment-service` subprojects.

**Motivation**: I built these services to practice designing and implementing a lightweight microservices architecture using Spring Boot. The goals were to learn inter-service DTOs, configuration management, simple controller endpoints, and how to run multiple services locally.

**What's Included**
- **Inventory service**: REST controller for inventory queries; provides `InventoryResponse` DTO.
- **Order service**: Accepts order requests, consults inventory, and coordinates payment requests/responses. Contains `OrderRequest`, `InventoryResponse`, `PaymentRequest`, and `PaymentResponse` DTOs.
- **Payment service**: Simple payment controller that accepts payment requests and returns `PaymentResponse`.

**How Each Service Works**
- **Inventory-service**: Exposes endpoints to check availability and return inventory metadata. Intended to be a read-only service for these examples.
- **Order-service**: Acts as the orchestrator for order placement: it receives an `OrderRequest`, calls Inventory (to validate/reserve), then calls Payment (to charge) and returns an aggregated result.
- **Payment-service**: Receives `PaymentRequest` and returns a success/failure `PaymentResponse`.

**How To Run Locally**
- **Build & Run (Windows)**: From each service folder run:

```
mvnw.cmd spring-boot:run
```

- **Build & Run (Unix / macOS)**: From each service folder run:

```
./mvnw spring-boot:run
```

- **Notes**: Each service has its own `application.properties` in `src/main/resources/`. Check those files for configured ports and other runtime settings before starting multiple services.

**What I Did (Implementation Summary)**
- **Defined DTOs**: Created simple POJOs for requests and responses so services can exchange typed payloads.
- **Implemented Controllers**: Each service exposes minimal REST controllers to receive and respond to HTTP requests.
- **Kept Services Decoupled**: Communication uses DTOs and HTTP; there is no shared persistence layer in this demo.

**Key Learnings**
- **Service Boundaries**: Designing clear responsibilities (inventory vs. order vs. payment) simplifies reasoning and testing.
- **DTO Design**: Small, focused DTOs make it easy to evolve service contracts and avoid leaking internal models.
- **Local Orchestration**: Running multiple Spring Boot apps locally highlights issues like port conflicts and configuration isolation.
- **Error Handling**: Orchestration requires careful error handling and compensation logic—important for reliability in distributed flows.

**Design Analysis & Decisions**
- **Why HTTP + DTOs**: Simplicity and wide tooling support; acceptable for small demos and development environments.
- **No Shared DB**: Avoids coupling and models a more realistic microservice architecture where each service owns its data.
- **Synchronous Orchestration**: Order-service calls Inventory and Payment synchronously for simplicity. In production, consider asynchronous patterns for reliability and scaling (e.g., events, message queues).

**Trade-offs Observed**
- **Synchronous Calls**: Easier to implement; harder to scale and more fragile under partial failures.
- **No Resilience Patterns**: This demo lacks retries, timeouts, circuit breakers—these are important next steps for robustness.

**Improvements & Next Steps**
- **Add Resilience**: Introduce timeouts, retries, and circuit breakers (e.g., resilience4j or Spring Cloud Circuit Breaker).
- **Add Observability**: Add structured logging, distributed tracing, and metrics to see cross-service flows.
- **Automated Tests**: Add integration tests that start multiple services (testcontainers or embedded servers) to validate orchestration flows.
- **Asynchronous Flow**: Replace synchronous payment with event-driven flow (e.g., using Kafka/RabbitMQ) to improve decoupling.

**Troubleshooting**
- **Port Conflicts**: If a service fails to start, check `src/main/resources/application.properties` for the server port and adjust as needed.
- **Build Failures**: Run `mvn -e -X` for verbose Maven errors and ensure Java and Maven toolchains are correctly configured for your environment.

**References & Notes**
- **Files of interest**: See each service's `src/main/java` and `src/main/resources/application.properties` for implementation details and runtime configuration.
- **Contact**: This README documents what I implemented and learned while building these three services.
