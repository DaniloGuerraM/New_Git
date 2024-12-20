package com.example.asistenciaites;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;




import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EleccionActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 1;



    public Button botonTomar;

    public ListView EventlistView;
    public ArrayList<String> arrayNombres;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eleccion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Verificar el permiso de la camara

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }



        EventlistView = findViewById(R.id.listWiewEvento);
        botonTomar = findViewById(R.id.tomarAsistencia);

        botonTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EscaneraQR();
            }
        });
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    //metodo para capturar imagen
    public void EscaneraQR(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

    }
    //Procesa la imagen capturada para detectar y leer el contenido
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap != null)
            {
                //covercion el bitmap en InputImage para usarlo en ML Kit
                InputImage image = InputImage.fromBitmap(imageBitmap, 0);

                //para leer el QR
                BarcodeScanning.getClient().process(image)
                        .addOnSuccessListener(this::onSuccess)
                        .addOnFailureListener(e -> {

                            Toast.makeText(this, "Error al procesar el código QR", Toast.LENGTH_SHORT).show();

                        });
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        if (grantResult. length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED)
        {

        }else{
            Toast.makeText(this, "Permiso Denegado", Toast.LENGTH_SHORT).show();
        }
    }
    private void onSuccess(List<Barcode> barcodes)
    {
        if (barcodes.isEmpty())
        {
            Toast.makeText(this, "QR no leido", Toast.LENGTH_SHORT).show();

        }else
        {
            for (Barcode barcode : barcodes)
            {
                String qrContext = barcode.getRawValue();

                get(qrContext);

            }
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    public void get(String qr) {

        String url = "http://192.168.0.104:3002/api/Event";
        new EleccionActivity.GetAPI().execute(url);
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

                try {
                    // Convertir el resultado en un JSONArray
                    JSONArray jsonArray = new JSONArray(result);
                    arrayNombres = new ArrayList<String>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String nombreUnix = jsonObject.getString("nombre");

                        arrayNombres.add(nombreUnix);

                    }
                    ArrayAdapter<String> nombresAdactado = new ArrayAdapter<String>( EleccionActivity.this, android.R.layout.simple_list_item_1, arrayNombres);
                    EventlistView.setAdapter(nombresAdactado);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            } else {
                Toast.makeText(EleccionActivity.this, "\"Error al realizar la solicitud\"", Toast.LENGTH_SHORT).show();
                //textMostrar.setText();
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
}