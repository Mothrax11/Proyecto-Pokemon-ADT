package com.proyectopokemonadt;

import java.util.ArrayList;
import java.util.Scanner;

import com.proyectopokemonadt.autenticacion.Login;
import com.proyectopokemonadt.autenticacion.idGenerator;
import com.proyectopokemonadt.autenticacion.Registro;
import com.proyectopokemonadt.autenticacion.Usuario;
import com.proyectopokemonadt.clasesBasicas.Entrenador;
import com.proyectopokemonadt.clasesBasicas.Torneo;
import com.proyectopokemonadt.complementarias.Menus;
import com.proyectopokemonadt.complementarias.ShowNations;

public class Main {

    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        ArrayList<Torneo> torneos = new ArrayList<>();
        Usuario adminTorneosDefault = new Usuario("admintorneos", "123", "ES", 2, "AT");
        Torneo torneoDefault = new Torneo("Primer Torneo Default", 'A', 0l);
        torneos.add(torneoDefault);
        Scanner sc = new Scanner(System.in);
        Menus.menuPrincipal();
        int eleccion = sc.nextInt();

        if (eleccion == 1) {
            Registro.registroData();
            menu();
            
        }

        if (eleccion == 2) {
            System.out.println("¿Cuál es tu nombre?");
            String nombre = sc.next();
            System.out.println("¿Cuál es tu contraseña?");
            String contraseña = sc.next();
            String nacionalidad = sc.nextLine();
            
            if (nombre.equals("admingeneral") && contraseña.equals("Passw0rd")) {
                Menus.mostrarMenuAdminGeneral();
                int choice = sc.nextInt();
                boolean booleanTorVal = false;
                String nombreTorneo;
                if (choice == 1) {
                    while (!booleanTorVal) {
                        System.out.println("Nombre del torneo: ");
                        nombreTorneo = sc.next();
                        // Revisa que el nuevo torneo no tiene el nombre de alguno ya creado
                        for (int i = 0; i < torneos.size(); i++) {
                            if (torneos.get(i).getNombre().equals(nombreTorneo)) {
                                System.out.println("Nombre del torneo en uso, escriba otro nombre.");
                            } else {
                                booleanTorVal = true;
                            }
                        }

                        System.out.println("Cual es la region del torneo: ");
                        System.out.println("1 -> AMERICAS | 2 -> EMEA | 3 -> CHINA | 4 -> PACIFICO");
                        int region = sc.nextInt();
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

                        System.out.print("Cuantos puntos se llevara el ganador: ");
                        float puntosVictoria = sc.nextInt();
                        System.out.print("El torneo " + nombreTorneo + "ha sido creado con éxito");
                        Torneo torneo = new Torneo(nombreTorneo, codRegion, puntosVictoria);
                        System.out.print("¿Cual es el nombre del administrador del torneo?: ");
                        String nombreAT = sc.next();
                        System.out.print("¿Cual es la contraseña del administrador del torneo?: ");
                        String passAT = sc.next();
                        System.out.print("¿Cual es la ncionalidad  del administrador del torneo?: ");

                        ShowNations.show();
                        String nacionalidadAT = sc.next();
                        Registro.registroData();
                        Usuario adminTorneos = new Usuario(nombreAT, passAT, nacionalidadAT, idGenerator.generador(), "AT");
                        torneos.add(torneo);
                        booleanTorVal = false;
                    }
                }

            } else {
                if (Login.getInstance().comprobarUsuario(nombre, contraseña, idGenerator
                        .generador(),
                        "ENT")) {
                    Entrenador activeUser = new Entrenador(nombre, contraseña, nacionalidad, idGenerator.generador());
                    activeUser.añadirTorneo(torneoDefault);
                    Menus.mostrarMenuEntrenador();
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        Exportar ex = new Exportar(activeUser);
                        ex.ejecutar();
                    }
                }
            }
        }

        if (eleccion == 3) {
            Menus.mostrarMenuInvitado();
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.print("¿Cuál es tu nombre?");
                String nombre = sc.next();
                System.out.print("¿Cuál es tu contraseña?");
                String contraseña = sc.next();
                System.out.print("¿Cuál es tu nacionalidad?");
                ShowNations.show();
                String nacionalidad = sc.next();
                if (nombre.equals("admingeneral") && contraseña.equals("Passw0rd")) {
                    Menus.mostrarMenuAdminGeneral();
                } else {
                    if (Login.getInstance().comprobarUsuario(nombre, contraseña, 
                            idGenerator.generador(),"ENT")) {
                                
                        Entrenador activeUser = new Entrenador(nombre, contraseña, nacionalidad, 
                                idGenerator.generador());
                        Menus.mostrarMenuEntrenador();
                        choice = sc.nextInt();
                        if (choice == 1) {
                            Exportar ex = new Exportar(activeUser);
                            ex.ejecutar();
                        }
                    }
                }
            }
            sc.close();
        }
        sc.close();
    }
}