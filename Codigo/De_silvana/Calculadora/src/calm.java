package com.example.calculadora;

import javax.swing.text.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    private Button btn_suma;
    private Button btn_resta;
    private Button btn_producto;
    private Button btn_sustraccion;

    private TextView text_resultado;

    private EditText edit_numero_uno;
    private EditText edit_numero_dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        text_resultado = findViewById(R.id.resultado);

        edit_numero_uno = findViewById(R.id.n1);
        edit_numero_dos = findViewById(R.id.n2);


        btn_suma = findViewById(R.id.suma);
        btn_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_resultado.setText(suma(Integer.parseInt(edit_numero_uno.getText().toString()), Integer.parseInt(edit_numero_dos.getText().toString())) + "");
            }
        });

        btn_resta = findViewById(R.id.resta);
        btn_resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_resultado.setText(resta(Integer.parseInt(edit_numero_uno.getText().toString()), Integer.parseInt(edit_numero_dos.getText().toString())) + "");
            }
        });

        btn_producto = findViewById(R.id.producto);
        btn_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_resultado.setText(producto(Integer.parseInt(edit_numero_uno.getText().toString()), Integer.parseInt(edit_numero_dos.getText().toString())) + "");
            }
        });
        btn_sustraccion = findViewById(R.id.sustracion);
        btn_sustraccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_resultado.setText(division(Integer.parseInt(edit_numero_uno.getText().toString()), Integer.parseInt(edit_numero_dos.getText().toString())) + "");
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public int suma(int a, int b) {
        return a + b;
    }

    public int resta(int a, int b) {
        return a - b;
    }

    public int producto(int a, int b) {
        return a * b;
    }

    public double division(int a, int b) {
        double resultado = 0.0;
        if (b != 0) {
            try {
                resultado = (double) a / b;
            } catch (ArithmeticException e) {
                Toast.makeText(MainActivity.this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Por favor ingrese un valor diferente de cero", Toast.LENGTH_SHORT).show();
        }
        return resultado;
    }
}
