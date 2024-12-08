package com.proyectopokemonadt.complementarias;

import com.mysql.cj.log.Log;
import com.proyectopokemonadt.DTO.EntrenadorDTO;
import com.proyectopokemonadt.DTO.TorneoDTO;
import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.ENTIDADES.Entrenador;
import com.proyectopokemonadt.ENTIDADES.Torneo;
import com.proyectopokemonadt.SERVICES.EntrenadorServices;
import com.proyectopokemonadt.SERVICES.TorneoServices;
import com.proyectopokemonadt.autenticacion.Login;
import com.proyectopokemonadt.autenticacion.Registro;
import com.proyectopokemonadt.autenticacion.Usuario;
import com.proyectopokemonadt.autenticacion.idGenerator;

import java.lang.runtime.SwitchBootstraps;
import java.security.CodeSigner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;
import javax.naming.InitialContext;

/**
 * Clase que contiene los métodos para mostrar los menús del sistema.
 * Estos menús permiten al usuario interactuar con diferentes opciones según su
 * rol
 * (invitado, administrador general, administrador de torneos o entrenador).
 * 
 * @author raullg97
 */
public class Menus {
    private static DataSource dataSource = DBConnection.getMySQLDataSource();
        static EntrenadorTemporal temp = null;
        /**
         * Muestra el menú principal del sistema, donde el usuario puede elegir entre
         * registrarse, iniciar sesión o iniciar sesión como invitado.
         */
        public static void menuPrincipal() {
            System.out.print("\033[H\033[2J");
    
            System.out.println("Bienvenido Invitado al Pokemon Torunament 2025!");
            System.out.println("¿Qué deseas hacer?");
            System.out.println("1 -> Registrarme.");
            System.out.println("2 -> Iniciar sesión.");
            System.out.println("3 -> Cerrar");
    
            Scanner sc = new Scanner(System.in);
            int eleccion = sc.nextInt();
    
            if(eleccion == 1){
                registrar();
    
            }
    
            if (eleccion == 2) {
               iniciarSesion();
    
            }
    
            if (eleccion == 3) {
                System.out.print("\033[H\033[2J");
                System.out.println("Hasta la próxima!");
                System.exit(0);
            }
        }
    
        public static void registrar(){
            Scanner sc = new Scanner(System.in);
            boolean vb = false;
            while (!vb) {
    
                System.out.println("¿Cuál es tu nombre?");
                String nombre = sc.next();
    
                System.out.println("¿Cuál es tu contraseña?");
                String contraseña = sc.next();
    
                System.out.println("¿Cuál es tu nacionalidad?");
                ShowNations.show();
                String nac = sc.next();
    
                while (!Registro.existeNacionalidad(nac)) {
                    System.out.println("Eliga una nacionalidad de la lista");
                    ShowNations.show();
                    nac = sc.next();
                }
    
                System.out.println("¿Desea continuar con los siguientes datos? S/N");
                System.out.println("Usuario: " + nombre);
                System.out.println("Contraseña: " + contraseña);
                System.out.println("Nacionalidad: " + nac);
                String respuesta = sc.next().toUpperCase();
    
                while (respuesta.equals("N")) {
    
                    System.out.print("Usuario: ");
                    nombre = sc.next();
                    System.out.print("Contraseña: ");
                    contraseña = sc.next();
                    System.out.println("Nacionalidad: ");
                    nac = sc.next();
    
                    while (!Registro.existeNacionalidad(nac)) {
                        System.out.println("Eliga una nacionalidad de la lista");
                        ShowNations.show();
                        nac = sc.next();
                    }
                    System.out.println("¿Desea continuar con los siguientes datos? S/N");
                    respuesta = sc.next().toUpperCase();
                }
                vb = true;
                temp = new EntrenadorTemporal(idGenerator.generador(), nombre, contraseña, nac);
                List<Combate> combatesEntrenador = new ArrayList<Combate>();
                List<Torneo> combatesTorneo = new ArrayList<Torneo>();
                int idParaEntrenador = idGenerator.generador();
                EntrenadorDTO edto = new EntrenadorDTO(idParaEntrenador, nombre, nac, 0f, combatesTorneo, combatesEntrenador);
                EntrenadorServices.getInstancia(dataSource).crearEntrenador(edto);
               if ( Registro.creadorUsuario(nombre, contraseña, "ENT", nac, idParaEntrenador )){
                    mostrarMenuEntrenador();
               }
                
        }
    }

    public static void iniciarSesion(){
        Scanner sc = new Scanner(System.in);
        String nombre;
        String contraseña;
        System.out.println("Ingrese su nombre de usuario:");
        nombre = sc.next();
        System.out.println("Ingrese su contraseña:");
        contraseña = sc.next();
        int idUsuario = Login.getInstance().comprobarUsuario(nombre, contraseña);

        if(nombre.equals("admingeneral") && contraseña.equals("Passw0rd")){
            mostrarMenuAdminGeneral();
        } else if (idUsuario != 0) {
            if (EntrenadorServices.getInstancia(dataSource).obtenerEntrenadorPorId(idUsuario) != null) {
                mostrarMenuEntrenador();
            } else {
                try {
                    System.out.println("Ha habido un error al iniciar sesión, volviendo al menú principal");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                menuPrincipal();
            }
        } else {
            try {
                System.out.println("Ha habido un error al iniciar sesión, volviendo al menú principal");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            menuPrincipal();
        }


    }

    /**
     * Muestra el menú para el administrador general.
     * El administrador general puede crear un torneo, iniciar sesión o cerrar
     * sesión.
     */
    public static void mostrarMenuAdminGeneral() {
        System.out.print("\033[H\033[2J");
        System.out.println("Bienvenido Administrador General, ¿que desea hacer?");
        System.out.println("1 -> Crear torneo.");
        System.out.println("2 -> Iniciar sesion.");
        System.out.println("3 -> Volver al menu principal.");
        System.out.println("4 -> Salir.");
        Scanner sc = new Scanner(System.in);
        int eleccion = sc.nextInt();

        switch (eleccion) {
            case 1:
                int idTorneo = idGenerator.generador();
                boolean torneoAceptado = false;
                while(torneoAceptado == false){
                    System.out.println("Ingrese el nombre del torneo:");
                    String nombre = sc.next();
                    System.out.println("Escoja una region para el torneo:");
                    System.out.println("1 - EMEA | 2 - AMERICAS | 3 - PACIFICO | 4 - CHINA");
                    int region = sc.nextInt();
                    char codRegion;
                    switch (region) {
                        case 1:
                            codRegion = 'E';
                            break;
                    
                        case 2:
                            codRegion = 'A';
                            break;
                    
                        case 3:
                            codRegion = 'P';
                            break;
                    
                        case 4:
                            codRegion = 'C';
                            break;
                    
                        default:
                            codRegion = 'X';
                            break;
                    }
                    float puntosVictoria;
                    System.out.println("Cuantos puntos se llevará el ganador?:");
                    puntosVictoria = sc.nextFloat();

                    System.out.println("Quiere crear el torneo con los siguientes datos? S/N:");
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Región del torneo: " + codRegion);
                    System.out.println("Puntos por victoria: " + puntosVictoria);
                    

                    String respuesta = sc.next();

                    if(respuesta.toUpperCase().equals("S")){
                        if (TorneoServices.getInstancia(dataSource).comprobarTorneoExiste(String.valueOf(codRegion),
                                nombre)) {
                            System.out.println("El torneo ya existe, desea salir o cambiar los datos del torneo?");
                            System.out.println("1 - Cambiar | 2 - Salir");
                            int eleccion2 = sc.nextInt();
                            switch (eleccion2) {
                                case 1:
                                    torneoAceptado = false;
                                    break;
                                case 2:
                                    mostrarMenuAdminGeneral();
                                    break;

                                default:
                                    break;
                            }
        
                        } else {
                            TorneoDTO torneoACrear = new TorneoDTO(idTorneo, nombre, codRegion, puntosVictoria);
                            if (TorneoServices.getInstancia(dataSource).crearTorneo(torneoACrear)) { 
                                try {
                                    System.out.println("Torneo creado con exito.");
                                    Thread.sleep(2000);
                                    torneoAceptado = true;
                                    mostrarMenuAdministradorTorneos();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    System.out.println("Ha habido un error al iniciar sesión, volviendo al menú de administrador de torneos:");
                                    Thread.sleep(2000);
                                    mostrarMenuAdministradorTorneos();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } 
                    }    
                }
                break;

            default:
                break;
        }
    }

    /**
     * Muestra el menú para el entrenador.
     * El entrenador puede exportar su carnet.
     */
    public static void mostrarMenuEntrenador() {
        Scanner sc = new Scanner(System.in);
        // Limpia la consola (dependiendo de la terminal)
        System.out.println("---------------------------------------------------------------");
        System.out.println("Bienvenido Entrenador, ¿que desea hacer?");
        System.out.println("1 -> Exportar mi carnet");
        System.out.println("2 -> Ver torneos activos");
        System.out.println("3 -> Volver al menu principal");
        System.out.println("4 -> Salir");
        System.out.println("---------------------------------------------------------------");
        int eleccion = sc.nextInt();

       if(eleccion == 1){

       }

       if(eleccion == 2){
            List<Torneo> torneos = TorneoServices.getInstancia(dataSource).obtenerTodosLosTorneos();
            TorneoServices.getInstancia(dataSource).obtenerTodosLosTorneosToString();
        }

       if(eleccion == 3){
        menuPrincipal();
       }

       if(eleccion == 4){
           cerrarPrograma();
       }
        
    }

    /**
     * Muestra el menú para el administrador de torneos.
     * El administrador de torneos puede salir del sistema.
     */
    public static void mostrarMenuAdministradorTorneos() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Bienvenido Administrador de Torneos, ¿que desea hacer?");
        System.out.println("1 -> Mostrar mi torneo por pantalla.");
        System.out.println("2 -> Exportar mi torneo en XML.");
        System.out.println("3 -> Volver al menu principal.");
        System.out.println("4 -> Salir.");
        Scanner sc = new Scanner(System.in);
        int eleccion = sc.nextInt();
        switch (eleccion) {
            case 1:
                
                break;
        
            case 2:
                
                break;
        
            case 3:
                menuPrincipal();
                break;
        
            case 4:
                cerrarPrograma();
                break;
        
            default:
                break;
        }
    }

    public static void cerrarPrograma(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("Hasta la próxima!");
        System.exit(0);
    }
}
