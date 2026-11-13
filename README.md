# Actividad 4 - API REST con Spring Security y JWT

**Erandi Camila Ruíz Jiménez**

## Descripción
API REST pura (sin vistas) para la gestión de estudiantes, con autenticación real basada en JWT (JSON Web Tokens), validación de datos de entrada con DTOs y protección de endpoints.

## Evidencias del Proyecto

### 1. Registro de Usuario
![Registro](img/captura_registro.png)

### 2. Login y Generación de Token
![Login](img/captura_login.png)

### 3. Validación de Datos (Error 400)
Petición con correo inválido rechazada por `@Valid`.
![Validación](img/captura_400.png)

### 4. Acceso Denegado sin Token (Error 401)
Petición al CRUD sin el Bearer Token.
![Sin Token](img/captura_401.png)

### 5. CRUD Protegido con Token (200 OK / 201 Created)
Peticiones exitosas enviando el token en el header `Authorization`.
![CRUD](img/captura_crud.png)

## Enlaces de Entrega
*   **Repositorio GitHub:** [https://github.com/camicrts5-hash/ECRJact4_t4](https://github.com/camicrts5-hash/ECRJact4_t4)
*   **API en VPS:** [http://18.218.44.223:8089](http://18.218.44.223:8089)
*   **Colección de Bruno:** Incluida en la carpeta `/bruno` de este repositorio.