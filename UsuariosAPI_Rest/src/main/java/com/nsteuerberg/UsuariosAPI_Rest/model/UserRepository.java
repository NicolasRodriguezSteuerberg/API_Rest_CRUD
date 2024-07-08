package com.nsteuerberg.UsuariosAPI_Rest.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// tenemos que poner la clase y el tipo de la clave primaria
// también existe JpaRepository (para usar mas cosas que crud)
@Repository
public interface UserRepository extends CrudRepository<Usuario, Long> {

}
