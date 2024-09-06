# Calculator Application

This Calculator Application is designed to perform basic arithmetic operations such as addition, subtraction, multiplication, and division. It supports operations on different data types including integers, floating-point numbers, and big decimals to ensure precision.

## Features

- **Basic Arithmetic**: Perform addition, subtraction, multiplication, and division.
- **Data Type Support**: Handles `Integer`, `Double`, and `BigDecimal` for precision.
- **Error Handling**: Properly manages division by zero and invalid input scenarios.
- **REST API**: Provides a RESTful service to perform calculations remotely.
- **Chaining Operations**: Supports chaining multiple operations in a single calculation.

## Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Simplifies the development of new Spring applications.
- **Lombok**: Reduces boilerplate code.
- **JUnit**: Used for unit testing.
- **Maven**: Dependency management and build automation.
- **Swagger**: To document Rest APIs.

## Getting Started

### Prerequisites

- Java JDK 11 or newer
- Maven 3.6 or newer

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/calculator-application.git
   cd calculator-application
2. Build the project using Maven:
mvn clean install
3. Run the application:
mvn spring-boot:run

### Usage

The application exposes a RESTful API that can be accessed through HTTP requests. Here are some examples using curl:

curl -X POST http://localhost:8080/api/calculate -H "Content-Type: application/json" -d '{"operation":"ADD", "num1": 5, "num2": 3}'

curl -X POST http://localhost:8080/api/calculate -H "Content-Type: application/json" -d '{"operation":"SUBTRACT", "num1": 5, "num2": 3}'

curl -X POST http://localhost:8080/api/calculate -H "Content-Type: application/json" -d '{"operation":"MULTIPLY", "num1": 5, "num2": 3}'

curl -X POST http://localhost:8080/api/calculate -H "Content-Type: application/json" -d '{"operation":"DIVIDE", "num1": 5, "num2": 3}'

Chaining Operations
The application also supports chaining multiple arithmetic operations in a single request. Here's how you can perform a chain calculation:

curl -X POST http://localhost:8080/api/chainCalculate -H "Content-Type: application/json" -d '{
    "initialValue": 0,
    "operations": ["ADD", "SUBTRACT", "MULTIPLY", "DIVIDE"],
    "operands": [1, 2, 3, 4]
}'

## API Documentation

This project uses Swagger to provide an interactive API documentation. Swagger UI allows you to explore the available RESTful endpoints, test them in real-time, and view expected request structures and response formats.

### Accessing Swagger UI

Once the application is running, you can access the Swagger UI by navigating to:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

This will load the interactive API documentation where you can execute API calls directly from your browser.

### Features of Swagger UI

- **Interactive API Calls**: Test the API endpoints directly from your browser without needing additional tools like Postman.
- **Real-time Feedback**: View responses and debug issues in real-time.
- **Schema Visualization**: Visualize the request and response schema for each endpoint.

### Future Improvements

- Extended Operation Support: Implement more complex mathematical functions such as exponentiation, modulus, and trigonometric functions.
- User Interface: Develop a web interface or mobile app for easier interaction.
- Performance Optimization: Improve handling of large numbers and complex operations.
- Authentication and Authorization: Enable user-specific features with secure access.
- API Rate Limiting: Manage load with API rate limiting.
- Decimal Precision Management: Allow users to set precision for operations.
- Accessibility Enhancements: Ensure the calculator is accessible to all users.