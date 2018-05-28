package com.tologo.energynotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.tologo.energynotes.Fragments.FragmentCliente;
import com.tologo.energynotes.Fragments.FragmentDatosGenerales;
import com.tologo.energynotes.Fragments.FragmentDinamico;
import com.tologo.energynotes.Fragments.FragmentProyecto;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentDinamico.OnFragmentInteractionListener,
        FragmentProyecto.OnFragmentInteractionListener, FragmentDatosGenerales.OnFragmentInteractionListener,
        FragmentCliente.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuramos el FloatingActionButton para finalizar la sesión de usuario activo.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mostramos un mensaje de alerta de fin de sesión
                AlertDialog.Builder builder = new AlertDialog.Builder(Principal.this);
                builder.setMessage("¿Quieres finalizar sesión?");
                builder.setTitle("Energy Notes");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cerramos la sesión activa
                        FirebaseAuth.getInstance().signOut();
                        // Vamos a programar nuestra llamada a la Activity de Login
                        Intent llamada = new Intent(Principal.this, MainActivity.class);
                        startActivity(llamada);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exportar) {
            return true;
        }
        if (id == R.id.action_pendientes) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean FragmentTransaction = false;
        Fragment fragment = null;

        if (id == R.id.nav_proyecto) {
            Toast.makeText(this, "Proyecto", Toast.LENGTH_LONG).show();
            fragment = new FragmentProyecto();
            FragmentTransaction=true;

        } else if (id == R.id.nav_generales) {
            Toast.makeText(this, "Datos generales", Toast.LENGTH_LONG).show();
            fragment = new FragmentDatosGenerales();
            FragmentTransaction=true;
        } else if (id == R.id.nav_cliente) {
            Toast.makeText(this, "Datos del cliente", Toast.LENGTH_LONG).show();
            fragment = new FragmentCliente();
            FragmentTransaction=true;

        } else if (id == R.id.nav_fachada) {

        } else if (id == R.id.nav_hueco) {

        } else if (id == R.id.nav_altura) {

        } else if (id == R.id.nav_acs) {

        } else if (id == R.id.nav_aire) {

        }


        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frcontenido, fragment)
                    .commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}