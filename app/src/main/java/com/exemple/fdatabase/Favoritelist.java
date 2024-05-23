package com.exemple.fdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.exemple.fdatabase.controler.MyAdapterCMND;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;

import java.util.ArrayList;
import java.util.List;

public class Favoritelist extends AppCompatActivity {
    List<Cmd> listcmnd = new ArrayList<>();
    List<Colie> listcolie = new ArrayList<>();
    List<Colie> listcolie2 = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritelist);
        MyDatabaseHalper db = new MyDatabaseHalper(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("idclient", -1);
        if(id != -1){
            listcmnd = db.getCMNDsByUserId(id);
            for (int i = 0; i < listcmnd.size(); i++){
                Cmd cmnd= listcmnd.get(i);
                listcolie = db.getAllColiesWithTypeAndCmndId("fav",cmnd.getId());
                for (int j = 0; j < listcolie.size(); j++){
                    Colie colie2 = listcolie.get(j);
                    listcolie2.add(colie2);
                }
            }
            listcolie2.add(null);

            if(listcolie2.get(0) != null ){
                recyclerView = findViewById(R.id.recycleview100);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                myAdapter = new MyAdapterCMND(listcmnd,Favoritelist.this);
                recyclerView.setAdapter(myAdapter);
            }else{}
        }else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
        ImageView imageView999 = findViewById(R.id.imageView999);
        imageView999.setOnClickListener(new View.OnClickListener() {
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