package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab2application.classes.Result;
import com.example.lab2application.interfaces.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    TextView textoNombre;
    TextView textoApellido;
    TextView textoCorreo;

    TextView textoContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button buttonRegistro = findViewById(R.id.buttonCreate);
        buttonRegistro.setEnabled(true);

        textoNombre = findViewById(R.id.nombre);
        textoApellido = findViewById(R.id.apelli);
        textoCorreo =findViewById(R.id.correo);
        textoContrasenia =findViewById(R.id.contrasenia);

        CheckBox deAcuerdo = findViewById(R.id.checkBox);

        obtenerWs();

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


    //obtener datos de la api
    public void obtenerWs(){

        Api typicodeService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);

        typicodeService.getResult().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if (response.isSuccessful()){

                    Log.d("nombre", "nombre: " + result.getName().getFirst());

                } else {
                    Log.d("msg-test", "error en la consulta");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}