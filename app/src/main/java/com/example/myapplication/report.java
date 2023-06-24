package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class report extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button button,button4;
    TextView textView11,textView;
    String[] type = {"sales","purchase"};
    String[] partners = {"customer","vendor"};
    Spinner partnerSpiner,spin;
    String  selectedPartner,selectedReportType;

    private Button btnSelectFromDate;
    private Button btnSelectToDate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report1);
        button=findViewById(R.id.button);
        button4=findViewById(R.id.button4);
        textView11=findViewById(R.id.textView11);
        textView=findViewById(R.id.textView);
//        spinner1=findViewById(R.id.spinner1);
//        ArrayAdapter<String> adapter =new ArrayAdapter<String>(report.this, android.R.layout.simple_spinner_item,type);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String value=parent.getItemAtPosition(position).toString();
//                Toast.makeText(report.this,value, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        //Getting the instance of Spinner and applying OnItemSelectedListener on it  
        spin = (Spinner) findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(report.this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        partnerSpiner = (Spinner) findViewById(R.id.spinner);
        partnerSpiner.setOnItemSelectedListener(report.this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter partnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,partners);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        partnerSpiner.setAdapter(partnerAdapter);


        btnSelectFromDate = findViewById(R.id.btnSelectFromDate);

        btnSelectFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog("from");
            }
        });

        btnSelectToDate = findViewById(R.id.btnSelectToDate);

        btnSelectToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog("to");
            }
        });

    }

    private void showDatePickerDialog(String fromOrTo) {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog and set the selected listener
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Handle the selected date
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        Toast.makeText(report.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                        if (fromOrTo.equals("from")) {
                            btnSelectFromDate.setText(selectedDate);
                        } else {
                            btnSelectToDate.setText(selectedDate);
                        }
                    }
                }, year, month, dayOfMonth);

        // Show the dialog
        datePickerDialog.show();
    }



    public void goreport (View v) {
        Intent intent = new Intent(report.this,report2.class);

        String fromDate = btnSelectFromDate.getText().toString();
        String toDate = btnSelectToDate.getText().toString();

        startActivity(intent);
    }
    public void report3 (View v) {
        Intent intent = new Intent(report.this,report4.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spinner1) {
           selectedReportType = type[position];
            Toast.makeText(getApplicationContext(), type[position], Toast.LENGTH_LONG).show();
        }else{
            selectedPartner = partners[position];
            Toast.makeText(getApplicationContext(), partners[position], Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}