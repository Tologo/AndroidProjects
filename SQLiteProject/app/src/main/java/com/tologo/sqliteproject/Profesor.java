package com.tologo.sqliteproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Profesor extends AppCompatActivity {

    // Indicamos las variables a emplear
    private EditText nombre;
    private EditText edad;
    private EditText ciclo;
    private EditText curso;
    private EditText despacho;
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);

        // Definimos los componentes

        nombre = (EditText) findViewById(R.id.nombreP);
        edad = (EditText) findViewById(R.id.edadP);
        ciclo = (EditText) findViewById(R.id.cicloP);
        curso = (EditText) findViewById(R.id.cursoP);
        despacho = (EditText) findViewById(R.id.despacho);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        // Generamos un listener de Button
        final Button button1 = (Button) findViewById(R.id.btnGuardaP);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Programamos la acción de guardar un alumno
                dbAdapter.insertarProfesor(nombre.getText().toString(),Integer.parseInt(edad.getText().toString()),ciclo.getText().toString(),curso.getText().toString(),despacho.getText().toString());
            }
        });

        // Generamos un listener de Button
        final Button button2 = (Button) findViewById(R.id.btnVolver);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(Profesor.this, MainActivity.class);
                startActivity(llamada);
            }
        });

        // Consultas

        // Manejador del botón que devuelve todos los profesores en la Activity de Resultados
        final Button button = (Button) findViewById(R.id.btnTodosP);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> resultado = dbAdapter.recuperarTodoProfesores();

                // Por último realizamos la llamada a la segunda Activity
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                // Añadimos el Array al intent para que lo reciba la activity Resultados
                intent.putExtra("resultado", resultado);
                startActivity(intent);

            }
        });

        // Manejador del botón que devuelve profesores por ciclo indicado en la Activity de Resultados
        final Button buttonC = (Button) findViewById(R.id.btnPciclo);
        buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> resultado = dbAdapter.recuperarProfesoresCiclo(ciclo.getText().toString());

                // Por último realizamos la llamada a la segunda Activity
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                // Añadimos el Array al intent para que lo reciba la activity Resultados
                intent.putExtra("resultado", resultado);
                startActivity(intent);

            }
        });

        // Manejador del botón que devuelve profesores por curso indicado en la Activity de Resultados
        final Button buttonCurso = (Button) findViewById(R.id.btnPcurso);
        buttonCurso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> resultado = dbAdapter.recuperarProfesoresCurso(curso.getText().toString());

                // Por último realizamos la llamada a la segunda Activity
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                // Añadimos el Array al intent para que lo reciba la activity Resultados
                intent.putExtra("resultado", resultado);
                startActivity(intent);

            }
        });

        // Manejador del botón que devuelve profesores por ciclo y curso indicado en la Activity de Resultados
        final Button buttonCiCu = (Button) findViewById(R.id.btnPCiCu);
        buttonCiCu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> resultado = dbAdapter.recuperarProfesoresCicloCurso(ciclo.getText().toString(),curso.getText().toString());

                // Por último realizamos la llamada a la segunda Activity
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                // Añadimos el Array al intent para que lo reciba la activity Resultados
                intent.putExtra("resultado", resultado);
                startActivity(intent);

            }
        });

    }
}
