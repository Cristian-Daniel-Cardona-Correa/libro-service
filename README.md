# Documentación de Endpoints para Postman

Este proyecto utiliza Java, Spring Boot y Maven. A continuación se describen los endpoints principales para pruebas en Postman.



# Requisitos previos
- Tener JDK 21 o superior.
- Tener Spring Boot 3.5.6 o superior.
- Tener el proyecto clonado y configurado.
- Asegurarse de que el servidor esté corriendo en `http://localhost:8080`.

---


## 1. Endpoint: Obtener todos los libros

- **Método:** GET  
- **URL:** `http://localhost:8080/api/v1/libro-service/libros`
- **Descripción:** Obtiene una lista de todos los libros disponibles.
- **Headers:**
  - `Content-Type: application/json`
- **Body:** No aplica
- **Respuesta esperada:**
  ```json
  {
  "libros" : [
        {
        "id": 1,
        "titulo": "La divina comedia",
        "autor": "Dante Alighieri",
        "editorial": "Editorial Clásica",
        "anoPublicacion": 1320
        },
        {
        "id": 2,
        "titulo": "Cien años de soledad",
        "autor": "Gabriel García Márquez",
        "editorial": "Editorial Sudamericana",
        "anoPublicacion": 1967
        },
    "..."
    ]
  }
  ```
  
## 2. Endpoint: Obtener libro por ID
- **Método:** GET  
- **URL:** `http://localhost:8080/api/v1/libro-service/libros/{id}`
- **Descripción:** Obtiene los detalles de un libro específico por su ID.
- **Headers:**
  - `Content-Type: application/json`
- **Body:** No aplica
- **Parámetros de ruta:**
  - `id`: ID del libro a obtener (ejemplo: 1)
- **Respuesta esperada:**
  ```json
  {
  "libro" : {
    "id": 1,
    "titulo": "La divina comedia",
    "autor": "Dante Alighieri",
    "editorial": "Editorial Clásica",
    "anoPublicacion": 1320
    }
  }
    ```
  
## 3. Endpoint: Crear un nuevo libro
- **Método:** POST  
- **URL:** `http://localhost:8080/api/v1/libro-service/libros`
- **Descripción:** Crea un nuevo libro en la base de datos.
- **Headers:**
  - `Content-Type: application/json`
- **Body:** (raw, JSON)
  ```json
    {
    "titulo": "Harry Potter y la piedra filosofal",
    "autor": "J.K. Rowling",
    "editorial": "Bloomsbury",
    "anoPublicacion": 1997
    }
    ```

- **Respuesta esperada:**
  ```json
  {
    "libro": {
        "id": 11,
        "titulo": "Harry Potter y la piedra filosofal",
        "autor": "J.K. Rowling",
        "editorial": "Bloomsbury",
        "anoPublicacion": 1997
    },
    "mensaje": "El libro ha sido creado con éxito!"
  }
    ```
  
## 4. Endpoint: Actualizar un libro existente
- **Método:** PUT  
- **URL:** `http://localhost:8080/api/v1/libro-service/libros`
- **Descripción:** Actualiza los detalles de un libro existente.
- **Headers:**
  - `Content-Type: application/json`
- **Body:** (raw, JSON)
  ```json
  {
  "id": 1,
  "titulo": "Harry Potter y la cámara secreta",
  "autor": "J.K. Rowling",
  "editorial": "Bloomsbury",
  "anoPublicacion": 1998
  }
    ```
  
- **Respuesta esperada:**
  ```json
  {
    "libro": {
        "id": 1,
        "titulo": "Harry Potter y la cámara secreta",
        "autor": "J.K. Rowling",
        "editorial": "Bloomsbury",
        "anoPublicacion": 1998
    },
    "mensaje": "El libro ha sido actualizado con éxito!"
  }
    ```

## 5. Endpoint: Eliminar un libro
- **Método:** DELETE  
- **URL:** `http://localhost:8080/api/v1/libro-service/libros
- - **Descripción:** Elimina un libro.
- **Headers:**
  - `Content-Type: application/json`
- **Body:** (raw, JSON)
  ```json
  {
  "id": 11
  }
    ```
- **Respuesta esperada:**
- ```json
  {
    "libro": null,
    "mensaje": "El libro ha sido eliminado con éxito!"
  }
    ```
  
## 6. Endpoint: Obtener paginación de libros
- **Método:** GET  
- **URL:** `http://localhost:8080/api/v1/libro-service/libros/page/{NumeroPagina}`
- **Descripción:** Obtiene una lista paginada de libros de 4 en 4.
- **Headers:**
  - `Content-Type: application/json`
- **Body:** No aplica
- **Parámetros de ruta:**
  - `NumeroPagina`: Número de página a obtener (ejemplo: 0)
- **Respuesta esperada:**
  - ```json
    {
    "content" : [
          {
          "id": 1,
          "titulo": "La divina comedia",
          "autor": "Dante Alighieri",
          "editorial": "Editorial Clásica",
          "anoPublicacion": 1320
          },
          {
          "id": 2,
          "titulo": "Cien años de soledad",
          "autor": "Gabriel García Márquez",
          "editorial": "Editorial Sudamericana",
          "anoPublicacion": 1967
          },
      "..."
      ]
    }
    ```
    
---
## Nota personal:
- No me gusta hacer videos.

![Logo](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaNjbD-QCcNOlxvbyeFgZI-NSBdo4wrJ15sQ&s)