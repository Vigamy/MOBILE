package com.aula.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aula.api.adapter.GetAllPhotosAdapter;
import com.aula.api.api.PhotoApi;
import com.aula.api.model.Foto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAllPhotos extends AppCompatActivity {

    RecyclerView recyclerView;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_photos);

        recyclerView = findViewById(R.id.fotoRecyclerView);

        // Chamar a API
        Intent intent = getIntent();
        if (intent.getBooleanExtra("byId", false)) {
            chamarAPI_Retrofit_byId();
        } else {
            chamarAPI_Retrofit();
        }

    }
    private void chamarAPI_Retrofit_byId() {
        String url = "https://jsonplaceholder.typicode.com/";

        // Configurar a API
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Criar chamada
        PhotoApi photoApi = retrofit.create(PhotoApi.class);
        Call<Foto> call = photoApi.getOne("12");

        call.enqueue(new Callback<Foto>() {
            @Override
            public void onResponse(Call<Foto> call, Response<Foto> response) {
                List<Foto> fotos = Collections.singletonList(response.body());
                recyclerView.setAdapter(new GetAllPhotosAdapter(fotos));
            }

            @Override
            public void onFailure(Call<Foto> call, Throwable throwable) {
                Toast.makeText(GetAllPhotos.this, "deu ruim", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void chamarAPI_Retrofit() {
        String url = "https://jsonplaceholder.typicode.com/";

        // Configurar a API
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Criar chamada
        PhotoApi photoApi = retrofit.create(PhotoApi.class);
        Call<List<Foto>> call = photoApi.getAll();

        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                List<Foto> fotos = response.body();

                // Configurar a recyclerview
                GetAllPhotosAdapter adapter = new GetAllPhotosAdapter(fotos);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(GetAllPhotos.this));
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable throwable) {
                Log.e("ERRO", throwable.getMessage());
            }
        });

    }
}