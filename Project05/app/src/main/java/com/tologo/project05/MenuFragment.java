package com.tologo.project05;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

// Creamos la clase que contendrá al menú de opciones y que extiende de Fragment
public class MenuFragment extends Fragment {

    MenuFragmentListener mCallback;
    private customListener mListener;

    /*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    */

    // Declaramos las variables a emplear
    private ArrayList<DatosMenu> listadoOpcionesDelMenu;  // Objecte on posarem les opcions del menu
    RecyclerView recyclerView;
    MenuAdapter mAdapter;

    //private OnFragmentInteractionListener mListener;

    // Constructor
    public MenuFragment() {
        // Required empty public constructor
    }

    public interface MenuFragmentListener {
        public void onListSelected(String item);
    }

    /*public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment mf = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        mf.setArguments(args);
        return mf;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/


    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Creamos una vista y le añadimos el fragment a inflar desde el layout fragment_menu
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Iniciamos el Array
        listadoOpcionesDelMenu = new ArrayList<>();
        // Añadimos el RecyclerView desde fragment_menu.xml
        recyclerView = (RecyclerView) view.findViewById(R.id.miRecyclerView);
        recyclerView.setHasFixedSize(true); //Fijamos el tamaño en el layout del RecyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        // Definimos el layout nuevo a adaptar
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        //Introducimos elementos en el ArrayList para pasárselos al Recycler
        listadoOpcionesDelMenu.add(new DatosMenu("PERFIL", R.drawable.ic_perfil));
        listadoOpcionesDelMenu.add(new DatosMenu("JUEGO", R.drawable.ic_juego));
        listadoOpcionesDelMenu.add(new DatosMenu("INSTRUCCIONES", R.drawable.ic_instrucciones));
        listadoOpcionesDelMenu.add(new DatosMenu("INFORMACIÓN", R.drawable.ic_informacion));

        //Añadimos el Adaptador creado en la clase MenuAdapter
        mAdapter = new MenuAdapter(listadoOpcionesDelMenu);
        recyclerView.setAdapter(mAdapter);

        // Esta sería la opción como INNER CLASS, pero no funciona...
        mAdapter.setOnClickListener(new customListener());

        /*mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = (String) listadoOpcionesDelMenu.get(recyclerView.getChildAdapterPosition(view)).getTitulo().toString();
                Toast.makeText(getContext(),"Seleccion: "+
                        item,Toast.LENGTH_SHORT).show();

                //Pasamos la información
                //mCallback.onListSelected(item);
            }
        });*/

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mCallback = (MenuFragmentListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+ "must implement OnHeadLineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // Esta sería la INNER CLASS para resolver los 'Click' sobre el recyclerView
    private class customListener implements AdapterView.OnClickListener{
        @Override
        public void onClick(View v) {
            /*Toast.makeText(getContext(),"Seleccion: "+
                    listadoOpcionesDelMenu.get(recyclerView.
                            getChildAdapterPosition(v)).getTitulo(),Toast.LENGTH_SHORT).show();*/
            String item = (String) listadoOpcionesDelMenu.get(recyclerView.getChildAdapterPosition(v)).getTitulo();

            //Pasamos la información
            mCallback.onListSelected(item);
        }

    }

}