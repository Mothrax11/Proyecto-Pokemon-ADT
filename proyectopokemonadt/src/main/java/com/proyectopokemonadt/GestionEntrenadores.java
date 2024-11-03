package com.proyectopokemonadt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import com.proyectopokemonadt.clasesBasicas.Entrenador;

public class GestionEntrenadores {
    

    public static void añadirEntrenadores(Entrenador entrenador){
        File entrenadoresDat = new File(
                "proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios\\entrenadores.dat");
        try {
            FileOutputStream fo = new FileOutputStream(entrenadoresDat);
            ObjectOutputStream oou = new ObjectOutputStream(fo);
            oou.writeObject(entrenador);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
