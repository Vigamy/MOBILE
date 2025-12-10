package com.mobile;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActiviyResult extends AppCompatActivity {
    TextView txtResultado;
    Button btnObterInfo;
    ActivityResultLauncher<Intent> activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiy_result);
        txtResultado = findViewById(R.id.textResultado);
        btnObterInfo = findViewById(R.id.obterInfo);

        // Definir o ActivityResult
        activity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK){
                Intent dados = result.getData();
                txtResultado.setText(dados.getStringExtra("retorno"));
            }else {
                Toast.makeText(this, "Você não terminou!", Toast.LENGTH_SHORT).show();
            }
        });

        // OnClick do Btn
        btnObterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActiviyResult.this, ActivityResult2.class);
                //Abrir um ActivityResult
                activity.launch(intent);
            }
        });
    }
}