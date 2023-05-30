# Demo api rest springboot

This project is a Spring Boot application that provides a REST endpoint for querying pricing information in an memory database.

# How to run
Execute the next maven command:
```
mvn Springboot:run
```

The project will be launch in http://localhost:8080/

## Swagger-UI:

The project have a Swagger-UI at http://localhost:8080/swagger-ui.html, you can try the REST API there.

## Request example:
URL: http://localhost:8080/api/price?date=2020-06-14T10%3A00%3A00.00Z&productId=35455&brandId=1

Request as JSON:
````json
{
  "date": "2020-06-14T10:00:00.00Z",
  "productId": 35455,
  "brandId": 1
}
````
