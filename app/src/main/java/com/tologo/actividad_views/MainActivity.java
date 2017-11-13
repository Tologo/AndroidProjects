package com.tologo.actividad_views;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToggleButton button1 = (ToggleButton) findViewById(R.id.toggleButton1);
        final ToggleButton button2 = (ToggleButton) findViewById(R.id.toggleButton2);
        final LinearLayout layout2 = (LinearLayout) findViewById(R.id.Layout2);
        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox);
        final TextView textOculto = (TextView) findViewById(R.id.textOculto);
        final TextView txtClicLargo = (TextView) findViewById(R.id.txtClicLargo);
        final TextView textEstrellas = (TextView) findViewById(R.id.textEstrellas);
        final RatingBar bar = (RatingBar) findViewById(R.id.ratingBar);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button1.isChecked()){
                    String message = getResources().getString(R.string.fondo2);
                    button1.setText(message);
                    layout2.setBackgroundColor(Color.rgb(255, 0, 0));
                } else {
                    String message = getResources().getString(R.string.fondo1);
                    button1.setText(message);
                    layout2.setBackgroundColor(Color.rgb(210, 210, 210));
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button2.isChecked()){
                    String message = getResources().getString(R.string.letras2);
                    button2.setText(message);
                    button2.setTextColor(Color.rgb(255,0,0));
                } else {
                    String message = getResources().getString(R.string.letras1);
                    button2.setText(message);
                    button2.setTextColor(Color.rgb(0,0,0));
                }
            }
        });

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()) {
                    textOculto.setVisibility(View.VISIBLE);
                } else {
                    textOculto.setVisibility(View.INVISIBLE);
                }
            }
        });

        txtClicLargo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "¡Muchas gracias!", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textEstrellas.setText("[" +  Math.round(ratingBar.getRating()) + "/" + ratingBar.getNumStars() + "]")
                ;
            }
        });

    }
}
