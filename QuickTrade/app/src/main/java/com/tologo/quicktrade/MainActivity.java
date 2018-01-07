package com.tologo.quicktrade;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText text_email;
    EditText text_contrasena;
    Button btn_registrar;
    Button btn_login;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_email = (EditText) findViewById(R.id.txtEmail);
        text_contrasena = (EditText) findViewById(R.id.txtContrasena);
        btn_registrar = (Button) findViewById(R.id.btnRegistrar);
        btn_login = (Button) findViewById(R.id.btnLogin);

        // Cerramos la sesión activa
        // FirebaseAuth.getInstance().signOut();

        // Definimos la acción para el botón de registro
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = text_email.getText().toString();
                String password = text_contrasena.getText().toString();

                registrar(email,password);
            }
        });

        // Definimos la acción para el botón de Login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = text_email.getText().toString();
                String password = text_contrasena.getText().toString();

                login(email,password);
            }
        });

    }

// Definimos el método de registro de nuevo usuario
    private void registrar (String email, String password){

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Authentication successful."+user.getUid(),
                                    Toast.LENGTH_SHORT).show();

                            if (user != null) {
                                Intent llamada = new Intent(MainActivity.this, Opciones.class);
                                startActivity(llamada);
                            } else {
                                Toast.makeText(MainActivity.this, "Ningún usuario autenticado",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed."+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

// Definimos el método de Login de un usuario previamente registrado en la base de datos.
    private void login (String email, String password){

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Signin successful."+user.getUid(),
                                    Toast.LENGTH_SHORT).show();

                            if (user != null) {
                                Intent llamada = new Intent(MainActivity.this, Opciones.class);
                                startActivity(llamada);
                            } else {
                                Toast.makeText(MainActivity.this, "Ningún usuario autenticado",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Signin failed."+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        /* Definimos la acción que ocurre una vez que la app detecta que hay un usuario
        // correctamente autenticado. Nos envía a la Activity de Opciones
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Vamos a programar nuestra llamada al Activity de Opciones
                if (mAuth.getCurrentUser() != null) {
                    Intent llamada = new Intent(MainActivity.this, Opciones.class);
                    startActivity(llamada);
                } else {
                    Toast.makeText(MainActivity.this, "Ningún usuario autenticado",
                            Toast.LENGTH_SHORT).show();
                }
            }

        };*/

    }

}
