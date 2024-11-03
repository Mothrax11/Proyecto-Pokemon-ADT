package com.proyectopokemonadt.clasesBasicas;

import java.time.LocalDate;

public class Entrenador {
    private long id;
    private String nombre;
    private String nacionalidad;
    private long puntos;
    private String contraseña;
    private Carnet carnet;
    private LocalDate fechaCreacion = LocalDate.now();

    public Entrenador (String nombre, String pass, String nacionalidad, long id){
        this.nombre = nombre;
        this.contraseña = pass;
        this.nacionalidad = nacionalidad;
        this.id  = id;
        this.carnet = new Carnet(id, fechaCreacion);
    }

    public long getId() {
        return id;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public long getPuntos() {
        return puntos;
    }
    public String getPass() {
        return contraseña;
    }

    public void setPuntos(long puntos) {
        this.puntos = puntos;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
