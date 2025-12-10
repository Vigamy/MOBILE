package com.aula.apis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btGetAll)).setOnClickListener(
                v -> startActivity(new Intent(this, MainActivity_getPhoto.class)));

        ((Button) findViewById(R.id.btGetID)).setOnClickListener(
                v -> startActivity(new Intent(this, MainActivity_getPhoto.class).putExtra("byId", true)));

        ((Button) findViewById(R.id.btPostFeeds)).setOnClickListener(
                v -> startActivity(new Intent(this, MainActivity_feeds_form.class).putExtra("byPost", "post")));

        ((Button) findViewById(R.id.btPutFeeds)).setOnClickListener(
                v -> startActivity(new Intent(this, MainActivity_feeds_form.class).putExtra("byPost", "put")));

        ((Button) findViewById(R.id.btDeleteFeeds)).setOnClickListener(
                v -> startActivity(new Intent(this, MainActivity_feeds_form.class).putExtra("byPost", "delete")));
    }

}