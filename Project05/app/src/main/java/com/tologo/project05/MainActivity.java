package com.tologo.project05;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class MainActivity extends AppCompatActivity implements MenuFragment.MenuFragmentListener,
        FragmentDinamico.OnFragmentInteractionListener, PerfilFragment.buttonListener, CameraFragment.OnFragmentInteractionListener{

    // Definimos un objeto de la clase Jugador
    public Jugador player = new Jugador();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Añadimos el Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Proyecto Final");
        setSupportActionBar(myToolbar);

        Bundle bundle = getIntent().getExtras();

        // Lanzamos la pantalla de bienvenida
        Pantalla_bienvenida();
    }

    public void Pantalla_bienvenida(){
        // Declaramos un nuevo Fragment para mostrar al lanzar
        FragmentDinamico firstFragment = new FragmentDinamico();
        // Añadimos el fragment al Framelayout
        getSupportFragmentManager().beginTransaction().add(R.id.frcontenido,firstFragment).commit();
    }


    // Acciones a realizar al hacer clic sobre la lista
    public void onListSelected(String item) {
        if (item == "PERFIL") {
            Toast.makeText(this,item, Toast.LENGTH_LONG).show();
            PerfilFragment FragmentparaPerfil = new PerfilFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frcontenido,FragmentparaPerfil).commit();
        }
        if (item == "JUEGO") {
            Toast.makeText(this,item, Toast.LENGTH_LONG).show();
            // Cargamos el fragment de juego, pasando 'nick' y puntuación del jugador registrado
            JuegoFragment nuevoJuego = new JuegoFragment();
            Bundle args = new Bundle(); // Definimos los argumentos que vamos a pasar al Fragment Juego
            args.putString(JuegoFragment.ARG_NICK, player.getNick());
            args.putString(JuegoFragment.ARG_PUNTOS, String.valueOf(player.getPuntos()));
            nuevoJuego.setArguments(args); // Encapsulamos los argumentos y lanzamos el Fragment Juego
            getSupportFragmentManager().beginTransaction().replace(R.id.frcontenido,nuevoJuego).commit();
        }
        if (item == "INSTRUCCIONES") {
            Toast.makeText(this,item, Toast.LENGTH_LONG).show();
        }
        if (item == "INFORMACIÓN") {
            Toast.makeText(this,item, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    // Implementamos el onClick sobre el Fragment de perfil para asignar nombre y nick al objeto jugador
    public void onClickButton(String nombreJugador, String nickJugador){
        player.setNombre(nombreJugador);
        player.setNick(nickJugador);
        Toast.makeText(this,"Hola "+ player.getNick(),Toast.LENGTH_LONG).show();
        // Y volvemos a la pantalla de Bienvenida
        Pantalla_bienvenida();
    }

    // Inflamos el menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // Gestionamos los eventos del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.perfil:
                PerfilFragment FragmentparaPerfil = new PerfilFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frcontenido,FragmentparaPerfil).commit();
                return true;
            case R.id.juego:
                JuegoFragment nuevoJuego = new JuegoFragment();
                Bundle args = new Bundle(); // Definimos los argumentos que vamos a pasar al Fragment Juego
                args.putString(JuegoFragment.ARG_NICK, player.getNick());
                args.putString(JuegoFragment.ARG_PUNTOS, String.valueOf(player.getPuntos()));
                nuevoJuego.setArguments(args); // Encapsulamos los argumentos y lanzamos el Fragment Juego
                getSupportFragmentManager().beginTransaction().replace(R.id.frcontenido,nuevoJuego).commit();
                return true;
            case R.id.menuCamara:
                CameraFragment FragmentparaCamara = new CameraFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frcontenido,FragmentparaCamara).commit();
                return true;
            case R.id.menuMapa:
                MapaFragment FragmentparaMapa = new MapaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frcontenido,FragmentparaMapa).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}


