package com.proyectopokemonadt.clasesBasicas;

import java.util.ArrayList;

public class Torneo {
    private int id;
    private String nombre;
    private char codRegion;
    private float puntosVictoria;
    private long idResponsable;
    private ArrayList<Entrenador> participantes = new ArrayList<>();

    public Torneo (String nombre, char codRegion, float puntosVictoria) {
            this.nombre = nombre;
            this.codRegion = codRegion;
            this.puntosVictoria = puntosVictoria;
    }

    public void inscribir(Entrenador entrenador){
        participantes.add(entrenador);
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdResponsable(long idResponsable) {
        this.idResponsable = idResponsable;
    }

    public ArrayList<Entrenador> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Entrenador> participantes) {
        this.participantes = participantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdResponsable() {
        return idResponsable;
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
