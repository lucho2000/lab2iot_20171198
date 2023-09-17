package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button buttonRegistro = findViewById(R.id.buttonCreate);
        buttonRegistro.setEnabled(false);

        TextView textoNombre = findViewById(R.id.nombre);
        TextView textoApellido =findViewById(R.id.apelli);
        TextView textoCorreo =findViewById(R.id.correo);
        TextView textoContrasenia =findViewById(R.id.contrasenia);

        CheckBox deAcuerdo = findViewById(R.id.checkBox);


        deAcuerdo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b && !textoNombre.getText().toString().isEmpty() &&
                        !textoApellido.getText().toString().isEmpty() &&
                        !textoCorreo.getText().toString().isEmpty() &&
                        !textoContrasenia.getText().toString().isEmpty()){

                    buttonRegistro.setEnabled(true);
                }else {
                    buttonRegistro.setEnabled(false);
                }
            }
        });


        textoNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (deAcuerdo.isChecked() && !textoApellido.getText().toString().isEmpty() &&
                        !textoCorreo.getText().toString().isEmpty() &&
                        !textoContrasenia.getText().toString().isEmpty()){

                    buttonRegistro.setEnabled(true);
                }else {
                    buttonRegistro.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textoApellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (deAcuerdo.isChecked() && !textoNombre.getText().toString().isEmpty() &&
                        !textoCorreo.getText().toString().isEmpty() &&
                        !textoContrasenia.getText().toString().isEmpty()){

                    buttonRegistro.setEnabled(true);
                }else {
                    buttonRegistro.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        textoCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (deAcuerdo.isChecked() && !textoNombre.getText().toString().isEmpty() &&
                        !textoApellido.getText().toString().isEmpty() &&
                        !textoContrasenia.getText().toString().isEmpty()){

                    buttonRegistro.setEnabled(true);
                }else {
                    buttonRegistro.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textoContrasenia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (deAcuerdo.isChecked() && !textoNombre.getText().toString().isEmpty() &&
                        !textoApellido.getText().toString().isEmpty() &&
                        !textoCorreo.getText().toString().isEmpty()){

                    buttonRegistro.setEnabled(true);
                }else {
                    buttonRegistro.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buttonRegistro.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this , PrincipalActivity.class);
            startActivity(intent);
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(RegisterActivity.this, "Esta en la vista de Registro",Toast.LENGTH_SHORT).show();
    }
}