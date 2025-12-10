package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class AppDeSorteio extends AppCompatActivity {
    Button btnSortear;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_de_sorteio);
        btnSortear = findViewById(R.id.sortear);
        result = findViewById(R.id.result);

    }
    public void sortear(View view){
        Random random = new Random();
        int aleatorio = random.nextInt(101);
        String num = Integer.toString(aleatorio);
        result.setText(num);
    }

    public void tpMain(View view) {
        finish();
    }
}