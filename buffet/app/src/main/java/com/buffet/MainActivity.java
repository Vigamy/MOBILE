package com.buffet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Crianca> listaCrianca = new ArrayList<>();
    Button btnAdicionar;
    RecyclerView recyclerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdicionar = findViewById(R.id.btnAdicionar);
        recyclerView = findViewById(R.id.recyclerView);

        listaCrianca.add(new Crianca("190341", 0, "Will Smith", "Ricardo", "1999", "10-12-12", false, 5));

        KidsAdapter kidsAdapter = new KidsAdapter(listaCrianca);

        recyclerView.setAdapter(kidsAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Formulario.class);
            startActivity(intent);
        });
    }
}