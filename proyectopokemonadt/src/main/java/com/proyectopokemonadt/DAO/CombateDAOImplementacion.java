package com.proyectopokemonadt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import com.proyectopokemonadt.ENTIDADES.Combate;

public class CombateDAOImplementacion implements CombateDAO {

    private CombateDAOImplementacion instancia;
    private DataSource dataSource;
    private CombateDAOImplementacion(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public CombateDAOImplementacion getInstance(DataSource dataSource){
        if (instancia == null) {
            instancia = new CombateDAOImplementacion(dataSource);
        }
        return instancia;
    }


    @Override
    public boolean crearCombate(Combate combate) {
         String sql = "INSERT INTO combate (id, fecha, idTorneo) VALUES (?,?,?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, combate.getId());
                statement.setDate(2, java.sql.Date.valueOf(combate.getFecha()));
                statement.setInt(3, combate.getIdTorneo());
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
    public boolean actualizarCombate(Combate combate) {
        String sql = "UPDATE combate SET fecha = ?, idTorneo = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(combate.getFecha()));
            statement.setInt(2, combate.getIdTorneo());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        

    @Override
    public Combate obtenerCombatePorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCombatePorId'");
    }

    @Override
    public List<Combate> obtenerTodosLosCombates() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodosLosCombates'");
    }

    @Override
    public List<Combate> obtenerTodosLosCombatesPorIdTorneo(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodosLosCombatesPorIdTorneo'");
    }
    
}
