package com.proyectopokemonadt.complementarias;

/**
 * Clase que contiene los métodos para mostrar los menús del sistema.
 * Estos menús permiten al usuario interactuar con diferentes opciones según su
 * rol
 * (invitado, administrador general, administrador de torneos o entrenador).
 * 
 * @author raullg97
 */
public class Menus {

    /**
     * Muestra el menú principal del sistema, donde el usuario puede elegir entre
     * registrarse, iniciar sesión o iniciar sesión como invitado.
     */
    public static void menuPrincipal() {
        // Limpia la consola (dependiendo de la terminal)
        System.out.print("\033[H\033[2J");

        // Muestra las opciones del menú principal
        System.out.println("Bienvenido al Pokemon Torunamet 2025!");
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1 -> Registrarme.");
        System.out.println("2 -> Iniciar sesión.");
        System.out.println("3 -> Iniciar sesion como invitado");
    }

    /**
     * Muestra el menú para el usuario invitado.
     * El invitado puede iniciar sesión o salir del sistema.
     */
    public static void mostrarMenuInvitado() {
        // Limpia la consola (dependiendo de la terminal)
        System.out.print("\033[H\033[2J");

        // Muestra las opciones del menú para el invitado
        System.out.println("¿Bienvenido Invitado, que desea hacer?");
        System.out.println("1 -> Iniciar sesion.");
        System.out.println("2 -> Salir.");
    }

    /**
     * Muestra el menú para el administrador general.
     * El administrador general puede crear un torneo, iniciar sesión o cerrar
     * sesión.
     */
    public static void mostrarMenuAdminGeneral() {
        // Limpia la consola (dependiendo de la terminal)
        System.out.print("\033[H\033[2J");

        // Muestra las opciones del menú para el administrador general
        System.out.println("Bienvenido Administrador General, ¿que desea hacer?");
        System.out.println("1 -> Crear torneo.");
        System.out.println("2 -> Iniciar sesion.");
        System.out.println("3 -> Cerrar sesion.");
    }

    /**
     * Muestra el menú para el entrenador.
     * El entrenador puede exportar su carnet.
     */
    public static void mostrarMenuEntrenador() {
        // Limpia la consola (dependiendo de la terminal)
        System.out.print("\033[H\033[2J");

        // Muestra las opciones del menú para el entrenador
        System.out.println("Bienvenido Entrenador, ¿que desea hacer?");
        System.out.println("1 -> Exportar mi carnet");
        System.out.println("2 -> Ver torneos activos");
        System.out.println("3 -> Volver al menu principal");
        System.out.println("4 -> Salir");
    }

    /**
     * Muestra el menú para el administrador de torneos.
     * El administrador de torneos puede salir del sistema.
     */
    public static void mostrarMenuAdministradorTorneos() {
        // Limpia la consola (dependiendo de la terminal)
        System.out.print("\033[H\033[2J");

        // Muestra las opciones del menú para el administrador de torneos
        System.out.println("Bienvenido Administrador de Torneos, ¿que desea hacer?");
        System.out.println("1 -> Salir.");
    }
}
