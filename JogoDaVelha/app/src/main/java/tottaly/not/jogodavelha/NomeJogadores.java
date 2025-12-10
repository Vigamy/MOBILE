package tottaly.not.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLInput;

public class NomeJogadores extends AppCompatActivity {
    TextInputEditText jogador1, jogador2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome_jogadores);
        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);
    }

    public void iniciar(View view) {
        Bundle jogadores = new Bundle();
        jogadores.putString("jogador1", jogador1.getText().toString());
        jogadores.putString("jogador2", jogador2.getText().toString());

        Intent intent = new Intent(NomeJogadores.this, Padrao.class);
        intent.putExtras(jogadores);

        startActivity(intent);
    }
}