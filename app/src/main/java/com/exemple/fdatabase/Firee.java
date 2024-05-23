package com.exemple.fdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.User;

import java.util.List;

public class Firee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firee);
        MyDatabaseHalper dbHelper = new MyDatabaseHalper(this);
        List<User> users = dbHelper.getAllUsers();

    }
}