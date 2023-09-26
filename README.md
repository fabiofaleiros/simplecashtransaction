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

- POST localhost:8080/users
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
	"id": 3,
	"firstName": "Joao",
	"lastName": "Siqueira",
	"document": "1234563",
	"email": "j@ffs.com",
	"password": "senha",
	"balance": 200,
	"userType": "ENTERPRISE"
}
```

- GET localhost:8080/users
```
HTTP/1.1 200 OK
Content-Type: application/json

[
    {
		"id": 3,
		"firstName": "Joao",
		"lastName": "Siqueira",
		"document": "1234563",
		"email": "j@ffs.com",
		"password": "senha",
		"balance": 200.00,
		"userType": "ENTERPRISE"
	}
]
```
- POST localhost:8080/transactions
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
	"id": 4,
	"amount": 10,
	"sender": {
		"id": 1,
		"firstName": "Fabio",
		"lastName": "Siqueira",
		"document": "1234561",
		"email": "fs@ffs.com",
		"password": "senha",
		"balance": 20.00,
		"userType": "PERSONAL"
	},
	"receiver": {
		"id": 2,
		"firstName": "Fran",
		"lastName": "Siqueira",
		"document": "1234562",
		"email": "ff@ffs.com",
		"password": "senha",
		"balance": 10.00,
		"userType": "PERSONAL"
	},
	"timestampTransaction": "2023-09-17T01:13:51.3366497"
}
```

- GET localhost:8080/transactions
```
HTTP/1.1 200 OK
Content-Type: application/json

[
	{
		"id": 1,
		"amount": 10.00,
		"sender": {
			"id": 2,
			"firstName": "Fran",
			"lastName": "Siqueira",
			"document": "1234562",
			"email": "ff@ffs.com",
			"password": "senha",
			"balance": 10.00,
			"userType": "PERSONAL"
		},
		"receiver": {
			"id": 1,
			"firstName": "Fabio",
			"lastName": "Siqueira",
			"document": "1234561",
			"email": "fs@ffs.com",
			"password": "senha",
			"balance": 20.00,
			"userType": "PERSONAL"
		},
		"timestampTransaction": "2023-09-17T01:12:02.531498"
	}
]
```