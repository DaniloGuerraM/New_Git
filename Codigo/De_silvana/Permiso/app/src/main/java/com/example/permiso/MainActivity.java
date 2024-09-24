
package com.example.permiso;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

//import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;


//<uses-feature android:name="android.hardware.camera.any" />
public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    public Button camara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verifica y solicita permisos de la cámara si no están concedidos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }

        camara = findViewById(R.id.button_camara);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al hacer clic en el botón, inicia el escaneo de código QR
                escanearcódigoQR();
            }
        });
    }

    // Método para capturar una imagen con la cámara
    public void escanearcódigoQR() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    // Procesa la imagen capturada para detectar y leer el código QR
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap != null) {
                // Convierte el Bitmap en InputImage para usarlo en ML Kit
                InputImage image = InputImage.fromBitmap(imageBitmap, 0);

                // Inicializa el detector de códigos QR
                BarcodeScanning.getClient().process(image)
                        .addOnSuccessListener(this::onSuccess)
                        .addOnFailureListener(e -> {
                            // Muestra un mensaje si hay un error al procesar la imagen
                            Toast.makeText(MainActivity.this, "Error al procesar el código QR", Toast.LENGTH_SHORT).show();
                        });
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onSuccess(List<Barcode> barcodes) {
        if (barcodes.isEmpty()) {
            Toast.makeText(MainActivity.this, "No se detectó un código", Toast.LENGTH_SHORT).show();
        } else {
            for (Barcode barcode : barcodes) {
                String qrContent = barcode.getRawValue();
                Toast.makeText(MainActivity.this, "Contenido: " + qrContent, Toast.LENGTH_LONG).show();
            }
        }
    }

}


/*package com.example.permiso;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    public Button camara;
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        camara = findViewById(R.id.button_camara);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cam();
            }
        });
    }
    public void cam()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Toast.makeText(this, "Foto tomada", Toast.LENGTH_SHORT).show();
        }
    }}*/