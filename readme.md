<h1>Avaliação 01 - Parte Prática da disciplina de <b>Projeto Microsserviços e NoSQL</b></h1>

<h2>Critérios de avaliação:</h2>

<p>Deve seguir a estrutura de pastas como a abaixo:</p>

 └── user_api<br>
 ├── controller<br>
 │ └── UserController.java<br>
 ├── models<br>
 │ ├── dto<br>
 │ │ └── UserDTO.java<br>
 │ └── User.java<br>
 ├── repositories<br>
 │ └── UserRepository.java<br>
 ├── services<br>
 │ └── UserService.java<br>
 └── UserApiApplication.java<br>

<p>Endpoitnts user-api (aula 04):</p>
/user<br>
POST user<br>
GET all users<br>
GET findById<br>
GET findByCPF<br>
GET search by name<br>
GET pageable<br>
PATCH/PUT user<br>
DELETE user by id<br>

<p>Endpoints product-api (aula 09):</p>
/category<br>
GET all<br>
POST<br>
PUT /{id}<br>
DELETE /{id}<br>
GET /pageable<br>

/product
GET getAll<br>
GET /{id}<br>
POST<br>
PUT /{id}<br>
DELETE /{id}<br>
GET /pageable<br>
GET /category/{categoryId}<br>
GET /{productIdentifier}<br>

<p>Endpoint shopping-api (aula 09):</p>
/shopping<br>
GET getAll<br>
GET /{id}<br>
POST<br>
GET /shopByUser<br>
GET /shopByDate<br>
GET /{productIdentifier}<br>
GET /shopping/search<br>
GET /shopping/report<br>
