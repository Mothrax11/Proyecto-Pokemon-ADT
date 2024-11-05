package com.proyectopokemonadt.autenticacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import com.proyectopokemonadt.complementarias.*;



public class Login {
    private static Login instance;

    private Login() {}

    public static Login getInstance(){
        if(instance == null){
            instance = new Login();
        }
        return instance;
    }

    public static void writeLog(String usuario) {
        LocalDate fecha = LocalDate.now();
        LocalTime lt  = LocalTime.now();

        File log = new File("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios", "log.txt");
        try {
            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("[" + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear() + "|" + lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond() + "]" +"-> " + usuario);
            System.out.println("[" + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear() + "|"
                    + lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond() + "]" +"->" + " El usuario " + usuario + " ha sido logueado correctamente.");
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean comprobarUsuario(String nombre, String pass, long id, String rol) {
            String buscar;
            File rd = new File("proyectopokemonadt\\src\\main\\java\\com\\proyectopokemonadt\\archviosComplementarios","credenciales.txt");

            try {
                FileReader fr = new FileReader(rd);
                BufferedReader br = new BufferedReader(fr);
                
                while ((buscar = br.readLine()) != null) {
                    String [] palabrasLinea = buscar.split(" ");
                    if(palabrasLinea[0].equals(nombre) && palabrasLinea[1].equals(pass)){
                        if(palabrasLinea[2].equals("AT")){
                            Menus.mostrarMenuAdministradorTorneos();
                        }
                        writeLog(nombre);
                        br.close();
                        return true;
                    }
                }
                br.close();
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        return true;
    }
}