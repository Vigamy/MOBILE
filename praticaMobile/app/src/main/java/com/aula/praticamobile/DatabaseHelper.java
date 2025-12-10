package com.aula.praticamobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.List;

public class DatabaseHelper {

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    public DatabaseHelper() {
    }
    // Salvar um colaborador
    public void salvar(Colaborador colaborador) {
        db.collection("dados").document(String.valueOf(colaborador.getNumCracha())).set(colaborador);
    }
    // Listar
    public void listar(List<Colaborador> lista, Adapter adapter) {
        db.collection("dados").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                lista.clear();

                adapter.notifyDataSetChanged();

                for (DocumentSnapshot document : value.getDocuments()) {

                    if(document.get("numCracha") != null) {

                        Colaborador colaborador = document.toObject(Colaborador.class);

                        lista.add(colaborador);

                        adapter.notifyItemInserted(lista.size());

                    }

                }
            }
        });
    }
    // Buscar um colaborador
    public Colaborador buscarColaborador(Colaborador colaborador) {
//        db.collection("dados").document(String.valueOf(colaborador.getNumCracha())).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        colaborador = document.toObject(Colaborador.class);
//                    }
//                }
//            }
//        });
        return colaborador;
    }

    // Apagar um colaborador
    public void deletar(Colaborador colaborador) {
        db.collection("dados").document(String.valueOf(colaborador.getNumCracha())).delete();
    }
    // Alterar um colaborador
    public void alterarData(Colaborador colaborador) {
        db.collection("dados").document(String.valueOf(colaborador.getNumCracha())).update("dataFim", new Date());
    }
}
