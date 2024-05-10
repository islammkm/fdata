package com.exemple.fdatabase;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.fdatabase.controler.MyAdapterColie;
import com.exemple.fdatabase.controler.MyAdapterColie2;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;

public class Select extends AppCompatActivity {
    List<Colie> listclient = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String selectedItem;

    String addressString2 ;
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest = new LocationRequest();
    MyDatabaseHalper db = new MyDatabaseHalper(this);
    Button scanbtn;
    Colie colie = new Colie();
    Fourniseur fourn = new Fourniseur();
    String ScanR = "حي صنوبر";
    TextView colieinf ,last_position,datec,cmndid,newposition,fournisseur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        recyclerView = findViewById(R.id.recycleview40);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyDatabaseHalper db = new MyDatabaseHalper(this);
        listclient.add(db.getColieByCode("JKL012"));
        myAdapter = new MyAdapterColie2(listclient,this);
        recyclerView.setAdapter(myAdapter);
        Button scanbtn = findViewById(R.id.scanbutton2);

        scanbtn.setOnClickListener(v ->
        {
            ScanOptions options = new ScanOptions();
            options.setPrompt("Volum up to flash on");
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            options.setCaptureActivity(CapturAct.class);
            barLaucher.launch(options);
        });
    }
    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            colie = db.getColieByCode(result.getContents());
            listclient.add(colie);
            myAdapter.notifyDataSetChanged();
        }

    });
}
