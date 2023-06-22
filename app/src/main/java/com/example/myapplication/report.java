package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class report extends AppCompatActivity {
    Button button,button4;
    TextView textView11,textView;
    Spinner spinner1;
    String[] type = {"sales","purches"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report1);
        button=findViewById(R.id.button);
        button4=findViewById(R.id.button4);
        textView11=findViewById(R.id.textView11);
        textView=findViewById(R.id.textView);
        spinner1=findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(report.this, android.R.layout.simple_spinner_item,type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value=parent.getItemAtPosition(position).toString();
                Toast.makeText(report.this,value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public void goreport (View v) {
        Intent intent = new Intent(report.this,report2.class);
        startActivity(intent);
    }
    public void report3 (View v) {
        Intent intent = new Intent(report.this,report4.class);
        startActivity(intent);
    }
}