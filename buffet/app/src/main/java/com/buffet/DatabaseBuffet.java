package com.buffet;

import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseBuffet {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public DatabaseBuffet() {
    }

    public void salvar(Crianca crianca) {
        db.collection("crianca")
                .add(crianca)
                .addOnSuccessListener(documentReference -> {

                    //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    //Log.w(TAG, "Error adding document", e);
                });
    }
}
