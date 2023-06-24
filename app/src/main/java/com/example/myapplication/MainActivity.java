package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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


        if (et_Usename.getText().toString().equals("admin") && et_Password.getText().toString().equals("admin")) {
            startActivity(intent);
        }else{

            new AlertDialog.Builder(this)
                    .setTitle("Incorrect info")
                    .setMessage("User name or password is incorrect?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

}
