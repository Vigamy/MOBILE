package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void msg(View view) {
        Toast.makeText(this, "Deu certo..!!", Toast.LENGTH_SHORT).show();
    }

    public void tpMain(View view) {
        finish();
    }
}