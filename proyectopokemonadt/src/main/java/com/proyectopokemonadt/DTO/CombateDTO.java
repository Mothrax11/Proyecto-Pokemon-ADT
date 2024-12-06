package com.proyectopokemonadt.DTO;

import java.time.LocalDate;

public class CombateDTO {
 
    private LocalDate fecha;
    private int id;
    private int idTorneo;
    private EntrenadorDTO entrenador1;
    private EntrenadorDTO entrenador2;
    
    public CombateDTO(int id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public CombateDTO(){
        
    }

    public EntrenadorDTO getEntrenador1() {
        return entrenador1;
    }

    public void setEntrenador1(EntrenadorDTO entrenador1) {
        this.entrenador1 = entrenador1;
    }

    public EntrenadorDTO getEntrenador2() {
        return entrenador2;
    }

    public void setEntrenador2(EntrenadorDTO entrenador2) {
        this.entrenador2 = entrenador2;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }
}
