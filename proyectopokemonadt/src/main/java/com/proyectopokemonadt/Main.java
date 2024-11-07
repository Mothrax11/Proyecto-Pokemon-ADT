package com.proyectopokemonadt;

import java.util.ArrayList;
import java.util.Scanner;

import com.proyectopokemonadt.autenticacion.Login;
import com.proyectopokemonadt.autenticacion.idGenerator;
import com.proyectopokemonadt.autenticacion.Registro;
import com.proyectopokemonadt.autenticacion.Usuario;
import com.proyectopokemonadt.clasesBasicas.Entrenador;
import com.proyectopokemonadt.clasesBasicas.Torneo;
import com.proyectopokemonadt.complementarias.ArchivadorTorneos;
import com.proyectopokemonadt.complementarias.Exportar;
import com.proyectopokemonadt.complementarias.Menus;
import com.proyectopokemonadt.complementarias.ShowNations;

public class Main {
    public static void main(String[] args) {
        ArrayList<Torneo> torneos = new ArrayList<>();
        Torneo torneoDefault = new Torneo("Primer Torneo Default", 'A', 0l);
        torneos.add(torneoDefault);
        Scanner sc = new Scanner(System.in);
        Menus.menuPrincipal();
        int eleccion = sc.nextInt();

        if (eleccion == 1) {
            Registro.registroData();

        }

        if (eleccion == 2) {
            System.out.println("¿Cuál es tu nombre?");
            String nombre = sc.next();
            System.out.println("¿Cuál es tu contraseña?");
            String contraseña = sc.next();
            System.out.println("¿Cuál es tu nacionalidad?");
            ShowNations.show();
            String nacionalidad = sc.next();
            if (nombre.equals("admintorneos") && contraseña.equals("Passw0rd")) {
                Menus.mostrarMenuAdministradorTorneos();
            } else if (nombre.equals("admingeneral") && contraseña.equals("Passw0rd")) {
                Menus.mostrarMenuAdminGeneral();
                int choice = sc.nextInt();
                boolean booleanTorVal = false;
                if (choice == 1) {
                    while (!booleanTorVal) {
                        Scanner topSc = new Scanner(System.in);
                        System.out.print("Nombre del torneo: ");
                        String nombreTorneo = topSc.next();
                        System.out.println("Cual es la region del torneo: ");
                        System.out.println("1 -> AMERICAS | 2 -> EMEA | 3 -> CHINA | 4 -> PACIFICO");
                        int region = topSc.nextInt();
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

                        // Revisa que el nuevo torneo no tiene el nombre de alguno ya creado
                        for (int i = 0; i < torneos.size(); i++) {
                            if (torneos.get(i).getNombre().equals(nombreTorneo)
                                    && (torneos.get(i).getCodRegion() == codRegion)) {
                                System.out.println(
                                        "Nombre del torneo con esa región en uso, escriba otro nombre o región.");
                            } else {
                                booleanTorVal = true;
                            }
                        }

                        System.out.print("Cuantos puntos se llevara el ganador: ");
                        float puntosVictoria = topSc.nextInt();
                        System.out.println("El torneo " + nombreTorneo + " ha sido creado con éxito");
                        Torneo torneo = new Torneo(nombreTorneo, codRegion, puntosVictoria);
                        ArchivadorTorneos.Archivador(torneoDefault);
                        System.out.print("¿Cual es el nombre del administrador del torneo?: ");
                        String nombreAT = topSc.next();
                        System.out.print("¿Cual es la contraseña del administrador del torneo?: ");
                        String passAT = topSc.next();
                        System.out.print("¿Cual es la nacionalidad  del administrador del torneo?: ");

                        ShowNations.show();
                        String nacionalidadAT = topSc.next();
                        new Usuario(nombreAT, passAT, nacionalidadAT, idGenerator.generador(), "AT");
                        Registro.registroDataAT(nombreAT, passAT, "AT", nacionalidadAT);
                        torneos.add(torneo);
                        booleanTorVal = true;
                        topSc.close();
                    }
                }

            } else {
                if (Login.getInstance().comprobarUsuario(nombre, contraseña, idGenerator
                        .generador(),
                        "ENT")) {
                    Entrenador activeUser = new Entrenador(nombre, contraseña, nacionalidad, idGenerator.generador());
                    activeUser.añadirTorneo(torneoDefault);
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        Exportar ex = new Exportar(activeUser);
                        ex.ejecutar();
                    } else if (choice == 2) {
                        System.out.println(
                                "Escriba el numero del torneo en el que se quiere inscribir o escriba '-1' para salir: ");
                        int numTorneo = 1;
                        for (int n = 0; n < torneos.size(); n++) {
                            System.out.println(numTorneo + " - " + torneos.get(n).getNombre());
                            numTorneo++;
                        }
                        int eleccionTorneo = sc.nextInt();

                        if (eleccionTorneo == -1) {
                            Menus.menuPrincipal();
                        } else {
                            torneos.get(eleccionTorneo - 1).inscribir(activeUser);
                            System.out.println("El usuario " + activeUser.getNombre()
                                    + " se ha inscrito correctamente en el torneo "
                                    + torneos.get(eleccionTorneo - 1).getNombre());
                        }

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
                } else if (nombre.equals("admintorneos") && contraseña.equals("Passw0rd")) {
                    Menus.mostrarMenuAdministradorTorneos();
                } else {
                    if (Login.getInstance().comprobarUsuario(nombre, contraseña, idGenerator.generador(), "ENT")) {

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
        }
        sc.close();
    }
}