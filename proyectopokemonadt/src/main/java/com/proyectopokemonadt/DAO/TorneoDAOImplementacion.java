package com.proyectopokemonadt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.proyectopokemonadt.ENTIDADES.Torneo;

public class TorneoDAOImplementacion implements TorneoDAO {


    private static TorneoDAOImplementacion instancia;
    private DataSource dataSource;

    private TorneoDAOImplementacion(DataSource dataSource) { 
        this.dataSource = dataSource; 
    }

    public static TorneoDAOImplementacion getInstancia(DataSource dataSource) {
        if (instancia == null) {
            instancia = new TorneoDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public boolean crearTorneo(Torneo torneoEntidad) {
        String sql="INSERT INTO torneo (id, nombre, codRegion, puntosVictoria) VALUES (?,?,?,?)";
        String codRegionChar = String.valueOf(torneoEntidad.getCodRegion());
        try (Connection connection = dataSource.getConnection(); 
            PreparedStatement statement = connection.prepareStatement(sql)) { 
            statement.setInt(1, torneoEntidad.getId()); 
            statement.setString(2, torneoEntidad.getNombre()); 
            statement.setString(3, codRegionChar); 
            statement.setFloat(4, torneoEntidad.getPuntosVictoria()); 
            statement.executeUpdate(); 
            return true;
        } catch (SQLException e) { 
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarTorneoPorId(int idTorneo) {
        String sql = "DELETE FROM torneo WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idTorneo);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarTorneoPorId(int idTorneo, Torneo torneoEntidad) {
        String sql = "UPDATE torneo SET nombre = ?, codRegion = ?, puntosVictoria WHERE id = ?"; 
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, torneoEntidad.getNombre());
                    statement.setString(2, String.valueOf(torneoEntidad.getCodRegion()));
                    statement.setFloat(3, torneoEntidad.getPuntosVictoria());
                    statement.execute();
                    return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Torneo obtenerTorneoPorId(int idTorneo) {
       String sql = "SELECT * FROM torneo WHERE id = ?";
       try (Connection connection = dataSource.getConnection();
               PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idTorneo);
                try (ResultSet resultSet = statement.executeQuery()) { 
                 if (resultSet.next()) { 
                     return mapearResultSetATorneo(resultSet); 
                 }
             } 
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public List<Torneo> obtenerTodosLosTorneos() {
        List<Torneo> torneos = new ArrayList<>();
        String sql = "SELECT * grupo FROM torneo";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Torneo torneo = mapearResultSetATorneo(resultSet);
                    torneos.add(torneo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return torneos;

    }

    private Torneo mapearResultSetATorneo(ResultSet resultSet) throws SQLException {
        int torneoId = 0;
        String nombre = "";
        char codRegion = ' ';
        float puntosVictoria = 0;
    
        while (resultSet.next()){
            torneoId = resultSet.getInt(1);
            nombre = resultSet.getString(2);
            codRegion = resultSet.getString(3).charAt(0);
            String.valueOf(codRegion);
            puntosVictoria = resultSet.getFloat(4);
            return new Torneo(torneoId, nombre, codRegion, puntosVictoria);
        }
        return null;
    }
    
}
