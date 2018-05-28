package com.tologo.energynotes.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.tologo.energynotes.model.Proyecto;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentProyecto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentProyecto extends Fragment {

    // Indicamos las variables a emplear
    private EditText txtnombre;
    private EditText txtFecha;
    private Button anyadir;

    DatabaseReference bbdd;
    FirebaseUser user;

    private OnFragmentInteractionListener mListener;

    public FragmentProyecto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_proyecto, container, false);

        // Definimos los componentes
        txtnombre = (EditText) v.findViewById(R.id.editProyecto);
        txtFecha = (EditText) v.findViewById(R.id.editFechaProy);
        anyadir = (Button) v.findViewById(R.id.btnGuardaProyecto);

        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodoProyectos));

        // Accedemos al usuario activo
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Implementamos la función del botón guardar proyecto

        anyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usuario = user.getUid().toString();
                final String nombreproyecto = txtnombre.getText().toString();
                final String fechavisita = txtFecha.getText().toString();

                // Hacemos una selección del proyecto según el nombre seleccionado
                Query q = bbdd.orderByChild("nombreproyecto").equalTo(nombreproyecto);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Si no obtenemos respuesta en el query el proyecto elegido por el usuario está disponible
                        if (!dataSnapshot.exists()) {
                            if (!TextUtils.isEmpty(nombreproyecto)) {
                                if (!TextUtils.isEmpty(fechavisita)) {
                                    Proyecto p = new Proyecto(
                                            usuario, nombreproyecto, fechavisita);
                                    String clave = bbdd.push().getKey();
                                    bbdd.child(clave).setValue(p);

                                    Toast.makeText(getActivity(), " Proyecto guardado", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getActivity(), "Introduce una fecha", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Introduce un nombre", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "El proyecto ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

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
        // Argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
