
package com.mobile;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class TestandoIntents extends AppCompatActivity {
    EditText nome, fone, email;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testando_intents);
        nome = findViewById(R.id.nome);
        fone = findViewById(R.id.telefone);
        email = findViewById(R.id.email);

    }
    public void discar(View view) {
        EditText fone = (EditText) findViewById(R.id.telefone);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        // dica: Uniform Resource Identifier
        intent.setData(Uri.parse("tel:" + fone.getText().toString()));

        startActivity(intent);
    }
    public void email(View view) {
        EditText email = findViewById(R.id.email);

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Suporte App: ");
        //intent.putExtra(Intent.EXTRA_TEXT, "Informação que deverá carregar no corpo do email");
        intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<html><body><h1>Corpo<</h1>da mensagem</body></html>", 1));

        startActivity(intent);
    }
    public void appInfo(View view){
        //Adicionando info no bundle
        Bundle dados = new Bundle();
        dados.putString("nome", nome.getText().toString());
        if(!fone.getText().toString().isEmpty()) {
            dados.putLong("fone", Long.parseLong(fone.getText().toString()));
        }
        dados.putString("email", email.getText().toString());

        //Criando Intent
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(dados);

        startActivity(intent);
        finish();
    }
    public void mostrarInfo(View view) {
//        Toast.makeText(this, "Nome: " + nome.getText().toString() + "\nTelefone: " + fone.getText().toString() + "\nEmail: " + email.getText().toString()
//                , Toast.LENGTH_SHORT).show();


        AlertDialog.Builder builder = new AlertDialog.Builder(TestandoIntents.this);
        builder.setMessage(nome.getText().toString() + fone.getText().toString() + email.getText().toString()).
                setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                })
                .setNegativeButton("Negar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Mensagem");
        alert.show();
    }
    public void aleatorio(View view) {
        Random random = new Random();
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.activity_dialog_win_lose);
        dialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        dialog.setCancelable(false);

        // Pegando os component da view e vapo
        TextView txtDialog = dialog.findViewById(R.id.txtDialogWinLose);
        ImageView imgDialog = dialog.findViewById(R.id.imageWinLose);


        if(random.nextInt(2) == 1) {
            Glide.with(TestandoIntents.this)
                    .load("https://i.pinimg.com/1200x/e6/9a/ca/e69acae1176c249d95dcea23f85381c9.jpg")
                    .centerCrop().
                    into(imgDialog);
            txtDialog.setText("PERDEU\nKKKKKKKKKKKKKK");
        } else {
            Glide.with(TestandoIntents.this)
                    .load("https://img.ifunny.co/images/820852641f9e2b12f0cac219f84fb070906f92ae2ba2f79ab701312eb2a065c1_3.jpg")
                    .centerCrop().
                    into(imgDialog);
            txtDialog.setText("Ganhou\n🎺🎺🎺");
        }

        // OK
        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}