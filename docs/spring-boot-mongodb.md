# Setting up the backend

File route to configure connection to MongoDB
```plaintext
films/
├── src/
│   ├── main/
│   │   ├── java/com/dev.javiermarro/films/
│   │   └── resources/
│   │       ├── application.properties  # MongoDB dependency configuration here
```
In order:
1. add the database
2. add uri from MongoDB Compass -> To do so, in MongoDB Compass, find the cluster in the connections tab, then click on the three dots to the right of the name of the cluster and click on 'Copy connection string'
3. make sure the connection is enabled in the dependencies in pom.xml
4. once added info to application.properties, right click on pom.xml, then at the bottom navigate to maven which will need to be reloaded(resync)
5. Finally run class to check connection

The setup created is better suited for a .env file, to be able to add it to a gitignore file and hide sensitive information (which lives in the resources folder)
The .env contains
```
MONGO_DATABASE=
MONGO_USER=
MONGO_PASSWORD=
MONGO_CLUSTER=
```
**HOWEVER, Spring does not support reading .env files out of the box**
Need to install a new dependency to the project:
- Go to https://mvnrepository.com/
- Search spring-dotenv
- Use the one developed by me.paulschwarz
- Then in pom.xml, add a dependency stating the groupId, artifactId and version (optional)
- Finally, use string interpolation to place the values from the .env into the application.properties as such:
```
spring.data.mongodb.database=${env.MONGO_DATABASE}
spring.data.mongodb.uri=mongodb+srv://${env.MONGO_user}:${env.MONGO_PASSWORD}@${env.MONGO_CLUSTER}
```

# Setting up the API (using Spring & MongoDB)
## Spring Boot
### General Spring Boot
- Resources linked to Spring Boot annotations:</br>
https://www.geeksforgeeks.org/blogs/top-spring-boot-annotations/</br>
https://github.com/gindex/spring-boot-annotation-list</br>
https://www.baeldung.com/spring-core-annotations</br>
https://iammadhankumar.medium.com/top-spring-boot-annotations-748878a96060
- Resources linked to REST API / MVC annotations:</br>
  https://www.baeldung.com/spring-mvc-annotations</br>
  https://www.geeksforgeeks.org/advance-java/spring-mvc-annotations-with-examples/</br> 
  https://medium.com/javarevisited/10-spring-mvc-and-rest-annotations-every-java-developer-should-learn-b3d052710d0b</br>
  https://www.youtube.com/watch?v=xZbrC7jDFVY</br>
### Spring Boot Controllers
ResponseEntity is a class in Spring Framework that represents an HTTP response, including the status code, headers, and body. It provides more control over the HTTP response compared to returning a simple object:

https://www.baeldung.com/spring-response-entity</br>
https://dev.to/devcorner/mastering-responseentity-and-controller-in-spring-boot-agc#:~:text=ResponseEntity%20is%20a%20class%20in,springframework</br>

### Spring Boot Repositories
Why we need them:
https://www.geeksforgeeks.org/springboot/spring-repository-annotation-with-example/</br>
https://stackoverflow.com/questions/66842685/why-do-we-need-repository-in-spring-boot-applications

### Spring Boot Services


### - GET endpoint

When the browser send a request to **/api/v1/films**:

```
1. Request hits Controller:
- FilmController.getAllFilms() runs.
- It calls filmService.allFilms().

2. Service delegates to Repository:
- FilmService.allFilms() calls filmRepo.findAll().
- filmRepo is an instance of the FilmRepository interface, which Spring automatically implements for you (via MongoRepository).

3. Repository talks to MongoDB:
- MongoRepository handles all CRUD operations (find, save, delete, etc.) without you writing queries. 
- It returns a List<Film> to the service.

4. Response goes back up:
- FilmController wraps the data in ResponseEntity and returns it with HTTP 200 OK.
```

### - POST endpoint

When the browser posts a request to **/api/v1/films/{filmId}**:

**<mark>Step 1</mark> — Insert a new review**
```java
Review review = reviewRepo.insert(new Review(reviewBody));
```

This creates a new **Review** object with the given text.

**MongoRepository** automatically assigns it a unique **_id (ObjectId)** and inserts it into the reviews collection.

The returned review object now has its generated id filled in.

**<mark>Step 2</mark> — Prepare the update for the Film collection**
```java
mongoTemplate.update(Film.class)
```

This tells MongoDB: “We’re going to update the documents belonging to the films collection.”

**<mark>Step 3</mark> — Define which document to target**
```java
.matching(Criteria.where("imdbId").is(imdbId))
```

This builds a query condition:
“Find the film whose imdbId field equals the value provided by the user.”

**<mark>Step 4</mark> — Define what the update does**
```java
.apply(new Update().push("reviewList").value(review))
```

**.apply()** takes an Update object that describes how to modify the document.

```java 
new Update().push("reviewList").value(review)
``` 

The above says: "Take the array field reviewList in this document and append (push) the given review object to it.”

Because of the **@DocumentReference** annotation in the Film class, the MongoDB document only stores the review’s **_id**, not the entire object.
When you query the Film, Spring automatically fetches the referenced Review documents and populates the list for you.

**<mark>Step 5</mark> — Execute the update**
```java
.first();
```

This runs the update for the first film document that matches the criteria.

If there were multiple films with the same imdbId, only one would be updated.

<mark>Analogy with Streams</mark>

```java
mongoTemplate.update(Film.class)
.matching(...)
.apply(...)
.first();
```


is conceptually similar to a fluent stream pipeline:

```java
films.stream()
.filter(f -> f.getImdbId().equals(imdbId))
.map(f -> { f.addReview(review); return f; })
.findFirst();
```

Both use a fluent API (method chaining) to express a query, a transformation, and an action in a single flow.

## MongoDB
- Documentation for Database References from MongoDB:</br>
https://www.baeldung.com/spring-data-mongodb-tutorial?utm_source=chatgpt.com </br>
https://docs.spring.io/spring-data/mongodb/reference/</br>
https://www.mongodb.com/docs/manual/reference/database-references/#:~:text=MongoDB%20applications%20use%20one%20of,sufficient%20for%20most%20use%20cases
