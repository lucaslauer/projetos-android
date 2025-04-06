package computacional.desafio.projetocalculator2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private TextView txtHistoryList;
    private Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        txtHistoryList = findViewById(R.id.txt_history_list);
        btBack = findViewById(R.id.bt_back);

        // Recebe o histórico da MainActivity
        Intent intent = getIntent();
        ArrayList<String> history = intent.getStringArrayListExtra("history");

        // Exibe o histórico
        if (history != null) {
            StringBuilder historyText = new StringBuilder();
            for (String entry : history) {
                historyText.append(entry).append("\n");
            }
            txtHistoryList.setText(historyText.toString());
        }

        // Botão para voltar à tela principal
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a Activity e volta para a calculadora
            }
        });
    }
}
