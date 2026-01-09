# Marro's films API

A **Spring Boot** backend for the **Marro's films** full-stack film review application.  
This service provides a RESTful API for managing movies and user reviews, using **MongoDB** as the database.

---

## Tech Stack

- **Java 21+**
- **Spring Boot 3**
- **MongoDB Atlas**
- **Maven**
- **Postman** (for API testing)

---

## Features

- RESTful API for film data
- Store and retrieve reviews for each film
- Integration with MongoDB Atlas
- Environment-based configuration
- Layered architecture
- CORS-enabled endpoints for frontend communication (React app)

---
asdfasf
## Project Structure
The backend follows a layered architecture with controllers, services, and repositories.
```plaintext

├── main/
│   ├── java/dev.javiermarro.films/
│   │   ├── controller/     # REST controllers (Movies, Reviews)
│   │   ├── model/          # Data models (Movie, Review)
│   │   ├── repository/     # MongoDB repositories
│   │   ├── service/        # Business logic
│   │   └── FilmsApplication.java
│   └── resources/
│       ├── application.properties  # Configurations
└── test/                           # Unit tests

```
The project is structured to allow an evolution toward hexagonal architecture by introducing ports and adapters as the application grows, at the moment I am still learning about Hexagonal Architecture via the following resources:
- https://github.com/kamilmazurek/hexagonal-architecture-template
- https://github.com/jaguililla/hexagonal_spring
- https://leandrofranchi.medium.com/hexagonal-architecture-with-spring-boot-building-truly-scalable-systems-7948472406ed
- https://www.reddit.com/r/softwarearchitecture/comments/1pb9zge/i_finally_understood_hexagonal_architecture_after/
- https://vaadin.com/blog/ddd-part-1-strategic-domain-driven-design
- https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/
- https://blog.allegro.tech/2020/05/hexagonal-architecture-by-example.html
- https://reflectoring.io/spring-hexagonal/


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
git clone https://github.com/JavierMarro/marrofilms.git
cd marrofilms
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

Once the server is running, open your browser or use Postman or curl to test API requests, for example:

- **Base URL:** `http://localhost:8080/api/v1/films`

**Check the endpoints.json file out to see what endpoints are available and the expected results format.**

Alternatively, you can set these in your system environment variables.

