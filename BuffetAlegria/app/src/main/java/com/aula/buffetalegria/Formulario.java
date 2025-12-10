package com.aula.buffetalegria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Formulario extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        TextInputEditText txtNome = findViewById(R.id.txtNome);
        TextInputEditText txtResponsavel = findViewById(R.id.txtResp);
        TextInputEditText txtFone = findViewById(R.id.txtFone);
        MaterialSwitch txtRestricao = findViewById(R.id.txtRestricao);
        TextInputEditText txtDtnasc = findViewById(R.id.txtDtnasc);

        ImageButton btCalendar = findViewById(R.id.btCalendar);
        btCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Formulario.this, null, year, month, dayOfMonth);

                dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date
                        String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                        txtDtnasc.setText(selectedDate);
                    }
                });

                dialog.show();
            }
        });

        Button btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Pessoa pessoa = new Pessoa(
                            Objects.requireNonNull(txtNome.getText()).toString(),
                            Objects.requireNonNull(txtResponsavel.getText()).toString(),
                            Objects.requireNonNull(txtFone.getText()).toString(),
                            dtf.parse(Objects.requireNonNull(txtDtnasc.getText()).toString()),
                            txtRestricao.isChecked(),
                            "",
                            0
                    );

                    Toast.makeText(Formulario.this, "Pessoa salva!", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button btCancel = findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}