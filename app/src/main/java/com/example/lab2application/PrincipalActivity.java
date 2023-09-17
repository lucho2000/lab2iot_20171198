package com.example.lab2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab2application.classes.Respuesta;
import com.example.lab2application.classes.Result;
import com.example.lab2application.interfaces.Api;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrincipalActivity extends AppCompatActivity {

    private ImageView imageView;

    private TextView textViewUsername;

    private TextView textViewNombApellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        Button irABotonCrono = findViewById(R.id.buttonCronometro);
        Button irABotonContador = findViewById(R.id.buttonContador);

        textViewUsername = findViewById(R.id.textViewUsername);
        textViewNombApellido = findViewById(R.id.textViewNomApe);

        imageView = findViewById(R.id.imageView);

        String imageUrl = "https://randomuser.me/api/portraits/men/59.jpg";

        Picasso.get()
                .load(imageUrl)
                .into(imageView);

        obtenerWs();

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


    //obtener datos de la api
    public void obtenerWs(){

        Api typicodeService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);

        typicodeService.getResult().enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.isSuccessful()){
                    Respuesta respuesta = response.body();

                    Result result = respuesta.getResults().get(0);
                    textViewUsername.setText(result.getLogin().getUsername() );
                    textViewNombApellido.setText( result.getName().getFirst() + " " + result.getName().getLast() );

                } else {
                    Log.d( "msg-test", "error de cosnulta" );
                }

            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}