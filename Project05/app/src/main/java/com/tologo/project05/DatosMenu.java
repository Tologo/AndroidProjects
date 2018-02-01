package com.tologo.project05;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class DatosMenu implements Serializable {

    //Datos de Texto
    private  String titulo;
    //Datos de Imagenes
    private  int iconoMenu;
    //Potser convindria indicar quin activity hauria d'invocar-se?
    //private Class activityACarregar; No esta implementat. Seria una possible ampliació del projecte
    // Caldria afegir aci els getters i setters i a més a més, Ccaldria canviar el mètode onClick() de la classe MenuAdapter.

    //Constructor
    public DatosMenu(String tit, int icona){
        this.titulo=tit;
        this.iconoMenu=icona;
    }
    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIconoMenu() {
        return iconoMenu;
    }

    public void setIconoMenu(int iconoMenu) {
        this.iconoMenu = iconoMenu;
    }



}
