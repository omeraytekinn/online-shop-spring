# Domain Design

This document defines the business and technical domains, domain structures, and service boundaries of the `online-shop-spring` microservice-based e-commerce platform.

---

## 🎯 Purpose

This project aims to build a modular and extensible platform for selling digital products (photos, videos, software licenses, PDFs, etc.), developed based on DDD (Domain-Driven Design), Clean Architecture, and Microservice architecture.

---

## 🧱 Subdomain Classification

According to DDD, the domain is divided into three categories: **Core**, **Supporting**, and **Generic**.

| Category   | Subdomain           | Description                                               |
|------------|---------------------|-----------------------------------------------------------|
| Core       | Order               | Directly impacts the user's shopping experience            |
|            | Payment             | Payment accuracy and real-time processing are critical     |
|            | Product Search      | Finding the desired product is at the core of the business |
|            | Refund Management   | Handling and tracking refund processes (bounded context)   |
| Supporting | Product Management  | Product addition, deletion, and product-specific refund policies |
|            | Cart                | Temporary process before ordering                           |
|            | User/Auth           | Identity and session management                             |
| Generic    | Notification        | Email, message notifications                                |
|            | File Storage        | File management and storage                                 |
|            | Monitoring          | Prometheus, Grafana, etc.                                   |
|            | API Gateway         | Routing, security, rate limiting                            |
|            | Service Discovery   | Technical routing service                                   |

---

## 🧱 Bounded Context List

Bounded Context defines technical boundaries for specific business areas.

| Context              | Responsibility                                              |
|----------------------|-------------------------------------------------------------|
| `product-service`    | Product and category management, search & filtering (CQRS), product-specific refund policies |
| `cart-service`       | Cart creation, updates, Redis-Mongo structure                |
| `user-service`       | User, roles, identity information                            |
| `order-service`      | Order creation, price calculation, invoice management, refund request handling |
| `payment-service`    | Stripe payments, event management, refund processing         |
| `notification-service`| Asynchronous notification sending                           |
| `file-storage-service`| File upload, deletion, access                               |
| `api-gateway`        | Single entry point, service routing and rate limiting        |
| `service-discovery`  | Microservice address discovery (Eureka/K8s DNS)              |

---

## 🔁 Data Ownership

| Service                | Entity / Data                                            |
|------------------------|----------------------------------------------------------|
| `product-service`       | Product, Category, Discount, Tax, FileId (reference), RefundPolicy (refund policy) |
| `cart-service`          | Cart, CartItem (Product snapshot)                         |
| `user-service`          | User, Role, BankInfo, Credentials                         |
| `order-service`         | Order, OrderItem, Invoice, PriceSummary, RefundRequest (refund request) |
| `payment-service`       | Payment, PaymentAttempt, StripeEvent, Refund              |
| `notification-service`  | Message, Channel, DeliveryResult                           |
| `file-storage-service`  | FileMetadata, FileBinary                                  |
| `api-gateway`           | RateLimit, RoutingRule, TokenInfo                         |

---

## 🗺️ Context Map

| From Service           | To Service             | Communication Type | Description                                            |
|-----------------------|------------------------|--------------------|--------------------------------------------------------|
| `api-gateway`         | All services           | REST               | Single entry point for REST calls to all services      |
| `order-service`       | `product-service`      | REST               | Product snapshot query for order product info          |
| `product-service`     | `cart-service`         | Event              | Publishes “ProductUpdated” event when product changes  |
| `product-service`     | `notification-service` | Event              | Triggers notifications related to products             |
| `order-service`       | `product-service`      | REST               | Queries refund policy during refund requests            |
| `payment-service`     | `order-service`        | Event              | Payment success/info events                              |
| `order-service`       | `payment-service`      | Event              | Sends refund request after refund approval              |
| `payment-service`     | `order-service`        | Event              | Sends refund result information                          |
| All services          | `notification-service` | Event              | Listens to events from various services for async notifications |
| `cart-service`        | `product-service`      | REST               | REST call to get current product snapshot info          |
| `user-service`        | `notification-service` | Event              | Triggers notifications related to refund and other events |
| `order-service`       | `notification-service` | Event              | Sends notifications for refund requests and status updates |

---

## 🗒️ Notes

- Domain and context boundaries may evolve over time.  
- This document will be updated according to the project's progress.

