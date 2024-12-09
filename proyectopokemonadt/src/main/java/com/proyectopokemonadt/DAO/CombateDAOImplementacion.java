package com.proyectopokemonadt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.proyectopokemonadt.DTO.CombateDTO;
import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.complementarias.DBConnection;

public class CombateDAOImplementacion implements CombateDAO {

    private static CombateDAOImplementacion instancia;
    private DataSource dataSource;

    private CombateDAOImplementacion(){
        this.dataSource = DBConnection.getMySQLDataSource();
    }

    public static CombateDAOImplementacion getInstance(){
        if (instancia == null) {
            instancia = new CombateDAOImplementacion();
        }
        return instancia;
    }

    @Override
    public boolean crearCombate(CombateDTO combatedto) {
         String sql = "INSERT INTO combate (id, fecha, idTorneo) VALUES (?,?,?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, combatedto.getId());
                statement.setDate(2, java.sql.Date.valueOf(combatedto.getFecha()));
                statement.setInt(3, combatedto.getIdTorneo());
                statement.executeUpdate();
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }    
    }

    @Override
    public boolean eliminarCombatePorId(int id) {
        String sql = "DELETE FROM combate WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Combate obtenerCombatePorId(int id) {
       String sql = "SELECT * FROM combate WHERE id = ?";
       try (Connection connection = dataSource.getConnection(); 
              PreparedStatement statement = connection.prepareStatement(sql)) { 
             statement.setInt(1, id); 
             try (ResultSet resultSet = statement.executeQuery()) { 
                 if (resultSet.next()) { 
                     return mapearResultSetACombate(resultSet); 
                 } 
             } 
         } catch (SQLException e) { 
             e.printStackTrace(); 
         } 
         return null; 
    }

    @Override
    public List<Combate> obtenerTodosLosCombates() {
        String sql = "SELECT * FROM combate";
        List<Combate> combates = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Combate combate = mapearResultSetACombate(resultSet);
                combates.add(combate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return combates;
    }

    @Override
    public List<Combate> obtenerTodosLosCombatesPorIdTorneo(int id) {
      String sql = "SELECT * FROM combate WHERE idTorneo = ?";
      List<Combate> combatesTorneo = new ArrayList<>();
      try {Connection connection = dataSource.getConnection();
              PreparedStatement statement = connection.prepareStatement(sql);
              statement.setInt(1, id);
              ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()) {
              Combate combate = mapearResultSetACombate(resultSet);
              combatesTorneo.add(combate);
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return combatesTorneo;
    }

    private Combate mapearResultSetACombate(ResultSet resultSet) throws SQLException {
        Combate combate = new Combate();
        combate.setId(resultSet.getInt("id"));
        combate.setFecha(resultSet.getDate("fecha").toLocalDate());
        combate.setIdTorneo(resultSet.getInt("idTorneo"));
        return combate;
    }

    @Override
    public boolean actualizarCombate(int idCombate, Combate combate) {
        String sql = "UPDATE combate SET fecha = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(combate.getFecha()));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
