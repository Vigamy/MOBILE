package com.aula.praticademilhoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class TabelaDeCalculo extends AppCompatActivity {

    TextView textRenda;
    Button btnTabela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_de_calculo);

        textRenda = findViewById(R.id.renda);
        btnTabela = findViewById(R.id.btTabela);



        Bundle dados = getIntent().getExtras();

        if (dados != null) {
            double vlRenda = dados.getDouble("vlRenda", 0);
            boolean tipoRenda = dados.getBoolean("tipoRenda", false);
            textRenda.setText("Imposto:\nR$" + String.format("%.2f", calcularImposto(vlRenda, tipoRenda)));
        }

        btnTabela.setOnClickListener(v -> {
            Intent intent2 = new Intent(TabelaDeCalculo.this, TabelaDeCalculo.class);
            startActivity(intent2);
        });



    }

    public static double calcularImposto(double renda, boolean anual){
        if(anual){
            if(renda <= 22847.76){
                return 0;
            } else if (renda <= 33919.80) {
                return renda * 0.075 - 1713.58;
            } else if (renda <= 45012.60) {
                return renda * 0.15 - 4257.57;
            } else if (renda <= 55976.16) {
                return renda * 0.225 - 7633.51;
            } else {
                return renda * 0.275 - 10432.32;
            }
        } else {
            if(renda <= 1903.98){
                return 0;
            } else if (renda <= 2826.65) {
                return 12 * (renda * 0.075 - 142.8);
            } else if (renda <= 3751.05) {
                return 12 * (renda * 0.15 - 354.8);
            } else if (renda <= 4664.68) {
                return 12 * (renda * 0.225 - 636.13);
            } else {
                return 12 * (renda * 0.275 - 869.36);
            }
        }
    }

}