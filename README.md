# Backend Management System

A scalable and efficient backend management system built with **Scala** and **Akka HTTP**. This project aims to provide a robust infrastructure for managing backend services, including user authentication, data processing, and API management. It is designed to be extendable, secure, and optimized for performance.

## Features

- **User Authentication & Authorization**: Secure user management with roles and permissions.
- **RESTful API**: Structured API for seamless frontend and third-party integration.
- **Data Processing**: Real-time and batch data processing with **Akka Streams**.
- **Database Integration**: Scalable data storage using **PostgreSQL**.
- **Error Handling & Logging**: Comprehensive logging and error tracking with **Logback** and **Akka's supervision strategy**.
- **Configuration Management**: Flexible configurations with **Typesafe Config**.

## Technologies Used

- **Scala 2.13**: Functional programming with robust type safety.
- **Akka HTTP**: Asynchronous, non-blocking HTTP server and client for handling RESTful requests.
- **Slick**: Functional Relational Mapping (FRM) for seamless interaction with SQL databases.
- **Akka Streams**: Stream processing and real-time data handling.
- **PostgreSQL**: High-performance relational database.
- **Docker**: Containerization for easy deployment and scalability.
- **Flyway**: Database migration management.

## Getting Started

### Prerequisites

- **Scala** 2.13+
- **SBT** (Scala Build Tool)
- **PostgreSQL** (Ensure a PostgreSQL instance is running and accessible)
- **Docker** (optional, for containerization)

### Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/mollybeach/backend-management-system.git
   cd backend-management-system
