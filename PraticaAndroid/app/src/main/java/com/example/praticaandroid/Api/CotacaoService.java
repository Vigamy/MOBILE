package com.example.praticaandroid.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CotacaoService {

    @GET("/USD-BRL")
    Call<List<Dolar>> obterDolar();

    @GET("/BRL-ARS")
    Call<List<Peso>> obterPeso();

}
