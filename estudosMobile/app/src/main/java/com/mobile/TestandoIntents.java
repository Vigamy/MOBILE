package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.math.BigInteger;

public class TestandoIntents extends AppCompatActivity {
    EditText nome, fone, email;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testando_intents);
        nome = findViewById(R.id.nome);
        fone = findViewById(R.id.telefone);
        email = findViewById(R.id.email);


    }
    public void appInfo(View view){
        //Adicionando info no bundle
        Bundle dados = new Bundle();
        dados.putString("nome", nome.getText().toString());
        if(!fone.getText().toString().isEmpty()) {
            dados.putLong("fone", Long.parseLong(fone.getText().toString()));
        }
        dados.putString("email", email.getText().toString());

        //Criando Intent
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(dados);

        startActivity(intent);
        finish();
    }
    public void mostrarInfo(View view) {
        Toast.makeText(this, "Nome: " + nome.getText().toString() + "\nTelefone: " + fone.getText().toString() + "\nEmail: " + email.getText().toString()
                , Toast.LENGTH_SHORT).show();
    }
}