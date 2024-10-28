package com.example.adivine_n;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextGuess;
    private Button boton;
    private TextView textViewHint;

    private int randomNumber;
    private boolean gameWon;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextGuess = findViewById(R.id.editTextGuess);
        boton = findViewById(R.id.buttonGuess);
        textViewHint = findViewById(R.id.textViewHint);

        startNewGame();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }
    private void startNewGame(){
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1; // Número aleatorio entre 1 y 100
        gameWon = false;
        textViewHint.setText("Adivina un número entre 1 y 100");
    }
    private void checkGuess() {
        if (!gameWon) {
            String guessString = editTextGuess.getText().toString();
            if (guessString.isEmpty()) {
                Toast.makeText(this, "Ingresa un número", Toast.LENGTH_SHORT).show();
                return;
            }

            int guess = Integer.parseInt(guessString);
            if (guess < 1 || guess > 100) {
                Toast.makeText(this, "El número debe estar entre 1 y 100", Toast.LENGTH_SHORT).show();
                return;
            }

            if (guess == randomNumber) {
                gameWon = true;
                textViewHint.setText("¡Correcto! Has adivinado el número " + randomNumber);
                Toast.makeText(this, "¡Felicidades!", Toast.LENGTH_SHORT).show();
            } else if (guess < randomNumber) {
                textViewHint.setText("Más alto");
            } else {
                textViewHint.setText("Más bajo");
            }
        } else {
            Toast.makeText(this, "Ya has ganado. Inicia un nuevo juego.", Toast.LENGTH_SHORT).show();
        }
    }
    public void newGame(View view) {
        startNewGame();
        editTextGuess.setText("");
    }
}