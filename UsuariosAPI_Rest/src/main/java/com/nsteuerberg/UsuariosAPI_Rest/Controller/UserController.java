package com.nsteuerberg.UsuariosAPI_Rest.Controller;

import com.nsteuerberg.UsuariosAPI_Rest.model.Usuario;
import com.nsteuerberg.UsuariosAPI_Rest.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuarios")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<Usuario> getAllUsers(){
        return (List<Usuario>) userRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Optional<Usuario> getUserById(@PathVariable("id") long id){
        return userRepository.findById(id);
    }

    @PostMapping("/save")
    public Usuario createUser(@RequestBody Usuario u){
        return userRepository.save(u);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") long id, @RequestBody Usuario u){
        Usuario user = userRepository.findById(id).orElse(null);
        if (user != null){
            user.setNombre(u.getNombre());
            user.setDireccion(u.getDireccion());
            user.setEmail(u.getEmail());
            Usuario updatedUser = userRepository.save(user);
            return ResponseEntity.ok().body(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /*
    @PutMapping("/update/{id}")
    public Usuario updateUser(@PathVariable("id") long id, @RequestBody Usuario u){
        Usuario user = userRepository.findById(id)
                .orElse(null);
        if (user != null){
            user.setNombre(u.getNombre());
            user.setDireccion(u.getDireccion());
            user.setEmail(u.getEmail());
            return userRepository.save(user);
        } else {
            return null;
        }
    }
     */

    /**
     * Borra un usuario por su id
     * @param id long: id del usuario
     * @return ResponseEntity<?>: con el mensaje y el estado de la petición
     */
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
    /*
    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            if(!userRepository.existsById(id)) {
                return "Usuario eliminado correctamente";
            } else {
                return "Error al eliminar el usuario";
            }
        } else {
            return "El usuario no existe en la base de datos";
        }
    }
    */

}
