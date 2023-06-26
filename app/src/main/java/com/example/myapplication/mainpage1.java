package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;


public class mainpage1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button button2,button3,button22;

    DataBaseHelper dataBaseHelper;
    ClassDate classDate = new ClassDate(0,"");

    CalendarView calendarView;

    Spinner spinner,selectAgent, selectedMaterial;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchIsBuy;
    EditText et_description_of_invoice,et_material,et_number_of_invoice_details,et_price,et_total;
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
        selectedMaterial = findViewById(R.id.materialSpinner);
        switchIsBuy = findViewById(R.id.switch_is_buy);
        et_description_of_invoice = findViewById(R.id.description_of_invoice);
        et_material = findViewById(R.id.et_material);
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
        spinner.setOnItemSelectedListener(this);
        selectType();
        selectAgent();
        selectMaterial();
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
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year,month,dayOfMonth);
                    classDate = new ClassDate(calendar.getTimeInMillis(),year + "-" + month +"-" + dayOfMonth);
                    tv_date.setText(classDate.toString());
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
        long date = classDate.dateLongTime;
        String material = et_material.getText().toString();
        int isBuy = switchIsBuy.isChecked() ? 1 : 0;
        int material_id = 0;
        if(selectedMaterial.getSelectedItem() != null) {
            material_id = ((Material) selectedMaterial.getSelectedItem()).id;
        }
        if(!descriptionInvoice.isEmpty() && !numberInvoice.isEmpty() && !price.isEmpty() && !total.isEmpty() && date != 0){
            int idAgent = ((Agent) selectAgent.getSelectedItem()).id;
            int idType = ((Type) spinner.getSelectedItem()).id;
            if(material_id != 0 && idType == 1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("agent_id", idAgent);
                contentValues.put("Type_id", idType);
                contentValues.put("date", date);
                contentValues.put("isBuy", isBuy);
                contentValues.put("description", descriptionInvoice);
                long id_invoice = dataBaseHelper.insert(ApiConstance.invoiceTable, contentValues);
                contentValues = new ContentValues();
                contentValues.put("invoice_id",id_invoice);
                contentValues.put("material_id",material_id);
                contentValues.put("number",numberInvoice);
                contentValues.put("price",price);
                contentValues.put("total",total);
                long id_invoice_details = dataBaseHelper.insert(ApiConstance.invoiceDetailTable,contentValues);
            }else if(!material.isEmpty() && idType == 2){
                ContentValues contentValues = new ContentValues();
                contentValues.put("agent_id", idAgent);
                contentValues.put("Type_id", idType);
                contentValues.put("date", date);
                contentValues.put("isBuy", isBuy);
                contentValues.put("description", descriptionInvoice);
                long id_invoice = dataBaseHelper.insert(ApiConstance.invoiceTable, contentValues);

                contentValues = new ContentValues();
                contentValues.put("name",material);
                contentValues.put("Type_Id",idType);
                contentValues.put("description","Ok");
                long id_material = dataBaseHelper.insert(ApiConstance.materialTable,contentValues);
                selectMaterial();

                contentValues = new ContentValues();
                contentValues.put("invoice_id",id_invoice);
                contentValues.put("material_id",id_material);
                contentValues.put("number",numberInvoice);
                contentValues.put("price",price);
                contentValues.put("total",total);
                long id_invoice_details = dataBaseHelper.insert(ApiConstance.invoiceDetailTable,contentValues);
            }
        }
        et_number_of_invoice_details.setText("");
        et_description_of_invoice.setText("");
        et_material.setText("");
        et_price.setText("");
        et_price.setHint("0");
        et_total.setText("");
        et_total.setHint("0");
        tv_date.setText("");

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

    public void selectMaterial(){
        try {
            final Cursor cursor = dataBaseHelper.select("SELECT * FROM "+ApiConstance.materialTable);
            if(cursor != null) {
                Material[] listMaterial = new Material[cursor.getCount()];
                int i = 0;
                while (cursor.moveToNext()) {
                    listMaterial[i] = new Material(cursor.getInt(0),cursor.getInt(2),cursor.getString(1),cursor.getString(2));
                    i++;
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listMaterial);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                selectedMaterial.setAdapter(arrayAdapter);
            }
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Type type = (Type) parent.getSelectedItem();
        System.out.println(type.name);
        switch (type.id){
            case 1:
                selectedMaterial.setVisibility(View.VISIBLE);
                et_material.setVisibility(View.INVISIBLE);
                break;
            case 2:
                selectedMaterial.setVisibility(View.INVISIBLE);
                et_material.setVisibility(View.VISIBLE);
                break;

            default:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}