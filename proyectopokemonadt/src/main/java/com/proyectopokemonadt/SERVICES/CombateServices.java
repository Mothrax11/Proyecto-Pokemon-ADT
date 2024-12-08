package com.proyectopokemonadt.SERVICES;

import com.proyectopokemonadt.DAO.CombateDAO;
import com.proyectopokemonadt.DTO.CombateDTO;
import com.proyectopokemonadt.ENTIDADES.Combate;

import java.util.ArrayList;
import java.util.List;

public class CombateServices {
    private CombateDAO combateDAO;

    public CombateServices(CombateDAO combateDAO) {
        this.combateDAO = combateDAO;
    }

    public boolean crearCombate(Combate combate) {
        return combateDAO.crearCombate(combate);
    }

    public boolean eliminarCombatePorId(int id) {
        return combateDAO.eliminarCombatePorId(id);
    }

    public boolean actualizarCombate(int idCombate, Combate combate) {
        return combateDAO.actualizarCombate(idCombate, combate);
    }

    public Combate obtenerCombatePorId(int id) {
        return combateDAO.obtenerCombatePorId(id);
    }

    public List<Combate> obtenerTodosLosCombates() {
        return combateDAO.obtenerTodosLosCombates();
    }

    public List<Combate> obtenerTodosLosCombatesPorIdTorneo(int idTorneo) {
        return combateDAO.obtenerTodosLosCombatesPorIdTorneo(idTorneo);
    }

     public static CombateDTO mapearEntidadACombateDTO(Combate combate) {
        return new CombateDTO(
                combate.getId(),
                combate.getFecha(),
                combate.getIdTorneo()
        );
    }

    public static Combate mapearCombateDTOAEntidad(CombateDTO combateDTO) {
        return new Combate(
                combateDTO.getId(),
                combateDTO.getFecha(),
                combateDTO.getIdTorneo()
        );
    }

    public static List<CombateDTO> mapearListaEntidadAListaCombateDTO(List<Combate> combates) {
        List<CombateDTO> combateDTOs = new ArrayList<>();
        for (Combate combate : combates) {
            combateDTOs.add(mapearEntidadACombateDTO(combate));
        }
        return combateDTOs;
    }

    public static List<Combate> mapearListaCombateDTOAListaEntidad(List<CombateDTO> combateDTOs) {
        List<Combate> combates = new ArrayList<>();
        for (CombateDTO combateDTO : combateDTOs) {
            combates.add(mapearCombateDTOAEntidad(combateDTO));
        }
        return combates;
    }
}
