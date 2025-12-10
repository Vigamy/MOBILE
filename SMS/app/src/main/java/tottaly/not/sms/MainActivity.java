package tottaly.not.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText telefone, mensagem;
    Button btnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMessage = findViewById(R.id.btnMessage);
        telefone = findViewById(R.id.telefone);
        mensagem = findViewById(R.id.mensagem);

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Preparando para rodar em 2° plano o SMS
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                Telephony.MmsSms.PendingMessages;
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),
                        0, intent, PendingIntent.FLAG_IMMUTABLE);
                //Carregar um gerenciador de SMS
                SmsManager sms = SmsManager.getDefault(); // Pega o do telefone

                sms.sendTextMessage(telefone.getText().toString(), null, mensagem.getText().toString(), pi, null);

                Toast.makeText(MainActivity.this, "Deu bom", Toast.LENGTH_SHORT).show();

            }
        });


    }
}