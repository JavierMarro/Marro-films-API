# CI GitHub Actions documentation

The outcome of the CI implemented covers:

- Event-based CI triggers (push, pull_request)
- Branch targeting (main, develop)
- Java toolchain setup with caching 
- Maven lifecycle usage (verify instead of test)
- Test report publication 
- Non-happy-path handling (if: always())

## Documentation used:

https://docs.github.com/en/actions/tutorials/build-and-test-code/java-with-maven
https://github.com/actions/setup-java
https://github.com/docker/build-push-action
https://docs.docker.com/build/ci/github-actions/
https://www.youtube.com/watch?v=eHZf-gaqAJQ

Adding Docker images to GHCR
https://github.com/orgs/community/discussions/27086
https://github.com/orgs/community/discussions/27086#discussioncomment-3254552
https://github.com/actions/starter-workflows/blob/82c43562976aef3d8f02643e00e979d65993244c/ci/docker-publish.yml