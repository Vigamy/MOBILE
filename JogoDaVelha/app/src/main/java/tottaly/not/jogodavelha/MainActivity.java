package tottaly.not.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//    View[][] casa = new View[2][2];
//    int rodada = 0;
    boolean vez = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        casa[0][0] = findViewById(R.id.img1);
//        casa[0][1] = findViewById(R.id.img2);
//        casa[0][2] = findViewById(R.id.img3);
//        casa[1][0] = findViewById(R.id.img4);
//        casa[1][1] = findViewById(R.id.img5);
//        casa[1][2] = findViewById(R.id.img6);
//        casa[2][0] = findViewById(R.id.img7);
//        casa[2][1] = findViewById(R.id.img8);
//        casa[2][2] = findViewById(R.id.img9);
    }
    public void selecionar(View view){
        ImageView bt = (ImageView) view;
        if(bt.getContentDescription() == null || bt.getContentDescription() == ""){
            if(vez) {
                bt.setImageResource(R.drawable.x);
                bt.setContentDescription("X");
            } else{
                bt.setImageResource(R.drawable.o);
                bt.setContentDescription("O");
            }
            vez = !vez;
        }
    }
    public void resetar(View view){
        recreate();
    }
}