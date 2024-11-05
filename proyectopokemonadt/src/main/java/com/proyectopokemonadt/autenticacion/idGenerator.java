package com.proyectopokemonadt.autenticacion;

public class idGenerator {
    private static long id = 999;
    public static long generador(){
        id++;
    return id;
    }
}
