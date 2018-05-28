package com.tologo.energynotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {


        private long SPLASH_DELAY = 4000; // 4 segundos

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            //TimerTask
            TimerTask task = new TimerTask() {
                @Override public void run() {

                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                    //Destruimos esta activity para prevenir que el usuario vuelva a este Activity presionando el boton Atras.
                    finish();
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, SPLASH_DELAY);
        }

    }