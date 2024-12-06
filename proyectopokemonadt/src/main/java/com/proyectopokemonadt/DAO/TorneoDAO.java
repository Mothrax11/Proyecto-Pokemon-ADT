package com.proyectopokemonadt.DAO;

import java.util.List;
import com.proyectopokemonadt.ENTIDADES.Torneo;

public interface TorneoDAO {
    public boolean crearTorneo(Torneo torneoEntidad);
    public boolean eliminarTorneoPorId(int idTorneo);
    public boolean actualizarTorneoPorId(int idTorneo, Torneo torneoEntidad);
    public Torneo obtenerTorneoPorId(int idTorneo);
    public List<Torneo> obtenerTodosLosTorneos();
}
