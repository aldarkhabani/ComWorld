package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    Button bt_sign ;
    EditText et_Usename,et_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_sign = findViewById(R.id.bt_sign);
        et_Usename = findViewById(R.id.et_Usename);
        et_Password = findViewById(R.id.et_Password);

    }
    public void sign (View v) {
        Intent intent = new Intent(MainActivity.this,mainpage1.class);
        startActivity(intent);
    }


}
