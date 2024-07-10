package com.nsteuerberg.model;

public class Users {
    private long id;
    private String nombre;
    private String direccion;
    private String email;

    // constructores
    public Users(){}

    public Users(String nombre, String direccion, String email) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
    }

    public Users(long id, String nombre, String direccion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
    }

    @Override
    public String toString() {
        return "id = " + id +
                "\n\tnombre='" + nombre + "'" +
                "\n\tdireccion='" + direccion + "'" +
                "\n\temail='" + email + "'";
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


