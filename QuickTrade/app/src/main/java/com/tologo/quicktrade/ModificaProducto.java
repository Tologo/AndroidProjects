package com.tologo.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tologo.quicktrade.model.Producto;
import com.tologo.quicktrade.model.Usuario;

import java.util.ArrayList;

public class ModificaProducto extends AppCompatActivity {

    // Indicamos las variables a emplear
    private EditText editNombre;
    private EditText editDescripcion;
    private EditText editCategoria;
    private EditText editPrecio;
    private EditText editKey;
    private Spinner spinner;
    DatabaseReference bbdd;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_producto);

        // Definimos los editText
        editNombre = (EditText) findViewById(R.id.editNombreP);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        editCategoria = (EditText) findViewById(R.id.editCategoría);
        editPrecio = (EditText) findViewById(R.id.editPrecio);
        editKey = (EditText)findViewById(R.id.txtKey);

        // Definimos el Spinner
        spinner = (Spinner)findViewById(R.id.spnProductosUsuario);
        // Accedemos a la referencia de la BBDD con la que nos hemos conectado
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_productos));
        // Es importante finalizar con el parámetro 'productos' puesto que será el nodo sobre el que se crearán 'hijos' o consultas

        // Accedemos al usuario activo
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Ahora vamos a seleccionar los productos de usuario activo para introducirlos en un Array y
        // poder rellenar el spinner de selección de producto a modificar.

        // Hacemos una selección de los productos cuyo atributo usuario coincide con el usuario activo
        Query qProductos = bbdd.orderByChild("usuario").equalTo(user.getUid().toString());

        // A partir de la selección anterior consultamos y rellenamos el ArrayList
        qProductos.addValueEventListener(new ValueEventListener() {
            @Override
            // onDataChange escucha los cambios en la referencia de la BBDD y DataSnapshot es el conjunto de objetos en la BBDD
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Creamos el Array que rellenará el Spinner
                ArrayList<String> listado = new ArrayList<String>();
                listado.removeAll(listado);

                // Recuperamos los nodos de producto que hay dentro de Productos (la referencia en la BBDD)
                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                    Producto producto = datasnapshot.getValue(Producto.class);

                    String prod = producto.getNombre();
                    listado.add(prod);

                }
                // Definimos el adaptador para el Spinner y lo rellenamos con los datos
                ArrayAdapter adaptador = new ArrayAdapter<String> (ModificaProducto.this, android.R.layout.simple_spinner_dropdown_item, listado);
                spinner.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Establecemos la acción a realizar al seleccionar un ítem del spinner
        // que será rellenar los campos editText del Activity con los valores del producto
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtenemos el nombre del ítem seleccionado
                String selected = parentView.getItemAtPosition(position).toString();

                // Hacemos una selección del producto según el nombre seleccionado
                Query qProductoSeleccionado = bbdd.orderByChild("nombre").equalTo(selected);

                // A partir de la selección anterior consultamos y rellenamos el ArrayList
                qProductoSeleccionado.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    // onDataChange escucha los cambios en la referencia de la BBDD y DataSnapshot es el conjunto de objetos en la BBDD
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Recuperamos el nodo de producto buscado que hay dentro de Productos (la referencia en la BBDD)
                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                            String key = datasnapshot.getKey();
                            Producto producto = datasnapshot.getValue(Producto.class);

                            String nombre = producto.getNombre();
                            String descripcion = producto.getDescripcion();
                            String categoria = producto.getCategoria();
                            String precio = producto.getPrecio();

                            editNombre.setText(nombre);
                            editDescripcion.setText(descripcion);
                            editCategoria.setText(categoria);
                            editPrecio.setText(precio);
                            editKey.setText(key);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(ModificaProducto.this, "Hay algún problema con este producto", Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        // Generamos un listener de Button para actualizar los datos del producto
        final Button buttonActP = (Button) findViewById(R.id.btnActualizaProducto);
        buttonActP.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String key = editKey.getText().toString();

                if(!TextUtils.isEmpty(key)) {

                    Query q = bbdd.orderByKey().equalTo(key);

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                                // Recuperamos la clave del nodo con el nick que buscamos
                                String clave=datasnapshot.getKey();
                                // Empleamos la clave recuperada del nodo a editar para actualizar sus campos
                                bbdd.child(clave).child(getString(R.string.campo_nombreP)).setValue(editNombre.getText().toString());
                                bbdd.child(clave).child(getString(R.string.campo_descripcionP)).setValue(editDescripcion.getText().toString());
                                bbdd.child(clave).child(getString(R.string.campo_categoriaP)).setValue(editCategoria.getText().toString());
                                bbdd.child(clave).child(getString(R.string.campo_precioP)).setValue(editPrecio.getText().toString());

                                Toast.makeText(ModificaProducto.this, "Producto actualizado", Toast.LENGTH_LONG).show();


                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                }
            }
        });

        // Generamos un listener de Button para eliminar el producto
        final Button buttonBorraP = (Button) findViewById(R.id.btnEliminaProducto);
        buttonBorraP.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String key = editKey.getText().toString();

                if(!TextUtils.isEmpty(key)) {

                    Query q = bbdd.orderByKey().equalTo(key);

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                                // Recuperamos la clave del nodo que hemos seleccionado
                                String clave=datasnapshot.getKey();
                                DatabaseReference ref = bbdd.child(clave);
                                ref.removeValue();

                                Toast.makeText(ModificaProducto.this, "Producto eliminado", Toast.LENGTH_LONG).show();

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
                Intent llamada = new Intent(ModificaProducto.this, Opciones.class);
                startActivity(llamada);
            }
        });

    }
}
