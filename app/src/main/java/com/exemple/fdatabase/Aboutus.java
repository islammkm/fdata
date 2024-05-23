package com.exemple.fdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.exemple.fdatabase.controler.MyDatabaseHalper;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        MyDatabaseHalper db = new MyDatabaseHalper(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("idclient", -1);



        ImageView image88 = findViewById(R.id.image88);
        image88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Client.class);
                intent.putExtra("idclient",id);
                startActivity(intent);
                finish();
            }
        });
    }
}