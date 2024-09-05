package com.tunombre.contadordevocales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextText;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextText = findViewById(R.id.editTextText);
        textViewResult = findViewById(R.id.textViewResult);

        Button buttonCount = findViewById(R.id.buttonCount);
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contarVocales();
            }
        });
    }

    private void contarVocales() {
        String texto = editTextText.getText().toString().toLowerCase();
        int countA = 0, countE = 0, countI = 0, countO = 0, countU = 0;

        for (char c : texto.toCharArray()) {
            switch (c) {
                case 'a': countA++; break;
                case 'e': countE++; break;
                case 'i': countI++; break;
                case 'o': countO++; break;
                case 'u': countU++; break;
            }
        }

        int totalVocales = countA + countE + countI + countO + countU;

        String resultado = "Cantidad de cada vocal:\n" +
                "a: " + countA + "\n" +
                "e: " + countE + "\n" +
                "i: " + countI + "\n" +
                "o: " + countO + "\n" +
                "u: " + countU + "\n" +
                "Total de vocales: " + totalVocales;

        textViewResult.setText(resultado);
    }
}
