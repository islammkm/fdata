package com.exemple.fdatabase.firebase;
import android.content.Context;

import com.exemple.fdatabase.Utils.Util;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDownloader {

    private DatabaseReference databaseReference;
    private MyDatabaseHalper dbHelper;

    public FirebaseDownloader(Context context) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        dbHelper = new MyDatabaseHalper(context); // Initialize your SQLite database helper with context
    }

    public void downloadPathsFromFirebase() {
        DatabaseReference pathRef = databaseReference.child(Util.TABLE_PATH);
        pathRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Path> paths = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Path path = snapshot.getValue(Path.class);
                    paths.add(path);
                }
                dbHelper.savePath(paths);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }

    public void downloadFournisseursFromFirebase() {
        DatabaseReference fournisseurRef = databaseReference.child(Util.TABLE_FOURNISSEUR);
        fournisseurRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Fourniseur> fournisseurs = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Fourniseur fournisseur = snapshot.getValue(Fourniseur.class);
                    fournisseurs.add(fournisseur);
                }
                dbHelper.saveFournisseur(fournisseurs);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
    public void downloadLivreurFromFirebase() {
        DatabaseReference livreurRef = databaseReference.child(Util.TABLE_LIVREUR);
        livreurRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Livreur> livreurList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Livreur livreur = snapshot.getValue(Livreur.class);
                    livreurList.add(livreur);
                }
                dbHelper.saveLivreur(livreurList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
    public void downloadVecFromFirebase() {
        DatabaseReference vecRef = databaseReference.child(Util.TABLE_VEC);
        vecRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Vec> vecList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Vec vec = snapshot.getValue(Vec.class);
                    vecList.add(vec);
                }
                dbHelper.saveVec(vecList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
    public void downloadUserFromFirebase() {
        DatabaseReference userRef = databaseReference.child(Util.TABLE_USERS);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    userList.add(user);
                }
                dbHelper.saveUser(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
    public void downloadCmdFromFirebase() {
        DatabaseReference cmdRef = databaseReference.child("cmnds");
        cmdRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Cmd> cmdList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Cmd cmd = snapshot.getValue(Cmd.class);
                    cmdList.add(cmd);
                }
                dbHelper.saveCmd(cmdList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
    public void downloadColieFromFirebase() {
        DatabaseReference colieRef = databaseReference.child(Util.TABLE_COLIE);
        colieRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Colie> colieList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Colie colie = snapshot.getValue(Colie.class);
                    colieList.add(colie);
                }
                dbHelper.saveColie(colieList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }

    public void downloadInfoFromFirebase() {
        DatabaseReference infoRef = databaseReference.child(Util.TABLE_CMND_DETAILS);
        infoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<CMNDDetails> colieList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CMNDDetails colie = snapshot.getValue(CMNDDetails.class);
                    colieList.add(colie);
                }
                dbHelper.saveCMNDDetails(colieList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }


}

