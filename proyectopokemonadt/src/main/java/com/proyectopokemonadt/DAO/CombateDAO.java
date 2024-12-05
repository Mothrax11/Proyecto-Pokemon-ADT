package com.proyectopokemonadt.DAO;

import java.util.List;

import com.proyectopokemonadt.ENTIDADES.Combate;

public interface CombateDAO {
    public boolean crearCombate(Combate combate);
    public boolean eliminarCombatePorId(int id);
    public boolean actualizarCombate(Combate combate);
    public Combate obtenerCombatePorId(int id);
    public List<Combate> obtenerTodosLosCombates();
    public List<Combate> obtenerTodosLosCombatesPorIdTorneo(int id);
}