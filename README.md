# COMPARUS Interview project

Application represents a service for aggregating users data from multiple databases. Application
provides single rest endpoint for selecting data, selected from all databases

### GET /users

Application has declarative configuration for specification of data sources, maximal quality
of data sources is infinite:

### Spring Boot

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
Application contains IT test for /users endpoint with multiple in memory H2 data sources.
Test checks that user are loaded from multiple dataSources. One more case ads checks for users search filter around
multiple DS.

# TODO
Application can support different strategies for DS setup