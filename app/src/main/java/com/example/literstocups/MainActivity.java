package com.example.literstocups;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Handler;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputLiter;
    private Button convertButton;
    private TextView outputCups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLiter = findViewById(R.id.inputLiter);
        convertButton = findViewById(R.id.convertButton);
        outputCups = findViewById(R.id.outputCups);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double liters = Double.parseDouble(inputLiter.getText().toString());
                double cups = liters * 4.22;
                outputCups.setText(String.format("%.2f", cups) + " cups");
            }
        });
    }
}

