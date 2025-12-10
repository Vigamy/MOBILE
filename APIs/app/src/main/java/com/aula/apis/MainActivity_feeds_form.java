package com.aula.apis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aula.apis.api.PhotoApi;
import com.aula.apis.model.Postagem;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_feeds_form extends AppCompatActivity {
    private Retrofit retrofit;
    private TextView id;
    private TextView userId;
    private TextView title;
    private TextView body;
    private TextView resultado;
    private Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feeds_form);

        id = findViewById(R.id.postId);
        userId = findViewById(R.id.postUserId);
        title = findViewById(R.id.postTitle);
        body = findViewById(R.id.postBody);
        resultado = findViewById(R.id.txtResultado);

        resultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setVisibility(View.INVISIBLE);
            }
        });

        // configuração evento Botão
        bt = findViewById(R.id.btPost);

        Intent intent = getIntent();
        if (Objects.equals(intent.getStringExtra("byPost"), "post")) {
            bt.setText("Enviar POST");
            bt.setOnClickListener(v -> {chamaAPIpost_Retrofit();});
        } else if (Objects.equals(intent.getStringExtra("byPost"), "put")) {
            bt.setText("Enviar PUT");
            bt.setOnClickListener(v -> {chamaAPIput_Retrofit();});
        } else if (Objects.equals(intent.getStringExtra("byPost"), "delete")) {
            bt.setText("Enviar DELETE");
            bt.setOnClickListener(v -> {chamaAPIdelete_Retrofit();});
        }

    }

    public void chamaAPIpost_Retrofit() {

        String urlAPI = "https://jsonplaceholder.typicode.com/";
        //configurar acesso a API
        retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoApi photoApi = retrofit.create(PhotoApi.class);

        // enviando objeto
        Postagem postagem = new Postagem(userId.getText().toString(),
                                         title.getText().toString(),
                                         body.getText().toString());

        Call<Postagem> call = photoApi.salvarPostagem(postagem);

        // enviando como Formulario
        //Call<Postagem> call = photoApi.salvarPostagem("4321", "titulo Form","Corpo Form");

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {

                if( response.isSuccessful() ){
                    Postagem postagemResposta = response.body();
                    resultado.setVisibility(View.VISIBLE);
                    resultado.setText(
                            "Status: " + response.code() +
                                    " \n\nId: " + postagemResposta.getId() +
                                    " \nuserId: " + postagemResposta.getUserId() +
                                    " \nTitle: " + postagemResposta.getTitle() +
                                    " \nBody: " + postagemResposta.getBody()
                    );
                }

            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    public void chamaAPIput_Retrofit() {

        String urlAPI = "https://jsonplaceholder.typicode.com/";
        //configurar acesso a API
        retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoApi photoApi = retrofit.create(PhotoApi.class);

        // enviando objeto
        Postagem postagem = new Postagem(userId.getText().toString(),
                title.getText().toString(),
                body.getText().toString());
        postagem.setId(id.getText().toString());

        Call<Postagem> call = photoApi.atualizarPostagem(Integer.parseInt(postagem.getId()),postagem);

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {

                if( response.isSuccessful() ){
                    Postagem postagemResposta = response.body();
                    resultado.setVisibility(View.VISIBLE);
                    resultado.setText(
                            "Status: " + response.code() +
                                    " \n\nId: " + postagemResposta.getId() +
                                    " \nuserId: " + postagemResposta.getUserId() +
                                    " \nTitle: " + postagemResposta.getTitle() +
                                    " \nBody: " + postagemResposta.getBody()
                    );
                }

            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    public void chamaAPIdelete_Retrofit() {

        String urlAPI = "https://jsonplaceholder.typicode.com/";
        //configurar acesso a API
        retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoApi photoApi = retrofit.create(PhotoApi.class);

        // passando o ID e não tem retorno de objeto
        Call<Void> call = photoApi.removerPostagem(Integer.parseInt(id.getText().toString()));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if( response.isSuccessful() ){
                    resultado.setVisibility(View.VISIBLE);
                    resultado.setText( "Status: " + response.code() );
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}