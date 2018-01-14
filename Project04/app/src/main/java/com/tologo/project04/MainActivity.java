package com.tologo.project04;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<DatosMenu> opcionsDelMenu=new ArrayList<>();
        opcionsDelMenu.add(new DatosMenu("PERFIL",R.drawable.ic_perfil));
        opcionsDelMenu.add(new DatosMenu("JUEGO",R.drawable.ic_juego));
        opcionsDelMenu.add(new DatosMenu("INSTRUCCIONES",R.drawable.ic_instrucciones));
        opcionsDelMenu.add(new DatosMenu("INFORMACIÓN", R.drawable.ic_informacion));


        //6-Le pasamos el Fragment definido en la clase MenuFragment
        //MANEL Comente esta línia la que els paràmetres del FRagment els passem via el mètode
        // newInstance(paràmetres)
        // MenuFragment fragment = new MenuFragment();
        MenuFragment fragment = MenuFragment.newInstance(opcionsDelMenu);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Le pasamos el ID del Fragment del activity_menu.xml
        fragmentTransaction.add(R.id.contenido,fragment);
        fragmentTransaction.commit(); //Lanzamos el fragment en nuestra Activity Menu_Activity


        //Bundle bundle = getIntent().getExtras();

        // Declaramos un nuevo Fragment para mostrar al lanzar
        FragmentDinamico firstFragment = new FragmentDinamico();

        // Añadimos el fragment al contenedor de tipo FrameLayout
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutDinam, firstFragment).addToBackStack(null).commit();

        }


    // Acciones a realizar al hacer clic sobre la lista
    /*@Override
    public void onListSelected(int position, String item) {
        if (position == 0) {
            Toast.makeText(this,item, Toast.LENGTH_LONG).show();
        }
        if (position == 1) {
            Toast.makeText(this,item, Toast.LENGTH_LONG).show();
        }
        if (position == 2) {
            Toast.makeText(this,item, Toast.LENGTH_LONG).show();
        }
        if (position == 3) {
            Toast.makeText(this,item, Toast.LENGTH_LONG).show();
        }
    }*/


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}


