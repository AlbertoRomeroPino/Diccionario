# Diccionario API

## Descripción

Este proyecto es una API desarrollada en **Spring Boot** para gestionar un diccionario de palabras y sus definiciones. La API permite almacenar, consultar y gestionar palabras junto con sus respectivas definiciones.

## Base de Datos

Se utiliza MySQL como sistema de base de datos, y su estructura consta de dos tablas principales:

### Creación de la Base de Datos

```sql
CREATE DATABASE IF NOT EXISTS diccionariodb;
USE diccionariodb;
```

### Tabla `palabra`

Almacena las palabras y su categoría gramatical.

```sql
CREATE TABLE palabra (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    termino VARCHAR(255) NOT NULL,
    categoria_gramatical VARCHAR(50) NOT NULL
);
```

### Tabla `definicion`

Guarda las definiciones asociadas a las palabras, incluyendo ejemplos.

```sql
CREATE TABLE definicion (
    id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    descripcion TINYTEXT NOT NULL,
    ejemplo TINYTEXT NOT NULL,
    palabra_id INT(11) NOT NULL,
    FOREIGN KEY (palabra_id) REFERENCES palabra(id) ON DELETE CASCADE
);
```

## Endpoints de la API

### Funcionalidades de `/palabra`

| Método | Endpoint        | Descripción                                    |
|--------|-----------------|------------------------------------------------|
| **GET** | `/palabra`      | Obtener todas las palabras sin sus definiciones |
| **GET** | `/palabra/{id}` | Obtener una palabra por ID sin sus definiciones |
| **POST** | `/palabra`      | Crear una nueva palabra                        |
| **PUT** | `/palabra{id}`  | Actualizar una palabra existente               |
| **DELETE** | `/palabra/{id}` | Eliminar una palabra por ID                    |

---

### Funcionalidades de `/definicion`

| Método | Endpoint          | Descripción |
|--------|-------------------|-------------|
| **DELETE** | `/definicion/{id}`| Eliminar una definición por ID |
| **GET** | `/definicion/{id}` | Obtener las definiciones de una palabra |
| **POST** | `/definicion/{id}` | Crear una definición para una palabra |

---

### Funcionalidades adicionales

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| **GET** | `/palabra/inicial/{letra}` | Obtener palabras por letra inicial |
| **GET** | `/palabra/categoria/{categoria}` | Obtener palabras por categoría |
| **POST** | `/palabra/con-definicion` | Crear una palabra con su definición |

## Tecnologías Utilizadas

- **Spring Boot**: Framework para la creación de la API REST.
- **Swagger UI**: Herramienta para documentar y probar la API.
- **MySQL**: Base de datos utilizada para almacenar las palabras y definiciones.

## Documentación de la API

Se ha implementado **Swagger UI** para facilitar la visualización y prueba de los endpoints de la API. Para acceder a la documentación, una vez que el servidor esté en ejecución, dirígete a:

```
http://localhost:8080/swagger-ui.html
```

## Instalación y Configuración

1. Clona el repositorio del proyecto.
2. Configura la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/diccionariodb
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Accede a los endpoints a través de Swagger UI o Postman.

## Creador

Alberto Romero Pino