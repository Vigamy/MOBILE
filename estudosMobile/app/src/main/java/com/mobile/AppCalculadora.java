package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AppCalculadora extends AppCompatActivity {
    TextView resultadoCalc;
    Button btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, soma, subtração, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_calculadora);
        resultadoCalc = findViewById(R.id.resultadoCalc);
        btnNum1 = findViewById(R.id.num1);
        btnNum2 = findViewById(R.id.num2);
        btnNum3 = findViewById(R.id.num3);
        btnNum4 = findViewById(R.id.num4);
        btnNum5 = findViewById(R.id.num5);
        btnNum6 = findViewById(R.id.num6);
        btnNum7 = findViewById(R.id.num7);
        btnNum8 = findViewById(R.id.num8);
        btnNum9 = findViewById(R.id.num9);
        soma = findViewById(R.id.soma);
        subtração = findViewById(R.id.subtração);
        result = findViewById(R.id.result);


    }
    public void calculo(View view){
        double calc = 0;
        Toast.makeText(AppCalculadora.this, view.getId(), Toast.LENGTH_SHORT).show();
//        calc += Double.parseDouble(String.valueOf(view));
    }
    public void tpMain() { finish(); }

}