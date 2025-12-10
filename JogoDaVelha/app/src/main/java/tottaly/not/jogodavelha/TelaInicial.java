package tottaly.not.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class TelaInicial extends AppCompatActivity {
    ImageView gifTicTacToe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        gifTicTacToe = findViewById(R.id.gifTicTacToe);
        Glide.with(TelaInicial.this).load("https://upload.wikimedia.org/wikipedia/commons/7/7d/Tic-tac-toe-animated.gif").centerCrop().into(gifTicTacToe);

    }

    public void normal(View view) {
        Intent in = new Intent(TelaInicial.this, NomeJogadores.class);
        startActivity(in);
//        finish();
    }

    public void bot(View view) {
        Intent in = new Intent(TelaInicial.this, Bot.class);
        startActivity(in);
//        finish();
    }
    public void IA(View view) {
        Intent in = new Intent(TelaInicial.this, IA.class);
        startActivity(in);
//        finish();
    }
}