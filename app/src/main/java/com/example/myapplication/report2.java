package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class report2 extends AppCompatActivity {
    Invoice[] listInvoice ;

    TableLayout tl_invoice;
    TextView tvTotalOrder;

    int totalOrder = 0;
    String typeOrder = "";

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);
        tl_invoice = findViewById(R.id.tl_invoice);
        tvTotalOrder = findViewById(R.id.totalOrder);
        if(getIntent().getStringExtra("Type") != null) {
            typeOrder = getIntent().getStringExtra("Type");
        }
        if(getIntent().getSerializableExtra("Invoice") != null) {
            listInvoice = (Invoice[]) getIntent().getSerializableExtra("Invoice");
        }
        if(listInvoice != null){
            System.out.println(listInvoice[0].id);
            TableRow tableRow;
            TextView textView;
            for (Invoice invoice : listInvoice) {
                tableRow = new TableRow(getApplicationContext());
                for (int j = 0; j < 9; j++) {
                    textView = new TextView(getApplicationContext());
                    textView.setText(invoice.getArray()[j]);
                    textView.setPadding(20, 0, 0, 0);
                    tableRow.addView(textView);
                }
                tl_invoice.addView(tableRow);
                totalOrder += invoice.invoiceDetail.total;
            }
            tvTotalOrder.setText("Total "+typeOrder+" ="+totalOrder);
        }
    }
}