# Simulación de red social
  Este es el backend realizado para simular una red social, utilizando Spring Security junto con Basic auth y JWT se desarrollo la seguridad del sistema.
  La aplicación te permite registrarte e iniciar sesión, las contraseñas se almacenan en la base de datos (utilizando MySQL como SGDB) hasheadas mediante BCrypt, luego de la autenticación en el formulario de registro/inicio sesión el usuario recibe su JWT y a partir de eso las requests se autentican a través del JWT. 
  En pocas palabras el sitio web te permite registrarte/iniciar sesión, enviar mensajes públicos, editarlos, eliminarlos y visualizar tu historial de mensajes propios. Los usuarios por defecto inician teniendo el rol USER, esto les permite ineractuar de la manera previamente mencionada, los admins son los únicos que pueden asignarle el rol de admin a otros usuarios. Los administradores tienen la posibilidad de generar nuevos roles, permisos y tienen la capacidad de eliminar mensajes de otros usuarios.

# 🔴 IMPORTANTE 🔴
La web esta desplegada con la capa gratuita de render (para el backend), netlify (para el frontend) y railway (para la base de datos MySQL). Por ende las primeras requests que se le hagan a la web pueden tardar entre 2 y 5 minutos debido a no estar constantemente recibiendo requests se pausan los recursos.
