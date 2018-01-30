package com.tologo.quicktrade.model;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Tologo on 12/12/2017.
 */

public class Usuario {

    // Definimos las variables del usuario
    private String nick;
    private String correo;
    private String nombre;
    private String direccion;

    // Definimos un constructor por defecto para no tener problemas a la hora de 'refrescar' la bbdd en Firebase
    public Usuario(){

    }

    // Generamos el constructor de la clase
    public Usuario(String nick, String correo, String nombre, String direccion) {
        this.nick = nick;
        this.correo = correo;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Generamos los getters y setters de las variables
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    // Generamos un método toString
    @Override
    public String toString() {
        return "Usuario{" +
                "nick='" + nick + '\'' +
                ", correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
