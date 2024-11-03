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
    public static void registroData() {
        boolean sesionAdmin = false;
        
        boolean su = false;
        Usuario admin = new Usuario("admingeneral", "Passw0rd", "ES", 1, "AG");
        Scanner sc = new Scanner(System.in);
        boolean vb = false;
        while (!vb) {

            System.out.println("¿Cuál es tu nombre?");
            String nombre = sc.next();

            System.out.println("¿Cuál es tu contraseña?");
            String contraseña = sc.next();

            String rol = "ENT";

            System.out.println("¿Cual es tu nacionalidad?");
            // Muestra la lista de todos los paises para la eleccion del usuario
            // ShowNations.show();
            String nacionalidad = sc.next();

            if (creadorUsuario(nombre, contraseña, rol, nacionalidad)) {
                System.out.println("Se ha registrado correctamente con el usuario " + nombre);
                vb = true;
                sesionAdmin = true;
            } else {
                System.out.println("Ha ocurrido un error en el registro, intentelo de nuevo.");
            }
        }
    }

    public static boolean creadorUsuario (String nombre, String contraseña, String tipoUsuario, String nacionalidad){
        if (usuarioExistente(nombre, contraseña)){

        } else {
            File usrs = new File ("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios", "usuarios.txt");
            try {
                FileWriter fw = new FileWriter(usrs, true);
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

    public static boolean usuarioExistente(String nombre, String contraseña){
        String buscar;
        File rd = new File ("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios", "usuarios.txt");

        try {
            FileReader fr = new FileReader(rd);
            BufferedReader br = new BufferedReader(fr);

            if (rd.length() == 0){
                br.close();
                return false;
            } else {
                while ((buscar = br.readLine()) != null){
                    if(buscar.equals(nombre)){
                        buscar = br.readLine();
                        if(buscar.equals(contraseña)){
                            br.close();
                            return true;
                        }
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
