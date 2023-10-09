<h1 align="center">
  Simple Cash Transaction Service
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Youtube&message=@DevCollab&color=8257E5&labelColor=000000" alt="@DevCollab" />
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Challenge" />
</p>

API to manage cash transaction [challenge](https://github.com/PicPay/picpay-desafio-backend).

Video Source [link](https://youtu.be/QXunBiLq2SM?si=3dpJtAyRMjRlPC-5).

## Technologies
 
- [Spring Boot](https://spring.io/projects/spring-boot)

## Adopted practices

- SOLID
- Automated tests
- DTOs
- Dependency injection
- Swagger automatic doc withGeração automática do Swagger com OpenAPI 3
- Entity Auditing to date(create and update)
- JWT

## Initial Considerations

New mocky created to replace the original [one](https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6):
- URL: [link](https://run.mocky.io/v3/da51a6c7-9ad8-475d-8c3b-7c406416a5f8)
- Secret delete: [link](https://designer.mocky.io/manage/delete/da51a6c7-9ad8-475d-8c3b-7c406416a5f8/H6I6tOKVG71HCzHJvtvtCNWlx6LfNth74UGN)

New wiremock created to replace the original [one] (http://o4d9z.mocklab.io/notify):
- URL: [link](https://v4goj.wiremockapi.cloud/notify)
 
## How to load

### Locally
- Clone git [repository](https://github.com/fabiofaleiros/simplecashtransaction)
- Initialize Postgres DB via docker:
```
cd db-docker
docker compose up
```
- Build project:
```
./mvnw clean package
```
- Execute:
```
java -jar simplecashtransaction/target/simplecashtransaction-0.0.1-SNAPSHOT.jar
```

- Link API [localhost:8080](http://localhost:8080).
- Swagger documentation: [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Tool [insomnia](https://insomnia.rest/):

- All requests to /api/* require token received throw /auth/login.

- POST localhost:8080/api/v1/users
```
Request:
{
	"firstName": "Joao",
	"lastName": "Siqueira",
	"password": "senha",
	"document": "1234563",
	"email": "j@ffs.com",
	"userType": "ENTERPRISE",
	"balance": 200
}

HTTP/1.1 201 CREATED
Content-Type: application/json

{
	"firstName": "Joao",
	"lastName": "Siqueira",
	"document": "1234563",
	"email": "j@ffs.com",
	"balance": 200,
	"userType": "ENTERPRISE",
	"role": null,
	"updatedAt": "2023-10-09T18:48:05.9371241"
}
```

- GET localhost:8080/api/v1/users
```
HTTP/1.1 200 OK
Content-Type: application/json

[
    {
        "firstName": "Fran",
        "lastName": "Faleiros",
        "document": "1234562",
        "balance": 200.00,
        "email": "ff@ffs.com",
        "role": "USER",
        "userType": "PERSONAL",
        "updatedAt": "2023-09-26T12:02:17.720958"
    },
    {
        "firstName": "Empresa",
        "lastName": "XPTO 2",
        "document": "1234564",
        "balance": 200.00,
        "email": "exe@ffs.com",
        "role": "USER",
        "userType": "ENTERPRISE",
        "updatedAt": "2023-09-26T17:29:30.935671"
    },
    {
        "firstName": "Fabio",
        "lastName": "Faleiros",
        "document": "1234561",
        "balance": 200.00,
        "email": "admin@ffs.com",
        "role": "ADMIN",
        "userType": "PERSONAL",
        "updatedAt": "2023-10-09T17:34:14.882944"
    }
]
```
- POST localhost:8080/api/v1/transactions
```
Request:
{
	"senderID": 1,
	"receiverID": 2,
	"value": 10
}

HTTP/1.1 201 CREATED
Content-Type: application/json

{
    "value": 10,
    "senderID": 1,
    "receiverID": 4,
    "transactionCode": "3b4ab7fe-906d-414e-9ed0-6c946f3ff9e1"
}
```

- GET localhost:8080/api/v1/transactions
```
HTTP/1.1 200 OK
Content-Type: application/json

[
    {
        "value": 10.00,
        "senderID": 1,
        "receiverID": 4,
        "transactionCode": "3b4ab7fe-906d-414e-9ed0-6c946f3ff9e1"
    }
]
```

- POST localhost:8080/auth/register
```
Request:
{
		"login": "admin@ffs.com",
		"password": "senha",
		"role": "ADMIN"
}

HTTP/1.1 201 CREATED
Content-Type: application/json

{
	"firstName": "Joao",
	"lastName": "Siqueira",
	"document": "1234563",
	"email": "j@ffs.com",
	"balance": 200,
	"userType": "ENTERPRISE",
	"role": null,
	"updatedAt": "2023-10-09T18:48:05.9371241"
}
```

- GET localhost:8080/auth/login
```
HTTP/1.1 200 OK
Content-Type: application/json

{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImFkbWluQGZmcy5jb20iLCJleHAiOjE2OTY4ODM5MTh9.vjRywJfMbflrwBNI8_OSqiFrgLKgTH7GM1nB7O160p0"
}
```