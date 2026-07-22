# Enterprise Travel Booking System

## Overview

Enterprise Travel Booking System is a Spring Boot based microservices application that provides flight and hotel search capabilities and allows users to create, retrieve, and cancel travel bookings.

The project demonstrates enterprise-level backend development practices including REST APIs, microservices architecture, database integration, exception handling, logging, testing, API documentation, and containerization using Docker.

---

# Architecture

```
                         Client
                           |
                           |
                    REST API Requests
                           |
          -----------------------------------
          |                                 |
          |                                 |
 Travel Search Service              Booking Service
          |                                 |
          |                                 |
 Flight Provider API                MySQL Database
 Hotel Provider API                       |
                                          |
                                   Docker Compose
```

## Architecture Flow

1. Client sends requests through REST APIs.
2. Travel Search Service aggregates flight and hotel information.
3. Booking Service manages booking lifecycle.
4. MySQL stores booking information.
5. Docker Compose manages application and database containers.

---

# Technology Stack

## Backend

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- REST APIs
- Maven

## Database

- MySQL 8

## Testing

- JUnit 5
- Mockito
- MockMvc
- Spring Boot Test

## API Documentation

- OpenAPI 3
- Swagger UI

## DevOps

- Docker
- Docker Compose

## Tools

- IntelliJ IDEA
- Postman
- Git
- GitHub

---

# Project Structure

```
enterprise-travel-booking-system

│
├── booking-service
│
│   ├── controller
│   │
│   ├── service
│   │
│   ├── repository
│   │
│   ├── entity
│   │
│   ├── dto
│   │
│   └── exception
│
├── travel-search-service
│
├── docker-compose.yml
│
├── pom.xml
│
└── README.md
```

---

# Microservices

## Booking Service

The Booking Service manages the complete booking lifecycle.

### Responsibilities

- Create booking
- Retrieve booking details
- Cancel booking
- Update booking status
- Store booking information

---

## Booking APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/bookings` | Create booking |
| GET | `/api/bookings/{bookingId}` | Get booking details |
| PATCH | `/api/bookings/{bookingId}/cancel` | Cancel booking |

---

## Travel Search Service

The Travel Search Service provides travel search functionality.

### Responsibilities

- Search flights
- Search hotels
- Aggregate provider responses
- Handle provider failures

---

# Database

## MySQL Database

Database Name:

```
travel_booking_db
```

Main Table:

```
booking
```

Booking information includes:

- Booking ID
- Passenger Name
- Flight Number
- Hotel Name
- Source
- Destination
- Travel Date
- Total Price
- Booking Status
- Booking Time

---

# Application Features

## REST API Development

Implemented REST endpoints using Spring Boot controllers.

## Exception Handling

Implemented:

- Custom exceptions
- Global exception handler
- Proper HTTP status responses

Example:

```
BookingNotFoundException -> HTTP 404
Validation Errors -> HTTP 400
```

## Logging

Implemented application logging using SLF4J.

Examples:

- Booking creation logs
- Booking cancellation logs
- Warning logs for missing bookings

## API Documentation

Swagger UI provides interactive API documentation.

URL:

```
http://localhost:8082/swagger-ui/index.html
```

---

# Testing

Implemented automated tests using:

## Service Layer Tests

- Booking creation test
- Booking retrieval test
- Booking cancellation test

## Controller Tests

Using MockMvc:

- POST booking API
- GET booking API
- PATCH cancel API

## Exception Tests

Validated:

- BookingNotFoundException
- Validation failures
- Error responses

---

# Running Application Locally

## Prerequisites

Install:

- Java 17
- Maven
- Docker Desktop

---

# Running With Docker Compose

Docker Compose starts:

1. Booking Service container
2. MySQL database container

Architecture:

```
Booking Service Container
          |
          |
     Docker Network
          |
          |
MySQL Database Container
```

---

## Build Docker Image

Navigate to booking-service:

```
cd booking-service
```

Build application:

```
mvn clean package -DskipTests
```

Create Docker image:

```
docker build -t booking-service .
```

---

## Start Application

Navigate to project root:

```
cd enterprise-travel-booking-system
```

Start containers:

```
docker compose up
```

Run in background:

```
docker compose up -d
```

---

## Stop Application

```
docker compose down
```

---

# Docker Configuration

Docker Compose creates:

## Booking Service

```
Container:
booking-service

Port:
8082
```

## MySQL

```
Container:
travel-mysql

Port:
3307 -> 3306
```

---

# Database Connection

When running with Docker Compose:

```
jdbc:mysql://mysql:3306/travel_booking_db
```

The hostname `mysql` refers to the MySQL container service name inside Docker networking.

---

# Monitoring

Spring Boot Actuator is enabled.

Health Check:

```
http://localhost:8082/actuator/health
```

Example response:

```json
{
  "status": "UP"
}
```

---

# Configuration Profiles

Supported environments:

```
application-dev.properties
application-test.properties
application-prod.properties
```

Different configurations are maintained for:

- Database
- Logging
- Environment-specific settings

---

# Future Enhancements

Possible improvements:

- Spring Cloud Gateway
- Service Discovery using Eureka
- Kafka event-driven communication
- JWT Authentication
- Kubernetes Deployment
- CI/CD Pipeline
- Cloud Deployment (AWS)

---

# Author

**Srujana Gattu**

Java Backend Engineer

Skills:
- Java
- Spring Boot
- Microservices
- REST APIs
- Docker
- MySQL