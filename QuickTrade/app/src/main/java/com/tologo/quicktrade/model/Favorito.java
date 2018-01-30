package com.tologo.quicktrade.model;

/**
 * Created by Tologo on 30/01/2018.
 */

public class Favorito {

    // Definimos las variables del usuario
    private String usuario;
    private String producto;

    // Definimos un constructor por defecto para no tener problemas a la hora de 'refrescar' la bbdd en Firebase
    public Favorito(String usuario, Producto producto){

    }

    // Generamos el constructor de la clase
    public Favorito(String usuario, String producto) {
        this.usuario = usuario;
        this.producto = producto;
    }

    // Generamos los getters y setters de las variables
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }


    // Generamos un método toString sin incorporar el dato de usuario
    @Override
    public String toString() {
        return usuario + ". " + producto;
    }

}


