package com.proyectopokemonadt.clasesBasicas;

public class Entrenador {
    private long id;
    private String nombre;
    private String nacionalidad;

    public Entrenador (long id, String nombre, String nacionalidad){
        this.id  = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    

}
