package com.tologo.preferencias;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends Activity {

    // Indicamos las variables a emplear
    // private Spinner spinner;
    private EditText nombre;
    private EditText dni;
    private EditText fecha;
    private RadioButton hombre;
    private RadioButton mujer;
    private String sexo;
    // private ListView listView;

    /* Aquí definimos los valores del Array que rellenará el listView sobre el Sexo
    private static final String[] datos = new String[]{"Hombre","Mujer"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*// Aquí enlazamos el spinner con un adaptador

        spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter adaptador = new ArrayAdapter (getApplicationContext(), android.R.layout.activity_list_item, datos);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adaptador);*/

        // Definimos e resto de los componentes

        nombre = (EditText) findViewById(R.id.editTextNombre);
        dni = (EditText) findViewById(R.id.editTextDNI);
        fecha = (EditText) findViewById(R.id.editTextFecha);
        // listView = (ListView) findViewById(R.id.listView);
        hombre = (RadioButton) findViewById(R.id.radioButtonHombre);
        mujer = (RadioButton) findViewById(R.id.radioButtonMujer);

        /* Definimos un adaptador para el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, datos);
        // Asignamos el adaptador al ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // ListView Clicked item index
                    int itemPosition = listView.getSelectedItemPosition();

                    // ListView Clicked item value
                    String  itemValue    = (String) listView.getItemAtPosition(itemPosition);
            }



        });*/

        // Aquí definimos al manejador del botón con el que guardaremos las Preferencias
        final Button button = (Button) findViewById(R.id.buttonGuardar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Especificamos el valor del String 'sexo' en función del radioButton seleccionado
                if(hombre.isChecked()){
                    sexo="Hombre";
                }
                if(mujer.isChecked()){
                    sexo="Mujer";
                }


                    // Creamos o recuperamos el objeto de preferencias compartidas
                    // Para esto necesitamos un objeto SharedPreferences y lo instanciomos con getSharedPreferences
                    // donde le pasamos el string de preferencias (PREFS) previamente definido como constante, y un modo
                    SharedPreferences mySharedPreferences = getSharedPreferences("mySharedPreferences", Activity.MODE_PRIVATE);

                    // Obtenemos un editor para modificar las preferencias
                    SharedPreferences.Editor editor = mySharedPreferences.edit();

                    // Guardamos nuevos valores
                    // String selectedString = datos[spinner.getSelectedItemPosition()];
                    editor.putString("nombre", nombre.getText().toString());
                    editor.putString("dni", dni.getText().toString());
                    editor.putString("fecha", fecha.getText().toString());
                    editor.putString("sexo", sexo);

                    // Guardamos los cambios
                    editor.commit();


                    // Por último realizamos la llamada a la segunda Activity
                    Intent intent = new Intent(getApplicationContext(), MostrarPreferencias.class);

                    startActivity(intent);

            }
        });

    }

}