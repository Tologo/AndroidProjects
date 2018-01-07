package com.tologo.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class NewProducto extends AppCompatActivity {

    // Indicamos las variables a emplear
    private EditText txtnombre;
    private EditText txtDescripcion;
    private RadioButton cat1,cat2,cat3;
    private EditText txtPrecio;
    private Button anyadir;
    private String categoriaRgr;

    DatabaseReference bbdd;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_producto);

        // Definimos los componentes
        txtnombre = (EditText) findViewById(R.id.editNombreP);
        txtDescripcion = (EditText) findViewById(R.id.editDescripcion);
        txtPrecio = (EditText) findViewById(R.id.editPrecio);
        anyadir = (Button) findViewById(R.id.btnGuardaProducto);
        cat1 = (RadioButton) findViewById(R.id.rdbOne);
        cat2 = (RadioButton) findViewById(R.id.rdbTwo);
        cat3 = (RadioButton) findViewById(R.id.rdbThree);

        // Esto nos devuelve la referencia de la BBDD con la que nos hemos conectado
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_productos));
        // Es importante finalizar con el parámetro 'productos' puesto que será el nodo sobre el que se crearán 'hijos' o consultas

        // Accedemos al usuario activo
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Generamos un listener de Button para guardar un usuario
        anyadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Comprobamos que el nombre del producto no existe con antelación
                Query q = bbdd.orderByChild(getString(R.string.campo_nombreP)).equalTo(txtnombre.getText().toString());

                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Especificamos el valor del String 'categoria' en función del radioButton seleccionado
                        if(cat1.isChecked()){
                            categoriaRgr="Tecnología";
                        }
                        if(cat2.isChecked()){
                            categoriaRgr="Coches";
                        }
                        if(cat3.isChecked()){
                            categoriaRgr="Hogar";
                        }

                        // Definimos los valores del producto. Para el usuario accedemos al dato de id del usuario activo
                        String usuario = user.getUid().toString();
                        String nombre = txtnombre.getText().toString();
                        String descripcion = txtDescripcion.getText().toString();
                        String categoria = categoriaRgr;
                        String precio = txtPrecio.getText().toString();

                        // Si no obtenemos respuesta en el query el nombre de producto elegido
                        // por el usuario está disponible
                        if (!dataSnapshot.exists()) {
                            // Comprobamos que los campos de usuario están cumplimentados
                            if (!TextUtils.isEmpty(nombre)) {
                                if (!TextUtils.isEmpty(descripcion)) {
                                    if (!TextUtils.isEmpty(categoria)) {
                                        if (!TextUtils.isEmpty(precio)) {

                                            // Creamos un nuevo objeto Producto con los datos del formulario
                                            Producto p = new Producto(usuario, nombre, descripcion, categoria, precio);
                                            // Generamos una nueva clave para incorporar un nodo en la BBDD
                                            String clave = bbdd.push().getKey();
                                            // Ahora añadimos a la referencia el nuevo usuario
                                            bbdd.child(clave).setValue(p);

                                            Toast.makeText(NewProducto.this, "Producto añadido", Toast.LENGTH_LONG).show();

                                        } else {
                                            Toast.makeText(NewProducto.this, "Debes de introducir una descripción", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(NewProducto.this, "Debes de introducir una categoría", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(NewProducto.this, "Debes de introducir un precio", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(NewProducto.this, "Debes de introducir un nombre", Toast.LENGTH_LONG).show();
                            }
                        } else{
                            Toast.makeText(NewProducto.this, "El producto ya existe", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        // Generamos un listener de Button para volver al Activity de Opciones
        final Button button2 = (Button) findViewById(R.id.btnVolver);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vamos a programar nuestra llamada a la segunda ventana
                Intent llamada = new Intent(NewProducto.this, Opciones.class);
                startActivity(llamada);
            }
        });


    }


}
