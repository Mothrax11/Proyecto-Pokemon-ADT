package com.proyectopokemonadt.DAO;

import java.util.List;

import com.proyectopokemonadt.DTO.CombateDTO;
import com.proyectopokemonadt.ENTIDADES.Combate;

public interface CombateDAO {
    public boolean crearCombate(CombateDTO combatedto);
    public boolean eliminarCombatePorId(int id);
    public boolean actualizarCombate(int idCombate, Combate combate);
    public Combate obtenerCombatePorId(int id);
    public List<Combate> obtenerTodosLosCombates();
    public List<Combate> obtenerTodosLosCombatesPorIdTorneo(int idTorneo);
}
