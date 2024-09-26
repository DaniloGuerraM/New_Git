package com.example.get_post;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public Button buttonget;
    public Button buttonput;
    public Button buttonpost;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.texto_1);
        buttonget = findViewById(R.id.boton_get);
        buttonput = findViewById(R.id.button_put);
        buttonpost = findViewById(R.id.button_post);  //
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
        buttonpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chequear("POST");
            }
        });
    }


    public void chequear(String metodo) {
        // Chequea si estás conectado a internet
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            if ("GET".equals(metodo)) {
                get();
            } else if ("PUT".equals(metodo)) {
                put();
            } else if ("POST".equals(metodo)) {
                post();
            }
        } else {

            textView.setText("No conectado");
        }
    }

    public void get() {
        textView.setText("Haciendo un GET");
        String url = "http://172.23.5.194:3002/API/Alumnos";
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
            Log.d("HTTP GET", "Response Code: " + responseCode);

            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Error de respuesta: " + responseCode);
            }

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

    public void put() {
        textView.setText("Haciendo un PUT");
        String url = "http://172.23.5.194:3002/API/Alumno";
        String jsonString = "{\"name\":\"danilo\"}";
        new PutAPI().execute(url, jsonString);
    }

    private class PutAPI extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                return hacerPut(params[0], params[1]);
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

    private String hacerPut(String urlstr, String jsonInputString) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlstr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);

            try (OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = urlConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Error de respuesta: " + responseCode);
            }

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
//hola

    public void post() {
        textView.setText("Haciendo un POST");
        String url = "http://172.23.5.194:3002/API/Registro";
        String jsonString = "{\"name\":\"carlos\"}";
        new PostAPI().execute(url, jsonString);
    }

    private class PostAPI extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                return hacerPost(params[0], params[1]);
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

    private String hacerPost(String urlstr, String jsonInputString) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlstr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);

            try (OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = urlConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_CREATED) {
                throw new IOException("Error de respuesta: " + responseCode);
            }

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
}
