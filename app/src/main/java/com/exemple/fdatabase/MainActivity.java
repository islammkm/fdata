package com.exemple.fdatabase;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.firebase.FirebaseDownloader;
import com.exemple.fdatabase.firebase.FirebaseUploader;
import com.exemple.fdatabase.models.CMNDDetails;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;
import com.exemple.fdatabase.models.Livreur;
import com.exemple.fdatabase.models.Path;
import com.exemple.fdatabase.models.Pic;
import com.exemple.fdatabase.models.User;
import com.exemple.fdatabase.models.Vec;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private MyDatabaseHalper db;
    private Button mainsignup , mainlogin;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDatabaseHalper(this);

        FirebaseUploader firebaseUploader = new FirebaseUploader();
        FirebaseDownloader firebaseDownloader = new FirebaseDownloader(this);
        List<User> user = db.getAllUsers();
        List<Cmd> cmnds = db.getAllCMNDs();
        List<Path> paths = db.getAllPaths();
        List<Colie> colies = db.getAllColies();
        List<Fourniseur> fourns = db.getAllFournisseurs();
        List<CMNDDetails> infos = db.getAllCommandDetails();
        List<Livreur> livreurs = db.getAllLivreurs();
        List<Vec> vecs = db.getAllVECs();
//        firebaseUploader.uploadCmndsToFirebase(cmnds);
//        firebaseUploader.uploadFournisseurToFirebase(fourns);
//        firebaseUploader.uploadColieToFirebase(colies);
//        firebaseUploader.uploadInfoToFirebase(infos);
        if(user.isEmpty()){
            db.insertUser("admin","admin","admin","admin@alger1.com");
//            firebaseUploader.uploadCmndsToFirebase(cmnds);
//            firebaseUploader.uploadFournisseurToFirebase(fourns);
//            firebaseUploader.uploadColieToFirebase(colies);
//            firebaseUploader.uploadInfoToFirebase(infos);
//            firebaseUploader.uploadUsersToFirebase(db.getAllUsers());
//            firebaseUploader.uploadLivreurToFirebase(livreurs);
//            firebaseUploader.uploadPathsToFirebase(paths);
//            firebaseUploader.uploadVecToFirebase(vecs);
//            firebaseDownloader.downloadUserFromFirebase();
//            firebaseDownloader.downloadPathsFromFirebase();
//            firebaseDownloader.downloadCmdFromFirebase();
//            firebaseDownloader.downloadColieFromFirebase();
//            firebaseDownloader.downloadFournisseursFromFirebase();
//            firebaseDownloader.downloadLivreurFromFirebase();
//            firebaseDownloader.downloadVecFromFirebase();
//            firebaseDownloader.downloadInfoFromFirebase();
        }

//        List<User> users = db.getAllUsers();
//        uploader.uploadUsersToFirebase(users);

//        List<Cmd> colies = db.getAllCMNDs();
//        uploader.uploadCmndsToFirebase(colies);

//        List<Colie> colies = db.getAllColies();
//        uploader.uploadColieToFirebase(colies);

//        List<Vec> vecs = db.getAllVECs();
//        uploader.uploadVecToFirebase(vecs);
//        List<Livreur> infos = db.getAllLivreurs();
//        uploader.uploadLivreurToFirebase(infos);
//        List<CMNDDetails> infos = db.getAllCommandDetails();
//        uploader.uploadInfoToFirebase(infos);
//        List<Path> paths = db.getAllPaths();
//        uploader.uploadPathsToFirebase(paths);










//        db.insertPath("fr1","fr");
//        Intent intent = new Intent(this, Adminpage.class);
//        startActivity(intent);
//        db.insertPath("test","liv");
//        db.insertPath("test2","em");

//        String str = db.getColiesByCMNDId(1).get(1).getLast_position();
////        db.insertUser("false","islam7","islam7","m");
//        long id = db.insertCMND(9,2,"Ain Taya","liv1",5,5);
////        long id2 = db.insertCMND(9,2,"Ain Taya","liv3",5,5);
//        int idcmnd = (int) id;
////        int idcmnd2 = (int) id2;
//        db.insertCMNDDetails("Ain taya",
//                "Usa",
//                "https://www.apple.com",
//                "18-05-2024",
//                "Apple",
//                idcmnd);
////        db.insertCMNDDetails("Ain taya",
////                "Chine",
////                "https://www.alibaba.com",
////                "15-05-2024",
////                "Alibaba",
////                idcmnd2);
//        FirebaseUploader uploader = new FirebaseUploader();
//        List<Cmd> cmnd = db.getAllCommands();
//        uploader.uploadCmnd(cmnd);
////        List<Colie> colie = new ArrayList<>();;
////        colie.add(db.getColieById(1));
////        uploader.uploadColie(colie);
//        List<Cmd> cmnd = db.getAllCommands();
//        uploader.uploadCmnd(cmnd);
//        uploader.uploadUsers(cmnd);
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");
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
