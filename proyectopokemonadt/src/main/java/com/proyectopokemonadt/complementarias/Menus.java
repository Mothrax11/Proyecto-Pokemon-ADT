package com.proyectopokemonadt.complementarias;

import com.proyectopokemonadt.DTO.CombateDTO;
import com.proyectopokemonadt.DTO.EntrenadorDTO;
import com.proyectopokemonadt.DTO.TorneoAdminDTO;
import com.proyectopokemonadt.DTO.TorneoDTO;
import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.ENTIDADES.Torneo;
import com.proyectopokemonadt.SERVICES.CombateServices;
import com.proyectopokemonadt.SERVICES.EntrenadorServices;
import com.proyectopokemonadt.SERVICES.TorneoAdminServices;
import com.proyectopokemonadt.SERVICES.TorneoServices;
import com.proyectopokemonadt.autenticacion.Login;
import com.proyectopokemonadt.autenticacion.Registro;
import com.proyectopokemonadt.autenticacion.idGenerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.sql.DataSource;

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
                List<Combate> combatesEntrenador = new ArrayList<Combate>();
                List<Torneo> combatesTorneo = new ArrayList<Torneo>();
                int idParaEntrenador = idGenerator.generador();
                EntrenadorDTO edto = new EntrenadorDTO(idParaEntrenador, nombre, nac, 0f, combatesTorneo, combatesEntrenador);
                EntrenadorTemporal temp = new EntrenadorTemporal(edto.getId(), edto.getNombre(),
                        edto.getNacionalidad(), edto.getPuntos(),
                        edto.getCombatesEntrenador(), edto.getTorneosEntrenador(), contraseña);
                EntrenadorServices.getInstancia(dataSource).crearEntrenador(edto);

                if (Registro.creadorUsuario(nombre, contraseña, "ENT", nac, idParaEntrenador )){
                    boolean apuntado = false;
                    while(apuntado == false){
                        System.out.println("Al registrarse, debe inscribirse en un torneo.");
                        System.out.println("en cual se quiere apuntar? (Introducir numero): ");
                        List<Torneo> torneos = TorneoServices.getInstancia(dataSource).obtenerTodosLosTorneos();
                        TorneoServices.getInstancia(dataSource).obtenerTodosLosTorneosToString();
                        int eleccionTorneo = sc.nextInt();
                        Random rm = new Random();

                        if (rm.nextInt(100) >= 0 && rm.nextInt(100) < 50) {
                            System.out.println(
                                    "Se ha apuntado al torneo " + torneos.get(eleccionTorneo).getNombre() + " con exito.");
                                    apuntado = true;
                        } else {
                            System.out.println("El torneo " + torneos.get(eleccionTorneo).getNombre() + " está lleno");
                        }
                        
                        try {
                            System.out.println("Volviendo al menu de entrenador");
                            Thread.sleep(2000);
                            mostrarMenuEntrenador(temp);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
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
        } else if (nombre.equals("pelayo") && contraseña.equals("123")) {

            int idAdminT = Login.getInstance().obtenerIdPorNombreContraseña(nombre, contraseña);

            EntrenadorTemporal temp = new EntrenadorTemporal(idAdminT,nombre,
                    "US", 0,
                    null,
                    null,
                     contraseña);

            mostrarMenuAdministradorTorneos(temp);
        
        } else if (idUsuario != 0) {
            if (EntrenadorServices.getInstancia(dataSource).obtenerEntrenadorPorId(idUsuario) != null) {
                int idABuscar = Login.getInstance().obtenerIdPorNombreContraseña(nombre, contraseña);
                EntrenadorDTO entrenadorDTO = EntrenadorServices.getInstancia(dataSource).obtenerEntrenadorPorId(idABuscar);
                EntrenadorTemporal temp = new EntrenadorTemporal(entrenadorDTO.getId(), entrenadorDTO.getNombre(), entrenadorDTO.getNacionalidad(), entrenadorDTO.getPuntos(), entrenadorDTO.getCombatesEntrenador(), entrenadorDTO.getTorneosEntrenador(), contraseña);
                mostrarMenuEntrenador(temp);
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
        System.out.println("2 -> Volver al menu principal.");
        System.out.println("3 -> Salir.");
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
                                    for(int k = 0; k <= 2; k++){
                                        CombateServices.getInstancia().crearCombate(new CombateDTO(idGenerator.generador(), LocalDate.now(), idTorneo));
                                    }
                                    System.out.println("Torneo creado con exito.");
                                    Thread.sleep(2000);
                                    torneoAceptado = true;
                                    TorneoAdminDTO torneoAdmin = new TorneoAdminDTO(idTorneo, 6);
                                    TorneoAdminServices.getInstancia(dataSource).crearTorneoAdmin(torneoAdmin);
                                    mostrarMenuAdminGeneral();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    System.out.println("Ha habido un error al iniciar sesión, volviendo al menú de administrador de torneos:");
                                    Thread.sleep(2000);
                                    mostrarMenuAdminGeneral();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } 
                    }    
                }
                break;

            case 2:
                menuPrincipal();
                break;

            case 3:
                cerrarPrograma();
                break;

            default:
                break;
        }
    }

    /**
     * Muestra el menú para el entrenador.
     * El entrenador puede exportar su carnet.
     */
    public static void mostrarMenuEntrenador(EntrenadorTemporal temp) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------");
        System.out.println("Bienvenido Entrenador, ¿que desea hacer?");
        System.out.println("1 -> Exportar mi carnet");
        System.out.println("2 -> Ver torneos activos");
        System.out.println("3 -> Volver al menu principal");
        System.out.println("4 -> Salir");
        System.out.println("---------------------------------------------------------------");
        int eleccion = sc.nextInt();

       if(eleccion == 1){
        Exportar ex = new Exportar(temp);
        if(ex.ejecutar()){
    
            try {
                System.out.println("Carnet exportado con exito, volviendo al menu de entrenador");
                Thread.sleep(2000);
                mostrarMenuEntrenador(temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       }

       if(eleccion == 2){
           System.out.println("A que torneo le gustaria apuntarse? (Introducir numero):");
            List<Torneo> torneos = TorneoServices.getInstancia(dataSource).obtenerTodosLosTorneos();
            TorneoServices.getInstancia(dataSource).obtenerTodosLosTorneosToString();
            int eleccionTorneo = sc.nextInt();
            Random rm = new Random();
            
            if(rm.nextInt(100) >= 0 && rm.nextInt(100) < 50){
                System.out.println("Se ha apuntado al torneo " + torneos.get(eleccionTorneo).getNombre() + " con exito.");
            } else {
                System.out.println("El torneo " + torneos.get(eleccionTorneo).getNombre() + " está lleno");
            }
            try {
                System.out.println("Volviendo al menu de entrenador");
                Thread.sleep(2000);
                mostrarMenuEntrenador(temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
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
    public static void mostrarMenuAdministradorTorneos(EntrenadorTemporal temp) {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Bienvenido Administrador de Torneos, ¿que desea hacer?");
        System.out.println("1 -> Mostrar el nombre mis torneos por pantalla.");
        System.out.println("2 -> Exportar mi torneo en TXT.");
        System.out.println("3 -> Mostrar el un torneo concreto por pantalla.");
        System.out.println("4 -> Volver al menu principal.");
        System.out.println("5 -> Salir.");
        Scanner sc = new Scanner(System.in);
        int eleccion = sc.nextInt();
        List<Integer> listatorneos;
        int idT;
        switch (eleccion) {
            case 1:
                listatorneos = TorneoAdminServices.getInstancia(dataSource).obtenerTorneosPorIdAdministrador(temp.getId());
                for(int k = 0; k <= listatorneos.size()-1; k++){
                    System.out.println(
                    TorneoServices.getInstancia(dataSource).obtenerTorneoPorId(listatorneos.get(k)).getId() + 
                    " - " +
                    TorneoServices.getInstancia(dataSource).obtenerTorneoPorId(listatorneos.get(k)).getNombre());
                 }
                mostrarMenuAdministradorTorneos(temp);
                break;
        
            case 2:
                System.out.println("Escriba el numero del torneo que desea exportar a .txt");
                listatorneos = TorneoAdminServices.getInstancia(dataSource).obtenerTorneosPorIdAdministrador(temp.getId());
                for (int k = 0; k <= listatorneos.size() - 1; k++) {
                    System.out.println(
                    TorneoServices.getInstancia(dataSource).obtenerTorneoPorId(listatorneos.get(k)).getId() +
                    " - " +
                    TorneoServices.getInstancia(dataSource).obtenerTorneoPorId(listatorneos.get(k)).getNombre());
                }
                idT = sc.nextInt();
                ArchivadorTorneos art = new ArchivadorTorneos();
                art.crearFichero(TorneoServices.getInstancia(dataSource).obtenerTorneoPorId(idT));
                break;

            case 3:
                System.out.println("Escriba el numero del torneo que desea exportar a .txt");
                listatorneos = TorneoAdminServices.getInstancia(dataSource)
                        .obtenerTorneosPorIdAdministrador(temp.getId());
                for (int k = 0; k <= listatorneos.size() - 1; k++) {
                    System.out.println(
                        TorneoServices.getInstancia(dataSource).obtenerTorneoPorId(listatorneos.get(k)).getId() +
                        " - " +
                        TorneoServices.getInstancia(dataSource).obtenerTorneoPorId(listatorneos.get(k)).getNombre());
                }
                idT = sc.nextInt();
                TorneoDTO torneoAMostrar = TorneoServices.getInstancia(dataSource).obtenerTorneoPorId(idT);
                System.out.println("Nombre: " + torneoAMostrar.getNombre());
                System.out.println("Codigo de Región: " + torneoAMostrar.getCodRegion());
                System.out.println("Combates del torneo: ");
                List<CombateDTO> combatesDelTorneo = CombateServices.getInstancia().obtenerTodosLosCombatesPorIdTorneo(idT);
                for (int k = 0; k <= combatesDelTorneo.size() - 1; k++){
                    System.out.println("Combate " + k 
                    + " | " 
                    + combatesDelTorneo.get(k).getFecha() 
                    + " | " 
                    + "Entrenador 1: "
                    + combatesDelTorneo.get(k).getEntrenador1()    
                    + " | " 
                    + "Entrenador 2: "
                    + combatesDelTorneo.get(k).getEntrenador2());
                }
                System.out.println("Puntos por victoria: " + torneoAMostrar.getPuntosVictoria() );
                break;
        
            case 4:
                menuPrincipal();
                break;
        
            case 5:
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
