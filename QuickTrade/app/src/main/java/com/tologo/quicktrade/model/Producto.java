package com.tologo.quicktrade.model;

/**
 * Created by Tologo on 03/01/2018.
 */

public class Producto {

    // Definimos las variables del usuario
    private String usuario;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String precio;

    // Definimos un constructor por defecto para no tener problemas a la hora de 'refrescar' la bbdd en Firebase
    public Producto(){

    }

    // Generamos el constructor de la clase
    public Producto(String usuario, String nombre, String descripcion, String categoria, String precio) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }

    // Generamos los getters y setters de las variables
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    // Generamos un método toString sin incorporar el dato de usuario
    @Override
    public String toString() {
        return nombre + ". " + descripcion + ". Categoría: " + categoria + ". Precio: " + precio + "€";
    }

}
