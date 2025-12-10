package com.aula.apis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.aula.apis.adapter.FotoAdapter;
import com.aula.apis.api.PhotoApi;
import com.aula.apis.model.Foto;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_getPhoto extends AppCompatActivity {

    private RecyclerView fotoRecyclerView;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_get_photo);

        fotoRecyclerView = findViewById(R.id.fotoRecyclerView);
        // Configurar RecycleView Apresentação
        fotoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //chamar API
        Intent intent = getIntent();
        if (intent.getBooleanExtra("byId", false)) {
            chamaAPI_Retrofit_byId();
        }else {
            chamaAPI_Retrofit();
        }
    }

    public void chamaAPI_Retrofit() {
        String API = "https://jsonplaceholder.typicode.com";

        //configurar acesso a API
        retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //criar chamada
        PhotoApi photoApi = retrofit.create(PhotoApi.class);
        Call<List<Foto>> call = photoApi.getAll();

        //executar chamada
        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, retrofit2.Response<List<Foto>> response) {
                List<Foto> fotos = response.body();
                fotoRecyclerView.setAdapter(new FotoAdapter(fotos));
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable throwable) {

            }
        });
    }

    public void chamaAPI_Retrofit_byId() {
        String API = "https://jsonplaceholder.typicode.com";

        //configurar acesso a API
        retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //criar chamada
        PhotoApi photoApi = retrofit.create(PhotoApi.class);
        Call<Foto> call = photoApi.getByID("2");

        //executar chamada
        call.enqueue(new Callback<Foto>() {
            @Override
            public void onResponse(Call<Foto> call, retrofit2.Response<Foto> response) {
                List<Foto> fotos = Collections.singletonList(response.body());
                fotoRecyclerView.setAdapter(new FotoAdapter(fotos));
            }

            @Override
            public void onFailure(Call<Foto> call, Throwable throwable) {

            }
        });
    }

}
