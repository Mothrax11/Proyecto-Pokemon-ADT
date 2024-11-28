package com.proyectopokemonadt.ENTIDADES;

import java.io.Serializable;

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
    public Entrenador(String nombre, String nacionalidad, long id) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.id = id;
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
     * Establece la nacionalidad del entrenador.
     *
     * @param nacionalidad Nueva nacionalidad del entrenador.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}