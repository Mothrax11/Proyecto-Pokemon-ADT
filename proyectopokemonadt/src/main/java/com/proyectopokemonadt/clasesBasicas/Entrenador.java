package com.proyectopokemonadt.clasesBasicas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Entrenador implements Serializable {
    private long id;
    private String nombre;
    private String nacionalidad;
    private long puntos;
    private String contraseña;
    private Carnet carnet;
    private LocalDate fechaCreacion = LocalDate.now();
    private ArrayList<Torneo> torneos = new ArrayList<>();

    public Entrenador (String nombre, String pass, String nacionalidad, long id){
        this.nombre = nombre;
        this.contraseña = pass;
        this.nacionalidad = nacionalidad;
        this.id  = id;
        this.carnet = new Carnet(id, fechaCreacion);
    }

    public void añadirTorneo(Torneo torneo){
        torneos.add(torneo);
    }

    public ArrayList<Torneo> getTorneos() {
        return torneos;
    }

    public void setTorneos(ArrayList<Torneo> torneos) {
        this.torneos = torneos;
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
