package com.proyectopokemonadt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Registro {

    private static long id = 999999;
    private static boolean registerOK = false;
    public Registro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del usuario añadir: ");
        String nombre = sc.nextLine();
        System.out.println("Contraseña del usuario a añadir: ");
        String contraseña = sc.nextLine();
        System.out.println("Nacionalidad del usuario: ");
        String nacionalidad = sc.nextLine();

        while (!usuarioExistente(nombre, nacionalidad)){
            System.out.println("Este nombre de usuario ya está en uso, utilice otro.");
            sc = new Scanner(System.in);
            System.out.println("Nombre del usuario añadir: ");
            nombre = sc.nextLine();
            System.out.println("Contraseña del usuario a añadir: ");
            contraseña = sc.nextLine();
            System.out.println("Nacionalidad del usuario: ");
            nacionalidad = sc.nextLine();
        }
    }

    public static boolean creadorUsuario (String nombre, String contraseña, String tipoUsuario, String nacionalidad){
        if (usuarioExistente(nombre, nacionalidad)){
            
        } else {
            File log = new File ("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios", "usuarios.txt");
            try {
                FileWriter fw = new FileWriter(log, true);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(nombre);
                bf.newLine();
                bf.write(contraseña);
                bf.newLine();
                bf.write(nacionalidad);
                bf.newLine();
                bf.write(tipoUsuario);
                bf.newLine();
                bf.close();
                return true;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public static boolean usuarioExistente(String nombre, String nacionalidad){
        String buscar;
        File rd = new File ("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios", "usuarios.txt");

        try {
            FileReader fr = new FileReader(rd);
            BufferedReader br = new BufferedReader(fr);

            while ((buscar = br.readLine()) != null){
                if(buscar.equals(nombre)){
                    buscar = br.readLine();
                    if(buscar.equals(nacionalidad)){
                        br.close();
                        return true;
                    }
                }
            }
            br.close();
            return false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean regOK(){
        return registerOK;
    }

    public static long idGenerator(){
        return id++;
    }
}
