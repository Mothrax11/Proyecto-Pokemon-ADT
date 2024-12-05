package com.proyectopokemonadt.DAO;

import java.util.List;

import com.proyectopokemonadt.ENTIDADES.Carnet;

public interface CarnetDAO {
    public boolean crearCarnet(Carnet carnet);
    public boolean eliminarCarnetPorIdEntrenador(int idEntrenador);
    public boolean actualizarCarnetPorIdEntrenador(Carnet carnet);
    public List<Carnet> obtenerTodosLosCarnetPorIdEntrenador();
    public Carnet obtenerCarnetPorIdEntrenador(int idEntrenador);
}
