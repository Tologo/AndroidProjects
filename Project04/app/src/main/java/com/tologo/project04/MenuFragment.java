package com.tologo.project04;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;


public class MenuFragment extends Fragment {

    // Declaramos la interface del Fragment y el Listener
    private ArrayList<DatosMenu> listadoOpcionesDelMenu;  // Objecte on posarem les opcions del menu
    private RecyclerView recyclerView;
    MenuAdapter ARVElemento;
    private OnFragmentInteractionListener mListener;
    //MenuFragmentListener mCallback;
    //private customListener mListener;

    // Constructor
    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(ArrayList<DatosMenu> opcionsDeMenuPerAlFragment){
        MenuFragment mf = new MenuFragment();
        if(opcionsDeMenuPerAlFragment!=null){
            Bundle b = new Bundle();
            b.putParcelableArrayList("key_llistaOpcionsMenuFragment",opcionsDeMenuPerAlFragment);
            mf.setArguments(b);
        }
        return mf;
    }

    //Interface al clickar en la lista coge la posición y el texto
    public interface MenuFragmentListener {
        public void onListSelected(int position, String item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listadoOpcionesDelMenu = getArguments().getParcelableArrayList("key_llistaOpcionsMenuFragment");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Añadimos el fragment a inflar desde el layout fragment_menu
        View view = inflater.inflate(R.layout.fragment_menu,container,false);

        //Añadimos el RecyclerView desde fragment_menu.xml
        // MANEL comente esta línia ja que al crear l'objecte recyclerView dins del mètode onCreateView()
        // ja que seria una variable local a este mètode, i no podriem utilitzar-la des d'altres mètodes.
        // La declare com un atribut de la classe MenuFragment

        recyclerView = view.findViewById(R.id.miRecyclerView);
        recyclerView.setHasFixedSize(true); //Fijamos el tamaño en el layout del RecyclerView
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL)); //Lineas Divisorias en el Layout
        //Definimos el layout nuevo a adaptar
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //Añadimos el Adaptador creado en la clase MenuAdapter
        MenuAdapter menuAdapter = new MenuAdapter(listadoOpcionesDelMenu);
        recyclerView.setAdapter(menuAdapter);

        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}