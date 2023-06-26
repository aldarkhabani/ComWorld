package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Best_Seller extends AppCompatActivity {

    Material[] listMaterial ;
    int[] listCountSale ;

    TableLayout tl_BestSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_saler);
        tl_BestSeller = findViewById(R.id.tl_BestSeller);
        if(getIntent().getIntArrayExtra("CountSale") != null) {
            listCountSale = getIntent().getIntArrayExtra("CountSale");
        }
        if(getIntent().getSerializableExtra("Material") != null) {
            listMaterial = (Material[]) getIntent().getSerializableExtra("Material");
        }
        if(listMaterial != null && listCountSale != null){
            TableRow tableRow;
            TextView textView;
            int i = 0;
            for (Material material : listMaterial) {
                tableRow = new TableRow(getApplicationContext());
                for (int j = 0; j < 3; j++) {
                    textView = new TextView(getApplicationContext());
                    textView.setText(material.getArray()[j]);
                    textView.setPadding(20, 0, 0, 0);
                    tableRow.addView(textView);
                }
                textView = new TextView(getApplicationContext());
                textView.setText(String.valueOf(listCountSale[i]));
                textView.setPadding(20, 0, 0, 0);
                tableRow.addView(textView);
                tl_BestSeller.addView(tableRow);
                i++;
            }
        }
    }
}