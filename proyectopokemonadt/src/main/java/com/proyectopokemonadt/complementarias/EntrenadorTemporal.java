package com.proyectopokemonadt.complementarias;

public class EntrenadorTemporal {
    private int id;
    private String nombre;
    private String nacionalidad;
    private String contrasena;

    public EntrenadorTemporal(int id, String nombre, String nacionalidad, String contrasena ) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.id = id;
        this.contrasena = contrasena;
    }

    public EntrenadorTemporal() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}
