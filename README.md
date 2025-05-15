# Easy Shops

**Easy Shops** is a full-stack e-commerce demo application that allows users to browse products, add them to a shopping cart, and complete purchases. The project is split into two main directories:

* `backend` - A Spring Boot REST API that handles user management, product catalog, orders, authentication, and email notifications.
* `frontend` - A React application built with Vite and Tailwind CSS that provides a responsive UI and interacts with the backend API.

---

## Table of Contents

1. [Features](#features)
2. [Tech Stack](#tech-stack)
3. [Prerequisites](#prerequisites)
4. [Getting Started](#getting-started)

   * [Backend Setup](#backend-setup)
   * [Frontend Setup](#frontend-setup)
5. [Configuration](#configuration)
6. [API Endpoints](#api-endpoints)
7. [Project Structure](#project-structure)

---

## Features

* User registration, login, and JWT-based authentication.
* Role-based access control (e.g., customer and admin).
* Product listing, creation, update, and deletion (admin only).
* Shopping cart management and order checkout.
* Email notifications for order confirmation.
* Secure password storage and validation.
* Responsive frontend built with React, React Router, and Redux Toolkit.
* Toast notifications for user feedback.

---

## Tech Stack

| Layer          | Technology                                          |
| -------------- | --------------------------------------------------- |
| Backend        | Java, Spring Boot, Spring Security, JPA, PostgreSQL |
| Authentication | JSON Web Tokens (JWT), Spring Security              |
| ORM            | Spring Data JPA, ModelMapper                        |
| Mailing        | Spring Boot Mail                                    |
| Frontend       | React, Vite, Tailwind CSS, Redux Toolkit            |
| UI Icons       | Font Awesome                                        |
| State Mgmt     | Redux Toolkit, React Redux                          |
| Routing        | React Router v7                                     |
| Notifications  | React Toastify                                      |

---

## Prerequisites

* Java 17 or higher
* Maven 3.8+
* Node.js 18+ and npm
* PostgreSQL database

---

## Getting Started

### Backend Setup

1. Navigate into the backend directory:

   ```bash
   cd backend
   ```

2. Configure your backend by editing the `src/main/resources/application.yml` file.  
   Here’s a description of the main settings you need to provide:

   - **Database Connection**  
     Set your PostgreSQL connection details:
     ```yaml
     spring:
       datasource:
         url: jdbc:postgresql://<HOST>:<PORT>/<DB_NAME>
         username: <DB_USER>
         password: <DB_PASSWORD>
     ```

   - **JPA & Hibernate**  
     These settings control how Hibernate interacts with your database.  
     You can usually leave these as provided, but you may adjust `ddl-auto` if you want to control schema updates.

   - **Mail Settings**  
     Configure your SMTP server for sending emails:
     ```yaml
     spring:
       mail:
         host: <SMTP_HOST>
         port: <SMTP_PORT>
         username: <SMTP_USER>
         password: <SMTP_PASSWORD>
         properties:
           mail:
             smtp:
               auth: true
               starttls:
                 enable: true
     ```

   - **JWT Authentication**  
     Set your JWT secret and token expiration:
     ```yaml
     auth:
       token:
         expirationInMils: 604800000
         jwtSecret: <YOUR_JWT_SECRET>
     ```

   - **Other Settings**  
     You can adjust the server port and other options as needed:
     ```yaml
     server:
       port: 8080
     ```

   > **Tip:** See the provided `application.yml` for a full example of all available settings.

3. Build and run the service:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Frontend Setup

1. Navigate into the frontend directory:

   ```bash
   cd frontend
   ```

2. Install dependencies:

   ```bash
   npm install
   ```

3. Start the development server:

   ```bash
   npm run dev
   ```

5. Open your browser at `http://localhost:3000` (or the port shown in the console).

---

## Configuration

### Backend Configuration (`application.yml`)

All backend configuration is managed in `src/main/resources/application.yml`. Below is a description of each section you can configure:

- **Database Connection**
  ```yaml
  spring:
    datasource:
      url: jdbc:postgresql://<HOST>:<PORT>/<DB_NAME>
      username: <DB_USER>
      password: <DB_PASSWORD>
  ```
  - `url`: JDBC URL for your PostgreSQL database.
  - `username` / `password`: Credentials for your database.

- **JPA & Hibernate**
  ```yaml
    jpa:
      database-platform: org.hibernate.dialect.PostgresSQLInnoDBDialect
      generate-ddl: true
      show-sql: true
      hibernate:
        ddl-auto: update
      properties:
        hibernate:
          globally_quoted_identifiers: true
          dialect: org.hibernate.dialect.PostgreSQLDialect
          format_sql: true
  ```
  - `database-platform`: Hibernate dialect for PostgreSQL.
  - `generate-ddl`: Whether to generate database schema.
  - `show-sql`: Show SQL statements in logs.
  - `hibernate.ddl-auto`: Auto schema update mode (`update` recommended for dev).
  - `hibernate.dialect`: SQL dialect.
  - `format_sql`: Pretty-print SQL in logs.

- **File Uploads**
  ```yaml
    servlet:
      multipart:
        enabled: true
        max-file-size: 1000MB
        max-request-size: 1000MB
  ```
  - Enables file uploads and sets size limits.

- **Spring MVC**
  ```yaml
    mvc:
      throw-exception-if-no-handler-found: true
  ```
  - Throws an exception if a route is not found.

- **Async Requests**
  ```yaml
    async:
      request-timeout: 3600000
  ```
  - Sets the async request timeout (in ms).

- **Mail Settings**
  ```yaml
    mail:
      host: <SMTP_HOST>
      port: <SMTP_PORT>
      username: <SMTP_USER>
      password: <SMTP_PASSWORD>
      properties:
        mail:
          smtp:
            auth: true
            starttls:
              enable: true
  ```
  - Configure SMTP for sending emails (order confirmations, etc).

- **Server Settings**
  ```yaml
  server:
    port: 8080
    error:
      path: /user/error
      whitelabel:
        enabled: false
  ```
  - `port`: Port for the backend server.
  - `error.path`: Custom error path.
  - `whitelabel.enabled`: Disable default error page.

- **API Prefix**
  ```yaml
  api:
    prefix: api/v1
  ```
  - Sets the base path for all API endpoints.

- **JWT Authentication**
  ```yaml
  auth:
    token:
      expirationInMils: 604800000
      jwtSecret: <YOUR_JWT_SECRET>
  ```
  - `expirationInMils`: JWT token expiration in milliseconds.
  - `jwtSecret`: Secret key for signing JWT tokens.

---

> **Tip:** See the provided `application.yml` or the example in `src/main/resources/application.example.yml` for a full configuration template.

## API Endpoints

| Method | Endpoint                | Description                        |
| ------ | ----------------------- | ---------------------------------- |
| POST   | `/api/v1/auth/login`       | Authenticate and receive token     |
| GET    | `/api/v1/products/all`     | List all products                  |
| POST   | `/api/products/add`         | Create a new product (admin)       |
| GET    | `/api/v1/products/product/{productId}/product`    | Get product details by ID |
| PUT    | `/api/products/product/{productId}/update`    | Update an existing product (admin) |
| DELETE | `/api/v1/products/product/{productId}/delete`    | Delete a product (admin)           |
| GET    | `/api/v1/cart/{cartId}/my-cart`             | Get current user cart   |
| POST   | `/api/v1/cart/items/item/add` | Add product to cart                |
| DELETE | `/api/v1/cart/items/{cartId}/item/{itemId}/remove` | Remove product from cart           |
| POST   | `/api/v1/orders/order`           | Create a new order from cart       |
| GET    | `/api/v1/orders/{userId}/user-orders`           | List user orders                   |

> **Note:** All `/api/v1/cart` and `/api/v1/orders` endpoints require a valid JWT token in the `Authorization` header.

## Project Structure

```
Easy-Shops/
├── backend/            # Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/easyshops/backend/
│   │   │   │   ├── controller/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   ├── service/
│   │   │   │   └── BackendApplication.java
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/
│   └── pom.xml
│
├── frontend/           # React + Vite application
│   ├── src/
|   |   ├── auth/
│   │   ├── components/
│   │   ├── routes/
│   │   ├── context/
│   │   ├── routes/
│   │   ├── services/
│   │   ├── utils/
│   │   ├── App.jsx
│   │   └── main.jsx
│   └── package.json
│
└── README.md
```

---
