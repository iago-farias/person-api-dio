# Person API
API de cadastro e gerenciamento de pessoas, desenvolvida durante um bootcamp da DIO. 

Bibliotecas utilizadas:
- Lombok
- Mapstruct (Mapeamento dos DTO)
- Mockito (Testes Unitários)
- JUnit (Testes Unitários)
- AssertJ (Testes Unitários)
- Swagger (Documentação)
- h2 database


## Endpoints

### GET

/api/v1/person

Parâmetros

Nenhum parâmetro

Respostas
- Código 200
  ~~~javascript
  [
    {
      "birthDate": "string",
      "cpf": "string",
      "firstName": "string",
      "id": 0,
      "lastName": "string",
      "phones": [
        {
          "id": 0,
          "number": "string",
          "type": "HOME"
        }
      ]
    }
  ]
  ~~~

- Código 401

  Unauthorized

- Código 403

  Forbidden 

- Código 404 
	
  Not Found


/api/v1/person/{id}

Parâmetros

- id (Obrigatório). Tipo: int64, path

Respostas
- Código 200
  ~~~javascript
  {
    "birthDate": "string",
    "cpf": "string",
    "firstName": "string",
    "id": 0,
    "lastName": "string",
    "phones": [
      {
        "id": 0,
        "number": "string",
        "type": "HOME"
      }
    ]
  }
  ~~~

- Código 401

  Unauthorized

- Código 403

  Forbidden 

- Código 404 
	
  Not Found

### POST

/api/v1/person

Parâmetros

- personDTO (Obrigatório). Tipo: personDTO, body.

Exemplo:
~~~javascript
  {
  "birthDate": "string",
  "cpf": "string",
  "firstName": "string",
  "lastName": "string",
  "phones": [
    {
      "number": "string",
      "type": "HOME"
    }
  ]
}
~~~

Respostas
- Código 200
  ~~~javascript
  {
    "birthDate": "string",
    "cpf": "string",
    "firstName": "string",
    "id": 0,
    "lastName": "string",
    "phones": [
      {
        "id": 0,
        "number": "string",
        "type": "HOME"
      }
    ]
  }
  ~~~

- Código 401

  Created

- Código 401

  Unauthorized

- Código 403

  Forbidden 

- Código 404 
	
  Not Found

### PUT
/api/v1/person/{id}

Parâmetros
- id (Obrigatório). Tipo: int64, path

- personDTO (Obrigatório). Tipo: personDTO, body

Exemplo:
~~~javascript
  {
  "birthDate": "string",
  "cpf": "string",
  "firstName": "string",
  "lastName": "string",
  "phones": [
    {
      "number": "string",
      "type": "HOME"
    }
  ]
}
~~~

Respostas
- Código 200
  ~~~javascript
  {
    "birthDate": "string",
    "cpf": "string",
    "firstName": "string",
    "id": 0,
    "lastName": "string",
    "phones": [
      {
        "id": 0,
        "number": "string",
        "type": "HOME"
      }
    ]
  }
  ~~~

- Código 401

  Created

- Código 401

  Unauthorized

- Código 403

  Forbidden 

- Código 404 
	
  Not Found


### DELETE

/api/v1/person/{id}

Parâmetros
- id (Obrigatório). Tipo: int64, path

Respostas
- Código 200
  ~~~javascript
  {
    "birthDate": "string",
    "cpf": "string",
    "firstName": "string",
    "id": 0,
    "lastName": "string",
    "phones": [
      {
        "id": 0,
        "number": "string",
        "type": "HOME"
      }
    ]
  }
  ~~~

- Código 204

  No Content

- Código 401

  Unauthorized

- Código 403

  Forbidden 
