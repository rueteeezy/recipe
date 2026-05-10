# Recipe Management REST API

RESTful API built with Spring Boot that allows users to manage cooking recipes.

The application supports:
- CRUD operations
- Dynamic recipe searching
- PostgreSQL persistence
- Swagger/OpenAPI documentation

Repository:

[Recipe Repository](https://github.com/rueteeezy/recipe)

---

# Tech Stack

- Java 17
- Spring Boot 3.5.0
- PostgreSQL 14
- Spring Data JPA / Hibernate
- Maven
- Lombok
- Swagger/OpenAPI

---

# Features

- Create recipe
- Get all recipes
- Get recipe by ID
- Update recipe
- Delete recipe
- Search recipes using:
    - Vegetarian filter
    - Servings filter
    - Include ingredient
    - Exclude ingredient
    - Instruction keyword

---

# Project Structure

```text
src/main/java/com/recime/recipe
│
├── config
│   └── OpenApiConfig.java
│
├── controller
│   └── RecipeController.java
│
├── dto
│   ├── RecipeRequest.java
│   └── RecipeResponse.java
│
├── entity
│   └── Recipe.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
│
├── repository
│   └── RecipeRepository.java
│
├── service
│   ├── RecipeService.java
│   └── impl
│       └── RecipeServiceImpl.java
│
├── specification
│   └── RecipeSpecification.java
│
└── RecipeApplication.java
```

---

# Prerequisites

Before running the application, install:

- Java 17
- Maven
- PostgreSQL 14
- pgAdmin 4 (optional)

---

# Database Setup

## Create Database

Using PostgreSQL or pgAdmin:

```sql
CREATE DATABASE recipe_db;
```

---

# Configure application.yml

Location:

```text
src/main/resources/application.yml
```

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/recipe_db
    username: postgres
    password: your_password

  jpa:
    hibernate:
      ddl-auto: update

    show-sql: true

    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
```

Replace:

```text
your_password
```

with your PostgreSQL password.

---

# Create Tables

```sql
CREATE TABLE IF NOT EXISTS recipes (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    servings INTEGER,
    vegetarian BOOLEAN,
    instructions TEXT
);

CREATE TABLE IF NOT EXISTS recipe_ingredients (
    recipe_id BIGINT NOT NULL,
    ingredient VARCHAR(255),
    CONSTRAINT fk_recipe
        FOREIGN KEY(recipe_id)
        REFERENCES recipes(id)
        ON DELETE CASCADE
);
```

---

# Insert Sample Data

## Insert Recipes

```sql
INSERT INTO recipes (
    title,
    description,
    servings,
    vegetarian,
    instructions
)
VALUES
(
    'Vegetarian Pasta',
    'Healthy vegetarian pasta recipe',
    2,
    true,
    'Boil pasta and mix with tomato sauce.'
),
(
    'Chicken Curry',
    'Spicy chicken curry',
    4,
    false,
    'Cook chicken with curry sauce and spices.'
),
(
    'Garlic Fried Rice',
    'Simple garlic fried rice',
    3,
    true,
    'Fry garlic then add rice and mix well.'
);
```

---

## Insert Ingredients

```sql
INSERT INTO recipe_ingredients (recipe_id, ingredient)
VALUES
(1, 'Pasta'),
(1, 'Tomato'),
(1, 'Garlic'),

(2, 'Chicken'),
(2, 'Curry Powder'),
(2, 'Onion'),

(3, 'Rice'),
(3, 'Garlic'),
(3, 'Soy Sauce');
```

---

# Verify Database Data

```sql
SELECT * FROM recipes;

SELECT * FROM recipe_ingredients;
```

---

# Install Dependencies

```bash
mvn clean install
```

---

# Run Application

```bash
mvn spring-boot:run
```

Application runs on:

```text
http://localhost:8080
```

---

# Swagger/OpenAPI Documentation

Open Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/v1/recipes | Create recipe |
| GET | /api/v1/recipes | Get all recipes |
| GET | /api/v1/recipes/{id} | Get recipe by ID |
| PUT | /api/v1/recipes/{id} | Update recipe |
| DELETE | /api/v1/recipes/{id} | Delete recipe |
| GET | /api/v1/recipes/search | Search recipes |

---

# Sample API Requests

# POST — Create Recipe

```http
POST /api/v1/recipes
Content-Type: application/json
```

```json
{
  "title": "Vegetarian Pasta",
  "description": "Healthy vegetarian pasta recipe",
  "servings": 2,
  "vegetarian": true,
  "ingredients": [
    "Pasta",
    "Tomato",
    "Garlic"
  ],
  "instructions": "Boil pasta and mix with tomato sauce."
}
```

---

# GET — Get All Recipes

```http
GET /api/v1/recipes
```

---

# GET — Get Recipe By ID

```http
GET /api/v1/recipes/1
```

---

# PUT — Update Recipe

```http
PUT /api/v1/recipes/1
Content-Type: application/json
```

```json
{
  "title": "Updated Vegetarian Pasta",
  "description": "Updated pasta recipe",
  "servings": 4,
  "vegetarian": true,
  "ingredients": [
    "Pasta",
    "Cheese",
    "Garlic"
  ],
  "instructions": "Cook pasta and add cheese."
}
```

---

# DELETE — Delete Recipe

```http
DELETE /api/v1/recipes/1
```

---

# GET — Search Vegetarian Recipes

```http
GET /api/v1/recipes/search?vegetarian=true
```

---

# GET — Search By Servings

```http
GET /api/v1/recipes/search?servings=4
```

---

# GET — Search By Included Ingredient

```http
GET /api/v1/recipes/search?includeIngredient=garlic
```

---

# GET — Search By Excluded Ingredient

```http
GET /api/v1/recipes/search?excludeIngredient=chicken
```

---

# GET — Search By Instruction Keyword

```http
GET /api/v1/recipes/search?instructionKeyword=boil
```

---

# GET — Combined Search

```http
GET /api/v1/recipes/search?vegetarian=true&includeIngredient=garlic&servings=2
```

---

# Design Decisions

- Used layered architecture:
    - Controller
    - Service
    - Repository

- Used DTOs to separate API contracts from persistence entities

- Used Spring Data JPA Specifications for scalable search filtering

- Used centralized exception handling

- Used OpenAPI/Swagger for API documentation

- Used ElementCollection for lightweight ingredient persistence

---

# Future Improvements

- Pagination and sorting
- Unit tests
- Integration tests
- Docker support
- Authentication & authorization
- Flyway/Liquibase migrations
- CI/CD pipeline
- Caching support

---

# Author

Recipe Management REST API
