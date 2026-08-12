# Insurance Risk Assessment API

A RESTful backend application built with **Java and Spring Boot** for assessing customer insurance risk and calculating insurance premiums based on risk-related factors.

The project focuses on clean backend architecture, validation, exception handling, persistence, automated testing, and maintainable business logic.

It is being developed as a foundation for a more comprehensive insurance calculation system covering both **life and non-life insurance products**.

## 🚀 Technologies

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Gradle
* Jakarta Validation
* MapStruct
* Lombok
* JUnit
* Swagger / OpenAPI

## 🏗️ Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Additional components are used to separate responsibilities:

```text
DTO
Mapper
Risk Calculator
Premium Calculator
Exception Handler
```

This structure keeps API handling, business logic, persistence, and data transformation separated.

## ✨ Features

* Create and store insurance risk information
* Calculate customer risk scores
* Classify customers by risk level
* Calculate insurance premiums based on risk score
* Retrieve risk records
* Filter records by age
* Retrieve high-risk customers
* Delete stored risk records
* Request validation
* Centralized exception handling
* DTO-based API communication
* Entity/DTO mapping with MapStruct
* Unit testing for business logic

## 🧮 Risk Assessment

The application evaluates customer information such as:

* Age
* BMI
* Smoking status
* Chronic disease status
* Income

These factors are processed by the risk calculation layer to generate a risk score and corresponding risk classification.

> The current calculation model is intended for software-development and educational purposes and should not be interpreted as an actuarially validated insurance pricing model.

## 💰 Premium Calculation

The application includes a separate premium calculation component responsible for converting calculated risk scores into insurance premium values.

Separating risk assessment from premium calculation makes it easier to extend the application with different insurance products and pricing models in the future.

## ⚠️ Error Handling

The application uses centralized exception handling to provide consistent API responses.

Examples include:

* Invalid request data → `400 Bad Request`
* Resource not found → `404 Not Found`
* Invalid business parameters → appropriate error response

## 🧪 Testing

Business logic is tested using JUnit.

Current tests focus on components such as:

* Risk calculation
* Premium calculation
* Invalid input handling

Run the tests with:

```bash
./gradlew test
```

On Windows:

```bash
gradlew.bat test
```

## ▶️ Running the Project

### Requirements

Make sure the following are installed:

* Java
* PostgreSQL

### Clone the repository

```bash
git clone https://github.com/atamertokay/insurance-risk-assessment-api.git
cd insurance-risk-assessment-api
```

### Configure PostgreSQL

Configure your database connection in the Spring Boot application configuration.

Example:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/insurance_db
    username: YOUR_USERNAME
    password: YOUR_PASSWORD
```

Do not commit real database credentials to the repository.

### Start the application

```bash
./gradlew bootRun
```

On Windows:

```bash
gradlew.bat bootRun
```

## 🗺️ Roadmap

The long-term goal is to transform this project into a more realistic insurance pricing and risk assessment platform.

Planned improvements include:

* [ ] Life insurance module
* [ ] Non-life insurance modules
* [ ] Product-specific risk models
* [ ] More realistic premium calculation models
* [ ] Extended actuarial risk factors
* [ ] Policy management
* [ ] Customer management
* [ ] Authentication and authorization
* [ ] Integration tests
* [ ] Docker support
* [ ] CI/CD with GitHub Actions
* [ ] API documentation improvements

## 🎯 Project Goal

This project combines **software engineering** with concepts from the **insurance and actuarial domain**.

The goal is not only to demonstrate Spring Boot development skills, but also to gradually build a domain-oriented backend system with increasingly realistic insurance risk and pricing logic.

## 👨‍💻 Author

**Ata Mert Okay**

GitHub: [@atamertokay](https://github.com/atamertokay)
