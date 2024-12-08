package com.proyectopokemonadt.DAO;

import java.util.List;

import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.ENTIDADES.Entrenador;
import com.proyectopokemonadt.ENTIDADES.Torneo;

public interface EntrenadorDAO {
    public boolean crearEntrenador(Entrenador entrenadorEntidad);
    public boolean actualizarEntrenadorPorId(int idEntrenador, Entrenador entrenadorEntidad);
    public boolean eliminarEntrenadorPorId(int id);
    public Entrenador obtenerEntrenadorPorId(int id);
    public List<Entrenador> obtenerTodosLosEntrenadores();
    public List<Combate> obtenerCombatesPorEntrenador(int idEntrenador);
    public List<Torneo> obtenerTorneosPorEntrenador(int idEntrenador);
}
