package com.tologo.sqliteproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Alumno extends AppCompatActivity {

    // Indicamos las variables a emplear
    private EditText nombre;
    private EditText edad;
    private EditText ciclo;
    private EditText curso;
    private EditText nota;
    private MyDBAdapter dbAdapter;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        // Definimos los componentes

        nombre = (EditText) findViewById(R.id.nombreA);
        edad = (EditText) findViewById(R.id.edadA);
        ciclo = (EditText) findViewById(R.id.cicloA);
        curso = (EditText) findViewById(R.id.cursoA);
        nota = (EditText) findViewById(R.id.notaA);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        // Generamos un listener de Button
        final Button button1 = (Button) findViewById(R.id.btnGuardaA);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Programamos la acción de guardar un alumno
                dbAdapter.insertarAlumno(nombre.getText().toString(),Integer.parseInt(edad.getText().toString()),ciclo.getText().toString(),curso.getText().toString(),Integer.parseInt(nota.getText().toString()));
            }
        });

        // Generamos un listener de Button
        final Button button2 = (Button) findViewById(R.id.btnVolver);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Alumno.this, MainActivity.class);
                startActivity(llamada);
            }
        });

        // Para las consultas

        // Manejador del botón que devuelve todos los alumnos en la Activity de Resultados
        final Button button = (Button) findViewById(R.id.btnTodosA);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

        ArrayList<String> resultado = dbAdapter.recuperarTodoAlumnos();

                // Por último realizamos la llamada a la segunda Activity
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                // Añadimos el Array al intent para que lo reciba la activity Resultados
                intent.putExtra("resultado", resultado);
                startActivity(intent);

            }
        });

        // Manejador del botón que devuelve alumnos por ciclo indicado en la Activity de Resultados
        final Button buttonC = (Button) findViewById(R.id.btnAciclo);
        buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> resultado = dbAdapter.recuperarAlumnosCiclo(ciclo.getText().toString());

                // Por último realizamos la llamada a la segunda Activity
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                // Añadimos el Array al intent para que lo reciba la activity Resultados
                intent.putExtra("resultado", resultado);
                startActivity(intent);

            }
        });

        // Manejador del botón que devuelve alumnos por curso indicado en la Activity de Resultados
        final Button buttonCurso = (Button) findViewById(R.id.btnAcurso);
        buttonCurso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> resultado = dbAdapter.recuperarAlumnosCurso(curso.getText().toString());

                // Por último realizamos la llamada a la segunda Activity
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                // Añadimos el Array al intent para que lo reciba la activity Resultados
                intent.putExtra("resultado", resultado);
                startActivity(intent);

            }
        });

        // Manejador del botón que devuelve alumnos por ciclo y curso indicado en la Activity de Resultados
        final Button buttonCiCu = (Button) findViewById(R.id.btnACiCu);
        buttonCiCu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> resultado = dbAdapter.recuperarAlumnosCicloCurso(ciclo.getText().toString(),curso.getText().toString());

                // Por último realizamos la llamada a la segunda Activity
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                // Añadimos el Array al intent para que lo reciba la activity Resultados
                intent.putExtra("resultado", resultado);
                startActivity(intent);

            }
        });


    }


}
