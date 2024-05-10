package com.exemple.fdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;
import com.exemple.fdatabase.models.Livreur;

public class ColieInf extends AppCompatActivity {
    Colie colie;
    MyDatabaseHalper db;
    Livreur livreur;
    Fourniseur fourn = new Fourniseur();
    Cmd cmnd;
    ImageView star;
    Button followbtn ;
    TextView colieinf ,cmndinftest,last_position,datec,cmndid,newposition,fournisseur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colie_inf);
        star = findViewById(R.id.favstar);
        Glide.with(this).load(R.drawable.star).into(star);

        db = new MyDatabaseHalper(this);
        int id_colie = getIntent().getIntExtra("id_colie", 0);

        colie = db.getColieById2(id_colie);
        fourn = db.getFournisseurById(colie.getFournisseurId());

        colieinf = findViewById(R.id.colieinf1);
        cmndid = findViewById(R.id.CmndId1);
        cmndinftest = findViewById(R.id.cmndinftest);
        last_position = findViewById(R.id.last_position1);
        datec = findViewById(R.id.DateC1);
        fournisseur = findViewById(R.id.fourni1);
        colieinf.setText("Colie N° " + colie.getCode());
        cmndid.setText("ID cmnd" + colie.getCmndId());
        datec.setText("Date " + colie.getDate());
        if(last_position==null){
            if (fourn != null) last_position.setText("Dernière position " + fourn.getAdresse());
        }else {
            last_position.setText("Dernière position " + colie.getLast_position());
            cmnd = db.getCmndByCmndId(colie.getCmndId());
            livreur = db.getLivreurById(cmnd.getLivreurId());
            cmndinftest.setText("livreur : "+livreur.getUsername());
        }
        if (fourn != null) {
            fournisseur.setText("fournisseur " + fourn.getNom());
        }else{
            fournisseur.setText("Le fournisseur n'est pas enregistré dans une base de données");
        }
        followbtn = findViewById(R.id.followbtn);
        followbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), Followpage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
