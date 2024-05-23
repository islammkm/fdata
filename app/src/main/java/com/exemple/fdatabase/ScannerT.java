package com.exemple.fdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Livreur;
import com.exemple.fdatabase.models.User;
import com.google.android.material.tabs.TabLayout;

public class ScannerT extends AppCompatActivity {
    FrameLayout frameLayout;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        MyDatabaseHalper db = new MyDatabaseHalper(this);
        String name = intent.getStringExtra("iduser");
        setContentView(R.layout.activity_scanner_t);
        frameLayout = findViewById(R.id.ramelayout);
        tabLayout=findViewById(R.id.tablayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.ramelayout,new FirstFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0:
                        fragment = new FirstFragment();
                        break;
                    case 1:
                        fragment = new SecondFragment();

                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.ramelayout,fragment).addToBackStack(null).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}