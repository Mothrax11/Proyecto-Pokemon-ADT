package com.proyectopokemonadt.autenticacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import com.proyectopokemonadt.clasesBasicas.Torneo;
import com.proyectopokemonadt.complementarias.ShowNations;

public class Registro {

    private static boolean registerOK = false;
    private static ArrayList<Torneo> torneos = new ArrayList<>();
    public static void registroData() {
        Usuario admin = new Usuario("admingeneral", "Passw0rd", "ES", 1, "AG");
        Scanner sc = new Scanner(System.in);
        boolean vb = false;
        while (!vb) {

            System.out.println("¿Cuál es tu nombre?");
            String nombre = sc.next();

            System.out.println("¿Cuál es tu contraseña?");
            String contraseña = sc.next();

            String rol = "ENT";

            if (creadorUsuario(nombre, contraseña, rol, idGenerator.generador())) {
                System.out.println("Se ha registrado correctamente con el usuario " + nombre);
                vb = true;
            } else {
                System.out.println("Ha ocurrido un error en el registro, intentelo de nuevo.");
            }
        }
        }
    public static void registroDataAT(String nombreAT, String passAT, String rol) {
        Usuario admin = new Usuario("admingeneral", "Passw0rd", "ES", 1, "AG");
        Scanner sc = new Scanner(System.in);
        boolean vb = false;
        while (!vb) {
            if (creadorUsuario(nombreAT, passAT, rol, idGenerator.generador())) {
                System.out.println("Se ha registrado correctamente con el usuario " + nombreAT);
                vb = true;
            } else {
                System.out.println("Ha ocurrido un error en el registro, intentelo de nuevo.");
            }
        }
    }

    public static boolean creadorUsuario (String nombre, String contraseña, String tipoUsuario, long id){
        int idInt = (int)id;
        if (usuarioExistente(nombre, contraseña)){

        } else {
            File usrs = new File ("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios", "credenciales.txt");
            try {
                FileWriter fw = new FileWriter(usrs, true);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(nombre);
                bf.write(" ");
                bf.write(contraseña);
                bf.write(" ");
                bf.write(tipoUsuario);
                bf.write(" ");
                bf.write(idInt);
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
        File rd = new File ("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios", "credenciales.txt");

        try {
            FileReader fr = new FileReader(rd);
            BufferedReader br = new BufferedReader(fr);

            if (rd.length() == 0){
                br.close();
                return false;
            } else {
                while ((buscar = br.readLine()) != null){
                    String [] palabrasLinea = buscar.split(" ");
                    if(palabrasLinea[0].equals(nombre) && palabrasLinea[1].equals(contraseña)){
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

}
