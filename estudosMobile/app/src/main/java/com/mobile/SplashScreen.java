package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {
    ImageView imagemSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // GLIDE
        // https://github.com/bumptech/glide
        imagemSplash = findViewById(R.id.imagemSplash);
        Glide.with(SplashScreen.this).load("https://media.tenor.com/mVzYEUtbFmYAAAAM/cat-piano.gif").centerCrop().into(imagemSplash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirTela();
            }
        }, 1000);

    }

    private void abrirTela() {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}