package com.example.get_post;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public Button button;
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
        textView = findViewById(R.id.texto_1);
        button = findViewById(R.id.boton_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chequear();
            }
        });
    }
    // chequea si estas conectrado a internet
    public void chequear(){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Operaciones http
            get();
        } else {
            // Mostrar errores
            textView.setText("no conectado");
        }
    }
    public void get()
    {
        textView.setText("estas conectado");
        try {
            URL url = new URL("https://api.weatherapi.com/v1/current.json?q=eduardo&lang=es&key=87637c428a6a496098d230942242604");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Toast.makeText(MainActivity.this, "siiiiuuuuuuuuu", Toast.LENGTH_SHORT).show();
        } catch (Exception exception){
            Toast.makeText((Context) MainActivity.this, (CharSequence) exception, Toast.LENGTH_SHORT).show();
        }
    }
}