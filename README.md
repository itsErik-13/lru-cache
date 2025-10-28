# API de Caché LRU de Alto Rendimiento con Spring Boot

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-6DB33F?style=for-the-badge&logo=spring)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven)
![Swagger](https://img.shields.io/badge/Swagger%20(OpenAPI)-3.0-85EA2D?style=for-the-badge&logo=swagger)

Este proyecto es una API RESTful construida con Java y Spring Boot que expone una implementación de una caché **LRU (Least Recently Used)**.

El núcleo del proyecto es una implementación de `LRUCache` desde cero, que demuestra el uso combinado de un **HashMap** y una **Lista Doblemente Enlazada** para lograr operaciones de `get` y `put` en tiempo de complejidad **O(1)**.

## 🎯 Características Principales

* **Lógica de LRU Eficiente:** Implementación pura de la estructura de datos LRU, gestionada como un `@Service` de Spring.
* **API RESTful:** Endpoints claros (`GET`, `POST`) para interactuar con la caché.
* **Documentación Interactiva:** Integración automática con **Swagger (OpenAPI)** para probar la API directamente desde el navegador.

## 🛠️ Estructuras de Datos Utilizadas

El rendimiento O(1) se logra mediante la combinación de dos estructuras de datos:

1.  **`HashMap<Integer, Node>`:** Permite el acceso instantáneo (O(1)) a cualquier nodo de la caché usando su clave.
2.  **Lista Doblemente Enlazada (Custom `Node` class):** Mantiene el orden de uso. El nodo más reciente está en la cabeza (`head`) y el menos reciente en la cola (`tail`). Esto permite mover y eliminar nodos en tiempo O(1).

---

## 🚀 Cómo Ejecutar Localmente

### Prerrequisitos

* **Java JDK 17** (o superior)
* **Git**
* **Maven** (o usar el wrapper `mvnw` incluido)

### Pasos

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/tu-usuario/lru-cache-api.git](https://github.com/tu-usuario/lru-cache-api.git)
    cd lru-cache-api
    ```

2.  **Ejecutar la aplicación con el wrapper de Maven:**
    * En Windows:
        ```bash
        .\mvnw.cmd spring-boot:run
        ```
    * En macOS/Linux:
        ```bash
        ./mvnw spring-boot:run
        ```

La API se iniciará en `http://localhost:8081`.

### 🧪 Ejecutar las Pruebas

Para verificar que la lógica de `LRUCache` funciona correctamente, puedes ejecutar los tests de JUnit 5:

* En Windows:
    ```bash
    .\mvnw.cmd test
    ```
* En macOS/Linux:
    ```bash
    ./mvnw test
    ```

---

## 📖 Documentación y Uso de la API (Swagger)

Una vez que la aplicación esté en marcha, puedes acceder a la documentación interactiva de Swagger UI en tu navegador:

➡️ **[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)**

Desde esta página, puedes probar los endpoints `GET` y `POST` directamente.

### Endpoints

#### `POST /api/cache`
Añade o actualiza un valor en la caché.

* **Request Body:**
    ```json
    {
      "key": 1,
      "value": 100
    }
    ```

#### `GET /api/cache/{key}`
Obtiene un valor de la caché usando su clave.

* **Ejemplo de Petición:**
    `GET http://localhost:8081/api/cache/1`

* **Respuesta Exitosa (200 OK):**
    ```json
    100
    ```

* **Respuesta de Fallo (404 Not Found):**
    (Se devuelve si la clave no existe en la caché)
