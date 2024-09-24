package com.example.maccel;

import android.annotation.SuppressLint;


import android.os.Bundle;
import android.provider.Settings;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import androidx.core.graphics.Insets;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public Button button;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.buttonMac);
        textView = findViewById(R.id.macView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String macMobile2 = getMac();
                textView.setText("este es el ID de este Android " + macMobile2);
            }
        });
    }

    public String getMac() {
        // Devuelve el ANDROID_ID como alternativa a la dirección MAC
        return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
