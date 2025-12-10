package com.mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;

public class AppIA extends AppCompatActivity {
//    TextInputEditText layout;
    TextInputEditText pergunta;
    Button enviar;
    TextView msg;
    String eventos = "Você é um mestre de um rpg de mesa que deve jogar com o usuário.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_ia);
//        layout = findViewById(R.id.textInputLayout);
        pergunta = findViewById(R.id.txt);
        enviar = findViewById(R.id.bt);
        msg = findViewById(R.id.msg);
    }

    public void enviar(View view) {
        // Fazer a API
        String url = "https://api.openai.com/v1/chat/completions";
        String apikey = "apiKey";
        String question = pergunta.getText().toString();
        System.out.println(eventos);
        eventos += "\nResposta do usuário:\n" + question;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("model", "gpt-3.5-turbo");

            //Montando o system
            JSONObject system = new JSONObject();
            system.put("role", "system");
            system.put("content", eventos);

            //Montando o user
            JSONObject user = new JSONObject();
            user.put("role", "user");
            user.put("content", question);

            JSONArray messagesArray = new JSONArray();
            messagesArray.put(system);
            messagesArray.put(user);

            // Colocando na array messages
            jsonObject.put("messages", messagesArray);

        }catch (JSONException je) {
            je.printStackTrace();
        }

        // preparando a request HTTP
        RequestBody body = RequestBody.create(jsonObject.toString() , MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apikey)
                .post(body)
                .build();

        // Executar a requisição
        OkHttpClient curl = new OkHttpClient();
        curl.newCall(request).enqueue(new Callback() { // Aqui fazemos a chamada e entramos na fila de consulta
            //Aqui trata a chamada de retorno (Callback)
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("api-gpt", e.getMessage());
                msg.setText("Não foi possível realizar a resposta.");
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                Log.d("api-gpt", response.body().string());
                String jsonContent = response.body().string();

                try {
                    JSONObject jsonObject = new JSONObject(jsonContent);
                    JSONObject choices = jsonObject.getJSONArray("choices").getJSONObject(0);
                    String content = choices.getJSONObject("message").getString("content");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            // Update your UI element here
                            msg.setText(content);
                        }
                    });

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        msg.setText("");
    }
}