package com.tologo.sqliteproject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity {

    private ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        // Definimos los componentes
        listado = (ListView) findViewById(R.id.lstResultados);

        // Recuperamos los valores asociados en el Intent.
        ArrayList<String> lista = (ArrayList<String>) getIntent().getSerializableExtra("resultado");

        // Definimos un adaptador para el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lista);
        // Asignamos el adaptador al ListView
        listado.setAdapter(adapter);

        //Asociamos el menú contextual al ListView
        registerForContextMenu(listado);
    }
}
