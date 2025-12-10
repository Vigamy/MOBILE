package tottaly.not.prticamobile;

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

        imagemSplash = findViewById(R.id.imagemSplash);
        Glide.with(SplashScreen.this).load("https://media.tenor.com/TSJ8PZ22LR8AAAAM/toothless-dragon.gif").centerCrop().into(imagemSplash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirTela();
            }
        }, 3000);
    }
    private void abrirTela() {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}