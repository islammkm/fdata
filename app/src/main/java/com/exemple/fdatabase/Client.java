package com.exemple.fdatabase;
//
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.exemple.fdatabase.controler.MyAdapterCMND;
//import com.exemple.fdatabase.controler.MyDatabaseHalper;
//import com.exemple.fdatabase.models.Cmd;
//import com.google.android.material.navigation.NavigationView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.fdatabase.controler.MyAdapterCMND;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Cmd;
import com.google.android.material.navigation.NavigationView;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Client extends AppCompatActivity  {
    List<Cmd> listcmnd = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    MyDatabaseHalper db;
//    NavigationView navigationView;
//    ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Intent intent = getIntent();
        Handler handler = new Handler(Looper.getMainLooper());
        int id = intent.getIntExtra("idclient", -1);
        TextView idclientclient = findViewById(R.id.idclientclient);
        idclientclient.setText("ID / "+id);
        db = new MyDatabaseHalper(this);
        listcmnd = db.getCMNDsByUserId(id);
        recyclerView = findViewById(R.id.recycleview1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
         listcmnd.add(null);
        if(listcmnd.get(0) != null) {
            myAdapter = new MyAdapterCMND(listcmnd, Client.this);
            recyclerView.setAdapter(myAdapter);
        }
        ImageView imageView6 = findViewById(R.id.imageView6);

        DrawerLayout drawerLayout2 = findViewById(R.id.drawer_layout);
        Button text222 = findViewById(R.id.text222);
        Button text333 = findViewById(R.id.text333);
        Button text444 = findViewById(R.id.text444);

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text222.setVisibility(View.VISIBLE);
                text333.setVisibility(View.VISIBLE);
                text444.setVisibility(View.VISIBLE);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        text222.setVisibility(View.GONE);
                        text333.setVisibility(View.GONE);
                        text444.setVisibility(View.GONE);
                    }
                }, 5000);
                drawerLayout2.openDrawer(GravityCompat.START);
            }
        });
        text333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Aboutus.class);
                intent.putExtra("idclient",id );
                startActivity(intent);
            }
        });
        text222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Favoritelist.class);
                intent.putExtra("idclient",id );
                startActivity(intent);
            }
        });
        text444.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

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