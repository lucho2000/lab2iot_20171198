package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.EditText;

public class ContadorActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private int counterValue = 104;
    private boolean counting = false;
    private boolean fastMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        Button botonIniciarParar = findViewById(R.id.buttonIniciarContador);
        EditText textInicial = findViewById(R.id.editTextNumber);

        botonIniciarParar.setOnClickListener(view -> {
            if (counting) {
                // Detener el contador
                countDownTimer.cancel();
                counting = false;
                botonIniciarParar.setText("Iniciar");
            } else {
                // Iniciar el contador
                counting = true;
                botonIniciarParar.setText("Detener");

                // Aumentar la velocidad si está en modo rápido
                int interval = fastMode ? 5000 : 10000;


                countDownTimer = new CountDownTimer((226 - counterValue) * 1000, interval) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        counterValue++;
                        textInicial.setText(String.valueOf(counterValue));
                    }

                    @Override
                    public void onFinish() {
                        textInicial.setText("226");
                        // Reproducir una alarma o vibrar aquí
                        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(2000); // Vibrar durante 2 segundos
                        }
                    }
                };

                countDownTimer.start();
            }
        });

    }
}