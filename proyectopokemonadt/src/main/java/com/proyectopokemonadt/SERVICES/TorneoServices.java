package com.proyectopokemonadt.SERVICES;

import com.proyectopokemonadt.DAO.TorneoDAO;
import com.proyectopokemonadt.DAO.TorneoDAOImplementacion;
import com.proyectopokemonadt.DTO.TorneoDTO;
import com.proyectopokemonadt.ENTIDADES.Torneo;
import com.proyectopokemonadt.complementarias.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TorneoServices {

    private TorneoDAOImplementacion torneoDAOImplementacion;

    private static TorneoServices instancia;
    private DataSource dataSource = DBConnection.getMySQLDataSource();

    public static TorneoServices getInstancia(DataSource dataSource) {
        if(instancia == null ){
            instancia = new TorneoServices(dataSource);
        }
        return instancia;
    }

    public TorneoServices(DataSource dataSource) {
        this.dataSource = dataSource;
        this.torneoDAOImplementacion = new TorneoDAOImplementacion();
        
    }

    public boolean crearTorneo(TorneoDTO torneoDTO) {
        Torneo torneoEntidad = mapearDTOAEntidad(torneoDTO);
        return torneoDAOImplementacion.crearTorneo(torneoEntidad);
    }

    public boolean eliminarTorneoPorId(int id) {
        return torneoDAOImplementacion.eliminarTorneoPorId(id);
    }

    public boolean actualizarTorneoPorId(int id, TorneoDTO torneoDTO) {
        Torneo torneoEntidad = mapearDTOAEntidad(torneoDTO);
        return torneoDAOImplementacion.actualizarTorneoPorId(id, torneoEntidad);
    }

    public TorneoDTO obtenerTorneoPorId(int id) {
        Torneo torneoEntidad = torneoDAOImplementacion.obtenerTorneoPorId(id);
        if(torneoEntidad != null) {
            return mapearEntidadADTO(torneoEntidad);
        } else {
            return null;
        }
    }

    private Torneo mapearResultSetATorneo(ResultSet resultSet) throws SQLException {
        Torneo torneo = new Torneo();
        torneo.setId(resultSet.getInt("id"));
        torneo.setNombre(resultSet.getString("nombre"));
        torneo.setCodRegion(resultSet.getString("codRegion").charAt(0));
        torneo.setPuntosVictoria(resultSet.getInt("puntosVictoria"));
        return torneo;
    }
    public List<Torneo> obtenerTodosLosTorneos() {
        List<Torneo> torneos = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM torneo");
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Torneo torneo = new Torneo();
                torneo.setId(rs.getInt("id"));
                torneo.setNombre(rs.getString("nombre"));
                torneo.setCodRegion(rs.getString("codRegion").charAt(0));
                torneo.setPuntosVictoria(rs.getInt("puntosVictoria"));
                torneos.add(torneo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return torneos;
    }

    public List<Torneo> obtenerTodosLosTorneosToString() {
        List<Torneo> torneos = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM torneo");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Torneo torneo = new Torneo();
                torneo.setId(rs.getInt("id"));
                torneo.setNombre(rs.getString("nombre"));
                torneo.setCodRegion(rs.getString("codRegion").charAt(0));
                torneo.setPuntosVictoria(rs.getInt("puntosVictoria"));
                torneos.add(torneo);
            }

            for (int k = 0; k < torneos.size(); k++) {
                System.out.println(k + " - " + torneos.get(k).getNombre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return torneos;
    }

    public boolean comprobarTorneoExiste(String codRegion, String nombre){
            return TorneoDAOImplementacion.getInstancia().obtenerTorneoRegionNombre(codRegion, nombre) != null;
    }

    private Torneo mapearDTOAEntidad(TorneoDTO torneoDTO) {
        Torneo torneoEntidad = new Torneo();
        torneoEntidad.setId(torneoDTO.getId());
        torneoEntidad.setNombre(torneoDTO.getNombre());
        torneoEntidad.setCodRegion(torneoDTO.getCodRegion());
        torneoEntidad.setPuntosVictoria(torneoDTO.getPuntosVictoria());
        return torneoEntidad;
    }

    private TorneoDTO mapearEntidadADTO(Torneo torneoEntidad) {
    return new TorneoDTO(
            torneoEntidad.getId(),
            torneoEntidad.getNombre(),
            torneoEntidad.getCodRegion(),
            torneoEntidad.getPuntosVictoria());
    }
}
