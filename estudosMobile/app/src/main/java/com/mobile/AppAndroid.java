package com.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AppAndroid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_android);
    }

    public void msg(View view) {
        Toast.makeText(this, "Deu certo..!!", Toast.LENGTH_SHORT).show();
    }

    public void tpMain(View view) {
        finish();
    }
}