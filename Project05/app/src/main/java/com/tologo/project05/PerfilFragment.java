package com.tologo.project05;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PerfilFragment extends android.support.v4.app.Fragment {

    /*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;*/

    buttonListener mButton;
    private customListenerp mListener;
    String nombreJugador;
    String nickJugador;

    // Establecemos el Listener sobre el botón de la interfaz
    public interface buttonListener{
        public void onClickButton(String nombre, String nick);
    }

    // Declaramos las variables a emplear
    //private PerfilFragment.OnFragmentInteractionListener mListener;

    // Constructor
    public PerfilFragment() {
        // Required empty public constructor
    }

    /*public static PerfilFragment newInstance(String param1, String param2){
        PerfilFragment pf = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        pf.setArguments(args);
        return pf;
    }*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }


    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Creamos una vista y le añadimos el fragment a inflar desde el layout fragment_menu
        View view = inflater.inflate(R.layout.fragment_perfil,container,false);

        // Declaramos los EditText y el Botón
        final EditText nombre = (EditText) view.findViewById(R.id.editTextNombre);
        final EditText nick = (EditText) view.findViewById(R.id.editTextNick);
        final Button guardar = (Button) view.findViewById(R.id.buttonGuPerfil);

        // Declaramos la acción del botón
        guardar.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    // Guardamos los valores indicados en los EditText
                    nombreJugador = nombre.getText().toString();
                    nickJugador = nick.getText().toString();
                    mButton.onClickButton(nombreJugador,nickJugador);
                }
            }
        );

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mButton = (PerfilFragment.buttonListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+ "must implement OnHeadLineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // Esta sería la INNER CLASS para resolver el 'Click' sobre el botón
    private class customListenerp implements AdapterView.OnClickListener{
        @Override
        public void onClick(View v) {
            //Pasamos la información
            mButton.onClickButton(nombreJugador,nickJugador);
        }

    }

}