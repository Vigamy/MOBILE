package com.aula.autenticacaofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        ((TextView) findViewById(R.id.txtNome)).setText(user.getDisplayName());
        ((TextView) findViewById(R.id.txtEmail)).setText(user.getEmail());
        ImageView fotoView = findViewById(R.id.imgFoto);



    }
}