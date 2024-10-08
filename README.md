# Interview project

Application represents a service for aggregating users data from multiple databases. Application
provides single rest endpoint for selecting data, selected from all databases

### GET /users

Application has declarative configuration for specification of data sources, maximal quality
of data sources is infinite:

### Spring Boot
    .\gradlew build
    .\gradlew build -x test
### Docker Start
    .\gradlew build -x test && docker build -t interview/app .
    docker run -p 8080:8080 interview/app
or

    docker-compose build && docker-compose up

### OpenApi

	http://localhost:8080/swagger-ui/index.html
	http://localhost:8080/v3/api-docs

### Git repository/Readme

# Setup

1. Get multiple databases with users tables
2. Edit application.yaml to provide your DBs connection settings. Example:

```
data-sources:
    - 
        name: data-base-1
        url: jdbc://.....
        table: users
        user: testuser
        password: testpass
        mapping:
            id: user_id
            username: login
            name: first_name
            surname: last_name
    - 
        name: data-base-2
        strategy: postgres
        url: jdbc://.....
        table: user_table
        user: testuser
        password: testpass
        mapping:
            id: ldap_login
            username: ldap_login
            name: name
            surname: surname
```

# IT tests

Tests are build based on tescontainers and
require Docker installation on your system

Application contains IT test for /users endpoint that provides users data from multiple data sources.
Test checks that user are loaded from multiple data sources. 
One more case ads checks for users search filter around
multiple DS.

# TODO

Application can support different strategies for DS setup