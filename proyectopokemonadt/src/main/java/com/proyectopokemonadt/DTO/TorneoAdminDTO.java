package com.proyectopokemonadt.DTO;

public class TorneoAdminDTO {
    private int idTorneo;
    private int idAdminTorneo;
    public TorneoAdminDTO(int idTorneo, int idAdminTorneo) {
        this.idTorneo = idTorneo;
        this.idAdminTorneo = idAdminTorneo;
    }
    public int getIdTorneo() {
        return idTorneo;
    }
    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }
    public int getIdAdminTorneo() {
        return idAdminTorneo;
    }
    public void setIdAdminTorneo(int idAdminTorneo) {
        this.idAdminTorneo = idAdminTorneo;
    }

    
}
