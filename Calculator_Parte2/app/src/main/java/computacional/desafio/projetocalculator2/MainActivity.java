package computacional.desafio.projetocalculator2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button numberZero, numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix, numberSeven, numberEight, numberNine, dot, addition, subtraction, multiplication, division, equals, btClear, btHistory;

    private TextView txtExpression, txtResult;
    private ImageView backspace;
    private List<String> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartComponents();

        numberZero.setOnClickListener(this);
        numberOne.setOnClickListener(this);
        numberTwo.setOnClickListener(this);
        numberThree.setOnClickListener(this);
        numberFour.setOnClickListener(this);
        numberFive.setOnClickListener(this);
        numberSix.setOnClickListener(this);
        numberSeven.setOnClickListener(this);
        numberEight.setOnClickListener(this);
        numberNine.setOnClickListener(this);
        dot.setOnClickListener(this);
        addition.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        btHistory.setOnClickListener(this);

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtExpression.setText(" ");
                txtResult.setText("");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = txtExpression.getText().toString();
                if (!string.isEmpty()) {
                    // Remove o último caractere da expressão
                    txtExpression.setText(string.substring(0, string.length() - 1));
                }
            }
        });

        // Botão de ver histórico
        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre a nova Activity de histórico
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                intent.putStringArrayListExtra("history", (ArrayList<String>) history);
                startActivity(intent);
            }
        });

        // Lógica para avaliar a expressão e outros botões...
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Expression expression = new ExpressionBuilder(txtExpression.getText().toString()).build();
                    double result = expression.evaluate();
                    long longResult = (long) result;

                    if (result == (double) longResult) {
                        txtResult.setText(String.valueOf(longResult));
                    } else {
                        txtResult.setText(String.valueOf(result));
                    }

                    // Adiciona a expressão e o resultado ao histórico
                    String expressionAndResult = txtExpression.getText().toString() + " = " + " " + txtResult.getText().toString();
                    history.add(expressionAndResult);

                } catch (Exception e) {
                    txtResult.setText("Erro");
                }
            }
        });
    }

    private void StartComponents() {
        numberZero = findViewById(R.id.number_zero);
        numberOne = findViewById(R.id.number_one);
        numberTwo = findViewById(R.id.number_two);
        numberThree = findViewById(R.id.number_three);
        numberFour = findViewById(R.id.number_four);
        numberFive = findViewById(R.id.number_five);
        numberSix = findViewById(R.id.number_six);
        numberSeven = findViewById(R.id.number_seven);
        numberEight = findViewById(R.id.number_eight);
        numberNine = findViewById(R.id.number_nine);
        dot = findViewById(R.id.dot);
        addition = findViewById(R.id.addition);
        subtraction = findViewById(R.id.subtraction);
        multiplication = findViewById(R.id.multiplication);
        division = findViewById(R.id.division);
        equals = findViewById(R.id.equals);
        btClear = findViewById(R.id.bt_clear);
        txtExpression = findViewById(R.id.txt_expression);
        txtResult = findViewById(R.id.txt_result);
        backspace = findViewById(R.id.backspace);
        btHistory = findViewById(R.id.bt_history);
    }

    public void addExpression(String string, boolean clearData) {
        // Adiciona novo conteúdo sem apagar o campo após o uso do Backspace
        if (clearData && txtResult.getText().toString().isEmpty()) {
            txtExpression.append(string);
        } else {
            txtExpression.append(txtResult.getText());
            txtExpression.append(string);
            txtResult.setText("");
        }
    }

    public void openHistory(View view) {
        // Cria um Intent para abrir a HistoryActivity
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);

        // Passa o histórico (history) como extra
        intent.putStringArrayListExtra("history", (ArrayList<String>) history);

        // Inicia a nova Activity
        startActivity(intent);
    }



    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.number_zero) {
            addExpression("0", true);
        } else if (id == R.id.number_one) {
            addExpression("1", true);
        } else if (id == R.id.number_two) {
            addExpression("2", true);
        } else if (id == R.id.number_three) {
            addExpression("3", true);
        } else if (id == R.id.number_four) {
            addExpression("4", true);
        } else if (id == R.id.number_five) {
            addExpression("5", true);
        } else if (id == R.id.number_six) {
            addExpression("6", true);
        } else if (id == R.id.number_seven) {
            addExpression("7", true);
        } else if (id == R.id.number_eight) {
            addExpression("8", true);
        } else if (id == R.id.number_nine) {
            addExpression("9", true);
        } else if (id == R.id.dot) {
            addExpression(".", true);
        } else if (id == R.id.addition) {
            addExpression("+", false);
        } else if (id == R.id.subtraction) {
            addExpression("-", false);
        } else if (id == R.id.multiplication) {
            addExpression("*", false);
        } else if (id == R.id.division) {
            addExpression("/", false);
        }
    }
}