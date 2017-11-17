package com.tologo.contextmenu_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView list_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos las referencias a los controles
        list_item = (ListView) findViewById(R.id.list_item);

        // Ahora definimos los valores del Array que rellenará el listView
        String[] datos = new String[]{"Víctor","Silvia","Manolo","Carlos","Ana"};

        // Definimos un adaptador para el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, datos);
        // Asignamos el adaptador al ListView
        list_item.setAdapter(adapter);

        //Asociamos los menús contextuales a los controles
        registerForContextMenu(list_item);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(
                    list_item.getAdapter().getItem(info.position).toString());
            inflater.inflate(R.menu.menu_ctx_lista, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Creamos un objeto de tipo AdapterContextMenuInfo que nos ayuda a identificar el ítem del ListView que hemos presionado
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()){

            //Elementos del menú contextual
            case R.id.CtxMostrar:
                Toast.makeText(MainActivity.this, list_item.getAdapter().getItem(info.position).toString()+" Opción Mostrar",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CtxEliminar:
                Toast.makeText(MainActivity.this, list_item.getAdapter().getItem(info.position).toString()+" Opción Eliminar",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}
