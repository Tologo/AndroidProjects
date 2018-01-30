package com.tologo.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Opciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        // Generamos un listener de Button para llegar a la Activity de añadir usuario
        final Button button1 = (Button) findViewById(R.id.btnNuevoUsuario);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Opciones.this, NewUser.class);
                startActivity(llamada);
            }
        });

        // Generamos un listener de Button para llegar a la Activity de mostrar usuarios
        final Button button2 = (Button) findViewById(R.id.btnTodosUsuarios);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Opciones.this, MuestraUsers.class);
                startActivity(llamada);
            }
        });

        // Generamos un listener de Button para llegar a la Activity de mostrar usuarios
        final Button button3 = (Button) findViewById(R.id.btnModificaUsuario);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Opciones.this, ModificaUser.class);
                startActivity(llamada);
            }
        });

        // Generamos un listener de Button para cerrar sesión y volver a la pantalla de Login
        final Button button4 = (Button) findViewById(R.id.btnCerrarSesion);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Cerramos la sesión activa
                FirebaseAuth.getInstance().signOut();
                // Vamos a programar nuestra llamada a la Activity de Login
                Intent llamada = new Intent(Opciones.this, MainActivity.class);
                startActivity(llamada);
                }
        });

        // Generamos un listener de Button para llegar a la Activity de añadir producto
        final Button button5 = (Button) findViewById(R.id.buttonNewProducto);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Opciones.this, NewProducto.class);
                startActivity(llamada);
            }
        });

        // Generamos un listener de Button para llegar a la Activity con los productos del usuario
        final Button button6 = (Button) findViewById(R.id.buttonVerProductosUsuario);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Opciones.this, VerProductosUsuario.class);
                startActivity(llamada);
            }
        });

        // Generamos un listener de Button para llegar a la Activity con los productos del usuario
        final Button button7 = (Button) findViewById(R.id.buttonVerProductosCategoria);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Opciones.this, FiltradoProductosCategoria.class);
                startActivity(llamada);
            }
        });

        // Generamos un listener de Button para llegar a la Activity de modificación de los productos del usuario
        final Button button8 = (Button) findViewById(R.id.buttonModifProductos);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Opciones.this, ModificaProducto.class);
                startActivity(llamada);
            }
        });

        // Generamos un listener de Button para llegar a la Activity de mostrar todos los productos
        final Button button9 = (Button) findViewById(R.id.buttonMuestraProductos);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Opciones.this, MuestraProductos.class);
                startActivity(llamada);
            }
        });

        // Generamos un listener de Button para llegar a la Activity de mostrar todos los productos
        final Button button10 = (Button) findViewById(R.id.btnVerFavoritos);
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Opciones.this, VerFavoritos.class);
                startActivity(llamada);
            }
        });

    }
}
