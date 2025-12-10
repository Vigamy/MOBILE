package com.mobile;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DatabaseSalaF {
    Map<String, Object> nota_id = new HashMap<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public DatabaseSalaF() {
    }

    public void salvar(Nota nota) {
        // Abrir Firebase

        db.collection("counters").document("nota_id").get().addOnCompleteListener(task -> {
            int id = 1;
            if(task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if(document.exists()) {
                    nota_id = document.getData();
                    id = Integer.parseInt(nota_id.get("chave").toString()) + 1;
                }
                nota_id.put("chave", id);
                db.collection("counters").document("nota_id").set(nota_id);

                // gravar a nota
                nota.setId(id);
                db.collection("Notas").document("lembrete " + id).set(nota);
            }
        });
    }

    public void listar(List<Nota> listaNota, AdapterNota adapterNota) {
        db.collection("Notas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                listaNota.clear();

                adapterNota.notifyDataSetChanged();

                for (DocumentSnapshot document : value.getDocuments()) {

                    Nota nota = document.toObject(Nota.class);

                    listaNota.add(nota);

                    adapterNota.notifyItemInserted(listaNota.size());

                }
            }
        });
    }

    public static void remover(Nota nota, Context context) {
        FirebaseFirestore.getInstance("Notas").document("lembrete " + nota.getId()).delete()
                .addOnSuccessListener(unused -> {
                    Toast.makeText(context, "Item removido" + nota.getTitulo() + "com sucesso", Toast.LENGTH_SHORT).show();
                });
    }

    public static void alterar(Nota nota, Context context) {
        FirebaseFirestore.getInstance("Notas").document("lembrete " + nota.getId()).set(nota)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(context, "Item alterado" + nota.getTitulo() + "com sucesso", Toast.LENGTH_SHORT).show();
                });
    }
}
