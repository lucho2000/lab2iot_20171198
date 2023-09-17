package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    Result result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para ver si hay conexion o no
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (tieneInternet){

            Toast.makeText(MainActivity.this, "Esta conectado a internet",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "No esta conectado a internet",Toast.LENGTH_SHORT).show();
        }

        //Log.d("msg-test","Internet: " + tieneInternet);

        //linkeo hacia el registro
        Button buttonRegistro = findViewById(R.id.button);
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

                //mandando los datos obtenidos al otro activity
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this, "Esta en la pagina de inicio de la app",Toast.LENGTH_SHORT).show();
    }


}