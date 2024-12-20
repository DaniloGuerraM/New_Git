package com.example.asistenciaites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// ESTO esta clonado..
public class MainActivity extends AppCompatActivity {
////////////////////////////////////////

////////////////////////////////////////
    public EditText editTextEMail;
    public EditText editTextNombre;
    public Button buttonEMail;

    public String stringEMail;
    public String stringNombre;
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
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        editTextEMail = findViewById(R.id.TextEmail);
        editTextNombre = findViewById(R.id.editTextText);
        buttonEMail = findViewById(R.id.buttonEmail);


        if (isLoggedIn){
            SegundaPantalla();
        }else {
            buttonEMail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stringEMail = editTextEMail.getText().toString();
                    stringNombre = editTextNombre.getText().toString();
                    if (!stringEMail.isEmpty()){
                        if (!stringNombre.isEmpty()){
                            Put(stringEMail, stringNombre);
                        }else{
                            Toast.makeText(MainActivity.this, "Nombre esta vacio", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "E-Mail esta vacio", Toast.LENGTH_LONG).show();
                    }


                }
            });
        }

//////////////////////////////////////////////////////////////////////////////////////


    }


    public void Put(String Email, String Nombre){
        String id = ObtenerIdAndroid();
        String url = "http://192.168.0.104:3002/api/Event";//http://http://172.23.5.191:3002/api/Alumno/mac
        String jsonString ="{ \"identificador\": 0,\"idAndroid\": \""+id +"\", \"gmail\": \""+ Email +"\", \"nombre\": \"" +Nombre+ "\" }";
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
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("EMail", stringEMail);
                editor.putString("Nombre", stringNombre);
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                SegundaPantalla();
            } else {
                Toast.makeText(MainActivity.this, "Intentalo de nuevo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String hacerPut(String urlstr, String jsonInputString) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlstr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            urlConnection.setRequestProperty("Accept", "application/json");
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


    public void SegundaPantalla(){
        Intent intent = new Intent(MainActivity.this, EleccionActivity.class);
        startActivity(intent);
        finish();
    }
    public String ObtenerIdAndroid(){
        return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}