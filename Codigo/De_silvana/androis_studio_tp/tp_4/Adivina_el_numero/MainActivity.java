package com.tunombre.adivinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextGuess;
    private Button buttonGuess;
    private TextView textViewHint;

    private int randomNumber;
    private boolean gameWon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextGuess = findViewById(R.id.editTextGuess);
        buttonGuess = findViewById(R.id.buttonGuess);
        textViewHint = findViewById(R.id.textViewHint);

        startNewGame();

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    private void startNewGame() {
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

    // Método para reiniciar el juego
    public void newGame(View view) {
        startNewGame();
        editTextGuess.setText("");
    }
}
