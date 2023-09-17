package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(PrincipalActivity.this, "Esta en la vista de escoger entre Contador y Cronometro",Toast.LENGTH_SHORT).show();
    }
}