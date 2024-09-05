package com.tunombre.contadordecaracteres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextText;
    private Button buttonCount;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextText = findViewById(R.id.editTextText);
        buttonCount = findViewById(R.id.buttonCount);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countCharacters();
            }
        });
    }

    private void countCharacters() {
        String inputText = editTextText.getText().toString();
        int count = 0;

        // Recorrer el texto y contar caracteres (letras y números)
        for (int i = 0; i < inputText.length(); i++) {
            char c = inputText.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                count++;
            }
        }

        // Mostrar el total de caracteres en el TextView
        textViewResult.setText("Total de caracteres: " + count);
    }
}
