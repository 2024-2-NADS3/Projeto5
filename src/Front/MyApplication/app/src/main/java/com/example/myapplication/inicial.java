package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class inicial extends AppCompatActivity { // Estende AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Sobrescreve onCreate()
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial); // Define o layout da Activity
    }
}