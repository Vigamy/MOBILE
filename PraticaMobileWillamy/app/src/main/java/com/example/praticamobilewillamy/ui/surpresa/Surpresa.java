package com.example.praticamobilewillamy.ui.surpresa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.praticamobilewillamy.Api;
import com.example.praticamobilewillamy.R;
import com.example.praticamobilewillamy.model.Meme;
import com.example.praticamobilewillamy.model.MemeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Surpresa extends Fragment {

    Retrofit retrofit;
    ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surpresa, container, false);

        Intent intent = new Intent();
        intent.getExtras();

        imageView = view.findViewById(R.id.imageViewPlaceHolder);

        Bundle bundle = new Bundle();

        if (bundle.getBoolean("Botao")){
            pegarMeme(getContext());
        } else {
            pegarCharacter(getContext());
        }

        return view;
    }

    private void pegarCharacter(Context context) {
        String url = "https://api.gameofthronesquotes.xyz/v1/";

        // Configurar a API
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Criar chamada
        Api apiCharacter = retrofit.create(Api.class);

        Call<Character> call = apiCharacter.getCharacter();

        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if (response.isSuccessful()) {
                    Character responseBody = response.body();

                    Glide.with(context).asBitmap().load(responseBody).into(imageView);
                } else {
                    // Trate o erro
                    Toast.makeText(context, "Não foi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable throwable) {

            }
        });

    }

    private void pegarMeme(Context context) {
        String url = "https://api.imgflip.com/";

        // Configurar a API
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Criar chamada
        Api apiMeme = retrofit.create(Api.class);

        Call<MemeResponse> call = apiMeme.getMemes();

        call.enqueue(new Callback<MemeResponse>() {
            @Override
            public void onResponse(Call<MemeResponse> call, Response<MemeResponse> response) {
                if (response.isSuccessful()) {
                    MemeResponse responseBody = response.body();
                    List<Meme> memes = responseBody.getData();
                    for (Meme meme : memes) {
                        String url = meme.getUrl();
                        Glide.with(context).asBitmap().load(url).into(imageView);
                    }
                } else {
                    // Trate o erro
                }
            }

            @Override
            public void onFailure(Call<MemeResponse> call, Throwable throwable) {
                Log.d("EROO", throwable.toString());

                Log.d("ERRO", call.toString());
//            }
            }
        });
    }

}