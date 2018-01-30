package com.tologo.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tologo.quicktrade.model.Favorito;
import com.tologo.quicktrade.model.Producto;
import com.tologo.quicktrade.model.Usuario;

import java.util.ArrayList;

public class MuestraProductos extends AppCompatActivity {

    // Indicamos las variables a emplear
    private ListView lstProductos;
    DatabaseReference bbdd;
    DatabaseReference bbdd2;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_productos);

        // Accedemos al usuario activo
        user = FirebaseAuth.getInstance().getCurrentUser();
        final String usuario = user.getUid();

        // Definimos los componentes
        lstProductos = (ListView) findViewById(R.id.lstResultadosProd);

        // Esto nos devuelve la referencia de la BBDD con la que nos hemos conectado
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_productos));
        // Es importante finalizar con el parámetro 'usuarios' puesto que será el nodo sobre el que se crearán 'hijos' o consultas
        // Esto nos devuelve la referencia de la BBDD con la que nos hemos conectado
        bbdd2 = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_favoritos));

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            // onDataChange escucha los cambios en la referencia de la BBDD y DataSnapshot es el conjunto de objetos en la BBDD
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Definimos el adaptador para el listView
                ArrayAdapter<String> adaptador;
                ArrayList<String> listado = new ArrayList<String>();

                // Recuperamos los nodos de productos que hay dentro de Productos (la referencia en la BBDD)
                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                    Producto producto = datasnapshot.getValue(Producto.class);

                    String nombre = producto.getNombre();
                    listado.add(nombre);
                }
                adaptador = new ArrayAdapter<String>(MuestraProductos.this,android.R.layout.simple_list_item_1,listado);
                lstProductos.setAdapter(adaptador);

                lstProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = (String) parent.getItemAtPosition(position);

                        // Hacemos una selección del producto según el nombre seleccionado
                        Query qProductoSeleccionado = bbdd.orderByChild("nombre").equalTo(selectedItem);

                        // A partir de la selección anterior consultamos y rellenamos el ArrayList
                        qProductoSeleccionado.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            // onDataChange escucha los cambios en la referencia de la BBDD y DataSnapshot es el conjunto de objetos en la BBDD
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                // Recuperamos el nodo de producto buscado que hay dentro de Productos (la referencia en la BBDD)
                                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                                    String key = datasnapshot.getKey();
                                    Producto producto = datasnapshot.getValue(Producto.class);
                                    String prodName = producto.getNombre().toString();

                                    // Creamos un nuevo objeto Favorito con los datos del formulario
                                    Favorito f = new Favorito(usuario, prodName);
                                    // Generamos una nueva clave para incorporar un nodo en la BBDD
                                    String clave = bbdd2.push().getKey();
                                    // Ahora añadimos a la referencia el nuevo producto
                                    bbdd2.child(clave).setValue(f);

                                    Toast.makeText(MuestraProductos.this, "Producto favorito añadido", Toast.LENGTH_LONG).show();

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(MuestraProductos.this, "Hay algún problema con este producto", Toast.LENGTH_LONG).show();
                            }


                        });

                    }

            });

          }



        @Override
        public void onCancelled(DatabaseError databaseError) {

             }
        });
    }

}

