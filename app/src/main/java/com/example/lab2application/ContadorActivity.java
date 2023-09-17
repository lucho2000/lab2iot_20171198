package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContadorActivity extends AppCompatActivity {

    //private CountDownTimer countDownTimer;
    private Button botonIniciarParar;

    private EditText textInicial;
    private int counterValue = 104; //valor inicial
    private boolean counting = false;
    private Thread counterThread; //hilo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        botonIniciarParar = findViewById(R.id.buttonIniciarContador);
        textInicial = findViewById(R.id.editTextNumber);

        botonIniciarParar.setOnClickListener(view -> {
            if (counting) {
                // Detener el contador
                pararCuenta();
            } else {
                // Iniciar el contador
                iniciarCuenta();
            }
        });


    }

    public void iniciarCuenta(){
        counting = true;
        botonIniciarParar.setText("Detener");

        counterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counting && counterValue < 226) {
                    try {
                        Thread.sleep(10000); // Espera 10 segundos
                        counterValue++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textInicial.setText(String.valueOf(counterValue));
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (counterValue >= 226) {

                    //vibrar
                    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    if (vibrator != null) {
                        vibrator.vibrate(2000);
                    }
                }
            }
        });
        counterThread.start();

    }

    public void pararCuenta(){
        counting = false;
        botonIniciarParar.setText("Iniciar");
        if (counterThread != null) {
            counterThread.interrupt();
            counterThread = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(ContadorActivity.this, "Esta en la vista de iniciar el Contador",Toast.LENGTH_SHORT).show();
    }
}