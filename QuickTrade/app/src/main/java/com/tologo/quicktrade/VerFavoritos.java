package com.tologo.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tologo.quicktrade.model.Producto;

import java.util.ArrayList;

public class VerFavoritos extends AppCompatActivity {

    // Indicamos las variables a emplear
    private ListView lstProductos;
    DatabaseReference bbdd;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_favoritos);

        // Definimos los componentes
        lstProductos = (ListView) findViewById(R.id.lstResultados);

        // Accedemos al usuario activo
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Esto nos devuelve la referencia de la BBDD con la que nos hemos conectado
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_favoritos));
        // Es importante finalizar con el parámetro 'productos' puesto que será el nodo sobre el que se crearán 'hijos' o consultas

        // Hacemos una selección de los productos del usuario activo
        Query qProductos = bbdd.orderByChild("usuario").equalTo(user.getUid());

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
                adaptador = new ArrayAdapter<String>(VerFavoritos.this,android.R.layout.simple_list_item_1,listado);
                lstProductos.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
