package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        Button irABotonCrono = findViewById(R.id.buttonCronometro);
        Button irABotonContador = findViewById(R.id.buttonContador);


        irABotonCrono.setOnClickListener(view -> {
            Intent intent = new Intent(PrincipalActivity.this, CronometroActivity.class);
            startActivity(intent);
        });

        irABotonContador.setOnClickListener(view -> {
            Intent intent = new Intent(PrincipalActivity.this, ContadorActivity.class);
            startActivity(intent);
        });

    }
}