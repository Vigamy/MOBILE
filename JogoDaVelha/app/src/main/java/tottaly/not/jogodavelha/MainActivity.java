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
            playerAdicionar(bt);

            //Verificar se o jogo acabou
            if (rodada >= 5 && rodada < 9) {
                if (acharVencedor() == 1) {
                    Toast.makeText(this, "FIM DE JOGO\nX VENCEU", Toast.LENGTH_SHORT).show();
                    stop = true;
                }else if (acharVencedor() == 0){
                    Toast.makeText(this, "FIM DE JOGO\nO VENCEU", Toast.LENGTH_SHORT).show();
                    stop = true;
                }
            } else if (rodada == 9 || acharVencedor() == -1) {
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
            }
            
            //SLEEP
//            try {
//                // Pausa a execução por 1 segundos (1000 milissegundos)
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                // Lidar com a exceção, se ocorrer
//                e.printStackTrace();
//            }

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
            if (rodada >= 5 && rodada < 9) {
                if (acharVencedor() == 1) {
                    Toast.makeText(this, "FIM DE JOGO\nX VENCEU", Toast.LENGTH_SHORT).show();
                    stop = true;
                }else if (acharVencedor() == 0){
                    Toast.makeText(this, "FIM DE JOGO\nO VENCEU", Toast.LENGTH_SHORT).show();
                    stop = true;
                }
            } else if (rodada == 9 || acharVencedor() == -1) {
                Toast.makeText(this, "EMPATE", Toast.LENGTH_SHORT).show();
            }

        }

    }
    public void playerAdicionar(ImageView bt){
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
    }
    public int acharVencedor(){
        // Coluna 1
        if(casa[0].getContentDescription() == casa[3].getContentDescription() && casa[3].getContentDescription() == casa[6].getContentDescription()){
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }else{
                return 0;
            }
        }
        // Coluna 2
        else if(casa[1].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[7].getContentDescription()){
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }else{
                return 0;
            }
        }
        // Coluna 3
        else if(casa[2].getContentDescription() == casa[5].getContentDescription() && casa[5].getContentDescription() == casa[8].getContentDescription()){
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }else{
                return 0;
            }
        }
        // Linha 1
        else if(casa[0].getContentDescription() == casa[1].getContentDescription() && casa[1].getContentDescription() == casa[2].getContentDescription()) {
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }else{
                return 0;
            }
        }
        // Linha 2
        else if(casa[3].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[5].getContentDescription()) {
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }else{
                return 0;
            }
        }
        // Linha 3
        else if(casa[6].getContentDescription() == casa[7].getContentDescription() && casa[7].getContentDescription() == casa[8].getContentDescription()) {
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }else{
                return 0;
            }
        }
        // Diagonal 1
        else if(casa[0].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[8].getContentDescription()) {
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }else{
                return 0;
            }
        }
        // Diagonal 2
        else if(casa[2].getContentDescription() == casa[4].getContentDescription() && casa[4].getContentDescription() == casa[6].getContentDescription()) {
            if(casa[0].getContentDescription() == "X"){
                return 1;
            }else{
                return 0;
            }
        }
        return -1;
    }
    public void resetar(View view){
        recreate();
    }
}