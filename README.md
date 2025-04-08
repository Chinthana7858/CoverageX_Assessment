# 📝 Full Stack Todo App (React + Spring Boot + MySQL)

This project is a **full-stack Todo app** built with:

- 🌐 **Frontend:** React (Vite + TypeScript)
- ☕ **Backend:** Spring Boot
- 🐬 **Database:** MySQL
- 🐳 Fully containerized using **Docker Compose**
- ✅ Includes unit, integration, and E2E tests (Vitest, JUnit, Cypress)

## 📦 Build & Run the App

### ✅ Step 1: Clone the Repository

### ✅ Step 2: Build and Start All Services
```bash
docker compose up --build
```

### 🌐 Access the Application
Open your browser and navigate to:
```
http://localhost:3000
```

---

## ✅ Running Tests

### 🧪 Run Backend Tests
```bash
docker compose run --rm backend-tests
```

### 🧪 Run Frontend Unit Tests
```bash
docker compose run --rm frontend-tests
```

### 🧪 Run Cypress E2E Tests
```bash
docker compose run --rm cypress
```


## ⚗️ Tech Stack

| Layer      | Tech                                      |
|------------|-------------------------------------------|
| Frontend   | React, TypeScript, Vite, Tailwind CSS     |
| Backend    | Spring Boot, Spring Data JPA              |
| Database   | MySQL                                     |
| Testing    | Vitest, JUnit, Mockito, Cypress           |
| Container  | Docker + Docker Compose                   |
