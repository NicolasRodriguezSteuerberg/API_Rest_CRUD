package com.nsteuerberg.UsuariosAPI_Rest.Controller;

import com.nsteuerberg.UsuariosAPI_Rest.model.Usuario;
import com.nsteuerberg.UsuariosAPI_Rest.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("/delete/{id}")
    public Boolean deleteUserById(@PathVariable("id") long id){
        try {
            Optional<Usuario> u = getUserById(id);
            userRepository.delete(u.get());
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
