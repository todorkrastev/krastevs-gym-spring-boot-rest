# Krastev's Gym

## Description

This repository is a RESTful API consumed by
the [Krastev's Gym App](https://github.com/todorkrastev/krastevs-gym-spring-boot-thymeleaf). The API is built with
Spring Boot and uses Spring
Data JPA to interact with a MySQL database. The API is secured with Spring Security and uses JWT tokens for
authentication. The API is documented with Swagger.

## Technologies Used

* [Spring Framework](https://docs.spring.io/spring-framework/reference/index.html)
* [Spring Boot](https://docs.spring.io/spring-boot/documentation.html)
* [Spring Data](https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#reference)
* [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
* [MySQL](https://dev.mysql.com/doc/refman/8.4/en/)
* [Swagger](https://swagger.io/docs/)

## API Endpoints

### Activities

* `GET /api/v1/activities/all` - Retrieve all activities
* `GET /api/v1/activities/{id}` - Retrieve activity by ID
* `POST /api/v1/activities/create` - Create a new activity
* `PUT /api/v1/activities/{id}` - Update an activity by ID
* `DELETE /api/v1/activities/{id}` - Delete an activity by ID

## Swagger

Swagger documentation is available at:

* [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

## License

The project is licensed under the terms of the Creative Commons Legal Code CC0 1.0 Universal. See the `LICENSE` file for
details.