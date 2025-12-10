package com.aula.praticamobile;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Colaborador> listaColaboradores = new ArrayList<>();
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper();

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);

        Adapter adapter = new Adapter(listaColaboradores);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseHelper.listar(listaColaboradores, adapter);

        btnAdd.setOnClickListener(v -> {

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.adicionar_pessoa_layout);
            dialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
            dialog.setCancelable(false);

            TextInputEditText numCracha = dialog.findViewById(R.id.numCracha);

            Button btnConfirm = dialog.findViewById(R.id.btnConfirmarRegistro);
            Button btnCancelar = dialog.findViewById(R.id.btnCancelarRegistro);

            numCracha.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    btnConfirm.setEnabled(!s.toString().trim().isEmpty());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            btnConfirm.setOnClickListener(v1 -> {

                Date dataInicio = new Date();
                Date dataFim = new Date();

                databaseHelper.salvar(new Colaborador(Integer.parseInt(numCracha.getText().toString()), dataInicio, dataFim));

                adapter.notifyItemInserted(listaColaboradores.size());

                dialog.dismiss();

            });

            btnCancelar.setOnClickListener(v1 -> dialog.dismiss());

            dialog.show();


        });

    }
}