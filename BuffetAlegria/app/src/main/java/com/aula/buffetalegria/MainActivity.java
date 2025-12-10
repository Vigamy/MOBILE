package com.aula.buffetalegria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcView;
    FloatingActionButton btMais;

    List<Pessoa> listaPessoa = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            listaPessoa.add(new Pessoa("Joaquim","Eu Mesmo", "123456789",  dtf.parse("01/01/2023"), false,"", 0));
          listaPessoa.add(new Pessoa("Joaquim2 silva","Eu Mesmo", "123456789", dtf.parse("01/01/2022"), true,"", 3));
        listaPessoa.add(new Pessoa("Joaquim3","Eu Mesmo", "123456789",  dtf.parse("01/01/2020"), false,"", 5));
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }


    rcView = findViewById(R.id.recycleView);

        // Configurar Adapter
        AdapterPessoa adapterPessoa = new AdapterPessoa(listaPessoa);
        rcView.setAdapter(adapterPessoa);

        // Configurar RecycleView Apresentação
        rcView.setLayoutManager(new LinearLayoutManager(this));


        btMais = findViewById(R.id.floatingActionButton);
        btMais.setOnClickListener(v -> {
            Intent intent = new Intent(this, Formulario.class);
            startActivity(intent);
        });


    }

}

//    TextView pontos =  findViewById(R.id.pontos);
//    RatingBar formulario_nota =  findViewById(R.id.formulario_nota);
//        formulario_nota.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//        @Override
//        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//            pontos.setText(String.valueOf(rating));
//        }
//    });