package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.time.Instant;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class mainpage1 extends AppCompatActivity {
    Button button2,button3,button22;

    DataBaseHelper dataBaseHelper;

    CalendarView calendarView;

    Spinner spinner,selectAgent;
    Switch switchIsBuy;
    EditText et_description_of_invoice,editTextText4,et_number_of_invoice_details,et_price,et_total;
    TextView textView11,textView2,textView5,textView6,textView,textView9,textView8,textView7,textView66,tv_date;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage1);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        button2 = findViewById(R.id.button2);
        button22 = findViewById(R.id.button22);
        button3= findViewById(R.id.button3);
        spinner = findViewById(R.id.selectType);
        selectAgent = findViewById(R.id.selectAgent);
        switchIsBuy = findViewById(R.id.switch_is_buy);
        et_description_of_invoice = findViewById(R.id.description_of_invoice);
        editTextText4 = findViewById(R.id.editTextText4);
        et_number_of_invoice_details = findViewById(R.id.et_number_of_invoice_details);
        et_price = findViewById(R.id.et_price);
        et_total = findViewById(R.id.et_total);
        textView11 = findViewById(R.id.textView11);
        textView2 = findViewById(R.id.textView2);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView = findViewById(R.id.textView);
        textView9 = findViewById(R.id.textView9);
        textView8 = findViewById(R.id.textView8);
        textView7 = findViewById(R.id.textView7);
        textView66 = findViewById(R.id.textView66);
        tv_date = findViewById(R.id.tv_date);
        selectType();
        selectAgent();
    }
    public void gogo (View gogo) {
        Intent intent = new Intent(mainpage1.this,report.class);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    public void openCalender(View view){
        try {
            calendarView = new CalendarView(this);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    tv_date.setText(year + "-" + month +"-" + dayOfMonth);
                }
            });
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(calendarView);
            builder.setTitle("Enter Date");
            builder.setPositiveButton("Yes", (dialog, which) ->{});
            builder.setNegativeButton("Cancel", (dialog, which) ->{});
            AlertDialog alert = builder.create();
            alert.show();
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    public void onClickSaveOrder(View view){
        String descriptionInvoice = et_description_of_invoice.getText().toString();
        String numberInvoice = et_number_of_invoice_details.getText().toString();
        String price = et_price.getText().toString();
        String total = et_total.getText().toString();
        String date = tv_date.getText().toString();
        int isBuy = switchIsBuy.isChecked() ? 1 : 0;
        if(!descriptionInvoice.isEmpty() && !numberInvoice.isEmpty() && !price.isEmpty() && !total.isEmpty() && !date.isEmpty()){
            int idAgent = ((Agent) selectAgent.getSelectedItem()).id;
            int idType = ((Type) spinner.getSelectedItem()).id;
            ContentValues contentValues = new ContentValues();
            contentValues.put("agent_id",idAgent);
            contentValues.put("date",date);
            contentValues.put("isBuy",isBuy);
            contentValues.put("description",descriptionInvoice);
            dataBaseHelper.insert(ApiConstance.invoiceTable,contentValues);
        }
        et_number_of_invoice_details.setText("");
        et_description_of_invoice.setText("");
        et_price.setText("0");
        et_total.setText("0");
        tv_date.setText("");

    }

    public void selectType(){
        try {
            final Cursor cursor = dataBaseHelper.select("SELECT * FROM "+ApiConstance.typeTable);
            if(cursor != null) {
                Type[] listType = new Type[cursor.getCount()];
                int i = 0;
                while (cursor.moveToNext()) {
                    System.out.println(cursor.getString(1));
                    listType[i] = new Type(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                    i++;
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listType);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
        }catch (Exception error){
            error.printStackTrace();
        }
    }
    public void selectAgent(){
        try {
            final Cursor cursor = dataBaseHelper.select("SELECT * FROM "+ApiConstance.agentTable);
            if(cursor != null) {
                Agent[] listAgent = new Agent[cursor.getCount()];
                int i = 0;
                while (cursor.moveToNext()) {
                    System.out.println(cursor.getString(1));
                    listAgent[i] = new Agent(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                    i++;
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listAgent);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                selectAgent.setAdapter(arrayAdapter);
            }
        }catch (Exception error){
            error.printStackTrace();
        }
    }
}