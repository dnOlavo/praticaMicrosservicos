<h1>Avaliação 01 - Parte Prática da disciplina de <b>Projeto Microsserviços e NoSQL</b></h1>

<h2>Critérios de avaliação:</h2>

Deve seguir a estrutura de pastas como a abaixo:

 └── user_api
 ├── controller
 │ └── UserController.java
 ├── models
 │ ├── dto
 │ │ └── UserDTO.java
 │ └── User.java
 ├── repositories
 │ └── UserRepository.java
 ├── services
 │ └── UserService.java
 └── UserApiApplication.java

Endpoitnts user-api (aula 04):
/user
POST user
GET all users
GET findById
GET findByCPF
GET search by name
GET pageable
PATCH/PUT user
DELETE user by id

Endpoints product-api (aula 09):
/category
GET all
POST
PUT /{id}
DELETE /{id}
GET /pageable

/product
GET getAll
GET /{id}
POST
PUT /{id}
DELETE /{id}
GET /pageable
GET /category/{categoryId}
GET /{productIdentifier}

Endpoint shopping-api (aula 09):
/shopping
GET getAll
GET /{id}
POST
GET /shopByUser
GET /shopByDate
GET /{productIdentifier}
GET /shopping/search
GET /shopping/report
