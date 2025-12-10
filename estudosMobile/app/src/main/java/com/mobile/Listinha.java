package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class Listinha extends AppCompatActivity {
    Button btnAdicionar;
    EditText texto;
    ListView lista;
    ArrayAdapter<String> adapter;
    List<String> lembretes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        texto = findViewById(R.id.textList);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        lista = findViewById(R.id.listaLembretes);

        lembretes = new ArrayList<String>();

        // Configurar o componente
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lembretes);
        lista.setAdapter(adapter);

        // Pegar o click
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Listinha.this, "posição" + position, Toast.LENGTH_SHORT).show();
                lembretes.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
    public void adicionar(View view){
        lembretes.add(texto.getText().toString());
        texto.setText("");
        adapter.notifyDataSetChanged();
    }
}