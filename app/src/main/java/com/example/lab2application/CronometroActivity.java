package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class CronometroActivity extends AppCompatActivity {

    TextView textoCuenta;

    Button botonInicio;

    Button botonPausa;

    Button botonLimpiar;

    Button botonReanudar;
    private boolean isRunning = false;
    private long startTime = 0;
    private long elapsedTime = 0;

    private Thread hiloTiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        textoCuenta = findViewById(R.id.textViewCuenta);
        botonInicio = findViewById(R.id.buttonInicio);
        botonPausa = findViewById(R.id.buttonPausar);
        botonLimpiar = findViewById(R.id.buttonLimpiar); //como un reset
        botonReanudar = findViewById(R.id.buttonReanudar);

        botonInicio.setOnClickListener(view -> {
            //isRunning = true;
            //botonInicio.setEnabled(false);
            //botonPausa.setEnabled(true);
            //botonLimpiar.setEnabled(true);
            //handler.post(runnable);
            iniciandoCronometro();



        });

        botonPausa.setOnClickListener(view -> {

            pausar();

        });

        botonLimpiar.setOnClickListener(view -> {

            limpiar();
        });

        botonReanudar.setOnClickListener(view -> {

            reanudar();

        });


    }

    public void iniciandoCronometro(){

        if (!isRunning) {
            isRunning = true;
            if (startTime == 0) {
                startTime = System.currentTimeMillis();
            }

            hiloTiempo = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        try {
                            Thread.sleep(1000); // Esperar 1 segundo
                            elapsedTime = System.currentTimeMillis() - startTime;
                            updateTimerText();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            hiloTiempo.start();
        }
    }

    public void pausar(){

        if (isRunning) {
            isRunning = false;

            elapsedTime = System.currentTimeMillis() - startTime;
            if (hiloTiempo != null) {
                hiloTiempo.interrupt(); // Detener el hilo
                hiloTiempo = null;
            }
        }
    }

    public void reanudar() {
        if (!isRunning) {
            isRunning = true;

            startTime = System.currentTimeMillis() - elapsedTime;

            // Iniciar un nuevo hilo del cronómetro si no hay uno activo
            if (hiloTiempo == null) {
                hiloTiempo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isRunning) {
                            try {
                                Thread.sleep(1000); // Esperar 1 segundo
                                elapsedTime = System.currentTimeMillis() - startTime;
                                updateTimerText();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                hiloTiempo.start();
            }

        }
    }

    public void limpiar(){

        isRunning = false;
        startTime = 0;
        elapsedTime = 0;
        updateTimerText();
    }

    public void updateTimerText(){

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                int seconds = (int) (elapsedTime / 1000);
                int minutes = seconds / 60;
                int hours = minutes / 60;

                seconds %=60;
                minutes %=60;


                //long milliseconds = time % 1000;
                String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                textoCuenta.setText(timeString);

                botonInicio.setEnabled(!isRunning);
                botonPausa.setEnabled(isRunning);
                botonReanudar.setEnabled(!isRunning && elapsedTime>0);
                botonLimpiar.setEnabled(!isRunning);

                if (isRunning) {
                    botonInicio.setEnabled(true);
                    botonReanudar.setEnabled(true);
                    botonLimpiar.setEnabled(true);
                }

            }
            //botonReanudar.setEnabled(!isRunning);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(CronometroActivity.this, "Se encuentra en la vista de Cronometro",Toast.LENGTH_SHORT).show();
    }
}