package tottaly.not.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.ImageWriter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

public class CaraOuCoroa extends AppCompatActivity {
    TextView result;
    ImageView btnVoltar, moeda;
    boolean jogando = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cara_ou_coroa);

        result = findViewById(R.id.result);
        btnVoltar = findViewById(R.id.btnVoltar);
        moeda = findViewById(R.id.moeda);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaraOuCoroa.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        jogarMoeda();
        moeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!jogando){
                    jogando = true;
                    jogarMoeda();
                }
            }
        });
    }
    public void jogarMoeda() {
        Random random = new Random();
        Handler handler = new Handler();
        Glide.with(CaraOuCoroa.this).load("https://media.tenor.com/t5DMW5PI8mgAAAAi/loading-green-loading.gif").fitCenter().into(moeda);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int valor = random.nextInt(2);
                if (valor == 1) {
                    result.setText("COROA");
                    moeda.setImageResource(R.drawable.moeda_coroa);
                } else {
                    result.setText("CARA");
                    moeda.setImageResource(R.drawable.moeda_cara);
                }
                jogando = false;
            }
        }, 1000);
    }
}