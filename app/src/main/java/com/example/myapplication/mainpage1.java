package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class mainpage1 extends AppCompatActivity {
    Button button2;
    EditText editTextText6,editTextText5,editTextText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage1);
        button2 = findViewById(R.id.button2);
    }
    public void gogo (View gogo) {
        Intent intent = new Intent(mainpage1.this,report.class);
        startActivity(intent);
    }
}