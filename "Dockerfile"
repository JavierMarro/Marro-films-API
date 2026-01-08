# Stage 1 - Build: Importing JDK and copying required files
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src src

# Copy Maven wrapper
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Set execution permission for the Maven wrapper
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2 - Runtime: Create the final Docker image using Eclipse Temurin as per Spring’s official docs
FROM eclipse-temurin:21-jre
VOLUME /tmp

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080