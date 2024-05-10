package com.exemple.fdatabase;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.exemple.fdatabase.Utils.Util;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.User;

import com.google.android.material.textfield.TextInputEditText;


public class Login extends AppCompatActivity {
    MyDatabaseHalper db;
    TextInputEditText textInputEditTextUsername,textInputEditTextPassword;
    Button button7;
    ProgressBar progressBar;
    TextView textViewSignUp;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        textInputEditTextUsername=findViewById(R.id.username);
        textInputEditTextPassword=findViewById(R.id.password);
        button7=findViewById(R.id.button7);
        textViewSignUp=findViewById(R.id.signUpText);
        progressBar=findViewById(R.id.progress);
        db = new MyDatabaseHalper(this);


        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username, password;
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                if (!username.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    User user = db.getUserByUsername(username);
                    if (user == null) {
                        Toast.makeText(getApplicationContext(), "No account exists ", Toast.LENGTH_SHORT).show();
                    } else if(username.equals(Util.ADMIN_USER)  && password.equals(Util.ADMIN_PASSWORD)){
                        Intent intent = new Intent(getApplicationContext(), Adminpage.class);
                        startActivity(intent);
                        finish();
                        }else {
                        // Check if the entered password matches the stored password
                        if (password.equals(((User) user).getPassword())) {
                            Toast.makeText(getApplicationContext(), "Password is correct", Toast.LENGTH_SHORT).show();
                            if(user.getFullName().equals("false")){
                                Intent intent = new Intent(getApplicationContext(), Client.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Intent intent = new Intent(getApplicationContext(), ScannerT.class);
                                intent.putExtra("username", username);
                                intent.putExtra("id_livreur",user.getId());
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}