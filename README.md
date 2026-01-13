# Marro's films API

A **Spring Boot** backend for the **Marro's films** backend film review application.  
This service provides a RESTful API for managing movies and user reviews, using **MongoDB** as the database.

---

## Tech Stack

- **Java 21+**
- **Spring Boot 3**
- **MongoDB Atlas**
- **Maven**
- **Insomnia/Postman** (for API testing)

---

## Features

- RESTful API for film data
- Store and retrieve reviews for each film
- Integration with MongoDB Atlas
- Environment-based configuration
- Layered architecture
- CORS-enabled endpoints for frontend communication (React app WIP)

## Live Demo

- **Backend (Render):** https://marro-films-api.onrender.com
- **API Endpoints documentation:** https://marro-films-api.onrender.com/api/v1

## Available Endpoints (Summary)

| Method | Endpoint               | Description                     |
|--------|-------------------------|---------------------------------|
| GET    | /api/v1/films          | Get all films                   |
| GET    | /api/v1/films/{imdbId} | Get a film by IMDb ID           |
| POST   | /api/v1/reviews        | Create a new review             |

---

## Project Structure
The backend follows a layered architecture with controllers, services, and repositories.
```plaintext

├── main/
│   ├── java/dev.javiermarro.films/
│   │   ├── controllers/     # REST controllers (Movies, Reviews)
│   │   ├── models/          # Data models (Movie, Review)
│   │   ├── repositories/    # MongoDB repositories
│   │   ├── services/        # Business logic
│   │   └── FilmsApplication.java
│   └── resources/
│       ├── application.properties  # Configurations
│       ├── endpoints.json          # API documentation
└── test/                           # Unit tests

```
The project is structured to allow an evolution toward Hexagonal Architecture by introducing ports and adapters as the application grows, at the moment I am still learning about Hexagonal Architecture via the following resources:
- https://github.com/kamilmazurek/hexagonal-architecture-template
- https://github.com/jaguililla/hexagonal_spring
- https://leandrofranchi.medium.com/hexagonal-architecture-with-spring-boot-building-truly-scalable-systems-7948472406ed
- https://vaadin.com/blog/ddd-part-1-strategic-domain-driven-design
- https://blog.allegro.tech/2020/05/hexagonal-architecture-by-example.html
- https://reflectoring.io/spring-hexagonal/

## CI - GitHub Actions running tests and build
I set up GitHub Actions CI that runs the full Maven verify lifecycle with JDK 21, caches dependencies, and publishes test results on every push and pull request. After that, I expanded unit and controller tests, and finally focused on production readiness with Docker and API documentation.

## Future Improvements

- Add authentication (JWT)
- Add pagination for film lists
- Improve error handling and validation
- Expand test coverage


## Documentation gathered throughout the development of this API

- [Spring Boot](docs/spring-boot-mongodb.md)
- [Testing](docs/testing.md)
- [Docker](docs/docker-and-cors.md)
- [Bugs & Fixes](docs/bugs-and-fixes.md)
- 
##  Setup Instructions

### Prerequisites

Make sure you have installed:

- [Java JDK 21+](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) or another Java IDE
- [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) account

---

### Clone the Repository

```
git clone https://github.com/JavierMarro/Marro-films-API.git
cd marro-films-api
```

### Configure Environment Variables

Create a file named .env (or edit application.properties) and set your MongoDB connection string:

```
MONGO_DATABASE=films_api_db
MONGO_URI=<your-mongodb-connection-string>
```

### Run the Application

Using Maven:
```
mvn spring-boot:run
```

Or from IntelliJ IDEA, run the main class:
```
FilmsApplication.java
```

### Test the API

Once the server is running, open your browser or use Insomnia/Postman or curl to test API requests, for example:

- **Base URL:** `http://localhost:8080/api/v1/films`

**Check the endpoints.json file out to see what endpoints are available and the expected results format.**

Alternatively, you can set these in your system environment variables.


