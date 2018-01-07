package com.tologo.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tologo.quicktrade.model.Usuario;

import java.util.ArrayList;

public class MuestraUsers extends AppCompatActivity {

    // Indicamos las variables a emplear
    private ListView lstUsuarios;
    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_users);

        // Definimos los componentes
        lstUsuarios = (ListView) findViewById(R.id.lstResultados);

        // Esto nos devuelve la referencia de la BBDD con la que nos hemos conectado
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_ppal));
        // Es importante finalizar con el parámetro 'usuarios' puesto que será el nodo sobre el que se crearán 'hijos' o consultas

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            // onDataChange escucha los cambios en la referencia de la BBDD y DataSnapshot es el conjunto de objetos en la BBDD
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Definimos el adaptador para el listView
                ArrayAdapter<String> adaptador;
                ArrayList<String> listado = new ArrayList<String>();

                // Recuperamos los nodos de usuario que hay dentro de Usuarios (la referencia en la BBDD)
                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                    Usuario usuario = datasnapshot.getValue(Usuario.class);

                    String nick = usuario.getNick();
                    listado.add(nick);
                }
                adaptador = new ArrayAdapter<String>(MuestraUsers.this,android.R.layout.simple_list_item_1,listado);
                lstUsuarios.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
