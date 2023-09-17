package com.example.lab2application.interfaces;

import com.example.lab2application.classes.Picture;
import com.example.lab2application.classes.Respuesta;
import com.example.lab2application.classes.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    //metodos
    @GET("/api/")
    Call<Respuesta> getResult();

    @GET("/api/portraits/med/women/18.jpg")
    Call<Picture> getPicture();


}
