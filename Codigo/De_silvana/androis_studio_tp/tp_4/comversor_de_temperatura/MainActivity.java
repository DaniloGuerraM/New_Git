package com.tunombre.conversordetemperatura;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTemperature;
    private Spinner spinnerUnit;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTemperature = findViewById(R.id.editTextTemperature);
        spinnerUnit = findViewById(R.id.spinnerUnit);
        textViewResult = findViewById(R.id.textViewResult);

        Button buttonConvert = findViewById(R.id.buttonConvert);
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirTemperatura();
            }
        });
    }

    private void convertirTemperatura() {
        String tempString = editTextTemperature.getText().toString();
        if (tempString.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese una temperatura", Toast.LENGTH_SHORT).show();
            return;
        }

        double temperatura = Double.parseDouble(tempString);
        String unidad = spinnerUnit.getSelectedItem().toString();
        double resultado = 0;

        if (unidad.equals("Celsius")) {
            resultado = (temperatura * 9/5) + 32;
            textViewResult.setText(String.format("Resultado: %.2f °F", resultado));
        } else if (unidad.equals("Fahrenheit")) {
            resultado = (temperatura - 32) * 5/9;
            textViewResult.setText(String.format("Resultado: %.2f °C", resultado));
        }
    }
}
