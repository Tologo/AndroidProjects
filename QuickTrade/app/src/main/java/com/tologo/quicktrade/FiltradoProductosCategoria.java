package com.tologo.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tologo.quicktrade.model.Producto;

import java.util.ArrayList;

public class FiltradoProductosCategoria extends AppCompatActivity {

    // Indicamos las variables a emplear
    private Spinner spinner;
    private ListView listView;
    DatabaseReference bbdd;

    // Aquí definimos los valores del Array que rellenará el spinner de Categorías
    private static final String[] datosCategoria = { "Tecnología", "Coches", "Hogar" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrado_productos_categoria);

        // Definimos el Spinner, lo enlazamos con un adaptador y lo rellenamos con los datos
        spinner = (Spinner)findViewById(R.id.spinnerCat);
        ArrayAdapter adaptadorSp = new ArrayAdapter<String> (FiltradoProductosCategoria.this, android.R.layout.simple_spinner_dropdown_item, datosCategoria);
        spinner.setAdapter(adaptadorSp);

        // Definimos el listView
        listView = (ListView) findViewById(R.id.lstViewProdFiltrados);

        // Establecemos la acción a realizar al seleccionar un ítem del spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtenemos el String del ítem seleccionado
                String selected = parentView.getItemAtPosition(position).toString();

                // Tomamos la referencia de la BBDD con la que nos hemos conectado
                bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_productos));
                // Es importante finalizar con el parámetro 'productos' puesto que será el nodo sobre el que se crearán 'hijos' o consultas

                // Hacemos una selección de los productos según la categoría seleccionada
                Query qProductos = bbdd.orderByChild("categoria").equalTo(selected);

                // A partir de la selección anterior consultamos y rellenamos el ArrayList
                qProductos.addValueEventListener(new ValueEventListener() {
                    @Override
                    // onDataChange escucha los cambios en la referencia de la BBDD y DataSnapshot es el conjunto de objetos en la BBDD
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Definimos el adaptador para el listView
                        ArrayAdapter<String> adaptador;
                        ArrayList<String> listado = new ArrayList<String>();
                        listado.removeAll(listado);

                        // Recuperamos los nodos de producto que hay dentro de Productos (la referencia en la BBDD)
                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                            Producto producto = datasnapshot.getValue(Producto.class);

                            String prod = producto.toString();
                            listado.add(prod);

                        }
                        adaptador = new ArrayAdapter<String>(FiltradoProductosCategoria.this,android.R.layout.simple_list_item_1,listado);
                        listView.setAdapter(adaptador);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }
}
