package com.proyectopokemonadt.clasesBasicas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase que representa a un entrenador. Contiene información relevante
 * del entrenador, como su nombre, nacionalidad, puntos acumulados y el carnet
 * asociado.
 * También incluye un historial de torneos en los que el entrenador ha
 * participado.
 * Implementa Serializable para permitir la persistencia de los datos del
 * entrenador.
 * 
 * @author raullg97
 */
public class Entrenador implements Serializable {
    private long id; // ID único del entrenador
    private String nombre; // Nombre del entrenador
    private String nacionalidad; // Nacionalidad del entrenador
    private long puntos; // Puntos acumulados por el entrenador
    private String contraseña; // Contraseña del entrenador
    private Carnet carnet; // Carnet del entrenador
    private LocalDate fechaCreacion = LocalDate.now(); // Fecha de creación del perfil
    private ArrayList<Torneo> torneos = new ArrayList<>(); // Lista de torneos en los que ha participado

    /**
     * Constructor que inicializa los datos básicos del entrenador: nombre,
     * contraseña,
     * nacionalidad e ID. Al crear un nuevo entrenador, también se genera un carnet
     * con la fecha de creación actual.
     *
     * @param nombre       Nombre del entrenador.
     * @param pass         Contraseña del entrenador.
     * @param nacionalidad Nacionalidad del entrenador.
     * @param id           ID único del entrenador.
     */
    public Entrenador(String nombre, String pass, String nacionalidad, long id) {
        this.nombre = nombre;
        this.contraseña = pass;
        this.nacionalidad = nacionalidad;
        this.id = id;
        this.carnet = new Carnet(id, fechaCreacion);
    }

    /**
     * Añade un torneo a la lista de torneos en los que ha participado el
     * entrenador.
     *
     * @param torneo Torneo a añadir.
     */
    public void añadirTorneo(Torneo torneo) {
        torneos.add(torneo);
    }

    /**
     * Devuelve la lista de torneos en los que ha participado el entrenador.
     *
     * @return Lista de torneos.
     */
    public ArrayList<Torneo> getTorneos() {
        return torneos;
    }

    /**
     * Establece la lista de torneos en los que ha participado el entrenador.
     *
     * @param torneos Lista de torneos a asignar.
     */
    public void setTorneos(ArrayList<Torneo> torneos) {
        this.torneos = torneos;
    }

    /**
     * Devuelve el ID único del entrenador.
     *
     * @return ID del entrenador.
     */
    public long getId() {
        return id;
    }

    /**
     * Devuelve el carnet asociado al entrenador.
     *
     * @return Carnet del entrenador.
     */
    public Carnet getCarnet() {
        return carnet;
    }

    /**
     * Establece el carnet del entrenador.
     *
     * @param carnet Nuevo carnet a asignar.
     */
    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }

    /**
     * Devuelve la fecha de creación del perfil del entrenador.
     *
     * @return Fecha de creación del perfil.
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del perfil del entrenador.
     *
     * @param fechaCreacion Nueva fecha de creación.
     */
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Establece el ID del entrenador.
     *
     * @param id Nuevo ID del entrenador.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del entrenador.
     *
     * @return Nombre del entrenador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del entrenador.
     *
     * @param nombre Nuevo nombre del entrenador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la nacionalidad del entrenador.
     *
     * @return Nacionalidad del entrenador.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Devuelve los puntos acumulados por el entrenador.
     *
     * @return Puntos del entrenador.
     */
    public long getPuntos() {
        return puntos;
    }

    /**
     * Devuelve la contraseña del entrenador.
     *
     * @return Contraseña del entrenador.
     */
    public String getPass() {
        return contraseña;
    }

    /**
     * Establece los puntos acumulados del entrenador.
     *
     * @param puntos Nuevos puntos a asignar al entrenador.
     */
    public void setPuntos(long puntos) {
        this.puntos = puntos;
    }

    /**
     * Establece la nacionalidad del entrenador.
     *
     * @param nacionalidad Nueva nacionalidad del entrenador.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}