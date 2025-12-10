package com.example.praticamobilewillamy;

import android.util.JsonReader;

import com.example.praticamobilewillamy.model.Meme;
import com.example.praticamobilewillamy.model.MemeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/get_memes")
    Call<MemeResponse> getMemes();

    @GET("/random")
    Call<Character> getCharacter();

}
