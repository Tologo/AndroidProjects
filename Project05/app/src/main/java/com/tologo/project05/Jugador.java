package com.tologo.project05;

/**
 * Created by Tologo on 31/01/2018.
 */

public class Jugador {

    private String nombre = "Invitado";
    private String nick = "Gamer";
    private int edad = 0;
    private int puntos = 0;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id = 0;

    public String toString(){
        return this.nombre + ". Nick: "+ this.nick + ". Edad: "+ this.edad + " años.";
    }
}
