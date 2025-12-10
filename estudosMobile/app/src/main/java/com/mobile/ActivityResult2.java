package com.mobile;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityResult2 extends AppCompatActivity {
    EditText text;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        text = findViewById(R.id.editTextActivity);
        btn = findViewById(R.id.voltarActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityResult2.this ,ActiviyResult.class);
                intent.putExtra("retorno", text.getText().toString());

                // Voltando
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}