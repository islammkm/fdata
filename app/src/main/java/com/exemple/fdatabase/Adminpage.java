package com.exemple.fdatabase;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;

import com.exemple.fdatabase.R;
import com.exemple.fdatabase.adminp.Em;
import com.exemple.fdatabase.adminp.Factory;
import com.exemple.fdatabase.adminp.Supplier;
import com.exemple.fdatabase.adminp.Vec2;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.firebase.FirebaseUploader;
import com.exemple.fdatabase.models.User;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.List;

public class Adminpage extends AppCompatActivity
{
    FrameLayout frameLayout;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        frameLayout = findViewById(R.id.tramelayout);
        tabLayout=findViewById(R.id.ttablayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.tramelayout,new Em()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0:
                        fragment = new Em();
                        break;
                    case 1:
                        fragment = new Vec2();
                        break;
                    case 2:
                        fragment = new Supplier();
                        break;
                    case 3:
                        fragment = new Factory();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.tramelayout,fragment).addToBackStack(null).commit();
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

//
//        Button insert = findViewById(R.id.insert);
//        insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<User> users1 = db.getAllUsers2();
//                FirebaseUploader uploader = new FirebaseUploader();
//                uploader.uploadUsers(users1);
//            }
//        });










//
//        String[] names = {
//                "Green Valley Farms",
//                "Golden Grain Suppliers",
//                "Silver Spoon Distributors",
//                "Fresh Harvest Enterprises",
//                "Organic Oasis Suppliers",
//                "Nature's Bounty Distributors",
//                "Sunrise Foods",
//                "Healthy Harvest Suppliers",
//                "Golden Acres Farms",
//                "Prime Produce Distributors",
//                "Farm Fresh Goods",
//                "Sunset Groves",
//                "Delightful Dairy Distributors",
//                "Sunflower Farms",
//                "Country Charm Suppliers"
//        };
//
//        String[] addresses = {
//                "123 Farm Lane, Green Valley, CA, USA",
//                "456 Wheat Avenue, Golden Fields, IL, USA",
//                "789 Silver Spoon Drive, Gourmet City, NY, USA",
//                "987 Fresh Harvest Lane, Orchard Town, FL, USA",
//                "654 Organic Oasis Boulevard, Health Haven, CA, USA",
//                "321 Nature's Bounty Street, Garden Grove, TX, USA",
//                "234 Sunrise Road, Sunnyvale, AZ, USA",
//                "567 Healthy Harvest Avenue, Wellness Village, CA, USA",
//                "890 Golden Acres Lane, Sunshine City, CO, USA",
//                "432 Prime Produce Drive, Quality Quarters, IL, USA",
//                "876 Farm Fresh Boulevard, Rural Retreat, WA, USA",
//                "345 Sunset Lane, Horizon Hills, NV, USA",
//                "678 Dairy Drive, Creamy Corner, MI, USA",
//                "901 Sunflower Street, Petal Park, MN, USA",
//                "543 Country Charm Lane, Rustic Ridge, WI, USA"
//        };
//
//
//// Insert each supplier into the table
//        for (int i = 0; i < names.length; i++) {
//            long id = db.insertFournisseur(names[i], addresses[i]);
//            if (id != -1) {
//                System.out.println("Inserted supplier with ID: " + id);
//            } else {
//                System.out.println("Failed to insert supplier.");
//            }
//        }
//
//
//
//        int userId = 2;
//        int livreurId = 1; // Assuming a default delivery person ID
//        String[] addresses2 = {
//                "123 Elm Street, Cityville, State, Country",
//                "456 Oak Avenue, Townsville, State, Country",
//                "789 Maple Lane, Villageton, State, Country",
//                "987 Pine Drive, Boroughburg, State, Country",
//                "654 Cedar Road, Hamletville, State, Country",
//                "321 Birch Street, Suburbia, State, Country",
//                "234 Spruce Court, Villageville, State, Country",
//                "567 Sycamore Lane, Ruralton, State, Country",
//                "890 Walnut Avenue, Countryside, State, Country",
//                "432 Cherry Circle, Metropolis, State, Country"
//        };
//        String[] states = {"Pending", "Processing", "Shipped", "Delivered", "Cancelled"};
//        int[] coteCMND = {4, 3, 5, 5, 1, 4, 3, 5, 5, 2};
//        int[] nPhones = {123456789, 234567890, 345678901, 456789012, 567890123, 678901234, 789012345, 890123456, 901234567, 123456789};
//
//// Insert each command into the table for user ID 2
//        for (int i = 0; i < addresses2.length; i++) {
//            long id = db.insertCMND(userId, livreurId, addresses2[i], states[i % states.length], coteCMND[i], nPhones[i]);
//            if (id != -1) {
//                System.out.println("Inserted command with ID: " + id);
//            } else {
//                System.out.println("Failed to insert command.");
//            }
//        }


//        int il;



//        String[] addresses3 = {
//                "123 Main Street, Cityville, State, Country",
//                "456 Elm Avenue, Townsville, State, Country",
//                "789 Oak Lane, Villageton, State, Country",
//                "987 Maple Drive, Boroughburg, State, Country",
//                "654 Pine Road, Hamletville, State, Country",
//                "321 Cedar Street, Suburbia, State, Country",
//                "234 Birch Court, Villageville, State, Country",
//                "567 Spruce Lane, Ruralton, State, Country",
//                "890 Sycamore Avenue, Countryside, State, Country",
//                "432 Walnut Circle, Metropolis, State, Country",
//                "876 Chestnut Place, Megatown, State, Country",
//                "345 Ash Boulevard, Capital City, State, Country",
//                "678 Pineapple Avenue, Seaside, State, Country",
//                "901 Cherry Lane, Mountainville, State, Country",
//                "543 Grapevine Street, Lakeside, State, Country"
//        };
//
//        String[] dates = {
//                "2024-05-01", "2024-05-02", "2024-05-03", "2024-05-04", "2024-05-05",
//                "2024-05-06", "2024-05-07", "2024-05-08", "2024-05-09", "2024-05-10",
//                "2024-05-11", "2024-05-12", "2024-05-13", "2024-05-14", "2024-05-15"
//        };
//
//// Insert each example into the table
//        for (int i = 0; i < addresses3.length; i++) {
//            long id = db.insertPath(addresses3[i], dates[i]);
//            if (id != -1) {
//                System.out.println("Inserted row with ID: " + id);
//            } else {
//                System.out.println("Failed to insert row.");
//            }
//        }
//
//
//
//
//// Example data for colis for each command (CMND)
//        int[] cmndIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int[] pathIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Assuming you have path IDs already
//        int[] fournisseurIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Assuming you have fournisseur IDs already
//        String[] dates2 = {
//                "2024-05-01", "2024-05-02", "2024-05-03", "2024-05-04", "2024-05-05",
//                "2024-05-06", "2024-05-07", "2024-05-08", "2024-05-09", "2024-05-10"
//        };
//        String[] types = {"Fragile", "Standard", "Express", "Special", "Standard", "Standard", "Express", "Fragile", "Special", "Standard"};
//        String[] lastPositions = {"Warehouse A", "Warehouse B", "Warehouse C", "Warehouse D", "Warehouse E", "Warehouse F", "Warehouse G", "Warehouse H", "Warehouse I", "Warehouse J"};
//        String[] codes = {"ABC123", "DEF456", "GHI789", "JKL012", "MNO345", "PQR678", "STU901", "VWX234", "YZA567", "BCD890"};
//        int[] coteColie = {5, 4, 3, 5, 4, 5, 3, 4, 5, 4};
//        int[] poids = {2, 3, 1, 4, 2, 3, 1, 4, 2, 3};
//
//// Insert 3 colis for each command
//        for (int cmndId : cmndIds) {
//            for (int i = 0; i < 3; i++) {
//                long id = db.insertColie(cmndId, pathIds[cmndId - 1], fournisseurIds[cmndId - 1], dates2[cmndId - 1], types[i], lastPositions[i], codes[i], coteColie[cmndId - 1], poids[i]);
//                if (id != -1) {
//                    System.out.println("Inserted colis with ID: " + id);
//                } else {
//                    System.out.println("Failed to insert colis.");
//                }
//            }
//        }
//
//


//        long id = db.insertCMNDDetails("Paris", "New York", "example.com", "2024-05-17", "Sample Command", 1);
//         id = db.insertCMNDDetails("Paris", "New York", "example.com", "2024-05-17", "Sample Command", 2);
//         id = db.insertCMNDDetails("Paris", "New York", "example.com", "2024-05-17", "Sample Command", 3);









