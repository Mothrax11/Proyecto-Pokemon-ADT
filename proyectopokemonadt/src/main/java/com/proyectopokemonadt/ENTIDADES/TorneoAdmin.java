package com.proyectopokemonadt.ENTIDADES;

public class TorneoAdmin {
    private int idTorneo;
    private int idAdminTorneo;

    public TorneoAdmin(int idTorneo, int idAdminTorneo) {
        this.idTorneo = idTorneo;
        this.idAdminTorneo = idAdminTorneo;
    }

    public TorneoAdmin() {
        
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
