package com.tologo.project05;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// Extendemos la clase de RecyclerView.Adapter porque se trata de una adaptador para RecyclerView
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ElViewHolder> implements View.OnClickListener {

    // Llista amb el menú que volem mostrar
    private List<DatosMenu> listadoOpcionesDelMenu;

    //Objeto listener que declaramos e iniciaremos en el setOnclick
    private View.OnClickListener listener;

    // Constructor del adaptador, que rebrà la llista de menu a mostrar
    // i el context on s'executa l'activity que conté la llista
    public MenuAdapter(List<DatosMenu> lista){
        listadoOpcionesDelMenu = lista;
    }

    // Método con el que inflamos el menú de ítems diseñado
    @Override
    public ElViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layParams);
        //Implementamos el setOnclick sobre la vista
        v.setOnClickListener(this);
        return new ElViewHolder(v);
    }

    public class ElViewHolder extends RecyclerView.ViewHolder {
        //Variables de la clase Elemento
        public ImageView imagen;
        public TextView nombre;

        //Constructor ViewHolder que nos servirá par asignar a cada parte del layout su variable
        public ElViewHolder(View v){
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imgIcon);
            nombre = (TextView) v.findViewById(R.id.txtMenu);
        }
        public TextView getNombre() {
            return nombre;
        }

    }

    // Método con el que crear el ViewHolder
    @Override
    public void onBindViewHolder(ElViewHolder holder, int position) {
        DatosMenu element = listadoOpcionesDelMenu.get(position);
        holder.nombre.setText(element.getTitulo());
        holder.imagen.setImageResource(element.getIconoMenu());
        //holder.nombre.setOnClickListener(this); // Li afegim el Listener al textView texto
    }

    @Override
    public int getItemCount() {
        return this.listadoOpcionesDelMenu.size();
    }

    //Método que pone en marcha el listener sobre los elementos o partes del RecyclerView
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    //Generamos el método onClick para los items del menu
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }

    }

}