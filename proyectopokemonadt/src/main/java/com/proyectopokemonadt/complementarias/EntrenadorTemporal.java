package com.proyectopokemonadt.complementarias;

import java.util.List;

import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.ENTIDADES.Torneo;

public class EntrenadorTemporal {
       private int id;
       private String nombre;
       private String nacionalidad;
       private float puntos;
       private List<Combate> combatesEntrenador;
       private List<Torneo> torneosEntrenador;
       private String contrasena;
   
       public EntrenadorTemporal(int id, String nombre, String nacionalidad, float puntos, List<Combate> combatesEntrenador, List<Torneo> torneosEntrenador, String contrasena) {
           this.id = id;
           this.nombre = nombre;
           this.nacionalidad = nacionalidad;
           this.puntos = puntos;
           this.combatesEntrenador = combatesEntrenador;
           this.torneosEntrenador = torneosEntrenador;
           this.contrasena = contrasena;
       }
   
       public EntrenadorTemporal() {
       }
   
       public int getId() {
           return id;
       }
   
       public void setId(int id) {
           this.id = id;
       }
   
       public String getNombre() {
           return nombre;
       }
   
       public void setNombre(String nombre) {
           this.nombre = nombre;
       }
   
       public String getNacionalidad() {
           return nacionalidad;
       }
   
       public void setNacionalidad(String nacionalidad) {
           this.nacionalidad = nacionalidad;
       }
   
       public float getPuntos() {
           return puntos;
       }
   
       public void setPuntos(float puntos) {
           this.puntos = puntos;
       }
   
       public List<Combate> getCombatesEntrenador() {
           return combatesEntrenador;
       }
   
       public void setCombatesEntrenador(List<Combate> combatesEntrenador) {
           this.combatesEntrenador = combatesEntrenador;
       }
   
       public List<Torneo> getTorneosEntrenador() {
           return torneosEntrenador;
       }
   
       public void setTorneosEntrenador(List<Torneo> torneosEntrenador) {
           this.torneosEntrenador = torneosEntrenador;
       }
   
       public String getContrasena() {
           return contrasena;
       }
   
       public void setContrasena(String contrasena) {
           this.contrasena = contrasena;
       }
   }

