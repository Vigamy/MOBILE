package tottaly.not.jogodavelha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IA extends AppCompatActivity {
    Random random = new Random();
    static ImageView[] casa = new ImageView[9];
    int rodada = 0;
    boolean xo = true;
    //true = X
    //false = O
    boolean stop = false;
    String jogador1, jogador2;
    TextView info, player1, player2;
    String eventos = "Você irá jogar o Jogo da Velha comigo, você é a bolinha.";
    int jogadaPlayer, jogadaIA;
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
        System.out.println(bt);
        if (!stop){

            //Jogador adicionar X
            if (bt.getContentDescription() == null || bt.getContentDescription() == "") {
                bt.setImageResource(R.drawable.x);
                bt.setContentDescription("X");

                // Find the position of my move
                for (int i = 0; i < casa.length; i++) {
                    if(casa[i] == bt){
                        jogadaPlayer = i;
                    }
                }

                eventos += "\nMinha jogada foi:\n" + jogadaPlayer;

                rodada++;

                //Verificar se o jogo acabou
                finishGame();

                //Atualizar a jogada do GPT

                //Colocar aleatório
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Conectar à API
                        String url = "https://api.openai.com/v1/chat/completions";
                        String apikey = "apiKey";

                        // Usando o GPT
                        JSONObject jsonObject = new JSONObject();
                        try{
                            jsonObject.put("model", "gpt-3.5-turbo");

                            // Atualizando o GPT com os eventos
                            JSONObject system = new JSONObject();
                            system.put("role", "system");
                            system.put("content", eventos);

                            // Montando a pergunta
                            JSONObject user = new JSONObject();
                            user.put("role", "user");
                            user.put("content", "Qual é a sua próxima jogada? (Responda apenas o número da posição)");

                            JSONArray messagesArray = new JSONArray();
                            messagesArray.put(system);
                            messagesArray.put(user);

                            jsonObject.put("messages", messagesArray);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // preparando a request HTTP
                        RequestBody body = RequestBody.create(jsonObject.toString() , MediaType.get("application/json"));
                        Request request = new Request.Builder()
                                .url(url)
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + apikey)
                                .post(body)
                                .build();

                        OkHttpClient curl = new OkHttpClient();
                        curl.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                info.setText("Algo deu Errado");
                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                info.setText("FUNCIONOU");
                                String jsonContent = response.body().string();

                                try {
                                    JSONObject jsonObject = new JSONObject(jsonContent);
                                    JSONObject escolha = jsonObject.getJSONArray("choices").getJSONObject(0);
                                    String content = escolha.getJSONObject("message").getString("content");

                                    jogadaIA = Integer.parseInt(content);

                                    eventos += "\nSua jogada foi:\n" + jogadaIA;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        //Bot jogar O
                        if(!stop) {
                            ImageView escolhaBot = casa[jogadaIA];
                            escolhaBot.setImageResource(R.drawable.o);
                            escolhaBot.setContentDescription("O");

                            rodada++;
                        }
                    }
                }, 1000);


                //Verificar se o jogo acabou
                finishGame();

            }
        }
    }
    private void finishGame(){
        if (acharVencedor() == 1) {
            Toast.makeText(this, "FIM DE JOGO\nVOCÊ VENCEU!", Toast.LENGTH_SHORT).show();
            info.setText("FIM DE JOGO\nVOCÊ VENCEU");
            stop = true;
        }else if (acharVencedor() == 2){
            Toast.makeText(this, "FIM DE JOGO\nO BOT VENCEU!", Toast.LENGTH_SHORT).show();
            info.setText("FIM DE JOGO\nO BOT VENCEU");
            stop = true;
        }
    }

    private int acharVencedor(){
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
    public void resetar(View view){
        recreate();
    }
}