This application is covered below topics:

1. Basic spring boot application
2. Excluding the API Version controller class
3. Enabled Actuator, check http://localhost:8080/actuator/info
4. Enabled IN-Memory DB like H2. use below url for DB access,
   http://localhost:8080/h2-console/
5. Created schema & data sql with user table & few records data
6. Insert query, insert into user values('1','malya@gmail.com','test123','Admin','malya')
7. get all user details with, http://localhost:8080/api/user
8. Added profiles like dev & prod
9. schema & data will load based on plantform declaration in properties file, spring.datasource.platform=h2
10. Added runners to insert initial data.
11. Validated duplicate mappings in different controller classes.(IllegalStateException: Ambiguous mapping)
    If mapping is different then server will start(like @GetMapping("/{userId}") & @GetMapping("/{id}"))
    But it will fail while sending the request for getUser because handler dont know which controker to send this
    request.
12. Added lombok and SL4J log for log messages instead off logger.
13. If not define db details then it will take testdb for h2 database.
14. Added flyway migration for prod and without flyway for dev. just disable it by using enable flag.
15. Added hazelcast for dev and default cache for dev profile.
16. CURL request with execution time:
    1. curl -o /dev/null -s -w 'Total: %{time_total}s\n\n'  http://localhost:8080/api/user
17. 