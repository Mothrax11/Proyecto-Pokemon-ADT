package com.proyectopokemonadt.clasesBasicas;

import java.time.LocalDate;

public class Combate {
    private LocalDate fecha;
    private long id;  
    
    public Combate(long id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    

}
