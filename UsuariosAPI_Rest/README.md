# CRUD REST API
Creación de api rest con spring boot

## Devolver resultados 
Es una buena práctica devolver ResponseEntity en lugar de solo el objeto, ya que ResponseEntity nos permite devolver un objeto con un código de estado HTTP y encabezados personalizados.
Esto nos permite que el cliente pueda tener una mejor comprensión de lo que sucedió en el servidor. 

1. Control de Códigos de Estado HTTP

Puedes especificar explícitamente el código de estado HTTP que se devolverá en la respuesta (como `200 OK`, `404 Not Found`).
De esta manera, el cliente puede saber si la solicitud fue exitosa o no, y actuar en consecuencia.
2. Flexibilidad en el cuerpo de la respuesta

Puedes devolver diferentes tipos de cuerpos, como un objeto, una lista, una cadena, mensajes de error, etc., permitiendo una respuesta más estructurada y semánticamente rica para los clientes de tu API.

3. Manejo de Excepciones

ResponseEntity también es útil para manejar excepciones de manera más estructurada. Puedes devolver respuestas personalizadas en caso de errores (por ejemplo, errores de validación, errores de negocio, etc.) usando los códigos de estado HTTP adecuados.

### Ejemplo de uso de ResponseEntity

```java
@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            if(!userRepository.existsById(id)) {
                return ResponseEntity.ok().body("Usuario eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe en la base de datos");
        }
    }
```