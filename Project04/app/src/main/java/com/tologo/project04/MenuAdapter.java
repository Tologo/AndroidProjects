package com.tologo.project04;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ElementoViewHolder> implements View.OnClickListener {

    // Llista amb el menú que volem mostrar
    private ArrayList<DatosMenu> listadoOpcionesDelMenu;

    //Objeto listener que declaramos e iniciaremos en el setOnclick
    private View.OnClickListener listener;

    // Constructor del adaptador, que rebrà la llista de menu a mostrar
    // i el context on s'executa l'activity que conté la llista
    public MenuAdapter (ArrayList<DatosMenu> parametre){
        this.listadoOpcionesDelMenu=parametre;
    }


    @Override
    public ElementoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layParams);
        //Implementamos el setOnclick sobre la vista
        v.setOnClickListener(this);
        return new ElementoViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ElementoViewHolder holder, int position) {
        DatosMenu element = listadoOpcionesDelMenu.get(position);
        holder.txtMenu.setText(element.getTitulo());
        holder.imgIcon.setImageResource(element.getIconoMenu());
        holder.txtMenu.setOnClickListener(this); // Li afegim el Listener al textView texto
    }

    @Override
    public int getItemCount() {
        return this.listadoOpcionesDelMenu.size();
    }

    //Método que pone en marcha el listener sobre los elementos o partes del RecyclerView
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    //Definimos el Método ListViewHolder e implementamos el Listener
    public class ElementoViewHolder extends RecyclerView.ViewHolder {
        //Definimos las variables a incluir ya creadas en el list_menu.xml
        private TextView txtMenu;
        private ImageView imgIcon;

        //Declaramos el Constructor
        public ElementoViewHolder(View v) {
            super(v);
            //Buscamos las variables por ID generadas list_menu.xml
            txtMenu = v.findViewById(R.id.txtMenu);
            imgIcon = v.findViewById(R.id.imgIcon);

        }

        //Creamos el Método bindView
        public void bindView(int position) {
            //Le pasamos el texto y las imagenes creadas en la clase DatosMenu y lo obtenemos por posición
            txtMenu.setText(listadoOpcionesDelMenu.get(position).getTitulo());
            imgIcon.setImageResource(listadoOpcionesDelMenu.get(position).getIconoMenu());
        }

        //Generamos el método onClick para los items del menu
        /*@Override
        public void onClick(View v) {
            int positon = getAdapterPosition();
            switch (positon) {
                case 0:
                    Log.d("CLICK", "Posición 1");
                    //v.getContext().startActivity(new Intent(v.getContext(), PerfilActivity.class)); //Pasamos a la Activity PERFIL
                    // MANEL Si en el model haguerem definit també l'atribut activityACarregar, caldria fer la crida de la següent manera:
                    //  v.getContext().startActivity(new Intent(v.getContext(),listadoOpcionesDelMenu.get(position).getActivityACarregar()));
                    break;
                case 1:
                    Log.d("CLICK", "Posición 2");
                    //v.getContext().startActivity(new Intent(v.getContext(), JuegoActivity.class)); //Pasamos a la Activity JUEGO
                    break;
                case 2:
                    Log.d("CLICK", "Posición 3");
                    //v.getContext().startActivity(new Intent(v.getContext(), InstActivity.class)); //Pasamos a la Activity INSTRUCCIONES
                    break;
                case 3:
                    Log.d("CLICK", "Posición 4");
                    //v.getContext().startActivity(new Intent(v.getContext(), InfoActivity.class)); //Pasamos a la Activity INFORMACIÓN
                    break;
            }
        }*/
    }
}