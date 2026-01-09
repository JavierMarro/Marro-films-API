# Deploying on Render

Spring Boot isn't a native way to deploy the API to production and needs the use of Docker for build and deployment:
https://medium.com/@pmanaktala/deploying-a-spring-boot-application-on-render-4e757dfe92ed

# Setting up CORS
## CORS Configuration

The API includes a global CORS configuration to allow communication with the React frontend during development and after deployment.

Allowed origins include (for now):
- http://localhost:5173 (development)
- https://<netlify-site-to-be-added>.netlify.app (production)

See `WebConfig.java` for implementation details.

https://spring.io/guides/gs/rest-service-cors
https://www.baeldung.com/spring-cors
https://docs.spring.io/spring-boot/reference/web/servlet.html#web.servlet.spring-mvc.cors
https://stackoverflow.com/questions/78136098/access-control-allow-origin-alwasy-set-to-in-spring-boot
