package com.exemple.fdatabase;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.exemple.fdatabase.controler.MyDatabaseHalper;


public class MainActivity extends AppCompatActivity {
    private MyDatabaseHalper db;
    private Button mainsignup , mainlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDatabaseHalper(this);
        mainlogin = findViewById(R.id.mainbuttonlogin1);
        mainsignup= findViewById(R.id.mainbuttonsign1);
        mainlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });
        mainsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
