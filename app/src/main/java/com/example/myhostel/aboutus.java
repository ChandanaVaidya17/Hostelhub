package com.example.myhostel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class aboutus extends AppCompatActivity {
    TextView us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        us = findViewById(R.id.text);
        us.setText(R.string.about_us);
    }
}