package com.proyectopokemonadt.SERVICES;

import java.util.List;
import javax.sql.DataSource;

import com.proyectopokemonadt.DAO.CombateDAO;
import com.proyectopokemonadt.DAO.CombateDAOImplementacion;
import com.proyectopokemonadt.DAO.CombateEntrenadorDAOImplementacion;
import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.ENTIDADES.Entrenador;
import com.proyectopokemonadt.complementarias.DBConnection;

public class CombateEntrenadorServices {

    private static CombateEntrenadorServices instancia;
    private DataSource dataSource = DBConnection.getMySQLDataSource();
    private CombateDAOImplementacion combateDAOImplementacion;
    private CombateEntrenadorDAOImplementacion combateEntrenadorDAOImplementacion;

    private CombateEntrenadorServices(CombateDAO combateDAO) {
        this.combateDAOImplementacion = combateDAOImplementacion.getInstance(dataSource);
        this.combateEntrenadorDAOImplementacion = combateEntrenadorDAOImplementacion.getInstancia(dataSource);
    }

    public static CombateEntrenadorServices getInstancia(CombateDAO combateDAO) {
        if (instancia == null) {
            instancia = new CombateEntrenadorServices(combateDAO);
        }
        return instancia;
    }

    public List<Combate> obtenerTodosCombatesPorIdEntrenador(int idEntrenador) {
        return combateEntrenadorDAOImplementacion.obtenerTodosCombatesPorIdEntrenador(idEntrenador);
    }

    public List<Entrenador> obtenerEntrenadoresPorIdCombate(int idCombate) {
        
        return combateEntrenadorDAOImplementacion.obtenerEntrenadoresPorIdCombate(idCombate);
    }
    } 
