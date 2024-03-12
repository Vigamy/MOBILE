package tottaly.not.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class Bot extends AppCompatActivity {

    Random random = new Random();
    static ImageView[] casa = new ImageView[9];
    int rodada = 0;
    boolean xo = true;
    //true = X
    //false = O
    boolean stop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        casa[0] = findViewById(R.id.img1);
        casa[1] = findViewById(R.id.img2);
        casa[2] = findViewById(R.id.img3);
        casa[3] = findViewById(R.id.img4);
        casa[4] = findViewById(R.id.img5);
        casa[5] = findViewById(R.id.img6);
        casa[6] = findViewById(R.id.img7);
        casa[7] = findViewById(R.id.img8);
        casa[8] = findViewById(R.id.img9);
    }
    public void selecionar(View view){
        ImageView bt = (ImageView) view;
        if (!stop){

            //Jogador adicionar X
            if (bt.getContentDescription() == null || bt.getContentDescription() == "") {
                bt.setImageResource(R.drawable.x);
                bt.setContentDescription("X");

                rodada++;

                //Verificar se o jogo acabou
                finishGame();

                Handler handler = new Handler();

                //Colocar aleatório
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(!stop) {
                                while (true) {
                                    ImageView escolhaBot = casa[random.nextInt(9)];
                                    if (escolhaBot.getContentDescription() == null || escolhaBot.getContentDescription() == "") {
                                        escolhaBot.setImageResource(R.drawable.o);
                                        escolhaBot.setContentDescription("O");

                                        rodada++;
                                        break;
                                    }
                                }
                            }
                        }
                    }, 1000);


                //Verificar se o jogo acabou
                finishGame();

            }
        }
    }
    private void finishGame(){
        if (rodada >= 5 && rodada < 10) {
            if (acharVencedor() == 1) {
                Toast.makeText(this, "FIM DE JOGO", Toast.LENGTH_SHORT).show();
                stop = true;
                //        }else if (acharVencedor() == 0){
                //            Toast.makeText(this, "FIM DE JOGO\nO VENCEU", Toast.LENGTH_SHORT).show();
                //            stop = true;
                //        }
            }
            if (rodada == 9 && !stop) {
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
                stop = true;
            }
        }
    }

    private static int acharVencedor(){
        // Coluna 1

        if(casa[0].getContentDescription() == casa[3].getContentDescription() && casa[3].getContentDescription() == casa[6].getContentDescription()){
            if(casa[0].getContentDescription() != null || casa[0].getContentDescription() != "") {
                return 1;
            }
//            }else{
//                return 0;
//            }
        }
        // Coluna 2
        else if(casa[1].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[7].getContentDescription()){
            if(casa[1].getContentDescription() != null || casa[1].getContentDescription() != "") {
                return 1;
            }
//            if(casa[0].getContentDescription() == "X"){
//                return 1;
//            }else{
//                return 0;
//            }
        }
        // Coluna 3
        else if(casa[2].getContentDescription() == casa[5].getContentDescription() && casa[5].getContentDescription() == casa[8].getContentDescription()){
            if(casa[2].getContentDescription() != null || casa[2].getContentDescription() != "") {
                return 1;
            }
//            if(casa[0].getContentDescription() == "X"){
//                return 1;
//            }else{
//                return 0;
//            }
        }
        // Linha 1
        else if(casa[0].getContentDescription() == casa[1].getContentDescription() && casa[1].getContentDescription() == casa[2].getContentDescription()) {
            if(casa[0].getContentDescription() != null || casa[0].getContentDescription() != "") {
                return 1;
            }
//            if(casa[0].getContentDescription() == "X"){
//                return 1;
//            }else{
//                return 0;
//            }
        }
        // Linha 2
        else if(casa[3].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[5].getContentDescription()) {
            if(casa[3].getContentDescription() != null || casa[3].getContentDescription() != "") {
                return 1;
            }
//            if(casa[0].getContentDescription() == "X"){
//                return 1;
//            }else{
//                return 0;
//            }
        }
        // Linha 3
        else if(casa[6].getContentDescription() == casa[7].getContentDescription() && casa[7].getContentDescription() == casa[8].getContentDescription()) {
            if(casa[6].getContentDescription() != null || casa[6].getContentDescription() != "") {
                return 1;
            }
//            if(casa[0].getContentDescription() == "X"){
//                return 1;
//            }else{
//                return 0;
//            }
        }
        // Diagonal 1
        else if(casa[0].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[8].getContentDescription()) {
            if(casa[0].getContentDescription() != null || casa[0].getContentDescription() != "") {
                return 1;
            }
//            if(casa[0].getContentDescription() == "X"){
//                return 1;
//            }else{
//                return 0;
//            }
        }
        // Diagonal 2
        else if(casa[2].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[6].getContentDescription()) {
            if(casa[2].getContentDescription() != null || casa[2].getContentDescription() != "") {
                return 1;
            }
//            if(casa[0].getContentDescription() == "X"){
//                return 1;
//            }else{
//                return 0;
//            }
        }
        return 0;
    }
    public void resetar(View view){
        recreate();
    }
}