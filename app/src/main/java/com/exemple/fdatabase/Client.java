package com.exemple.fdatabase;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.exemple.fdatabase.controler.MyAdapterCMND;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Cmd;

import java.util.ArrayList;
import java.util.List;

public class Client extends AppCompatActivity {
    List<Cmd> listcmnd = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    int id = 4;
    MyDatabaseHalper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        db = new MyDatabaseHalper(this);
        listcmnd = db.getCMNDsByUserId(id);
        recyclerView = findViewById(R.id.recycleview1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapterCMND(listcmnd,Client.this);
        recyclerView.setAdapter(myAdapter);
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Client.this);
        View myview = getLayoutInflater().inflate(R.layout.alertbox, null);
        builder.setView(myview);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        myview.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finishAndRemoveTask();
            }
        });
        myview.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}