package com.proyectopokemonadt.DTO;

import java.util.List;

public class EntrenadorDTO {
    private int id;
    private String nombre;
    private String nacionalidad;
    private float puntos;

    public EntrenadorDTO(int id, String nombre, String nacionalidad, List<TorneoDTO> listaTorneos, List<CombateDTO> listaCombates, float puntos){
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.puntos = puntos;
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

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "EntrenadorDTO [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", puntos="
                + puntos + "]";
    }
}


