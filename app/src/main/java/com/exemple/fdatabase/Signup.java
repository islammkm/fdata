package com.exemple.fdatabase;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.User;
import com.google.android.material.textfield.TextInputEditText;


public class Signup extends AppCompatActivity {
    MyDatabaseHalper db;
    CheckBox checkBox;
    TextInputEditText textInputEditTextFullname, textInputEditTextEmail, textInputEditTextUsername, textInputEditTextPassword;
    Button button4;
    ProgressBar progressBar;
    TextView textViewLogin;
    boolean agent ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        db = new MyDatabaseHalper(this);
        agent = false;
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

        checkBox.setOnClickListener(v -> {
            agent = true ;
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String username = textInputEditTextUsername.getText().toString();
                String password = textInputEditTextPassword.getText().toString();
                String email = textInputEditTextEmail.getText().toString();
                String fullname ;

                if(agent){
                    fullname = "true";
                }else{
                    fullname = "false";
                }
                if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {

                    long id = db.insertUser(fullname, username, password, email);
                    db.close();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                } else {
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
