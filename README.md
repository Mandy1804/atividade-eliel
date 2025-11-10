AMANDA RODRIGUES CAMARGO----
ADS 4S NOTURNO

RA:24440170-2


para testar no http://localhost:8081/swagger-ui/index.html#/ 
POST  / aluno

coloque
 {
    "id": 2,
    "nome": "amanda",
    "matricula": "1256",
    "status": "ATIVO",
    "curso": null
  },


-------------------------------------------------------------------------------------------------

ATIVIDADE PRÁTICA ELIEL NASCIMENTO — CRUD *RESTful com Spring Boot
Objetivo
Desenvolver uma aplicação RESTful utilizando Java + Spring Boot, implementando operações CRUD, relacionamento entre entidades, enum, 
documentação com Swagger e versionamento no GitHub.

Requisitos Técnicos

Criar um projeto Spring Boot com as dependências:

Spring Web

Spring Data JPA

Springdoc OpenAPI (Swagger UI)

<dependencies>
    <!-- Springdoc OpenAPI (Swagger UI) -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.5.0</version> <!-- verifique a versão mais recente -->
    </dependency>
</dependencies>


H2 Database

Criar duas entidades com relacionamento:

Aluno

id (Long)

nome (String)

matricula (String)

status (Enum: ATIVO, INATIVO, TRANCADO)

curso (Relacionamento com Curso)


Curso

id (Long)

nome (String)

cargaHoraria (Integer)

alunos (Lista de alunos)


Relacionamento:

Curso possui vários Alunos (@onetomany)

Aluno pertence a um Curso (@manytoone)

Implementar o Enum StatusAluno com os valores:
ATIVO, INATIVO, TRANCADO.

Implementar endpoints RESTfull para cada entidade:

POST – Criar

GET – Listar todos

GET /{id} – Buscar por ID

PUT /{id} – Atualizar
uir
DELETE /{id} – Excl

Configurar o banco H2 em memória e habilitar o con'sole.

Configurar o Swagger para documentação dos endpoints, acessível em:

http://localhost:8080/swagger-ui.html
