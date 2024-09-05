xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textview_hello"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hola Mundo"
        android:textSize="30sp"
        android:layout_centerInParent="true"/>
</RelativeLayout>


java


package com.tu.paquete;  // Asegúrate de que este paquete sea correcto

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
