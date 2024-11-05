package com.proyectopokemonadt.autenticacion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class idGenerator {
    public static long generador(){
        int id;
        File f = new File("proyectopokemonadt/src/main/java/com/proyectopokemonadt/archviosComplementarios/id.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            
            try {
                
                id = Integer.valueOf(br.readLine());
                br.close();
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(String.valueOf(id + 1));
                bw.close();
                return id;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    return -1;
    }
}
