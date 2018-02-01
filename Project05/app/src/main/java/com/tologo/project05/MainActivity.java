package com.tologo.project05;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MenuFragment.MenuFragmentListener,
        FragmentDinamico.OnFragmentInteractionListener, PerfilFragment.buttonListener{

    // Definimos un objeto de la clase Jugador
    public Jugador player = new Jugador();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}


