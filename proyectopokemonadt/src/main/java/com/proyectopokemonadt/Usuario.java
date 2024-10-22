package com.proyectopokemonadt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class Usuario {
    public Usuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del usuario añadir: ");
        String nombre = sc.nextLine();
        System.out.println("Contraseña del usuario a añadir: ");
        String contraseña = sc.nextLine();

        while (!(creadorUsuario(nombre, contraseña,"invitado"))){
            System.out.println("Este nombre de usuario ya está en uso, utilice otro.");
            sc = new Scanner(System.in);
            System.out.println("Nombre del usuario añadir: ");
            nombre = sc.nextLine();
            System.out.println("Contraseña del usuario a añadir: ");
            contraseña = sc.nextLine();
        }
    }

    public static boolean creadorUsuario (String nombre, String contraseña, String tipoUsuario){
        if (usuarioExistente(nombre)){
            return false;
        } else {
            File log = new File ("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios", "usuarios.txt");
            try {
                FileWriter fw = new FileWriter(log, true);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(nombre);
                bf.newLine();
                bf.write(contraseña);
                bf.newLine();
                bf.write(tipoUsuario);
                bf.close();
                return true;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public static boolean usuarioExistente(String nombre){
        String buscar;
        File rd = new File ("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios", "usuarios.txt");

        try {
            FileReader fr = new FileReader(rd);
            BufferedReader br = new BufferedReader(fr);

            while ((buscar = br.readLine()) != null){
                if(buscar.equals(nombre)){
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
