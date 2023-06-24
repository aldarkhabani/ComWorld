package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class mainpage1 extends AppCompatActivity {
    Button button2,button3,button22;
    EditText editTextText,editTextText2,editTextText3,editTextText4,editTextNumber,editTextNumber2,editTextText33;
    TextView textView11,textView2,textView5,textView6,textView,textView9,textView8,textView7,textView66;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage1);
        button2 = findViewById(R.id.button2);
        button22 = findViewById(R.id.button22);
        button3= findViewById(R.id.button3);
        editTextText = findViewById(R.id.editTextText);
        editTextText2= findViewById(R.id.editTextText2);
        editTextText3 = findViewById(R.id.editTextText3);
        editTextText4 = findViewById(R.id.editTextText4);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber2= findViewById(R.id.editTextNumber2);
        editTextText33 = findViewById(R.id.editTextText33);
        textView11 = findViewById(R.id.textView11);
        textView2 = findViewById(R.id.textView2);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView = findViewById(R.id.textView);
        textView9 = findViewById(R.id.textView9);
        textView8 = findViewById(R.id.textView8);
        textView7 = findViewById(R.id.textView7);
        textView66 = findViewById(R.id.textView66);
    }
    public void gogo (View gogo) {
        Intent intent = new Intent(mainpage1.this,report.class);
        startActivity(intent);
    }
}