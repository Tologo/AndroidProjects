package com.tologo.preferencias;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarPreferencias extends AppCompatActivity {

    // Indicamos las variables a emplear
    private TextView nombreR;
    private TextView DNIR;
    private TextView fechaR;
    private TextView sexoR;
    private String nombre;
    private String dni;
    private String fecha;
    private String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_preferencias);

        // Definimos los componentes
        nombreR = (TextView) findViewById(R.id.textViewNombreR);
        DNIR = (TextView) findViewById(R.id.textViewDNIR);
        fechaR = (TextView) findViewById(R.id.textViewFechaR);
        sexoR = (TextView) findViewById(R.id.textViewSexoR);

        // Recuperamos el objeto de preferencias compartidas
        SharedPreferences mySharedPreferences = getSharedPreferences("mySharedPreferences", Activity.MODE_PRIVATE);

        // Recuperamos los valores guardados. Aquí no necesitamos el editor porque no vamos a modificar nada.
        nombre = mySharedPreferences.getString("nombre", "-");
        fecha = mySharedPreferences.getString("fecha", "--/--/----");
        dni = mySharedPreferences.getString("dni", "-");
        sexo = mySharedPreferences.getString("sexo", "-");

        // Y lo asociamos al textView correspondiente del Activity
        nombreR.setText(nombre);
        DNIR.setText(dni);
        fechaR.setText(fecha);
        sexoR.setText(sexo);

    }
}
