package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent in = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(in);
    }

    public void tpSorteio(View view) {
        Intent in = new Intent(MainActivity.this, AppDeSorteio.class);
        startActivity(in);
    }
    public void tpConversor(View view) {
        Intent in = new Intent(MainActivity.this, AppDeConversao.class);
        startActivity(in);
    }
    public void tpGasolina(View view) {
        Intent in = new Intent(MainActivity.this, AppCalculoGasolina.class);
        startActivity(in);
    }
    public void tpIMC(View view) {
        Intent in = new Intent(MainActivity.this, AppCalculoIMC.class);
        startActivity(in);
    }
    public void tpNetflix(View view){
        Intent in = new Intent(MainActivity.this, Netflix.class);
        startActivity(in);
    }
}