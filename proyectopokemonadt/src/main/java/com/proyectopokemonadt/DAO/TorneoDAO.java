package com.proyectopokemonadt.DAO;

import java.util.List;

import com.proyectopokemonadt.ENTIDADES.Torneo;

public interface TorneoDAO {
    public void crearTorneo();
    public void eliminarTorneoPorId(int idTorneo);
    public void actualizarTorneoPorId(int idTorneo);
    public Torneo obtenerTorneoPorId(int idTorneo);
    public List<Torneo> obtenerTodosLosTorneos();
}
