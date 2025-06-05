# ✈️ Travel Agency – Spring Boot MVC Application

A full-featured travel agency system built with Java and Spring Boot using **MVC pattern** and **server-side rendering (SSR)** via Thymeleaf.  
This app allows users to browse, book, and manage tours with role-based access and secure authentication using **JWT and HTTP sessions combined**.

---

## 📌 Features

- 🔐 **Secure Authentication**: Uses **JWT tokens** for authorization and **HTTP sessions** to support the SSR (Thymeleaf-based) web interface.
- 🧳 **Tour Browsing & Filtering**:
    - by type (rest, excursion, shopping)
    - by price
    - by transfer type (bus, plane, ship, etc.)
    - by hotel type (1–5 stars)
- 👤 **User Profile**: Shows selected tours and their current status.
- 💼 **Manager Panel**:
    - Mark tours as **hot**
    - Change tour status (registered → paid/canceled)
- 🛠 **Admin Panel**:
    - Add/delete/edit tours
    - Block/unblock users
- 🌍 **Internationalization**: Multilingual UI support
- 🧹 **Validation & Error Handling**: Input checks and informative feedback
- 🔍 **Pagination & Sorting**: View tours in a clean paginated interface
- 📜 **Logging**: Application event tracking and debugging

---

## 🛠️ Technologies Used

- **Java 17**, **Spring Boot 3+**
- **Spring MVC**, **Thymeleaf**
- **Spring Security** (JWT + Session)
- **Spring Data JPA**, **Hibernate**
- **Lombok**, **ModelMapper**
- **H2** (configurable)
- **Maven**

---

## 🗂 Project Structure

- `aspect/` – Logging or cross-cutting concerns
- `auth/` – Authentication and token logic
- `config/` – App configuration (Spring, security, locale)
- `controller/` – MVC controllers for SSR views
- `dto/` – Data Transfer Objects for passing data
- `exception/` – Custom exceptions & global handlers
- `mapper/` – Entity ↔ DTO mappers
- `model/` – JPA entity classes (User, Tour, etc.)
- `repository/` – JPA Repositories (UserRepository, etc.)
- `security/` – JWT utilities, filters, config
- `service/` – Business logic layer
- `token/` – Token generation and validation

---

## 🔧 Setup & Running

### Prerequisites

- Java 17+
- Maven 3.6+

### Steps

1. **Clone repo**

   ```bash
   git clone https://github.com/andrii102/travel-agency.git
   cd travel-agency
