package com.example.myapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class report extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button button,btnSearchAgent,best_seller;
    TextView textView11,textView;
    String[] type = {"sales","purchase"};
    String[] partners = {"customer","vendor"};
    Spinner partnerSpiner,spin;
    int selectedPartner = 1;
    DataBaseHelper dataBaseHelper;
    ClassDate fromClassDate = new ClassDate(0,""),toClassDate = new ClassDate(0,"");

    private Button btnSelectFromDate;
    private Button btnSelectToDate;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report1);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        button=findViewById(R.id.button);
        btnSearchAgent=findViewById(R.id.btnSearchAgent);
        textView11=findViewById(R.id.textView11);
        textView=findViewById(R.id.textView);
        best_seller = findViewById(R.id.best_seller);
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

        selectType();


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        partnerSpiner = (Spinner) findViewById(R.id.spinner);
        partnerSpiner.setOnItemSelectedListener(report.this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter partnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,partners);
        partnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(Calendar.DAY_OF_MONTH,view.getDayOfMonth());
                        calendar1.set(Calendar.MONTH,view.getMonth());
                        calendar1.set(Calendar.YEAR,view.getYear());

                        if (fromOrTo.equals("from")) {
                            fromClassDate = new ClassDate(calendar1.getTimeInMillis(),dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            btnSelectFromDate.setText(selectedDate);
                        } else {
                            toClassDate = new ClassDate(calendar1.getTimeInMillis(),dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            btnSelectToDate.setText(selectedDate);
                        }
                    }
                }, year, month, dayOfMonth);

        // Show the dialog
        datePickerDialog.show();
    }



    public void goreport (View v) {
        Intent intent = new Intent(report.this,report2.class);

        try {
            if(fromClassDate.dateLongTime != 0 && toClassDate.dateLongTime != 0) {
                final Cursor cursor = dataBaseHelper.select("SELECT * From " + ApiConstance.invoiceTable + " WHERE Type_id="+((Type)spin.getSelectedItem()).id+" AND date BETWEEN " + fromClassDate.dateLongTime + " AND " + toClassDate.dateLongTime);
                if(cursor.getCount() > 0) {
                    Invoice[] listInvoice = new Invoice[cursor.getCount()];
                    int i = 0;
                    while (cursor.moveToNext()){
                        final Cursor cursorDetails = dataBaseHelper.select("SELECT * From " + ApiConstance.invoiceDetailTable + " WHERE invoice_id = " + cursor.getString(0));
                        if(cursorDetails.moveToNext()){
                            InvoiceDetail invoiceDetail = new InvoiceDetail(cursorDetails.getInt(0),cursor.getInt(0),cursorDetails.getInt(2),cursorDetails.getInt(3),cursorDetails.getDouble(4),cursorDetails.getDouble(5));
                            listInvoice[i] = new Invoice(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(4),cursor.getString(3),cursor.getString(5),invoiceDetail);
                            i++;
                        }
                    }
                    intent.putExtra("Invoice",listInvoice);
                    intent.putExtra("Type",((Type)spin.getSelectedItem()).name);
                    startActivity(intent);
                }
            }
        }catch (Exception error){
            error.printStackTrace();
        }
    }
    public void btnSearchAgent (View v) {
        Intent intent = new Intent(report.this,report4.class);
        try {
            final Cursor cursor = dataBaseHelper.select("SELECT a.id, a.name, a.description,COUNT(b.id) as count FROM " + ApiConstance.agentTable + " a INNER JOIN " + ApiConstance.invoiceTable + " b ON a.id=b.agent_id WHERE b.Type_id = " + selectedPartner + " GROUP BY a.id");
            if (cursor.getCount() > 0) {
                System.out.println(cursor.getCount());
                Agent[] agents = new Agent[cursor.getCount()];
                int[] countOrder = new int[cursor.getCount()];
                int i = 0;
                while (cursor.moveToNext()) {
                    agents[i] = new Agent(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                countOrder[i] = cursor.getInt(3);
                    i++;
                }
                intent.putExtra("Agent", agents);
                intent.putExtra("CountOrder", countOrder);
                startActivity(intent);
            }
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spinner1) {
            Toast.makeText(getApplicationContext(), parent.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
        }else if(parent.getId() == R.id.spinner){
            if(partners[position].equals("customer")) {
                selectedPartner = 1;
            }else{
                selectedPartner = 2;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void selectType(){
        try {
            final Cursor cursor = dataBaseHelper.select("SELECT * FROM "+ApiConstance.typeTable);
            if(cursor != null) {
                Type[] listType = new Type[cursor.getCount()];
                int i = 0;
                while (cursor.moveToNext()) {
                    listType[i] = new Type(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                    i++;
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listType);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(arrayAdapter);
            }
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    public void onClickBest_seller(View view){
        Intent intent = new Intent(report.this,Best_Seller.class);
        try {
            final Cursor cursor = dataBaseHelper.select(
                    "SELECT b.id, b.name, b.description,COUNT(a.number) as count FROM " + ApiConstance.invoiceDetailTable + " a " +
                    "INNER JOIN " + ApiConstance.materialTable + " b ON b.id=a.material_id " +
                    "INNER JOIN " + ApiConstance.invoiceTable + " c ON c.id=a.invoice_id " +
                            "WHERE b.Type_Id=1 AND c.date BETWEEN " + fromClassDate.dateLongTime + " AND "+toClassDate.dateLongTime+" " +
                            "GROUP BY a.id " +
                            "ORDER BY count DESC");
            System.out.println(cursor.getCount());
            if (cursor.getCount() > 0) {
                Material[] materials = new Material[cursor.getCount()];
                int[] countSale = new int[cursor.getCount()];
                int i = 0;
                while (cursor.moveToNext()) {
                    materials[i] = new Material(cursor.getInt(0),((Type)spin.getSelectedItem()).id, cursor.getString(1), cursor.getString(2));
                    countSale[i] = cursor.getInt(3);
                    i++;
                }
                intent.putExtra("Material", materials);
                intent.putExtra("CountSale", countSale);
                startActivity(intent);
            }
        }catch (Exception error){
            error.printStackTrace();
        }
    }
}