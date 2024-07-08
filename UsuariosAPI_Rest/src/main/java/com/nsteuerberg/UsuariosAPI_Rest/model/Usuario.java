package com.nsteuerberg.UsuariosAPI_Rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id // nuestra clave primaria será la que tenga esto encima de el
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera automaticamente el id
    private long id;
    private String nombre;
    private String direccion;
    private String email;

    // constructores
    public Usuario(){}

    public Usuario(String nombre, String direccion, String email) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
    }

    // setters and getters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


