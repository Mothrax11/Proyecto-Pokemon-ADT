package com.proyectopokemonadt;

public class Usuario {
    private String nombre;
    private String pass;
    private String nacionalidad;
    private long id;
    private String rol;

    public Usuario(String nombre, String pass, String nacionalidad, long id, String rol) {
        this.id = id;
        this.pass = pass;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass(){
        return pass;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    
}
