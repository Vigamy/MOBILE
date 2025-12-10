package com.aula.api.api;

import com.aula.api.model.Foto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotoApi {
    @GET("/photos")
    Call<List<Foto>> getAll();

    @GET("/photos/{id}")
    Call<Foto> getOne(@Path("id") String id);
}
