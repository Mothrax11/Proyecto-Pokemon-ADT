package com.proyectopokemonadt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.proyectopokemonadt.ENTIDADES.Carnet;
import com.proyectopokemonadt.complementarias.DBConnection;

public class CarnetDAOImplementacion implements CarnetDAO {

    private static CarnetDAOImplementacion instancia;
    private DataSource dataSource;
    private CarnetDAOImplementacion(DataSource dataSource) {
        this.dataSource = DBConnection.getMySQLDataSource();
    }

    public static CarnetDAOImplementacion getInstancia(DataSource dataSource) {
        if (instancia == null) {
            instancia = new CarnetDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public boolean crearCarnet(Carnet carnet) {
        String sql = "INSERT INTO carnet (idEntrenador, fechaExpedicion, puntos, numVictorias) VALUES (?,?,?,?)";
        try (Connection connection = dataSource.getConnection(); 
            PreparedStatement statement = connection.prepareStatement(sql)) { 
            statement.setInt(1, carnet.getIdEntrenador()); 
            statement.setDate(2, java.sql.Date.valueOf(carnet.getFechaExpedicion())); 
            statement.setFloat(3, carnet.getPuntos()); 
            statement.setInt(4, carnet.getNumVictorias()); 
            statement.executeUpdate(); 
            return true;
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return false;
        }
    }

    @Override
    public boolean eliminarCarnetPorIdEntrenador(int idEntrenador) {
        String sql = "DELETE FROM carnet WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEntrenador);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarCarnetPorIdEntrenador(int idEntrenador, Carnet carnet) {
        String sql = "UPDATE carnet SET fechaExpedicion = ?, puntos = ?, numVictorias = ? WHERE idEntrenador = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(carnet.getFechaExpedicion()));
            statement.setFloat(2, carnet.getPuntos());
            statement.setInt(3, carnet.getNumVictorias());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Carnet> obtenerTodosLosCarnets() {
         List<Carnet> carnets = new ArrayList<>();
        String sql = "SELECT * FROM carnet";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                    Carnet carnet = new Carnet();
                    carnet.setIdEntrenador(resultSet.getInt("idEntrenador"));
                    carnet.setFechaExpedicion(resultSet.getDate("fechaExpedicion").toLocalDate());
                    carnet.setPuntos(resultSet.getFloat("puntos"));
                    carnet.setNumVictorias(resultSet.getInt("numVictorias"));
                carnets.add(carnet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carnets;
    }


    @Override
    public Carnet obtenerCarnetPorIdEntrenador(int idEntrenador) {
        String sql = "SELECT * FROM carnet WHERE idEntrenador = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEntrenador);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearResultSetACarnet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private Carnet mapearResultSetACarnet(ResultSet resultSet) throws SQLException {
        Carnet carnet = new Carnet();
        carnet.setIdEntrenador(resultSet.getInt("idEntrenador"));
        //carnet.setFechaExpedicion(resultSet.getDate("fechaExpedicion"));
        carnet.setPuntos(resultSet.getFloat("puntos"));
        carnet.setNumVictorias(resultSet.getInt("numVictorias"));
        return carnet;
    }
}
