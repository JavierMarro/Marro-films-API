# CI GitHub Actions documentation

The outcome of the CI implemented covers:

- Event-based CI triggers (push, pull_request)
- Branch targeting (main, develop)
- Java toolchain setup with caching 
- Maven lifecycle usage (verify instead of test)
- Test report publication 
- Non-happy-path handling (if: always())

## Documentation used:

### Github Actions for CI Builds with Maven
https://docs.github.com/en/actions/tutorials/build-and-test-code/java-with-maven
https://github.com/actions/setup-java
https://github.com/docker/build-push-action
https://docs.docker.com/build/ci/github-actions/
https://www.youtube.com/watch?v=eHZf-gaqAJQ

### Solving @SpringBootTests throwing an error at contextLoads() (due to no access to MongoDB to load the app)
https://java.testcontainers.org/modules/databases/mongodb/
https://docs.spring.io/spring-boot/reference/testing/testcontainers.html
https://github.com/testcontainers/testcontainers-java/tree/main/examples
https://stackoverflow.com/questions/79598084/how-to-define-a-testconfiguration-for-testcontainers
https://www.baeldung.com/java-mongodb-testcontainers (outdated, but good explanation)

### Adding Docker images to GHCR
https://github.com/orgs/community/discussions/27086
https://github.com/orgs/community/discussions/27086#discussioncomment-3254552
https://github.com/actions/starter-workflows/blob/82c43562976aef3d8f02643e00e979d65993244c/ci/docker-publish.yml