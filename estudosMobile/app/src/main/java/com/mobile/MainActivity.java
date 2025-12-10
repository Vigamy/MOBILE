package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    TextView txt, textInformation;
    ImageView gifGPT, cameraImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInformation = findViewById(R.id.textInformation);

        //RECEBENDO INFORMAÇÕES
        Bundle dados = getIntent().getExtras();
        if(dados != null){
            String nome = dados.getString("nome");
            Long fone = dados.getLong("fone");
            String email = dados.getString("email");
            textInformation.setText("Nome: " + nome + "\nTelefone: " + fone + "\nEmail: " + email);
        }

        // GLIDE
        // https://github.com/bumptech/glide
        gifGPT = findViewById(R.id.gifGPT);
        Glide.with(MainActivity.this).load("https://www.primecursos.com.br/blog/wp-content/uploads/2023/09/chatgpt-gif.gif").centerCrop().into(gifGPT);

        cameraImg = findViewById(R.id.cameraImg);
        Glide.with(MainActivity.this).load("https://cdn.pixabay.com/animation/2023/06/13/15/13/15-13-23-8_512.gif").centerCrop().into(cameraImg);
        // Ativar o listener
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txt.setText("É os business, fi");
//                Toast.makeText(MainActivity.this, txt.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
//    public void alterarTexto(View view) {
//        if (txt.getText() != "É os tech, fi"){
//            txt.setText("É os tech, fi");
//        }else{
//            txt.setText("Opa");
//        }
//    }

    public void teleport(View view) {
        mudarTela(AppAndroid.class);
    }
    public void tpSorteio(View view) {
        mudarTela(AppDeSorteio.class);
    }
    public void tpConversor(View view) {
        mudarTela(AppDeConversao.class);
    }
    public void tpGasolina(View view) {
        mudarTela(AppCalculoGasolina.class);
    }
    public void tpIMC(View view) {
        mudarTela(AppCalculoIMC.class);
    }
    public void tpCalculadora(View view) {
        mudarTela(AppCalculadora.class);
    }
    public void tpNetflix(View view){
        mudarTela(Netflix.class);
    }
    public void tpIA(View view){
        mudarTela(AppIA.class);
    }
    public void tpActivityResult(View view) { mudarTela(ActiviyResult.class); }
    public void tpYoutube(View view) {
        mudarTela(AppYoutube.class);
    }
    public void tpButtons(View view) {
        mudarTela(BtnLayout.class);
    }
    public void tpInfo(View view) {
        mudarTela(TestandoIntents.class);
    }
    public void tpListinha(View view) {
        mudarTela(Listinha.class);
    }
    public void tpRecycleView(View view) {
        mudarTela(RecyclerLista.class);
    }
    public void tpBuffet(View view) {
        mudarTela(Buffet.class);
    }
    public void tpCamera(View view) {
        mudarTela(Camera.class);
    }


    public void mudarTela(Class activityMudar){
        Intent in = new Intent(MainActivity.this, activityMudar);
        startActivity(in);
    }

}