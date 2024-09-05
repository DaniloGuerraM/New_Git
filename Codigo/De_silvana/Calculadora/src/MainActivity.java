// src/main/java/com/tuempresa/calculadora/MainActivity.java
package com.tuempresa.calculadora;

import javax.swing.text.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1EditText, num2EditText;
    Spinner operationSpinner;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1EditText = findViewById(R.id.num1);
        num2EditText = findViewById(R.id.num2);
        operationSpinner = findViewById(R.id.operation);
        resultTextView = findViewById(R.id.result);
        Button calculateButton = findViewById(R.id.calculate);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {
        int num1 = Integer.parseInt(num1EditText.getText().toString());
        int num2 = Integer.parseInt(num2EditText.getText().toString());
        String operation = operationSpinner.getSelectedItem().toString();

        int result = 0;
        switch (operation) {
            case "Sumar":
                result = num1 + num2;
                break;
            case "Restar":
                result = Math.abs(num1 - num2);
                break;
            case "Multiplicar":
                result = num1 * num2;
                break;
            case "Dividir":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    resultTextView.setText("No se puede dividir entre 0");
                    return;
                }
                break;
        }
        resultTextView.setText("El resultado es: " + result);
    }
}


