package com.proyectopokemonadt.clasesBasicas;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa un torneo en el sistema. Un torneo tiene un nombre,
 * una región asociada, un responsable, y una lista de participantes.
 * También cuenta con puntos que se otorgan por cada victoria en el torneo.
 * 
 * @author raullg97
 */
public class Torneo implements Serializable {
    private int id; // ID único del torneo
    private String nombre; // Nombre del torneo
    private char codRegion; // Código de la región a la que pertenece el torneo
    private float puntosVictoria; // Puntos asignados por cada victoria
    private long idResponsable; // ID del entrenador responsable del torneo
    private ArrayList<Entrenador> participantes = new ArrayList<>(); // Lista de entrenadores participantes

    /**
     * Constructor que inicializa el nombre, el código de región y los puntos de
     * victoria
     * para el torneo.
     *
     * @param nombre         Nombre del torneo.
     * @param codRegion      Código de la región del torneo.
     * @param puntosVictoria Puntos otorgados por cada victoria en el torneo.
     */
    public Torneo(String nombre, char codRegion, float puntosVictoria) {
        this.nombre = nombre;
        this.codRegion = codRegion;
        this.puntosVictoria = puntosVictoria;
    }

    /**
     * Método para inscribir un entrenador en el torneo, agregándolo a la lista de
     * participantes.
     *
     * @param entrenador Entrenador a inscribir.
     */
    public void inscribir(Entrenador entrenador) {
        participantes.add(entrenador);
    }

    /**
     * Devuelve el ID del torneo.
     *
     * @return ID del torneo.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el ID del torneo.
     *
     * @param id ID único del torneo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece el ID del responsable del torneo.
     *
     * @param idResponsable ID del responsable.
     */
    public void setIdResponsable(long idResponsable) {
        this.idResponsable = idResponsable;
    }

    /**
     * Devuelve la lista de entrenadores participantes en el torneo.
     *
     * @return Lista de participantes.
     */
    public ArrayList<Entrenador> getParticipantes() {
        return participantes;
    }

    /**
     * Establece la lista de entrenadores participantes.
     *
     * @param participantes Lista de entrenadores a inscribir en el torneo.
     */
    public void setParticipantes(ArrayList<Entrenador> participantes) {
        this.participantes = participantes;
    }

    /**
     * Devuelve el nombre del torneo.
     *
     * @return Nombre del torneo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del torneo.
     *
     * @param nombre Nombre del torneo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el ID del responsable del torneo.
     *
     * @return ID del responsable.
     */
    public long getIdResponsable() {
        return idResponsable;
    }

    /**
     * Devuelve el código de la región del torneo.
     *
     * @return Código de la región.
     */
    public char getCodRegion() {
        return codRegion;
    }

    /**
     * Establece el código de la región a la que pertenece el torneo.
     *
     * @param codRegion Código de la región.
     */
    public void setCodRegion(char codRegion) {
        this.codRegion = codRegion;
    }

    /**
     * Devuelve los puntos otorgados por cada victoria en el torneo.
     *
     * @return Puntos por victoria.
     */
    public float getPuntosVictoria() {
        return puntosVictoria;
    }

    /**
     * Establece los puntos que se otorgan por cada victoria en el torneo.
     *
     * @param puntosVictoria Puntos otorgados por victoria.
     */
    public void setPuntosVictoria(float puntosVictoria) {
        this.puntosVictoria = puntosVictoria;
    }
}
