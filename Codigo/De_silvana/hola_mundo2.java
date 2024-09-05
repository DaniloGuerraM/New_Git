xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFC0CB"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/imageview_example"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/imagen"  <!-- Reemplaza con el nombre de tu imagen -->
        android:layout_centerHorizontal="true"
        android:layout_marginTop="20dp"/>

    <TextView
        android:id="@+id/textview_hello"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hola Mundo"
        android:textSize="30sp"
        android:textColor="#FFFFFF"
        android:layout_below="@id/imageview_example"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="20dp"/>

    <Button
        android:id="@+id/button_change_message"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Cambiar Mensaje"
        android:background="#FF69B4"
        android:textColor="#FFFFFF"
        android:layout_below="@id/textview_hello"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="20dp"/>
</RelativeLayout>


java

package com.tu.paquete;  // Asegúrate de que este paquete sea correcto

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewHello;
    private Button buttonChangeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHello = findViewById(R.id.textview_hello);
        buttonChangeMessage = findViewById(R.id.button_change_message);

        buttonChangeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewHello.setText("Mensaje Cambiado");
            }
        });
    }
}
