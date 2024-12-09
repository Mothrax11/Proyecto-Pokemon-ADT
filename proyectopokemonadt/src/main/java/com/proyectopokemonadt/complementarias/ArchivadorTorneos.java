package com.proyectopokemonadt.complementarias;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.proyectopokemonadt.DTO.TorneoDTO;
import com.proyectopokemonadt.SERVICES.CombateServices;

public class ArchivadorTorneos {

    public boolean crearFichero(TorneoDTO torneo){
        File file = new File( torneo.getNombre() + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            escribirFichero(torneo, file);
            return true;
        } else if(file.exists()){
            escribirFichero(torneo, file);
            System.out.println("Torneo exportado a fichero");
            return true;
        } else {
            return false;
        }
        
    }

     public static void escribirFichero (TorneoDTO torneo, File file) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            try {
                fileWriter.write("Nombre Torneo: " + torneo.getNombre() + "\n");
                fileWriter.write("Region Torneo: " + torneo.getCodRegion() + "\n");
                fileWriter.write("Combates del torneo" + "\n");
                int numCombatesEnTorneo = CombateServices.getInstancia().obtenerTodosLosCombatesPorIdTorneo(torneo.getId()).size();
                for (int i = 0; i <= numCombatesEnTorneo - 1; i++) {
                    fileWriter.write("Combate " + i + ": " +
                    CombateServices.getInstancia().obtenerTodosLosCombatesPorIdTorneo(torneo.getId()).get(i).getFecha() + 
                    " | " + 
                    CombateServices.getInstancia().obtenerTodosLosCombatesPorIdTorneo(torneo.getId()).get(i).getEntrenador1() +
                    CombateServices.getInstancia().obtenerTodosLosCombatesPorIdTorneo(torneo.getId()).get(i).getEntrenador2() + 
                    "\n");
                }
                fileWriter.write("Puntos por victoria: " + torneo.getPuntosVictoria());
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
}
