package com.tologo.sqliteproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Definimos las variables a emplear
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        // Generamos un listener de Button
        final Button button1 = (Button) findViewById(R.id.btnIrAlumno);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(MainActivity.this, Alumno.class);
                startActivity(llamada);
            }
        });

        // Generamos un listener de Button
        final Button button2 = (Button) findViewById(R.id.btnIrProfesor);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(MainActivity.this, Profesor.class);
                startActivity(llamada);
            }
        });

        // Manejador del botón que devuelve todos los profesores y alumnos en la Activity de Resultados
        final Button button = (Button) findViewById(R.id.btnTodos);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> resultado = dbAdapter.recuperarTodo();

                // Por último realizamos la llamada a la segunda Activity
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                // Añadimos el Array al intent para que lo reciba la activity Resultados
                intent.putExtra("resultado", resultado);
                startActivity(intent);

            }
        });


    }
}
