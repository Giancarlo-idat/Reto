# Documentación del Backend - Java Spring con Seguridad JWT

Este documento detalla las rutas y permisos configurados en el backend utilizando Java Spring y seguridad JWT, incluyendo la paginación de futbolistas.

## Rutas y Permisos

### Autenticación

- **POST /auth/login**
  - Permite a todos los usuarios autenticarse y obtener un token JWT.
  - Ejemplo de cuerpo de solicitud para el rol por defecto:
    ```json
    {
      "email": "admin@hotmail.com",
      "password": "1234"
    }
    ```
  - El token JWT debe ser enviado en el encabezado `Authorization` para acceder a los endpoints protegidos.

### Usuarios

- **GET /users/**
  - Requiere rol `ROLE_Admin` para listar todos los usuarios.
- **POST /users/**
  - Requiere rol `ROLE_Admin` para crear un usuario nuevo.
- **PUT /users/**
  - Requiere rol `ROLE_Admin` para actualizar un usuario existente.
- **DELETE /users/**
  - Requiere rol `ROLE_Admin` para eliminar un usuario.

### Roles

- **GET /roles/**
  - Requiere rol `ROLE_Admin` para listar todos los roles disponibles.
- **POST /roles/**
  - Requiere rol `ROLE_Admin` para crear un nuevo rol.
- **PUT /roles/**
  - Requiere rol `ROLE_Admin` para actualizar un rol existente.
- **DELETE /roles/**
  - Requiere rol `ROLE_Admin` para eliminar un rol.

### Futbolistas

- **GET /futbolista/**
  - Requiere rol `ROLE_Admin` para listar todos los futbolistas con paginación.
  - Parámetros de paginación: `page` (número de página), `size` (tamaño de la página).
- **POST /futbolista/**
  - Requiere rol `ROLE_Admin` para agregar un nuevo futbolista.
- **PUT /futbolista/**
  - Requiere rol `ROLE_Admin` para actualizar un futbolista existente.
- **DELETE /futbolista/**
  - Requiere rol `ROLE_Admin` para eliminar un futbolista.

### Posiciones de Futbolistas

- **GET /posicion/**
  - Requiere rol `ROLE_Admin` para listar todas las posiciones de futbolistas.
- **POST /posicion/**
  - Requiere rol `ROLE_Admin` para agregar una nueva posición de futbolista.
- **PUT /posicion/**
  - Requiere rol `ROLE_Admin` para actualizar una posición de futbolista existente.
- **DELETE /posicion/**
  - Requiere rol `ROLE_Admin` para eliminar una posición de futbolista.

### Relación entre Futbolistas y Posiciones

- **GET /futbolista-posicion/**
  - Requiere rol `ROLE_Admin` para listar todas las relaciones entre futbolistas y posiciones.
- **POST /futbolista-posicion/**
  - Requiere rol `ROLE_Admin` para establecer una nueva relación entre futbolistas y posiciones.
- **PUT /futbolista-posicion/**
  - Requiere rol `ROLE_Admin` para actualizar una relación existente entre futbolistas y posiciones.
- **DELETE /futbolista-posicion/**
  - Requiere rol `ROLE_Admin` para eliminar una relación entre futbolistas y posiciones.

## Configuración Adicional

Este backend utiliza Spring Security junto con JWT para gestionar la autenticación y autorización de los usuarios. El rol por defecto para autenticarse y obtener el token JWT es:

```json
{
  "email": "admin@hotmail.com",
  "password": "1234"
}
