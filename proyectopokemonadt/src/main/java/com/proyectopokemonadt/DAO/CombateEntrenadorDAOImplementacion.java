package com.proyectopokemonadt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.ENTIDADES.Entrenador;
import com.proyectopokemonadt.complementarias.DBConnection;


public class CombateEntrenadorDAOImplementacion implements CombateEntrenadorDAO {

    private static CombateEntrenadorDAOImplementacion instancia;
    private DataSource dataSource;

    private CombateEntrenadorDAOImplementacion(){
        this.dataSource = DBConnection.getMySQLDataSource();
    }

    public static CombateEntrenadorDAOImplementacion getInstancia() {
        if (instancia == null) {
            instancia = new CombateEntrenadorDAOImplementacion();
        }
        return instancia;
    }

    @Override
    public List<Combate> obtenerTodosCombatesPorIdEntrenador(int idEntrenador) {
        String sql = "SELECT combate.id, combate.fecha, combate.idTorneo FROM combate combate INNER JOIN combateentrenadores ce ON combate.id = ce.idCombate WHERE ce.idEntrenador1 = ? OR ce.idEntrenador2 = ?";
        List<Combate> combates = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEntrenador);
            statement.setInt(2, idEntrenador);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Combate combate = new Combate();
                    combate.setId(resultSet.getInt("id "));
                    combate.setFecha(resultSet.getDate("fecha").toLocalDate());
                    combate.setIdTorneo(resultSet.getInt("idTorneo"));
                    combates.add(combate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return combates;
    }


    @Override
    public List<Entrenador> obtenerEntrenadoresPorIdCombate(int idCombate) {
        String sql = "SELECT entrenador.id, entrenador.nombre, entrenador.nacionalidad FROM entrenador entrenador INNER JOIN combateentrenadores ce ON e.id = ce.idEntrenador1 OR entrenador.idEntrenador = ce.idEntrenador2 WHERE ce.idCombate = ?";
        List<Entrenador> entrenadores = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCombate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Entrenador entrenador = new Entrenador();
                    entrenador.setId(resultSet.getInt("id"));
                    entrenador.setNombre(resultSet.getString("nombre"));
                    entrenador.setNacionalidad(resultSet.getString("nacionalidad"));
                    entrenadores.add(entrenador);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    }
}
