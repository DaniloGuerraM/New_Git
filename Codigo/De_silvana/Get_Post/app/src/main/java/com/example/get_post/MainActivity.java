package com.example.get_post;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public Button buttonget;
    public Button buttonput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.texto_1);
        buttonget = findViewById(R.id.boton_get);
        buttonput = findViewById(R.id.button_put);

        buttonget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chequear("GET");
            }
        });
        buttonput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chequear("PUT");
            }
        });
    }

    // Chequea si estás conectado a internet
    public void chequear(String metodo) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Operaciones HTTP
            if ("GET".equals(metodo))
            {
                get();
            } else if ("PUT".equals(metodo))
            {
                put();
            }
        } else {
            // Mostrar errores
            textView.setText("No conectado");
        }
    }

    public void get() {
        textView.setText("Asiendo un GET");
        String url = "https://api.weatherapi.com/v1/current.json?q=eduardo&lang=es&key=87637c428a6a496098d230942242604";
        new GetAPI().execute(url);
    }


    private class GetAPI extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return doGetRequest(urls[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                textView.setText(result);
            } else {
                textView.setText("Error al realizar la solicitud");
            }
        }
    }

    private String doGetRequest(String urlstr) throws MalformedURLException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlstr);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK)
                throw new IOException("Error de respuesta: " + responseCode);

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void put(){
        textView.setText("Asiendo un PUT");
        String url = "La url";
        String jsonString = "{\"name\":\"value\"}";
        new PutAPI().execute(url, jsonString);
    }
    private class PutAPI extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params){
            try {
                return HacerPut(params[0], params[1]);
            }catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                textView.setText(result);
            } else {
                textView.setText("Error al realizar la solicitud");
            }
        }

    }