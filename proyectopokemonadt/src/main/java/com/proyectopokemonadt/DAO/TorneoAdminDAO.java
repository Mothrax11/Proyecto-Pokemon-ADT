package com.proyectopokemonadt.DAO;

import java.util.List;

import com.proyectopokemonadt.DTO.TorneoAdminDTO;
import com.proyectopokemonadt.ENTIDADES.TorneoAdmin;

public interface TorneoAdminDAO {
    public boolean crearTorneoAdmin(TorneoAdminDTO torneoAdmin);
    public boolean eliminarTorneoAdminPorIdTorneo(int idTorneo);
    public List<TorneoAdmin> obtenerTodosLosTorneosAdmin();
    public List<Integer> obtenerTorneosPorIdAdministrador(int idTorneoAdmin);
    public int obtenerIdEntrenadorPorIdTorneo(int idTorneo);
}
