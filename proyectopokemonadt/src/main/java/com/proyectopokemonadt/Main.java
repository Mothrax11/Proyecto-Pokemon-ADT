package com.proyectopokemonadt;
import com.proyectopokemonadt.*;
import com.proyectopokemonadt.clasesBasicas.Torneo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        ArrayList<Torneo> torneos = new ArrayList<>();
        Torneo torneoDefault = new Torneo(1l, "Torneo Default", 'E', 0l);
        torneos.add(torneoDefault);
        long idTorneo = 100000;
        boolean sesionAdmin = false;
        boolean su = false;
        Usuario admin = new Usuario("admingeneral", "Passw0rd", "ES", 1, "AG");
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al Pokemon Torunamet 2025!");
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1 -> Registrarme.");
        System.out.println("2 -> Iniciar sesión.");
        System.out.println("3 -> Iniciar sesion como invitado");
        int eleccion = sc.nextInt();
        
        if(eleccion == 1){
            boolean vb = false;
            while (!vb){
                System.out.println("¿Cuál es tu nombre?");
                    String nombre = sc.next();
                System.out.println("¿Cuál es tu contraseña?");
                    String contraseña = sc.next();
                System.out.println("¿Cuál es tu rol?");
                    String rol = "INV";

                while (!su){
                    rol = sc.next();
                    if(rol.equals("AG")){
                        System.out.println("Usted no tiene permisos para registrarse como Administrador General.");
                    } else {
                        su = true;
                    }
                }

                System.out.println("Cual es tu nacionalidad?");
                //ShowNations.show();
                String nacionalidad = sc.next();
                Registro.creadorUsuario(nombre, contraseña, rol, nacionalidad);

                if(Registro.creadorUsuario(nombre, contraseña, rol, nacionalidad)){
                    Usuario usr = new Usuario(nombre, contraseña, nacionalidad, Registro.idGenerator(), rol);
                    System.out.println("Se ha registrado correctamente con el usuario " + nombre);
                    vb = true;  
                    sesionAdmin = true; 
                } else {
                    System.out.println("Ha ocurrido un error en el registro, intentelo de nuevo.");
                }
            }
        }

        if (eleccion == 2){
            System.out.println("¿Cuál es tu nombre?");
                    String nombre = sc.next();
                System.out.println("¿Cuál es tu contraseña?");
                    String contraseña = sc.next();
            if (nombre.equals("admingeneral") && contraseña.equals("Passw0rd")) {
                System.out.println("Bienvenido Super Admin, que desea hacer?:");
                System.out.println("1 -> Crear torneo.");
                System.out.println("2 -> Iniciar sesion.");
                System.out.println("3 -> Cerrar sesion.");
                int choice = sc.nextInt();
                boolean booleanTorVal = false;
                String nombreTorneo = "";
                if (choice == 1){
                    while(!booleanTorVal){
                        System.out.println("Nombre del torneo: ");
                        nombreTorneo = sc.next();
                        for(int i = 0; i <= torneos.size(); i++){
                            if(torneos.get(i).getNombre().equals(nombreTorneo)){
                                System.out.println("Nombre del torneo en uso, escriba otro nombre.");
                            }
                    }
                    System.out.println("Cual es la region del torneo: ");
                    System.out.println("1 -> AMERICAS | 2 -> EMEA | 3 -> CHINA | 4 -> PACIFICO");
                    int  region = sc.nextInt();
                    char codRegion = '*';
                    switch (region) {
                        case 1:
                            codRegion = 'A';
                            break;
                        case 2:
                            codRegion = 'E';
                            break;

                        case 3:
                            codRegion = 'C';
                            break;

                        case 4:
                            codRegion = 'P';
                            break;

                        default:
                            break;
                    }
                    System.out.println("Cuantos puntos se llevara el ganador: ");
                    float puntosVictoria = sc.nextInt();
                    Torneo t1 = new Torneo(Registro.idGenerator(), nombreTorneo, codRegion, puntosVictoria);
                }
            } else {
                System.out.println("Bienvenido Entrenador, que desa hacer?:");
                System.out.println("1 -> Exportar mi carnet.");
                int choice = sc.nextInt();
                if(choice == 1){
                    //Exportar.exportCarnet(nombre, contraseña);
                }
            }
        }

        if (eleccion == 3){
            
        }

        if (eleccion == 4){

        }
    }
}