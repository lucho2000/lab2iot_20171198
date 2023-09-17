package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CronometroActivity extends AppCompatActivity {

    TextView textoCuenta;
    private boolean isRunning = false;
    private long startTime = 0;
    private long elapsedTime = 0;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                long currentTime = System.currentTimeMillis();
                elapsedTime = currentTime - startTime;
                updateTimerText(elapsedTime);
                handler.postDelayed(this, 10); // Actualiza cada segundo
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        textoCuenta = findViewById(R.id.textViewCuenta);
        Button botonInicio = findViewById(R.id.buttonInicio);
        Button botonDetener = findViewById(R.id.buttonDetener);
        Button botonPausa = findViewById(R.id.buttonPausar);
        Button botonLimpiar = findViewById(R.id.buttonLimpiar); //como un reset

        botonInicio.setOnClickListener(view -> {
            isRunning = true;
            botonInicio.setEnabled(false);
            botonPausa.setEnabled(true);
            botonLimpiar.setEnabled(true);
            handler.post(runnable);
        });

        botonPausa.setOnClickListener(view -> {

            isRunning=false;
            botonInicio.setEnabled(true);
            botonPausa.setEnabled(false);
            botonLimpiar.setEnabled(true);

        });

        botonLimpiar.setOnClickListener(view -> {

            isRunning=false;
            botonInicio.setEnabled(true);
            botonPausa.setEnabled(false);
            botonLimpiar.setEnabled(false);
            elapsedTime = 0;
            updateTimerText(0);
        });

    }

    public void updateTimerText(long time){

        long minutes = (time / 60000) % 60;
        long seconds = (time / 1000) % 60;
        long milliseconds = time % 1000;
        String timeString = String.format("%02d:%02d.%03d", minutes, seconds, milliseconds);
        textoCuenta.setText(timeString);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(CronometroActivity.this, "Se encuentra en la vista de Cronometro",Toast.LENGTH_SHORT).show();
    }
}