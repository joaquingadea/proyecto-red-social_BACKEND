# 🔴 IMPORTANTE 🔴
La web esta desplegada con la capa gratuita de render (para el backend), netlify (para el frontend) y railway (para la base de datos MySQL). Por ende las primeras requests que se le hagan a la web pueden tardar entre 2 y 5 minutos debido a no estar constantemente recibiendo requests se pausan los recursos.

# 🧑‍🤝‍🧑 Simulación de Red Social

Este proyecto consiste en el backend de una aplicación que simula una red social, desarrollado con el objetivo de demostrar el diseño, la arquitectura y la seguridad de una API REST utilizando Spring Boot y Spring Security.

El foco principal del proyecto está puesto en el backend. El frontend es mínimo y fue desarrollado únicamente para consumir la API y demostrar su funcionamiento.

# 📌 Descripción general

La aplicación permite a los usuarios registrarse e iniciar sesión, gestionando la seguridad mediante Basic Authentication y JWT (JSON Web Token) bajo un enfoque stateless.

Las contraseñas se almacenan en la base de datos hasheadas con BCrypt, garantizando buenas prácticas de seguridad.
Una vez autenticado, el usuario recibe un JWT, el cual debe ser enviado en cada request posterior para acceder a los recursos protegidos.

En términos funcionales, la aplicación permite:

- Registrarse e iniciar sesión

- Enviar mensajes públicos

- Editar y eliminar mensajes propios

- Visualizar el historial de mensajes del usuario

- Gestionar roles y permisos (según el tipo de usuario)

# 🔐 Seguridad y roles

Todos los usuarios nuevos inician con el rol USER

Los usuarios con rol USER pueden:

- Crear mensajes

- Editar y eliminar sus propios mensajes

- Visualizar su historial de mensajes

Los usuarios con rol ADMIN pueden:

- Asignar el rol de administrador a otros usuarios

- Crear nuevos roles y permisos

- Eliminar mensajes de otros usuarios

La autorización se basa en roles y permisos, los cuales son evaluados por Spring Security mediante GrantedAuthority.

# 🎯 Objetivos del proyecto

Implementar una API REST segura

Aplicar autenticación stateless con JWT

Modelar usuarios, roles y permisos

Comprender y aplicar el flujo interno de Spring Security

Separar responsabilidades entre controller, service y security

Simular un entorno real de despliegue en la nube

# 🛠️ Tecnologías utilizadas
BACKEND

Java 17+

Spring Boot

Spring Web

Spring Data JPA (Hibernate)

Spring Security

JWT (JSON Web Token)

MySQL

Maven

FRONTEND

HTML5

CSS3

JavaScript (Vanilla JS)

INFRAESTRUCTURA Y DESPLIEGUE

Render → Backend

Netlify → Frontend

Railway → Base de datos MySQL
