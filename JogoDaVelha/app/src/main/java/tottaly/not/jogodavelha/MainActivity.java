package tottaly.not.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();
    ImageView[] casa = new ImageView[9];
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
                if (xo) {
                    bt.setImageResource(R.drawable.x);
                    bt.setContentDescription("X");
                } //else {
//                    bt.setImageResource(R.drawable.o);
//                    bt.setContentDescription("O");
//                }

                xo = !xo;
                rodada++;
            }

            //Verificar se o jogo acabou
            if (rodada >= 5) {
                if (acharVencedor()) {
                    Toast.makeText(this, "FIM DE JOGO", Toast.LENGTH_SHORT).show();
                    stop = true;
                }
            } else if (rodada == 9) {
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
            }
            
            //SLEEP
            try {
                // Pausa a execução por 1 segundos (1000 milissegundos)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Lidar com a exceção, se ocorrer
                e.printStackTrace();
            }

            //Colocar aleatório
            while (true){
                ImageView escolhaBot = casa[random.nextInt(9)];
                if (escolhaBot.getContentDescription() == null || escolhaBot.getContentDescription() == "") {
                    escolhaBot.setImageResource(R.drawable.o);
                    escolhaBot.setContentDescription("O");

                    xo = !xo;
                    rodada++;
                    break;
                }
            }

            //Verificar se o jogo acabou
            if (rodada >= 5) {
                if (acharVencedor()) {
                    Toast.makeText(this, "FIM DE JOGO", Toast.LENGTH_SHORT).show();
                    stop = true;
                }
            } else if (rodada == 9) {
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
            }

        }

    }
    public boolean acharVencedor(){
        // Coluna 1

        if(casa[0].getContentDescription() == casa[3].getContentDescription() && casa[3].getContentDescription() == casa[6].getContentDescription()){
           return true;
        }
        // Coluna 2
        else if(casa[1].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[7].getContentDescription()){
                return true;
        }
        // Coluna 3
        else if(casa[2].getContentDescription() == casa[5].getContentDescription() && casa[5].getContentDescription() == casa[8].getContentDescription()){
                return true;
        }
        // Linha 1
        else if(casa[0].getContentDescription() == casa[1].getContentDescription() && casa[1].getContentDescription() == casa[2].getContentDescription()) {
                return true;
        }
        // Linha 2
        else if(casa[3].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[5].getContentDescription()) {
                return true;
        }
        // Linha 3
        else if(casa[6].getContentDescription() == casa[7].getContentDescription() && casa[7].getContentDescription() == casa[8].getContentDescription()) {
            return true;
        }
        // Diagonal 1
        else if(casa[0].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[8].getContentDescription()) {
            return true;
        }
        // Diagonal 2
        else if(casa[2].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[6].getContentDescription()) {
            return true;
        }
        return false;
    }
    public void resetar(View view){
        recreate();
    }
}