package com.proyectopokemonadt.DAO;

import java.util.List;

import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.ENTIDADES.Entrenador;

public interface CombateEntrenadorDAO {
    public List<Combate> obtenerTodosCombatesPorIdEntrenador(int idEntrenador);
    public List<Entrenador> obtenerEntrenadoresPorIdCombate(int idCombate);
    
}
