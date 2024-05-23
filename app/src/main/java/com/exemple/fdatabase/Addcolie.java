package com.exemple.fdatabase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Fourniseur;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Addcolie extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private String nameColie;
    Button addcolie2 ;
    int idpic;
    boolean j ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcolie);
        TextView textView23, textView24, textView25, textView26;
        Intent intent = getIntent();
        int idcmnd = intent.getIntExtra("idcmnd", -1);
        String email = intent.getStringExtra("email");
        MyDatabaseHalper db = new MyDatabaseHalper(this);
        Cmd cmnd = db.getCmdById(idcmnd);
        textView23 = findViewById(R.id.textView23);
        textView24 = findViewById(R.id.textView24);
        EditText editTextText11, editTextText12, editTextText13, editTextText15, editTextText16, editTextText14;
        editTextText11 = findViewById(R.id.editTextText11);
//        editTextText12 = findViewById(R.id.editTextText12);
        editTextText13 = findViewById(R.id.editTextText13);
        editTextText15 = findViewById(R.id.editTextText15);
        editTextText16 = findViewById(R.id.editTextText16);
        editTextText14 = findViewById(R.id.editTextText14);
        EditText namepr = findViewById(R.id.editTextText12);
        TextView switchtoname = findViewById(R.id.switchtoname);
        final boolean[] switchid = {false};
        switchtoname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchid[0]){
                    editTextText14.setHint("Supplier id");
                    editTextText14.setInputType(InputType.TYPE_CLASS_NUMBER);

                    switchid[0] = false;
                    switchtoname.setText("use the Name instead of the ID.");


                }else {
                    editTextText14.setHint("Supplier name");
                    editTextText14.setInputType(InputType.TYPE_CLASS_TEXT);
                    switchid[0] = true;
                    switchtoname.setText("use the Id instead of the Name.");
                }
            }
        });
        textView23.setText("ID Order :" + cmnd.getId());
        textView24.setText("Email Client :" + email);

        ImageView addpic = findViewById(R.id.imageView14);
        addcolie2 = findViewById(R.id.addcolie2);
        j = false;
        addpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!namepr.getText().toString().isEmpty()) {
                    nameColie = namepr.getText().toString();
                    dispatchTakePictureIntent();
                    j= true;
                    addpic.setVisibility(View.INVISIBLE);
                }else {
                    Toast.makeText(Addcolie.this, "Add a product name to perform this operation", Toast.LENGTH_SHORT).show();
                }
            }
        });


        addcolie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextText14.getText().toString().isEmpty() &&
                        !editTextText13.getText().toString().isEmpty() &&
                        !editTextText11.getText().toString().isEmpty() &&
                        !editTextText15.getText().toString().isEmpty() &&
                        !editTextText16.getText().toString().isEmpty()) {
                    if(j) {
                        Fourniseur fourn = new Fourniseur();
                        boolean r =true;

                        if(switchid[0]){
                            r=false;
                        }else {
                            fourn = db.getFournisseurById(Integer.parseInt(editTextText14.getText().toString()));
                        }
                            if(fourn != null && r) {
                            long id = db.insertColie(idcmnd, idpic, Integer.parseInt(editTextText14.getText().toString()), "No update",
                                    editTextText13.getText().toString(), fourn.getAdresse(), editTextText11.getText().toString(),
                                    Integer.parseInt(editTextText15.getText().toString()), Integer.parseInt(editTextText16.getText().toString()));
                            Intent intent = new Intent(getApplicationContext(), Addcolie.class);
                            intent.putExtra("idcmnd", idcmnd);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(Addcolie.this, "Couldn't find supplier , use ID ", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Addcolie.this, "Add pic", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Addcolie.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button finish2 = findViewById(R.id.finish2);
        finish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCMND.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.setType("image/*");
        startActivityForResult(pickPhoto , REQUEST_IMAGE_PICK);
    }

    // الطريقة التي ستستلم نتيجة الصورة الملتقطة أو المحددة
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                MyDatabaseHalper db = new MyDatabaseHalper(Addcolie.this);
                long idpic1 = db.insertPic(nameColie,byteArray);
                idpic = (int) idpic1;
                Toast.makeText(this, "Image uploaded successfully / ID : "+idpic, Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}