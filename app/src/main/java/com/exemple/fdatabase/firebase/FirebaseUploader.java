package com.exemple.fdatabase.firebase;

import com.exemple.fdatabase.Utils.Util;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.CMNDDetails;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;
import com.exemple.fdatabase.models.Livreur;
import com.exemple.fdatabase.models.Path;
import com.exemple.fdatabase.models.Pic;
import com.exemple.fdatabase.models.User;
import com.exemple.fdatabase.models.Vec;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FirebaseUploader {

    private DatabaseReference databaseReference;

    public FirebaseUploader() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

        public void uploadPathsToFirebase(List<Path> paths) {
        DatabaseReference cmndDetailsRef = databaseReference.child(Util.TABLE_PATH);
        for (Path path : paths) {
            String key = cmndDetailsRef.push().getKey();
            cmndDetailsRef.child(key).setValue(path)
                    .addOnSuccessListener(aVoid -> {
                        // Data uploaded successfully
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                    });
        }
    }
    public void uploadFournisseurToFirebase(List<Fourniseur> fourniseurs) {
        DatabaseReference cmndDetailsRef = databaseReference.child(Util.TABLE_FOURNISSEUR);
        for (Fourniseur path : fourniseurs) {
            String key = cmndDetailsRef.push().getKey();
            cmndDetailsRef.child(key).setValue(path)
                    .addOnSuccessListener(aVoid -> {
                        // Data uploaded successfully
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                    });
        }
    }
    public void uploadInfoToFirebase(List<CMNDDetails> fourniseurs) {
        DatabaseReference cmndDetailsRef = databaseReference.child(Util.TABLE_CMND_DETAILS);
        for (CMNDDetails path : fourniseurs) {
            String key = cmndDetailsRef.push().getKey();
            cmndDetailsRef.child(key).setValue(path)
                    .addOnSuccessListener(aVoid -> {
                        // Data uploaded successfully
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                    });
        }
    }
    public void uploadLivreurToFirebase(List<Livreur> fourniseurs) {
        DatabaseReference cmndDetailsRef = databaseReference.child(Util.TABLE_LIVREUR);
        for (Livreur path : fourniseurs) {
            String key = cmndDetailsRef.push().getKey();
            cmndDetailsRef.child(key).setValue(path)
                    .addOnSuccessListener(aVoid -> {
                        // Data uploaded successfully
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                    });
        }
    }
    public void uploadVecToFirebase(List<Vec> fourniseurs) {
        DatabaseReference cmndDetailsRef = databaseReference.child(Util.TABLE_VEC);
        for (Vec path : fourniseurs) {
            String key = cmndDetailsRef.push().getKey();
            cmndDetailsRef.child(key).setValue(path)
                    .addOnSuccessListener(aVoid -> {
                        // Data uploaded successfully
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                    });
        }
    }
    public void uploadColieToFirebase(List<Colie> fourniseurs) {
        DatabaseReference cmndDetailsRef = databaseReference.child(Util.TABLE_COLIE);
        for (Colie path : fourniseurs) {
            String key = cmndDetailsRef.push().getKey();
            cmndDetailsRef.child(key).setValue(path)
                    .addOnSuccessListener(aVoid -> {
                        // Data uploaded successfully
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                    });
        }
    }
    public void uploadCmndsToFirebase(List<Cmd> fourniseurs) {
        DatabaseReference cmndDetailsRef = databaseReference.child("cmnds");
        for (Cmd path : fourniseurs) {
            String key = cmndDetailsRef.push().getKey();
            cmndDetailsRef.child(key).setValue(path)
                    .addOnSuccessListener(aVoid -> {
                        // Data uploaded successfully
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                    });
        }
    }
    public void uploadUsersToFirebase(List<User> fourniseurs) {
        DatabaseReference cmndDetailsRef = databaseReference.child(Util.TABLE_USERS);
        for (User path : fourniseurs) {
            String key = cmndDetailsRef.push().getKey();
            cmndDetailsRef.child(key).setValue(path)
                    .addOnSuccessListener(aVoid -> {
                        // Data uploaded successfully
                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                    });
        }
    }




}
//    public void uploadPicToFirebase(List<Pic> pics) {
//        DatabaseReference picRef = FirebaseDatabase.getInstance().getReference().child(Util.TABLE_PIC);
//
//        for (Pic pic : pics) {
//            String key = picRef.push().getKey();
//            picRef.child(key).setValue(pic)
//                    .addOnSuccessListener(aVoid -> {
//                        // Data uploaded successfully
//                    })
//                    .addOnFailureListener(e -> {
//                        // Handle the error
//                    });
//        }
//    }