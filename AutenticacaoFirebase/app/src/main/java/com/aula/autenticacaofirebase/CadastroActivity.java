package com.aula.autenticacaofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CadastroActivity extends AppCompatActivity {

    String txtNome;
    String txtEmail;
    String txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button bt = findViewById(R.id.btCadastro);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNome = ((EditText) findViewById(R.id.editNome)).getText().toString();
                txtEmail = ((EditText) findViewById(R.id.editEmail)).getText().toString();
                txtSenha = ((EditText) findViewById(R.id.editSenha)).getText().toString();

                // Validações básicas para evitar entradas vazias
                if (txtNome.isEmpty() || txtEmail.isEmpty() || txtSenha.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {
                    salvarLogin();
                }
            }
        });
    }

    private void salvarLogin() {
        FirebaseAuth autenticator = FirebaseAuth.getInstance();
        autenticator.createUserWithEmailAndPassword(txtEmail, txtSenha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso.", Toast.LENGTH_SHORT).show();

                            // Atualizar o nome do usuário e foto
                            FirebaseUser userLogin = autenticator.getCurrentUser();
                            if (userLogin != null) {
                                UserProfileChangeRequest profileUser = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(txtNome)
                                        .setPhotoUri(Uri.parse("https://i.pinimg.com/474x/10/74/6f/10746fe59079e46d62375be74eaf043d.jpg"))
                                        .build();

                                userLogin.updateProfile(profileUser)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(CadastroActivity.this, "Perfil atualizado com sucesso.", Toast.LENGTH_SHORT).show();
                                                    finish(); // Encerrar atividade após o sucesso
                                                } else {
                                                    Toast.makeText(CadastroActivity.this, "Erro ao atualizar perfil.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(CadastroActivity.this, "Erro ao obter o usuário logado.", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            String msg = "Erro ao cadastrar: " + task.getException().getMessage();
                            Toast.makeText(CadastroActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Log.e("ERRO", task.getException().getMessage());
                        }
                    }
                });
    }
}
