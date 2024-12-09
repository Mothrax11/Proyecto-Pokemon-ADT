package com.proyectopokemonadt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.proyectopokemonadt.DTO.TorneoAdminDTO;
//import com.proyectopokemonadt.ENTIDADES.Entrenador;
import com.proyectopokemonadt.ENTIDADES.TorneoAdmin;
import com.proyectopokemonadt.complementarias.DBConnection;

public class TorneoAdminDAOImplementacion implements TorneoAdminDAO {

    private static TorneoAdminDAOImplementacion instancia;
    private DataSource dataSource;

    private TorneoAdminDAOImplementacion (DataSource dataSource){
        this.dataSource = DBConnection.getMySQLDataSource();
    }

    public static TorneoAdminDAOImplementacion getInstancia(DataSource dataSource){
        if (instancia == null) {
            return new TorneoAdminDAOImplementacion(dataSource);
        }
        return instancia;
    }


    @Override
    public boolean crearTorneoAdmin(TorneoAdminDTO torneoAdmin) {
        String sql = "Insert into torneoadmin (idtorneo, idAdminTorneo) values (?,?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, torneoAdmin.getIdTorneo());
                statement.setInt(2, torneoAdmin.getIdAdminTorneo());
                statement.executeUpdate();
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }    
    }

    @Override
    public boolean eliminarTorneoAdminPorIdTorneo(int idTorneo) {
        String sql = "DELETE FROM torneoadmin WHERE idtorneo = ?";
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
    public List<TorneoAdmin> obtenerTodosLosTorneosAdmin() {
        String sql = "Select * from torneoadmin";
        List<TorneoAdmin> listaTorneoAdmin = new ArrayList<>();
       try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                TorneoAdmin torneoAdmin = mapearResultSetATorneoAdmin(resultSet);
                listaTorneoAdmin.add(torneoAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTorneoAdmin;
    }

    

    @Override
    public int obtenerIdEntrenadorPorIdTorneo(int idTorneo) {
        String sql = "Select idAdminTorneo from torneoadmin where idtorneo = ?";
        
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idTorneo);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()){
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    private TorneoAdmin mapearResultSetATorneoAdmin(ResultSet resultSet) throws SQLException{
        TorneoAdmin torneoAdmin = new TorneoAdmin();
        torneoAdmin.setIdTorneo(resultSet.getInt("idtorneo"));
        torneoAdmin.setIdAdminTorneo(resultSet.getInt("idAdminTorneo"));
        return torneoAdmin;
    }
    
/**
 * 
 * private Entrenador mapearResultSetAEntrenador(ResultSet resultSet) throws
 * SQLException {
 * Entrenador entrenador = new Entrenador();
 * entrenador.setId(resultSet.getInt("id"));
 * entrenador.setNombre(resultSet.getString("nombre"));
 * entrenador.setNacionalidad(resultSet.getString("nacionalidad"));
 * return entrenador;
 * }
 */
   

    @Override
    public List<Integer> obtenerTorneosPorIdAdministrador(int idTorneoAdmin) {
        String sql = "Select idtorneo from torneoadmin where idAdminTorneo = ?";
        List<Integer> torneosPorIdAdmin = new ArrayList<>();
         try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idTorneoAdmin);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                torneosPorIdAdmin.add(resultSet.getInt("idtorneo"));
            }
            return torneosPorIdAdmin;
        
        } catch (SQLException e) {
            e.printStackTrace();
            return torneosPorIdAdmin;
        }
    }

    
}
