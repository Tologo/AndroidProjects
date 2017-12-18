package com.tologo.project03Fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<String> listaMenu; // Llista amb el menú que volem mostrar

    // Classe interna que defineix el viewHolder.
    // El ViewHolder será un objecte que ens permetrà accedir a tots els
    // camps de l'XML que dissenya el contingut d'un item de la llista
    public static class MenuViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener{
        //elements d'un ítem a mostrar
        public ImageView icon;
        public TextView texto;

        //Constructor de MenuViewHolder
        public MenuViewHolder(View v) {
            super(v);
            icon = (ImageView) v.findViewById(R.id.imgIcon);
            texto = (TextView) v.findViewById(R.id.txtMenu);
            texto.setOnClickListener(this); // Li afegim el Listener al textView texto
        }

    @Override
    // Definim les accions que volem realitzar cada vega que l'usuari faça un click.
    public void onClick(View view) {
            // Sin implementar en esta versión

        /*int idView = view.getId();  // Agafem l'identificador del wiew on s'ha produït l'event

        if (texto.getId()==idView){  //Han fet click sobre el textview texto
            //Snackbar.make(view,"Has polsat en el nom",Snackbar.LENGTH_LONG).show();
        }*/
    }
}

    // El constructor de l'adaptador, rebrà la llista de menu a mostrar
    // i el context on s'executa l'activity que conté la llista
    public MenuAdapter(List<String> lista){

        this.listaMenu=lista;
    }

    @Override
    public int getItemCount(){
        return this.listaMenu.size();
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.texto.setText(listaMenu.get(position));
    }

}