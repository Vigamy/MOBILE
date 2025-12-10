package com.aula.buffetalegria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class DataBase {
    Map<String, Object> pessoa_id = new HashMap<>();
    //Abrir firebase
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public DataBase(){

    }

    public void salvar(Pessoa pessoa){
        if(pessoa.getId() == 0) { //novo registro
            db.collection("counters").document("pessoa_id").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    int id = 1;
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            pessoa_id = document.getData();
                            id = Integer.parseInt(pessoa_id.get("chave").toString()) + 1;

                        }
                        pessoa_id.put("chave", id);
                        db.collection("counters").document("pessoa_id").set(pessoa_id);

                        //gravra a pessoa
                        pessoa.setId(id);
                        db.collection("pessoas").document(String.valueOf(id)).set(pessoa);
                    }
                }
            });
        } else{
            db.collection("pessoas").document(String.valueOf(pessoa.getId())).set(pessoa);
        }
    }

    public void listar(List<Pessoa> listaPessoa, AdapterPessoa adapterPessoa){
        //Recuperar dados em tempo real
        db.collection("pessoas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                listaPessoa.clear();
                adapterPessoa.notifyDataSetChanged();
                for (DocumentSnapshot document : value.getDocuments()) {
                    Pessoa pessoa = document.toObject(Pessoa.class);
                    listaPessoa.add(pessoa);
                    adapterPessoa.notifyItemInserted(listaPessoa.size());
                }
            }
        });
    }

    public void remover (Pessoa pessoa){
        db.collection("pessoas").document(String.valueOf(pessoa.getId())).delete();
    }
}
