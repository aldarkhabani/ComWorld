package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class report4 extends AppCompatActivity {


    Agent[] listAgent ;
    int[] listCountOrder ;

    TableLayout tl_agent;

    String typeAgent = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report4);
        tl_agent = findViewById(R.id.tl_agent);
        if(getIntent().getIntArrayExtra("CountOrder") != null) {
            listCountOrder = getIntent().getIntArrayExtra("CountOrder");
        }
        if(getIntent().getSerializableExtra("Agent") != null) {
            listAgent = (Agent[]) getIntent().getSerializableExtra("Agent");
        }
        if(listAgent != null && listCountOrder != null){
            TableRow tableRow;
            TextView textView;
            int i = 0;
            for (Agent agent : listAgent) {
                tableRow = new TableRow(getApplicationContext());
                for (int j = 0; j < 3; j++) {
                    textView = new TextView(getApplicationContext());
                    textView.setText(agent.getArray()[j]);
                    textView.setPadding(20, 0, 0, 0);
                    tableRow.addView(textView);
                }
                textView = new TextView(getApplicationContext());
                textView.setText(String.valueOf(listCountOrder[i]));
                textView.setPadding(20, 0, 0, 0);
                tableRow.addView(textView);
                tl_agent.addView(tableRow);
                i++;
            }
        }
    }
}