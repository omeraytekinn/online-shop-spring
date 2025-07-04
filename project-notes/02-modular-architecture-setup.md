# Modular Project Structure with Hexagonal Architecture

This document explains the reasoning behind our modular project setup and how it aligns with Hexagonal Architecture and Domain-Driven Design (DDD) principles.

---

## 🧱 Goals

- Clean architectural separation: domain logic decoupled from frameworks
- Modular structure: each service is built as independent units (domain, application, infrastructure)
- Port & Adapter (Hexagonal) architecture for flexibility and testability
- Spring Boot only at infrastructure level
- Maven multi-module for shared versioning and reusable libraries

---

## 📁 Folder Structure

```

online-shop-spring/
├── pom.xml                      # Root Maven module
├── services/
│   ├── product-service/
│   │   ├── pom.xml              # Aggregator for domain/app/infra
│   │   ├── domain/              # Pure business logic
│   │   │   ├── pom.xml
│   │   │   └── src/main/java/com/omeraytekin/shop/product/domain/...
│   │   ├── application/         # Use cases
│   │   │   ├── pom.xml
│   │   │   └── src/main/java/com/omeraytekin/shop/product/application/...
│   │   ├── infrastructure/      # Spring Boot, adapters, wiring
│   │   │   ├── pom.xml
│   │   │   └── src/main/java/com/omeraytekin/shop/product/infrastructure/...

```

---

## 🔗 Dependency Flow

We follow the **clean dependency rule**:

```

domain -> application -> infrastructure

````

- Domain: no dependencies, pure Java
- Application: depends on domain
- Infrastructure: depends on domain, application and framework & libraries
- All dependency injection and framework wiring happens in the infrastructure layer.

---

## 🚀 Maven Setup

Each `product-service` (or other service) is a multi-module Maven project:

- `product-service/pom.xml`: defines modules (domain, application, infra)
- `domain` & `application`: `packaging = jar`
- `infrastructure`: `packaging = jar` with Spring Boot dependencies

The root `pom.xml` registers all services as modules and manages shared dependency versions.

```xml
<modules>
  <module>services/product-service</module>
</modules>
````

Each module inherits from this root unless it has its own.

---

## ❓ Why Hexagonal Architecture?

* Keep domain logic independent of frameworks
* Make core business testable without Spring
* Delay technical decisions (e.g., database, messaging tech)
* Cleanly separate responsibilities

---

## ✅ Benefits

* Framework-free domain and use case code
* Easily testable core
* Isolated responsibilities
* Reusable base for other services (with common-lib in future)
* More readable and professional-looking architecture
