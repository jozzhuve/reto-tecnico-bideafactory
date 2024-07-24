## Reto Tecnico Bidea Factory

### Microservicio basado en spring boot 3.x y Java 17

### Stack:
- Spring boot
- Spring retry
- Postgres
- WebFlux
- Open Feign
- Open Api 3.0
- JPA
- Lombok
- MapStruck
- Docker
- Junit

## Ejecutar mediante comando Spring Boot application
```
mvn spring-boot:run
```
## Construir y ejecutar con docker
```
docker build -t reto-tecnico .
docker run -p 8080:8080 reto-tecnico
```

## Ruta Swagger
```
http://localhost:8080/swagger-ui/index.html
```

Endpoints disponibles:

1- Realiza una reservacion de casa.

| Método | URI       |
|--------|-----------|
|POST| /api/book |

##### Payload Request

```javascript
{
    "id": "14564088-4",
    "name": "Gonzalo",
    "lastname": "Pérez",
    "age": 33,
    "phoneNumber": "56988123222",
    "startDate": "2024-07-24",
    "endDate": "2024-07-24",
    "houseId": "213132",
    "discountCode": "D0542A23"
}
```

##### Payload Response exitoso

```javascript
{
    "code": 200,
    "message": "Book Accepted"
}
```




