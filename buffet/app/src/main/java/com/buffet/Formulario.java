package com.buffet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class Formulario extends AppCompatActivity {
    ImageView profile_pic, calendario;
    TextInputEditText nome, responsavel, telefone;
    TextView data_nascimento;
    CheckBox restricao;
    Button btnSalvar;

    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        calendario = findViewById(R.id.calendario);
        nome = findViewById(R.id.nome);
        responsavel = findViewById(R.id.responsavel);
        telefone = findViewById(R.id.telefone);
        restricao = findViewById(R.id.checkbox_restricao);
        data_nascimento = findViewById(R.id.text_calendario);
        profile_pic = findViewById(R.id.profile_pic);
        btnSalvar = findViewById(R.id.btnSalvar);



        Intent dados = getIntent();
        if (dados.hasExtra("crianca")){

            Crianca crianca = (Crianca) dados.getSerializableExtra("crianca");
            nome.setText(crianca.getNome());
            responsavel.setText(crianca.getResponsavel());
            telefone.setText(crianca.getTelefone());
            data_nascimento.setText(dtf.format(crianca.getDataNascimento()));
            restricao.setChecked(crianca.isRestricao());

        }

        btnSalvar.setOnClickListener((v -> {
            Crianca crianca = new Crianca(
                    " ",
                    0,
                    Objects.requireNonNull(nome.getText()).toString(),
                    Objects.requireNonNull(responsavel.getText()).toString(),
                    Objects.requireNonNull(telefone.getText()).toString(),
                    Objects.requireNonNull(data_nascimento.getText()).toString(),
                    restricao.isChecked(),
                    0
            );
        }));

        calendario.setOnClickListener((v -> {

        }));
    }
}