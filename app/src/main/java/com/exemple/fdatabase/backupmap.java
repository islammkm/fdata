package com.exemple.fdatabase;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;
import com.exemple.fdatabase.models.Livreur;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//public class backupmap {
//}
//
//package com.exemple.fdatabase;
//
//        import androidx.annotation.NonNull;
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.content.Intent;
//        import android.content.pm.PackageManager;
//        import android.location.Address;
//        import android.location.Geocoder;
//        import android.location.Location;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.ImageView;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//        import com.bumptech.glide.Glide;
//        import com.exemple.fdatabase.controler.MyDatabaseHalper;
//        import com.exemple.fdatabase.models.Cmd;
//        import com.exemple.fdatabase.models.Colie;
//        import com.exemple.fdatabase.models.Fourniseur;
//        import com.exemple.fdatabase.models.Livreur;
//        import com.google.android.gms.location.LocationCallback;
//        import com.google.android.gms.location.LocationRequest;
//        import com.google.android.gms.location.LocationResult;
//        import com.google.android.gms.maps.CameraUpdateFactory;
//        import com.google.android.gms.maps.GoogleMap;
//        import com.google.android.gms.maps.OnMapReadyCallback;
//        import com.google.android.gms.maps.model.LatLng;
//        import com.google.android.gms.maps.model.MarkerOptions;
//
//        import java.io.IOException;
//        import java.util.List;
//        import java.util.Locale;
//
//        import com.google.android.gms.location.FusedLocationProviderClient;
//        import com.google.android.gms.location.LocationServices;
//        import com.google.android.gms.maps.SupportMapFragment;

//public class ColieInf extends AppCompatActivity implements OnMapReadyCallback {
//    Colie colie;
//    MyDatabaseHalper db;
//    Livreur livreur;
//    Fourniseur fourn = new Fourniseur();
//    Cmd cmnd;
//    ImageView star;
//    Button followbtn ;
//    TextView colieinf ,cmndinftest,last_position,datec,cmndid,newposition,fournisseur;
//    private GoogleMap gMap;
//    private FusedLocationProviderClient fusedLocationClient;
//    private LocationRequest locationRequest;
//    private LocationCallback locationCallback;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_colie_inf);
//        star = findViewById(R.id.favstar);
//        Glide.with(this).load(R.drawable.star).into(star);
//
//        db = new MyDatabaseHalper(this);
//        int id_colie = getIntent().getIntExtra("id_colie", 0);
//
//        colie = db.getColieById2(id_colie);
//        fourn = db.getFournisseurById(colie.getFournisseurId());
//
//        colieinf = findViewById(R.id.colieinf1);
//        cmndid = findViewById(R.id.CmndId1);
//        cmndinftest = findViewById(R.id.cmndinftest);
//        last_position = findViewById(R.id.last_position1);
//        datec = findViewById(R.id.DateC1);
//        fournisseur = findViewById(R.id.fourni1);
//        colieinf.setText("Colie N° " + colie.getCode());
//        cmndid.setText("ID cmnd" + colie.getCmndId());
//        datec.setText("Date " + colie.getDate());
//        if(last_position==null){
//            if (fourn != null) last_position.setText(fourn.getAdresse());
//        }else {
//            last_position.setText("Dernière position " + colie.getLast_position());
//            cmnd = db.getCmndByCmndId(colie.getCmndId());
//            livreur = db.getLivreurById(cmnd.getLivreurId());
//            cmndinftest.setText(livreur.getUsername());
//        }
//        if (fourn != null) {
//            fournisseur.setText( fourn.getNom());
//        }else{
//            fournisseur.setText("Le fournisseur n'est pas enregistré dans une base de données");
//        }
//        followbtn = findViewById(R.id.followbtn);
//        followbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getApplicationContext(), Testmap.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
//        mapFragment.getMapAsync(this);
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        createLocationRequest();
//        createLocationCallback();
//        ImageView imageView4 = findViewById(R.id.imageView4);
//        imageView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getApplicationContext(), Testmap.class);
//                startActivity(intent);
//            }
//        });
//        TextView textview22 = findViewById(R.id.textview22);
//        if(!db.getCmndByCmndId(colie.getCmndId()).getEtat().equals("liv")){
//            textview22.setVisibility(View.INVISIBLE);
//            followbtn.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        MyDatabaseHalper db = new MyDatabaseHalper(ColieInf.this);
//        Colie colie = db.getColieById(4);
//        gMap = googleMap;
////        getCurrentLocation();
//        String hhhh = "1600 Amphitheatre Parkway, Mountain View, CA";
//        moveMarkerToAddress(colie.getLast_position());
//    }
//
//    private void createLocationRequest() {
//        locationRequest = LocationRequest.create();
//        locationRequest.setInterval(10000); // Update location every 10 seconds
//        locationRequest.setFastestInterval(5000); // The fastest update interval, in milliseconds
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // Use high accuracy
//    }
//
//    private void createLocationCallback() {
//        locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                if (locationResult == null) {
//                    return;
//                }
//                for (Location location : locationResult.getLocations()) {
//                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                    gMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
//                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
//                    break; // Get only the first location
//                }
//            }
//        };
//    }
//
//    private void getCurrentLocation() {
//        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            fusedLocationClient.getLastLocation()
//                    .addOnSuccessListener(this, location -> {
//                        if (location != null) {
//                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                            gMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
//                            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
//                        } else {
//                            // If location is null, request location updates
//                            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
//                        }
//                    });
//        } else {
//            // Permission not granted, request it
//            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            getCurrentLocation();
//        } else {
//            Toast.makeText(this, "Location permission denied!", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    // Method to move marker to a specific address
//    private void moveMarkerToAddress(String address) {
//        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//        try {
//            List<Address> addresses = geocoder.getFromLocationName(address, 1);
//            if (addresses != null && !addresses.isEmpty()) {
//                Address location = addresses.get(0);
//                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                gMap.addMarker(new MarkerOptions().position(latLng).title(address));
//                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
//            } else {
//                Toast.makeText(this, "Address not found!", Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Error retrieving address!", Toast.LENGTH_SHORT).show();
//        }
//    }
//}