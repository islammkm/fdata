package com.exemple.fdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Livreur;
import com.exemple.fdatabase.models.User;

public class AddCMND extends AppCompatActivity {
    int userid; // Make sure to assign a valid user ID before use

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cmnd);

        // Initialize EditText fields
        EditText liv, adres, price, nphone, cityclient, cityf, site, name, date,username;
        username = findViewById(R.id.editTextText);
        liv = findViewById(R.id.editTextText2);
        adres = findViewById(R.id.editTextText3);
        price = findViewById(R.id.editTextText4);
        nphone = findViewById(R.id.editTextText5);
        cityclient = findViewById(R.id.editTextText6);
        cityf = findViewById(R.id.editTextText7);
        site = findViewById(R.id.editTextText8);
        date = findViewById(R.id.editTextText9);
        name = findViewById(R.id.editTextText10);

        // Instantiate database helper class
        MyDatabaseHalper db = new MyDatabaseHalper(this);

        // Example of inserting a new CMND entry
        // Make sure to assign a valid user ID before use
        Button btn = findViewById(R.id.nextclmnd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = db.getUserByUsername(username.getText().toString());
                if(user != null ) {
                    int iduser = user.getId();
                    Livreur liv2 = db.getLivreurByUsername(liv.getText().toString());
                    if (liv2 != null) {
                        int idlivreur = liv2.getId();
                        if(!adres.getText().toString().isEmpty() &&
                                !price.getText().toString().isEmpty() &&
                                !nphone.getText().toString().isEmpty() &&
                                !cityclient.getText().toString().isEmpty() &&
                                !cityf.getText().toString().isEmpty() &&
                                !site.getText().toString().isEmpty() &&
                                !date.getText().toString().isEmpty() &&
                                !name.getText().toString().isEmpty()){
                            String adrescmnd = adres.getText().toString();
                            int priiiice = Integer.parseInt(price.getText().toString());
                            int nphonee = Integer.parseInt(nphone.getText().toString());
                            long d = db.insertCMND(iduser, idlivreur, adrescmnd, "liv1", priiiice, nphonee);
                            int idcmnd = (int) d;
                            db.insertCMNDDetails(cityclient.getText().toString(),
                                    cityf.getText().toString(),
                                    site.getText().toString(),
                                    date.getText().toString(),
                                    name.getText().toString(),
                                    idcmnd);
                            Intent intent = new Intent(getApplicationContext(), Addcolie.class);
                            intent.putExtra("idcmnd", idcmnd);
                            intent.putExtra("email", user.getEmail());
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(AddCMND.this, "This delivery person is not registered in the system", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(AddCMND.this, "This user does not exist in the application database", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Uncomment this section if you intend to insert CMND details
        /*
        if (idcmnd != -1) { // Check if CMND insertion was successful
            db.insertCMNDDetails(cityclient.getText().toString(), cityf.getText().toString(), site.getText().toString(), date.getText().toString(), name.getText().toString(), (int) idcmnd);
        } else {
            // Handle insertion failure
        }
        */
    }
}
