package com.tologo.project04;

import android.os.Parcel;
import android.os.Parcelable;


public class DatosMenu implements Parcelable {

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

    // Métodos necesarios al hacer esta clase Parcelable

    protected DatosMenu(Parcel in) {
        titulo = in.readString();
        iconoMenu = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeInt(iconoMenu);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DatosMenu> CREATOR = new Parcelable.Creator<DatosMenu>() {
        @Override
        public DatosMenu createFromParcel(Parcel in) {
            return new DatosMenu(in);
        }

        @Override
        public DatosMenu[] newArray(int size) {
            return new DatosMenu[size];
        }
    };

}
