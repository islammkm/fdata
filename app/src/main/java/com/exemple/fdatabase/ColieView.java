package com.exemple.fdatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.fdatabase.controler.MyAdapterCMND;
import com.exemple.fdatabase.controler.MyAdapterColie;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Colie;

import java.util.ArrayList;
import java.util.List;

public class ColieView extends AppCompatActivity {
    List<Colie> listcolie = new ArrayList<>();
    MyDatabaseHalper db;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter2;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colie_view);
        Intent intent = getIntent();
        db = new MyDatabaseHalper(this);
        List<Colie> listcolie = new ArrayList<>();
        if(intent != null) {
            int id_cmnd = intent.getIntExtra("id_cmnd", -1);
            if (id_cmnd != -1) {
                listcolie = db.getColiesByCMNDId(id_cmnd);
                recyclerView = findViewById(R.id.recycleview2);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                myAdapter2 = new MyAdapterColie(listcolie,ColieView.this);
                recyclerView.setAdapter(myAdapter2);

            } else {
                Toast.makeText(this, "ID_CMND not found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
