package com.exemple.fdatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.fdatabase.controler.MyAdapterCMND;
import com.exemple.fdatabase.controler.MyAdapterColie;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;

import java.util.ArrayList;
import java.util.List;

public class ColieView extends AppCompatActivity {
    List<Colie> listcolie = new ArrayList<>();
    MyDatabaseHalper db;
    ImageView imageView8;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter2;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colie_view);
        Intent intent = getIntent();
        int id_cmnd = intent.getIntExtra("id_cmnd", -1);
        db = new MyDatabaseHalper(this);
        List<Colie> listcolie = new ArrayList<>();
        if(intent != null) {
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
        imageView8 = findViewById(R.id.imageView8);
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Client.class);
                Cmd cmnd = db.getCmndById(id_cmnd);
                intent.putExtra("idclient",cmnd.getUserid());
                startActivity(intent);
                finish();
            }
        });
    }
}
