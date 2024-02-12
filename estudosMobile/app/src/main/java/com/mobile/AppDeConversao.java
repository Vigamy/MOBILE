package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AppDeConversao extends AppCompatActivity {
    Button btnConverter;
    EditText editDolar;
    TextView textReal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_de_conversao);
        btnConverter = findViewById(R.id.btnConverter);
        editDolar = findViewById(R.id.valorDolar);
        textReal = findViewById(R.id.valorReal);

        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double valorDolar = Double.parseDouble(editDolar.getText().toString());
                Double valorConvertido = valorDolar * 4.95;
                textReal.setText("R$ " + valorConvertido);

            }

        });

    }

    public void tpMain(View view) {
        Intent in = new Intent(AppDeConversao.this, MainActivity.class);
        startActivity(in);
    }

    public void inverter(View view) {

    }
}