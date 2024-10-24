package com.proyectopokemonadt.clasesBasicas;

import com.proyectopokemonadt.Usuario;

public class Torneo {
    private long id;
    private String nombre;
    private char codRegion;
    private float puntosVictoria;
    private String organizador;
    private String nacOrganizador;

    public Torneo (long id, String nombre, char codRegion, float puntosVictoria) {
        
            this.id = id;
            this.nombre = nombre;
            this.codRegion = codRegion;
            this.puntosVictoria = puntosVictoria;
            this.organizador = "admin";
    }

    public long getId() {
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

    public char getCodRegion() {
        return codRegion;
    }

    public void setCodRegion(char codRegion) {
        this.codRegion = codRegion;
    }

    public float getPuntosVictoria() {
        return puntosVictoria;
    }

    public void setPuntosVictoria(float puntosVictoria) {
        this.puntosVictoria = puntosVictoria;
    }
    

}
