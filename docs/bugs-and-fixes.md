# Encountered bugs and fixes

1. MongoDB cluster not connecting. Message:</br>
```text
1. 1288500402752:error:10000438:SSL routines:OPENSSL_internal:TLSV1_ALERT_INTERNAL_ERROR:../../third_party/boringssl/src/ssl/tls_record.cc:486:SSL alert number 80
```
Solution: https://stackoverflow.com/questions/77091330/how-to-connect-to-mi-mongodb-compass

2. Render env variables needed from MongoDB + Render displaying a 500 status after build and deployment due to IP allowed access: </br>
```text
at java.base/java.lang.Thread.run(Unknown Source) ~[na:na]
```
Solution: https://render.com/docs/connect-to-mongodb-atlas

```text
javax.net.ssl.SSLException: (internal_error) Received fatal alert: internal_error
```
Solution: https://www.mongodb.com/community/forums/t/cant-connect-to-mongodb-atlas-from-render-web-hosted-app/192110/5

```text
could not find a valid docker environment. please see logs and check configuration
```
Partly solution: https://stackoverflow.com/questions/61108655/test-container-test-cases-are-failing-due-to-could-not-find-a-valid-docker-envi
https://github.com/testcontainers/testcontainers-java/issues/2431