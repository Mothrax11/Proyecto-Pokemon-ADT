package com.proyectopokemonadt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.proyectopokemonadt.ENTIDADES.Torneo;
import com.proyectopokemonadt.complementarias.DBConnection;

public class TorneoDAOImplementacion implements TorneoDAO {


    private static TorneoDAOImplementacion instancia;
    private DataSource dataSource;

    public TorneoDAOImplementacion() { 
        this.dataSource = DBConnection.getMySQLDataSource(); 
    }

    public static TorneoDAOImplementacion getInstancia() {
        if (instancia == null) {
            instancia = new TorneoDAOImplementacion();
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
        String sql = "SELECT * FROM torneo";
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
        Torneo torneo = new Torneo();
        torneo.setId(resultSet.getInt("id"));
        torneo.setNombre(resultSet.getString("nombre"));
        torneo.setCodRegion(resultSet.getString("codRegion").charAt(0));
        torneo.setPuntosVictoria(resultSet.getInt("puntosVictoria"));
        return torneo;
    }
    
    @Override
    public Torneo obtenerTorneoRegionNombre(String codRegion, String nombre) {
        String sql = "SELECT * FROM torneo WHERE codRegion = ? AND nombre = ?";
            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, codRegion);
                statement.setString(2, nombre);
                ResultSet resultSet = statement.executeQuery();
                    
                    
                    if (resultSet.next()) {
                        Torneo torneo = new Torneo();
                        torneo.setId(resultSet.getInt("id"));
                        torneo.setNombre(resultSet.getString("nombre"));
                        torneo.setCodRegion(resultSet.getString("codRegion").charAt(0));
                        torneo.setPuntosVictoria(resultSet.getInt("puntosVictoria"));
                        return torneo;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
                return null;
    }
    
}
