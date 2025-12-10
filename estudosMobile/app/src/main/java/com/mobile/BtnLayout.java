package com.mobile;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BtnLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn_layout);
    }
    public void criarBox(View view){
        //usando Dialog
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.card);
        dialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.card_background));

        //não permitir clique fora da caixinha
        dialog.setCancelable(false);

        //inicializar os componentes do dialog
        TextView txtTitulo = dialog.findViewById(R.id.txtTitulo);
        txtTitulo.setText("R$2 ou uma prática misteriosa?");
        TextView txtMsg = dialog.findViewById(R.id.txtMensagem);
        txtMsg.setText("Aula do nisflas");

        Button btnOk = dialog.findViewById(R.id.btnConfirm);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BtnLayout.this, "Parabéns!!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //Carregar
        dialog.show();
    }
}