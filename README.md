# Online Shop Spring

A modular microservice-based e-commerce platform focused on selling digital products such as photos, videos, software licenses, PDFs and more.

---

## Project Overview

This project is designed following Domain-Driven Design (DDD), Clean Architecture, and Microservices principles to ensure scalability, maintainability, and clear separation of concerns.

Key features include:

- Product management with support for diverse digital product types and return policies  
- Order and payment processing with integration to Stripe  
- Shopping cart management with Redis and MongoDB  
- Asynchronous notification system for email and messaging  
- File storage for digital product assets  
- API Gateway for unified service access  
- Service discovery for dynamic service registration and lookup

---

## Architecture Overview

This project follows Domain-Driven Design (DDD) principles and is divided into multiple microservices representing distinct business domains.

For detailed domain design and context mappings, see the [Domain Design documentation](project-notes/01-domain-design.md).

---

## Project Structure

The project is organized into multiple microservices, each with its own domain and bounded context:

- `product-service`  
- `order-service`  
- `payment-service`  
- `cart-service`  
- `user-service`  
- `notification-service`  
- `file-storage-service`  
- `api-gateway`  
- `service-discovery`

Each service contains its own README file detailing service-specific information.
