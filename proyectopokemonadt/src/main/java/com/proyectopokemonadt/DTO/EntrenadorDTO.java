package com.proyectopokemonadt.DTO;

import java.util.List;

import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.ENTIDADES.Torneo;

public class EntrenadorDTO {
    private int id;
    private String nombre;
    private String nacionalidad;
    private List<Torneo> listaTorneos;
    private List<Combate> listaCombates;
    private float puntos;

    public EntrenadorDTO(int id, String nombre, String nacionalidad, List<Torneo> listaTorneos, List<Combate> listaCombates, float puntos){
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.listaTorneos = listaTorneos;
        this.listaCombates = listaCombates;
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

    public List<Torneo> getListaTorneos() {
        return listaTorneos;
    }

    public void setListaTorneos(List<Torneo> listaTorneos) {
        this.listaTorneos = listaTorneos;
    }

    public List<Combate> getListaCombates() {
        return listaCombates;
    }

    public void setListaCombates(List<Combate> listaCombates) {
        this.listaCombates = listaCombates;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "EntrenadorDTO [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", listaTorneos="
                + listaTorneos + ", listaCombates=" + listaCombates + ", puntos=" + puntos + "]";
    }

    
}


