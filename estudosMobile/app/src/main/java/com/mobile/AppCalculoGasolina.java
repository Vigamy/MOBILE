package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class AppCalculoGasolina extends AppCompatActivity {
    TextInputEditText editPrecoAlcool, editPrecoGasolina;
    Button btnCalcular;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_calculo_gasolina);
        editPrecoAlcool = findViewById(R.id.editPeso);
        editPrecoGasolina = findViewById(R.id.editAltura);
        btnCalcular = findViewById(R.id.calcular);
        resultado = findViewById(R.id.resultado);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double precoAlcool = Double.parseDouble(editPrecoAlcool.getText().toString());
                
                double precoGasolina = Double.parseDouble(editPrecoGasolina.getText().toString());

                double calculo = (precoAlcool/precoGasolina);
                if(calculo >= 0.7){
                    resultado.setText("Use Gasolina.\nEstá" + calculo + "% melhor!");
                }else{
                    resultado.setText("Use Álcool." + calculo + "% melhor!");
                }
            }
        });
    }
    public void tpMain(){ finish(); }

}