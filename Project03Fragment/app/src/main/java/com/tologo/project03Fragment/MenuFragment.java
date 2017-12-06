package com.tologo.project03Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;


public class MenuFragment extends Fragment {

    /*Declaramos la interface del Fragment y el Listener
    MenuFragmentListener mCallback;
    private customListener mListener;
*/
    public MenuFragment() {
        // Required empty public constructor
    }

    //Interface al clickar en la lista coge la posición y el texto
    public interface MenuFragmentListener {
        public void onListSelected(int position, String item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        // --------------------------------------------------------------
        // En primer lugar definimos el Array de Strings y lo convertimos a una Lista, en este caso ArrayList
        //DATOS
        String[] opcionesMenu = new String[]{"Perfil", "Juego", "Instrucciones",
                "Información"};
        // Convertimos el Array en una lista necesaria para el adaptador
        ArrayList<String> listaMenu = new ArrayList<String>(Arrays.asList(opcionesMenu));

        //Obtenim una instància del RecyclerView
        RecyclerView rv = (RecyclerView) getActivity().findViewById(R.id.miRecyclerView);
        //Triem el LayoutManager que volem utilitzar i l'assignem a l'objecte recyclerView
        LinearLayoutManager rvLM = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(rvLM);

        //Creem l'adaptador que interactuarà amb les dades
        MenuAdapter aRVclient = new MenuAdapter(listaMenu);

        //Enllacem el RecyclerView amb l'adaptador
        rv.setAdapter(aRVclient);
// ------------------------------------------------------------

    }

    @Override
    public void onDetach() {
        super.onDetach();
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /*Implementamos Listener para el listView
    private class customListener implements AdapterView.OnItemClickListener{

        //Recibe el Adapter con el implements, el view, donde se ha clickado y la id
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //String de la posición clickada
            String item = (String) parent.getItemAtPosition(position);
            //Pasamos la información
            mCallback.onListSelected(position,item);
        }
    }*/

}