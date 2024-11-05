package com.proyectopokemonadt.complementarias;

public class Menus {
    public static void menuPrincipal(){
        System.out.print("\033[H\033[2J");
        System.out.println("Bienvenido al Pokemon Torunamet 2025!");
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1 -> Registrarme.");
        System.out.println("2 -> Iniciar sesión.");
        System.out.println("3 -> Iniciar sesion como invitado");
    }

    public static void mostrarMenuInvitado(){
        System.out.print("\033[H\033[2J");
        System.out.println("¿Bienvenido Invitado, que desea hacer?");
        System.out.println("1 -> Iniciar sesion.");
        System.out.println("2 -> Salir.");
    }

    public static void mostrarMenuAdminGeneral(){
        System.out.print("\033[H\033[2J");
        System.out.println("Bienvenido Administrador General, ¿que desea hacer?");
        System.out.println("1 -> Crear torneo.");
        System.out.println("2 -> Iniciar sesion.");
        System.out.println("3 -> Cerrar sesion.");
    }

    public static void mostrarMenuEntrenador(){
        System.out.print("\033[H\033[2J");
        System.out.println("Bienvenido Entrenador, ¿que desea hacer?");
        System.out.println("1 -> Exportar mi carnet");
        }
    public static void mostrarMenuAdministradorTorneos(){
        System.out.print("\033[H\033[2J");
        System.out.println("Bienvenido Administrador de Torneos, ¿que desea hacer?");
        System.out.println("1 -> Salir.");
        }
       
}
