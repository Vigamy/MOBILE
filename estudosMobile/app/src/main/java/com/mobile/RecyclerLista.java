package com.mobile;

import static android.content.ContentValues.TAG;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.List;

public class RecyclerLista extends AppCompatActivity {
    List<Nota> listaNota = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton btnMais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_lista);

        DatabaseSalaF databaseSalaF = new DatabaseSalaF();

        recyclerView = findViewById(R.id.lista);
        btnMais = findViewById(R.id.floatingActionButton);

        // Instanciando o banco de dados
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Configurar adapter
        AdapterNota adapter = new AdapterNota(listaNota);
        recyclerView.setAdapter(adapter);

        // Como aparecer o grid do recyclerView
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        // Listar
        databaseSalaF.listar(listaNota, adapter);


        // Adicionar um listener para caso atualize no banco de dados
        db.collection("Notas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                listaNota.clear();

                adapter.notifyDataSetChanged();

                for (DocumentSnapshot document : value.getDocuments()) {

                    Nota nota = document.toObject(Nota.class);

                    listaNota.add(nota);

                    adapter.notifyItemInserted(listaNota.size());
                }
            }

        });


        btnMais.setOnClickListener(v -> {

            // Mostrar o dialog em alert
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.lembrete_dialog);
            dialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
            dialog.setCancelable(false);

            EditText editTitulo = dialog.findViewById(R.id.lembreteTituloEditText);
            EditText editDescricao = dialog.findViewById(R.id.lembreteDescricaoEditText);

            Button btnConfirm = dialog.findViewById(R.id.btnConfirmarLembrete);
            Button btnCancelar = dialog.findViewById(R.id.btnCancelarLembrete);

            btnConfirm.setOnClickListener(v1 -> {

                databaseSalaF.salvar(new Nota(editTitulo.getText().toString(), editDescricao.getText().toString()));

                adapter.notifyItemInserted(listaNota.size());

                dialog.dismiss();
            });

            btnCancelar.setOnClickListener(v1 -> dialog.dismiss());


            dialog.show();

        });

    }
}