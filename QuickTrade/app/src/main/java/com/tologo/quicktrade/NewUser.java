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

public class NewUser extends AppCompatActivity {

    // Indicamos las variables a emplear
    private EditText txtnick;
    private EditText txtcorreo;
    private EditText txtnombre;
    private EditText txtdireccion;
    private Button anyadir;

    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        // Definimos los componentes
        txtnick = (EditText) findViewById(R.id.editNick);
        txtcorreo = (EditText) findViewById(R.id.editCorreo);
        txtnombre = (EditText) findViewById(R.id.editNombre);
        txtdireccion = (EditText) findViewById(R.id.editDireccion);
        anyadir = (Button) findViewById(R.id.btnGuardaUser);

        // Esto nos devuelve la referencia de la BBDD con la que nos hemos conectado
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_ppal));
        // Es importante finalizar con el parámetro 'usuarios' puesto que será el nodo sobre el que se crearán 'hijos' o consultas


        // Generamos un listener de Button para guardar un usuario
        anyadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Comprobamos que el usuario no existe con antelación
                Query q = bbdd.orderByChild(getString(R.string.campo_nick)).equalTo(txtnick.getText().toString());

                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String nick = txtnick.getText().toString();
                        String correo = txtcorreo.getText().toString();
                        String nombre = txtnombre.getText().toString();
                        String direccion = txtdireccion.getText().toString();

                        // Si no obtenemos respuesta en el query el nick elegido por el usuario está disponible
                            if (!dataSnapshot.exists()) {
                                // Comprobamos que los campos de usuario están cumplimentados
                                if (!TextUtils.isEmpty(nick)) {
                                    if (!TextUtils.isEmpty(correo)) {
                                        if (!TextUtils.isEmpty(nombre)) {
                                            if (!TextUtils.isEmpty(direccion)) {

                                                // Creamos un nuevo objeto Usuario con los datos del formulario
                                                Usuario u = new Usuario(nick, correo, nombre, direccion);
                                                // Generamos una nueva clave para incorporar un nodo en la BBDD
                                                String clave = bbdd.push().getKey();
                                                // Ahora añadimos a la referencia el nuevo usuario
                                                bbdd.child(clave).setValue(u);

                                                Toast.makeText(NewUser.this, "Usuario añadido", Toast.LENGTH_LONG).show();

                                            } else {
                                                Toast.makeText(NewUser.this, "Debes de introducir una dirección", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Toast.makeText(NewUser.this, "Debes de introducir un nombre", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(NewUser.this, "Debes de introducir un correo", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(NewUser.this, "Debes de introducir un nick", Toast.LENGTH_LONG).show();
                                }
                            } else{
                                Toast.makeText(NewUser.this, "El usuario ya existe", Toast.LENGTH_LONG).show();
                            }

                        }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
                });


        // Generamos un listener de Button para volver al MainActivity
        final Button button2 = (Button) findViewById(R.id.btnVolver);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(NewUser.this, Opciones.class);
                startActivity(llamada);
            }
        });


    }
}