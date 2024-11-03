package com.proyectopokemonadt;

import java.util.ArrayList;
import java.util.Scanner;

import com.proyectopokemonadt.clasesBasicas.Entrenador;
import com.proyectopokemonadt.clasesBasicas.Torneo;

public class Main {

    public static void main(String[] args) {
        ArrayList<Torneo> torneos = new ArrayList<>();
        Torneo torneoDefault = new Torneo(1l, "Torneo Default", 'E', 0l,1000000);
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
            if (nombre.equals("admingeneral") && contraseña.equals("Passw0rd")) {
                Menus.mostrarMenuAdminGeneral();
                int choice = sc.nextInt();
                boolean booleanTorVal = false;
                String nombreTorneo = "";
                long idTorneo = 100000l;

                if (choice == 1) {
                    while (!booleanTorVal) {
                        System.out.println("Nombre del torneo: ");
                        nombreTorneo = sc.next();
                        // Revisa que el nuevo torneo no tiene el nombre de alguno ya creado
                        for (int i = 0; i < torneos.size(); i++) {
                            if (torneos.get(i).getNombre().equals(nombreTorneo)) {
                                System.out.println("Nombre del torneo en uso, escriba otro nombre.");
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

                        System.out.println("Cuantos puntos se llevara el ganador: ");
                        float puntosVictoria = sc.nextInt();

                        System.out.println("El torneo " + nombreTorneo + "ha sido creado con éxito");
                        System.out.println("¿Cual es el id del administrador responsable?: ");
                        long idResponsable = sc.nextInt();

                        Torneo t1 = new Torneo(idTorneo, nombreTorneo, codRegion, puntosVictoria, idResponsable);
                        idTorneo++;
                        booleanTorVal = true;
                    }
                }

            } else {
                if (Login.getInstance().comprobarUsuario(nombre, contraseña, nacionalidad, Registro.idGenerator(),
                        "ENT")) {
                    Entrenador activeUser = new Entrenador(nombre, contraseña, nacionalidad, Registro.idGenerator(), torneos);
                    Menus.mostrarMenuEntrenador();
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        Exportar ex = new Exportar(activeUser.getNombre(), activeUser.getNacionalidad(), activeUser.getPuntos(), activeUser.getFechaCreacion(), activeUser.getId(), torneos);
                        ex.ejecutar();
                        System.out.println(activeUser.getId());
                    }
                }
            }
        }

        if (eleccion == 3) {
            Menus.mostrarMenuInvitado();
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("¿Cuál es tu nombre?");
                String nombre = sc.next();
                System.out.println("¿Cuál es tu contraseña?");
                String contraseña = sc.next();
                System.out.println("¿Cuál es tu nacionalidad?");
                ShowNations.show();
                String nacionalidad = sc.next();
                if (nombre.equals("admingeneral") && contraseña.equals("Passw0rd")) {
                    Menus.mostrarMenuAdminGeneral();
                } else {
                    if (Login.getInstance().comprobarUsuario(nombre, contraseña, nacionalidad, Registro.idGenerator(),"ENT")) {
                                
                        Entrenador activeUser = new Entrenador(nombre, contraseña, nacionalidad, Registro.idGenerator(), torneos);
                        Menus.mostrarMenuEntrenador();
                        choice = sc.nextInt();
                        if (choice == 1) {
                            Exportar ex = new Exportar(activeUser.getNombre(), activeUser.getPass(),
                                    activeUser.getPuntos(), activeUser.getFechaCreacion(), activeUser.getId(), torneos);
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