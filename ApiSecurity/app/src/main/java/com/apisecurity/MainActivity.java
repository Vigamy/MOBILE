package com.apisecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnApi = findViewById(R.id.btnApi);

        btnApi.setOnClickListener(v -> {
            callApi();
        });

    }

    private void callApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://spring-security-97sl.onrender.com/")
                .build();

        
    }
}