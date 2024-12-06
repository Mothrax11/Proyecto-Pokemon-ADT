package com.proyectopokemonadt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.proyectopokemonadt.ENTIDADES.Entrenador;

public class EntrenadorDAOImplementacion implements EntrenadorDAO {

    private static EntrenadorDAOImplementacion instancia;
    private DataSource dataSource;
    private EntrenadorDAOImplementacion(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EntrenadorDAOImplementacion getInstancia(DataSource dataSource) {
        if(instancia == null){
            instancia = new EntrenadorDAOImplementacion(dataSource);
        }
        return instancia;
    }


    @Override
    public boolean crearEntrenador(Entrenador entrenadorEntidad) {

        String sql = "INSERT INTO entrenador (id, nombre, nacionalidad) VALUES (?,?,?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, entrenadorEntidad.getId());
                statement.setString(2, entrenadorEntidad.getNombre());
                statement.setString(3, entrenadorEntidad.getNacionalidad());
                statement.executeUpdate();
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }    
    }

    @Override
    public boolean actualizarEntrenadorPorId(int idEntrenador, Entrenador entrenadorEntidad) {
        String sql = "UPDATE entrenador SET nombre = ?, nacionalidad = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entrenadorEntidad.getNombre());
            statement.setString(2, entrenadorEntidad.getNacionalidad());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEntrenadorPorId(int id) {
        String sql = "DELETE FROM entrenador WHERE id = ?";
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
    public Entrenador obtenerEntrenadorPorId(int id) {
       String sql = "SELECT * FROM entrenador WHERE id = ?";
        try (Connection connection = dataSource.getConnection(); 
              PreparedStatement statement = connection.prepareStatement(sql)) { 
             statement.setInt(1, id); 
             try (ResultSet resultSet = statement.executeQuery()) { 
                 if (resultSet.next()) { 
                     return mapearResultSetAEntrenador(resultSet); 
                 } 
             } 
         } catch (SQLException e) { 
             e.printStackTrace(); 
         } 
         return null;
    }

    @Override
    public List<Entrenador> obtenerTodosLosEntrenadores() {
        List <Entrenador> entrenadores = new ArrayList<>();
        String sql = "SELECT * FROM entrenador";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Entrenador entrenador = mapearResultSetAEntrenador(resultSet);
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    }

    private Entrenador mapearResultSetAEntrenador(ResultSet resultSet) throws SQLException {
        Entrenador entrenador = new Entrenador();
        entrenador.setId(resultSet.getInt("id"));
        entrenador.setNombre(resultSet.getString("nombre"));
        entrenador.setNacionalidad(resultSet.getString("nacionalidad"));
        return entrenador;
    }

    
    
}
