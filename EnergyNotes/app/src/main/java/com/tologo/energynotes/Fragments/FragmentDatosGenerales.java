package com.tologo.energynotes.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.tologo.energynotes.R;
import com.tologo.energynotes.model.DatosGenerales;
import com.tologo.energynotes.model.Proyecto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDatosGenerales.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentDatosGenerales extends Fragment {

    // Indicamos las variables a emplear
    private Spinner spinner;
    DatabaseReference bbdd, bbdd2;
    FirebaseUser user;
    private EditText txtEdificio, txtDireccion, txtLocalidad, txtCodigo, txtReferencia, txtFechaCons, txtProyecto;
    private Button btnGuardar, btnActualizar, btnRecuperar;

    private OnFragmentInteractionListener mListener;

    public FragmentDatosGenerales() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_fragment_datos_generales, container, false);

        // Definimos los componentes
        spinner = (Spinner)v.findViewById(R.id.spinnerPr);
        txtEdificio = (EditText) v.findViewById(R.id.editNombreEdif);
        txtDireccion = (EditText) v.findViewById(R.id.editDireccion);
        txtLocalidad = (EditText) v.findViewById(R.id.editLocalidad);
        txtCodigo = (EditText) v.findViewById(R.id.editCodigoPostal);
        txtReferencia = (EditText) v.findViewById(R.id.editReferencia);
        txtFechaCons = (EditText) v.findViewById(R.id.editFechaConst);
        txtProyecto = (EditText) v.findViewById(R.id.editProyecto);
        btnGuardar = (Button) v.findViewById(R.id.btnGuardaDatos);
        btnActualizar = (Button) v.findViewById(R.id.btnActualizaDatos);
        btnRecuperar = (Button) v.findViewById(R.id.btnRecuperaDatos);

        // Accedemos al usuario activo
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Esto nos devuelve la referencia de la BBDD con la que nos hemos conectado
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodoProyectos));
        // Es importante finalizar con el parámetro 'proyectos' puesto que será el nodo sobre el que se crearán 'hijos' o consultas
        bbdd2 = FirebaseDatabase.getInstance().getReference(getString(R.string.nodoGenerales));
        // Lo mismo para la rama de datos generales

        // Hacemos una selección de los proyectos del usuario activo
        Query qProyectos = bbdd.orderByChild("usuario").equalTo(user.getUid());

        // A partir de la selección anterior consultamos y rellenamos el Spinner
        qProyectos.addValueEventListener(new ValueEventListener() {
            @Override
            // onDataChange escucha los cambios en la referencia de la BBDD y DataSnapshot es el conjunto de objetos en la BBDD
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Definimos el array de datos y lo vaciamos por seguridad
                List<String> listado = new ArrayList<String>();
                listado.removeAll(listado);

                // Recuperamos los nodos de proyecto que hay dentro de Proyectos (la referencia en la BBDD)
                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {

                    Proyecto proyecto = datasnapshot.getValue(Proyecto.class);
                    String proy = proyecto.getNombreproyecto().toString();
                    listado.add(proy);
                }

                ArrayAdapter adaptador = new ArrayAdapter<String> (getActivity(), android.R.layout.simple_spinner_dropdown_item, listado);
                spinner.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Establecemos la acción a realizar al seleccionar un ítem del spinner
        // que será rellenar los campos editText del Fragment con los valores asociados al proyecto
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtenemos el nombre del ítem seleccionado
                String selected = parent.getItemAtPosition(position).toString();
                txtProyecto.setText(selected);
                txtEdificio.setText("");
                txtDireccion.setText("");
                txtLocalidad.setText("");
                txtCodigo.setText("");
                txtReferencia.setText("");
                txtFechaCons.setText("");
                Toast.makeText(getActivity(),"Proyecto seleccionado: "+selected, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nothing happens
            }

        });


        // Implementamos la función del botón guardar

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String proyecto = txtProyecto.getText().toString();
                final String edificio = txtEdificio.getText().toString();
                final String direccion = txtDireccion.getText().toString();
                final String localidad = txtLocalidad.getText().toString();
                final String codigo = txtCodigo.getText().toString();
                final String referencia = txtReferencia.getText().toString();
                final String fechaconst = txtFechaCons.getText().toString();

                // Comprobamos que el nombre del producto no existe con antelación
                Query q = bbdd2.orderByChild(getString(R.string.campo_proyectoDG)).equalTo(proyecto);

                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Si no obtenemos respuesta en el query el nombre de producto elegido
                        // por el usuario está disponible
                        if (!dataSnapshot.exists()) {
                            // Comprobamos que los campos de usuario están cumplimentados
                            if (!TextUtils.isEmpty(edificio)) {
                                if (!TextUtils.isEmpty(direccion)) {
                                    if (!TextUtils.isEmpty(localidad)) {
                                        if (!TextUtils.isEmpty(codigo)) {
                                            if (referencia.length()==20) {
                                                if (TextUtils.isDigitsOnly(fechaconst)) {

                                                    // Creamos un nuevo objeto DatosGenerales con los datos del formulario
                                                    DatosGenerales d = new DatosGenerales(edificio, direccion, localidad, codigo, referencia, fechaconst, proyecto);
                                                    // Generamos una nueva clave para incorporar un nodo en la BBDD
                                                    String clave = bbdd2.push().getKey();
                                                    // Ahora añadimos a la referencia el nuevo producto
                                                    bbdd2.child(clave).setValue(d);

                                                    Toast.makeText(getActivity(), "Datos incorporados", Toast.LENGTH_LONG).show();

                                                } else {
                                                    Toast.makeText(getActivity(), "Debes de introducir un año correcto", Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                Toast.makeText(getActivity(), "La referencia catastral no es válida", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Toast.makeText(getActivity(), "Debes de introducir un código", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Debes de introducir una localidad", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Debes de introducir una dirección", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Debes de introducir un edificio", Toast.LENGTH_LONG).show();
                            }
                        } else{
                            Toast.makeText(getActivity(), "Los datos de este proyecto ya existen", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        // Generamos un listener de Button para actualizar los datos generales del proyecto

        btnActualizar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String key = txtProyecto.getText().toString();

                if(!TextUtils.isEmpty(key)) {

                    Query q = bbdd2.orderByChild("proyecto").equalTo(key);

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                                // Recuperamos la clave del nodo con el nick que buscamos
                                String clave=datasnapshot.getKey();
                                // Empleamos la clave recuperada del nodo a editar para actualizar sus campos
                                bbdd2.child(clave).child(getString(R.string.campo_edificioDG)).setValue(txtEdificio.getText().toString());
                                bbdd2.child(clave).child(getString(R.string.campo_direccionDG)).setValue(txtDireccion.getText().toString());
                                bbdd2.child(clave).child(getString(R.string.campo_localidadDG)).setValue(txtLocalidad.getText().toString());
                                bbdd2.child(clave).child(getString(R.string.campo_codigoDG)).setValue(txtCodigo.getText().toString());
                                bbdd2.child(clave).child(getString(R.string.campo_referenciaDG)).setValue(txtReferencia.getText().toString());
                                bbdd2.child(clave).child(getString(R.string.campo_fechaConsDG)).setValue(txtFechaCons.getText().toString());
                                bbdd2.child(clave).child(getString(R.string.campo_proyectoDG)).setValue(txtProyecto.getText().toString());

                                Toast.makeText(getActivity(), "Datos de proyecto actualizado", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getActivity(), "Proyecto no actualizado", Toast.LENGTH_LONG).show();
                        }

                    });
                } else {
                    Toast.makeText(getActivity(), "No hay un proyecto definido", Toast.LENGTH_LONG).show();
                }

            }
        });


        // Generamos un listener de Button para recuperar los datos generales del proyecto

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String proyecto = txtProyecto.getText().toString();
                // Hacemos una selección del proyecto según el nombre seleccionado
                Query qProyectoSeleccionado = bbdd2.orderByChild("proyecto").equalTo(proyecto);

                // A partir de la selección anterior consultamos y rellenamos los campos
                qProyectoSeleccionado.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    // onDataChange escucha los cambios en la referencia de la BBDD y DataSnapshot es el conjunto de objetos en la BBDD
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Recuperamos el nodo de proyecto buscado que hay dentro de 'generales'
                        for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                            String key = datasnapshot.getKey();
                            DatosGenerales generales = datasnapshot.getValue(DatosGenerales.class);

                            String edificio = generales.getEdificio();
                            String direccion = generales.getDireccion();
                            String localidad = generales.getLocalidad();
                            String codigopostal = generales.getCodigopostal();
                            String referencia = generales.getReferencia();
                            String fechaconstruccion = generales.getFechaconstruccion();
                            String proyecto = generales.getProyecto();

                            txtEdificio.setText(edificio);
                            txtDireccion.setText(direccion);
                            txtLocalidad.setText(localidad);
                            txtCodigo.setText(codigopostal);
                            txtReferencia.setText(referencia);
                            txtFechaCons.setText(fechaconstruccion);
                            txtProyecto.setText(proyecto);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Este proyecto aún no tiene Datos generales", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



        return  v;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
