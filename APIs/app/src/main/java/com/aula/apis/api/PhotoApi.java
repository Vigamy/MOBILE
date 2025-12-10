package com.aula.apis.api;

import com.aula.apis.model.Foto;
import com.aula.apis.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PhotoApi {

    // recuperado portal de teste: 5000 itens
    @GET("/photos")
    Call<List<Foto>> getAll();

    // recuperado pelo id
    @GET("/photos/{id}")
    Call<Foto> getByID(@Path("id") String id);




    // usando um envio por json
    @POST("/posts")
    Call<Postagem> salvarPostagem(@Body Postagem postagem);








    // Fazendo via formulario
    //userId=1234&title=Título postagem&body=Corpo postagem
    @FormUrlEncoded
    @POST("/posts")
    Call<Postagem> salvarPostagem(
            @Field("userId") String userId,
            @Field("title") String title,
            @Field("body") String body
    );

    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagem(@Path("id") int id, @Body Postagem postagem );

    @DELETE("/posts/{id}")
    Call<Void> removerPostagem(@Path("id") int id);

}
