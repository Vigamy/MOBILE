package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    TextView txt, textInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInformation = findViewById(R.id.textInformation);

        //RECEBENDO INFORMAÇÕES
        Bundle dados = getIntent().getExtras();
        if(dados != null){
            String nome = dados.getString("nome");
            Long fone = dados.getLong("fone");
            String email = dados.getString("email");
            textInformation.setText("Nome: " + nome + "\nTelefone: " + fone + "\nEmail: " + email);
        }

        // Ativar o listener
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txt.setText("É os business, fi");
//                Toast.makeText(MainActivity.this, txt.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
//    public void alterarTexto(View view) {
//        if (txt.getText() != "É os tech, fi"){
//            txt.setText("É os tech, fi");
//        }else{
//            txt.setText("Opa");
//        }
//    }

    public void teleport(View view) {
        mudarTela(MainActivity2.class);
    }
    public void tpSorteio(View view) {
        mudarTela(AppDeSorteio.class);
    }
    public void tpConversor(View view) {
        mudarTela(AppDeConversao.class);
    }
    public void tpGasolina(View view) {
        mudarTela(AppCalculoGasolina.class);
    }
    public void tpIMC(View view) {
        mudarTela(AppCalculoIMC.class);
    }
    public void tpCalculadora(View view) {
        mudarTela(AppCalculadora.class);
    }
    public void tpNetflix(View view){
        mudarTela(Netflix.class);
    }
    public void mudarTela(Class activityMudar){
        Intent in = new Intent(MainActivity.this, activityMudar);
        startActivity(in);
        finish();
    }
}