package com.exemple.fdatabase;


import static android.graphics.Color.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Path;
import com.exemple.fdatabase.models.User;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;


public class Signup extends AppCompatActivity {
    MyDatabaseHalper db;
    CheckBox checkBox;
    String idf;
    Path path = null;
    TextInputEditText textInputEditTextFullname, textInputEditTextEmail, textInputEditTextUsername, textInputEditTextPassword;
    Button button4;
    ProgressBar progressBar;
    TextView textViewLogin,visibcode,txxt;
    boolean agent ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        db = new MyDatabaseHalper(this);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);
        checkBox = findViewById(R.id.checkBox2);
        textViewLogin = findViewById(R.id.logintext);
        button4 = findViewById(R.id.signupbotton);
        progressBar = findViewById(R.id.progress);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        TextView usereror = findViewById(R.id.textView17);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    agent = true ;
                }else {
                    agent = false ;
                }
                progressBar.setVisibility(View.VISIBLE);
                String username = textInputEditTextUsername.getText().toString();
                String password = textInputEditTextPassword.getText().toString();
                String email = textInputEditTextEmail.getText().toString();
                String fullname ;
                if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
                    User user = db.getUserByUsername(username);
                    if (user != null) {
                        usereror.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    } else {
                        if (agent) {
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(Signup.this);
                        View myview2 = getLayoutInflater().inflate(R.layout.identification, null);
                        builder2.setView(myview2);
                        final AlertDialog dialog2 = builder2.create();
                        dialog2.setCancelable(false);
                        dialog2.show();
                        EditText edit = myview2.findViewById(R.id.editTextTextPassword);
                        visibcode = myview2.findViewById(R.id.visibcode);

                        myview2.findViewById(R.id.check).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String emailString = edit.getText().toString();
                                path = db.getPathByAddress2(emailString);
                                if (path != null) {
                                    if (path.getDate().toString().equals("fr")) {
                                        long id = db.insertUser("fr", username, password, email);
                                        db.deletePath(path.getId());
                                        Toast.makeText(Signup.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                        db.close();
                                        dialog2.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (path.getDate().toString().equals("em")) {
                                        long id = db.insertUser("true", username, password, email);
                                        db.deletePath(path.getId());
                                        Toast.makeText(Signup.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                        dialog2.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (path.getDate().toString().equals("liv")) {
                                        long id = db.insertUser("true", username, password, email);
                                        db.deletePath(path.getId());
                                        db.insertLivreurs(username,password);
                                        Toast.makeText(Signup.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                        dialog2.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(Signup.this, "The account has not been activated yet", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    visibcode.setVisibility(View.VISIBLE);
                                    Toast.makeText(Signup.this, "The code is incorrect.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    } else {
                        fullname = "false";
                        long id = db.insertUser(fullname, username, password, email);
                        db.close();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }
                }
                }else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
