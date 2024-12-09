package com.proyectopokemonadt.SERVICES;

import com.proyectopokemonadt.DAO.EntrenadorDAOImplementacion;
import com.proyectopokemonadt.DTO.EntrenadorDTO;
import com.proyectopokemonadt.ENTIDADES.Entrenador;
import com.proyectopokemonadt.complementarias.DBConnection;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class EntrenadorServices {

    private static EntrenadorServices instancia;
    private DataSource dataSource;

    private EntrenadorServices(DataSource dataSource) {
        this.dataSource = DBConnection.getMySQLDataSource();
    }

    public static EntrenadorServices getInstancia(DataSource dataSource) {
        if (instancia == null) {
            instancia = new EntrenadorServices(dataSource);
        }
        return instancia;
    }



    public boolean crearEntrenador(EntrenadorDTO entrenadorDTO) {
        Entrenador entrenador = new Entrenador();
        entrenador.setId(entrenadorDTO.getId());
        entrenador.setNombre(entrenadorDTO.getNombre());
        entrenador.setNacionalidad(entrenadorDTO.getNacionalidad());
        return EntrenadorDAOImplementacion.getInstancia(dataSource).crearEntrenador(entrenador);
    }

    public boolean actualizarEntrenador(int id, EntrenadorDTO entrenadorDTO) {
        Entrenador entrenador = new Entrenador();
        entrenador.setId(id);
        entrenador.setNombre(entrenadorDTO.getNombre());
        entrenador.setNacionalidad(entrenadorDTO.getNacionalidad());
        if (EntrenadorDAOImplementacion.getInstancia(dataSource).actualizarEntrenadorPorId(entrenador.getId(), entrenador)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarEntrenador(int id) {
        return EntrenadorDAOImplementacion.getInstancia(dataSource).eliminarEntrenadorPorId(id);
    }

    public EntrenadorDTO obtenerEntrenadorPorId(int id) {
        Entrenador entrenador = EntrenadorDAOImplementacion.getInstancia(dataSource).obtenerEntrenadorPorId(id);
        if (entrenador != null) {
            return new EntrenadorDTO(
                    entrenador.getId(),
                    entrenador.getNombre(),
                    entrenador.getNacionalidad(),
                    0f,
                    EntrenadorDAOImplementacion.getInstancia(dataSource).obtenerTorneosPorEntrenador(id),
                    EntrenadorDAOImplementacion.getInstancia(dataSource).obtenerCombatesPorEntrenador(id)
            );
        }
        return null;
    }

    public List<EntrenadorDTO> obtenerTodosLosEntrenadores() {
        List<Entrenador> entrenadores = EntrenadorDAOImplementacion.getInstancia(dataSource).obtenerTodosLosEntrenadores();
        List<EntrenadorDTO> entrenadorDTOList = new ArrayList<>();

        for (Entrenador entrenador : entrenadores) {
            entrenadorDTOList.add(new EntrenadorDTO(
                    entrenador.getId(),
                    entrenador.getNombre(),
                    entrenador.getNacionalidad(),
                    0f,
                    EntrenadorDAOImplementacion.getInstancia(dataSource).obtenerTorneosPorEntrenador(entrenador.getId()),
                    EntrenadorDAOImplementacion.getInstancia(dataSource).obtenerCombatesPorEntrenador(entrenador.getId())
            ));
        }
        return entrenadorDTOList;
    }

    


}
