package com.tologo.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tologo.quicktrade.model.Usuario;

public class ModificaUser extends AppCompatActivity {

    // Indicamos las variables a emplear
    private EditText txtnick;
    private EditText editNick;
    private EditText editCorreo;
    private EditText editNombre;
    private EditText editDireccion;
    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_user);

        // Definimos los componentes
        txtnick = (EditText) findViewById(R.id.recuperaNick);
        editNick = (EditText) findViewById(R.id.editNick);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editDireccion = (EditText) findViewById(R.id.editDireccion);
        // Esto nos devuelve la referencia de la BBDD con la que nos hemos conectado
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_ppal));
        // Es importante finalizar con el parámetro 'usuarios' puesto que será el nodo sobre el que se crearán 'hijos' o consultas


        // Generamos un listener de Button para recuperar los datos del usuario señalado en el editText
        final Button buttonR = (Button) findViewById(R.id.btnRecuperaUser);
        buttonR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                Query q=bbdd.orderByChild(getString(R.string.campo_nick)).equalTo(txtnick.getText().toString());

                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){

                            Usuario usuario = datasnapshot.getValue(Usuario.class);

                            String nick = usuario.getNick();
                            String correo = usuario.getCorreo();
                            String nombre = usuario.getNombre();
                            String direccion = usuario.getDireccion();

                            editNick.setText(nick);
                            editCorreo.setText(correo);
                            editNombre.setText(nombre);
                            editDireccion.setText(direccion);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Toast.makeText(ModificaUser.this, "No se ha encontrado ningún usuario con ese Nick", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        // Generamos un listener de Button para actualizar los datos del usuario
        final Button buttonA = (Button) findViewById(R.id.btnActualizaUser);
        buttonA.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String nick = txtnick.getText().toString();

                if(!TextUtils.isEmpty(nick)) {

                    Query q = bbdd.orderByChild(getString(R.string.campo_nick)).equalTo(nick);

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                                // Recuperamos la clave del nodo con el nick que buscamos
                                String clave=datasnapshot.getKey();
                                // Empleamos la clave recuperada del nodo a editar para actualizar sus campos
                                bbdd.child(clave).child(getString(R.string.campo_correo)).setValue(editCorreo.getText().toString());
                                bbdd.child(clave).child(getString(R.string.campo_nombre)).setValue(editNombre.getText().toString());
                                bbdd.child(clave).child(getString(R.string.campo_direccion)).setValue(editDireccion.getText().toString());
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                }
            }
        });

        // Generamos un listener de Button para volver al Menú de opciones
        final Button buttonVolver = (Button) findViewById(R.id.btnVolver);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(ModificaUser.this, Opciones.class);
                startActivity(llamada);
            }
        });

    }
}
