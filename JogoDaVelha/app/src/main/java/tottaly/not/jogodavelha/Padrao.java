package tottaly.not.jogodavelha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Padrao extends AppCompatActivity {
    ImageView[] casa = new ImageView[9];
    int rodada = 0;
    boolean xo = true;
    //true = X
    //false = O
    boolean stop = false;

    String jogador1, jogador2;
    TextView info, player1, player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle jogadores = getIntent().getExtras();
        assert jogadores != null;
        jogador1 = jogadores.getString("jogador1");
        jogador2 = jogadores.getString("jogador2");


        player1 = findViewById(R.id.viewPlayer1);
        player2 = findViewById(R.id.viewPlayer2);
        player1.setText(jogador1);
        player2.setText(jogador2);

        info = findViewById(R.id.info);
        info.setText("Vez de " + jogador1);
        player1.setAllCaps(true);

        casa[0] = findViewById(R.id.img1);
        casa[1] = findViewById(R.id.img2);
        casa[2] = findViewById(R.id.img3);
        casa[3] = findViewById(R.id.img4);
        casa[4] = findViewById(R.id.img5);
        casa[5] = findViewById(R.id.img6);
        casa[6] = findViewById(R.id.img7);
        casa[7] = findViewById(R.id.img8);
        casa[8] = findViewById(R.id.img9);

        info = findViewById(R.id.info);
    }
    public void selecionar(View view){
        ImageView bt = (ImageView) view;

        if (!stop){

            //Jogador adicionar
            if (bt.getContentDescription() == null || bt.getContentDescription() == "") {
                if (xo) {
                    bt.setImageResource(R.drawable.x);
                    bt.setContentDescription("X");
                } else {
                    bt.setImageResource(R.drawable.o);
                    bt.setContentDescription("O");
                }

                xo = !xo;
                rodada++;

                //Verificar se o jogo acabou
                finishGame();

            }
        }
        if(!stop){
            if(xo){
                info.setText("Vez de " + jogador1);
                player1.setAllCaps(true);
                player2.setAllCaps(false);
            }else{
                info.setText("Vez de " + jogador2);
                player2.setAllCaps(true);
                player1.setAllCaps(false);
            }
        }
    }
    private void finishGame(){

        if (rodada >= 5 && rodada < 10) {
            if (acharVencedor() == 1) {
                if(jogador1 != null){
                    Toast.makeText(this, "FIM DE JOGO\n" + jogador1 + " VENCEU", Toast.LENGTH_SHORT).show();
                    info.setText("FIM DE JOGO\n" + jogador1.toUpperCase() + " VENCEU");
                }else {
                    Toast.makeText(this, "FIM DE JOGO\n" + "jogador nulo" + " VENCEU", Toast.LENGTH_SHORT).show();
                    info.setText("FIM DE JOGO\n" + "jogador nulo" + " VENCEU");
                }
                stop = true;
            }else if (acharVencedor() == 2){
                if(jogador2 != null){
                    Toast.makeText(this, "FIM DE JOGO\n" + jogador2 + " VENCEU", Toast.LENGTH_SHORT).show();
                    info.setText("FIM DE JOGO\n" + jogador2.toUpperCase() + " VENCEU");
                }else {
                    Toast.makeText(this, "FIM DE JOGO\n" + "jogador nulo" + " VENCEU", Toast.LENGTH_SHORT).show();
                    info.setText("FIM DE JOGO\n" + "jogador nulo" + " VENCEU");
                }
                stop = true;
            }
        }
        if (rodada == 9 && !stop) {
            Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
            stop = true;
        }
    }

    public int acharVencedor(){
        // Coluna 1

        if(casa[0].getContentDescription() != null && casa[0].getContentDescription() == casa[3].getContentDescription() && casa[3].getContentDescription() == casa[6].getContentDescription()){
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }
            else if (casa[0].getContentDescription() == "O"){
                return 2;
            }
        }
        // Coluna 2
        else if(casa[1].getContentDescription() != null && casa[1].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[7].getContentDescription()){
            if(casa[1].getContentDescription() == "X"){
                return 1;
            }
            else if (casa[1].getContentDescription() == "O"){
                return 2;
            }
        }
        // Coluna 3
        else if(casa[2].getContentDescription() != null && casa[2].getContentDescription() == casa[5].getContentDescription() && casa[5].getContentDescription() == casa[8].getContentDescription()){
            if(casa[2].getContentDescription() == "X"){
                return 1;
            }
            else if (casa[2].getContentDescription() == "O"){
                return 2;
            }
        }
        // Linha 1
        else if(casa[2].getContentDescription() != null && casa[0].getContentDescription() == casa[1].getContentDescription() && casa[1].getContentDescription() == casa[2].getContentDescription()) {
            if(casa[2].getContentDescription() == "X"){
                return 1;
            }
            else if (casa[2].getContentDescription() == "O"){
                return 2;
            }
        }
        // Linha 2
        else if(casa[3].getContentDescription() != null && casa[3].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[5].getContentDescription()) {
            if(casa[3].getContentDescription() == "X"){
                return 1;
            }
            else if (casa[3].getContentDescription() == "O"){
                return 2;
            }
        }
        // Linha 3
        else if(casa[6].getContentDescription() != null && casa[6].getContentDescription() == casa[7].getContentDescription() && casa[7].getContentDescription() == casa[8].getContentDescription()) {
            if(casa[6].getContentDescription() == "X"){
                return 1;
            }
            else if (casa[6].getContentDescription() == "O"){
                return 2;
            }
        }
        // Diagonal 1
        else if(casa[0].getContentDescription() != null && casa[0].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[8].getContentDescription()) {
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }
            else if (casa[0].getContentDescription() == "O"){
                return 2;
            }
        }
        // Diagonal 2
        else if(casa[2].getContentDescription() != null && casa[2].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[6].getContentDescription()) {
            if(casa[2].getContentDescription() == "X"){
                return 1;
            }
            else if (casa[2].getContentDescription() == "O"){
                return 2;
            }
        }
        return 0;
    }
    //SALVAR INFORMAÇÕES

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
//        outState.putString(outPersistentState.getString("rodada"));

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        txt.setText(savedInstanceState.getString().toString());
//        txt.setText(savedInstanceState.getString().toString());
//
//        super.onRestoreInstanceState(savedInstanceState);
    }

    public void resetar(View view){
        recreate();
    }
}