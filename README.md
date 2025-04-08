# 📝 Full Stack Todo App (React + Spring Boot + MySQL)

This project is a **full-stack Todo app** built with:

- 🌐 **Frontend:** React (Vite + TypeScript)
- ☕ **Backend:** Spring Boot
- 🐬 **Database:** MySQL
- 🐳 Fully containerized using **Docker Compose**
- ✅ Includes unit, integration, and E2E tests (Vitest, JUnit, Cypress)

# 📦 Build & Run the App
Step 1: Clone the Repository
Step 2: Build and Start All Services - docker compose up --build

Access the application: Open your browser and navigate to  http://localhost:3000

✅ Running Tests
docker compose run --rm backend-tests
docker compose run --rm frontend-tests
docker compose run --rm frontend-tests

## ⚗️ Tech Stack

| Layer      | Tech                                      |
|------------|-------------------------------------------|
| Frontend   | React, TypeScript, Vite, Tailwind CSS     |
| Backend    | Spring Boot, Spring Data JPA              |
| Database   | MySQL                                     |
| Testing    | Vitest, JUnit, Mockito, Cypress           |
| Container  | Docker + Docker Compose                   |
