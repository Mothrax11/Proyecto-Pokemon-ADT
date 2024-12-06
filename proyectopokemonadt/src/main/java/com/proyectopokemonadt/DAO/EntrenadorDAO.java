package com.proyectopokemonadt.DAO;

import java.util.List;
import com.proyectopokemonadt.ENTIDADES.Entrenador;

public interface EntrenadorDAO {
    public boolean crearEntrenador(Entrenador entrenadorEntidad);
    public boolean actualizarEntrenadorPorId(int idEntrenador, Entrenador entrenadorEntidad);
    public boolean eliminarEntrenadorPorId(int id);
    public Entrenador obtenerEntrenadorPorId(int id);
    public List<Entrenador> obtenerTodosLosEntrenadores();
}
