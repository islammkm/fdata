package com.exemple.fdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Colie;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
public class Testmap extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap gMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    int id_colie;
    TextView textView20;

    String addressString23;
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testmap);
        updateGPS();
        textView20 = findViewById(R.id.textView20);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        createLocationRequest();
        createLocationCallback();
        id_colie = getIntent().getIntExtra("id_colie", -1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LatLng latLng = getLatLngFromAddress(Testmap.this, addressString23.toString());
                double startLatitude = latLng.latitude;
                double startLongitude = latLng.longitude;
                MyDatabaseHalper db = new MyDatabaseHalper(Testmap.this);
                id_colie = getIntent().getIntExtra("id_colie", -1);
                Colie colie = db.getColieById2(id_colie);
                LatLng latLng2 = getLatLngFromAddress(Testmap.this, colie.getLast_position().toString());
                double endLatitude = latLng2.latitude;
                double endLongitude = latLng2.longitude;
                float[] results = new float[1];
                Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results);
                double distanceInKm = results[0] / 1000.0;
                String formattedDistance = String.format("%.2f", distanceInKm);
                textView20.setText(distanceInKm*5+" min ( "+formattedDistance + " km )");
            }
        }, 1000);

        ImageView imageView12 = findViewById(R.id.imageView12);
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), Testmap.class);
                intent.putExtra("id_colie",id_colie);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        MyDatabaseHalper db = new MyDatabaseHalper(this);
        id_colie = getIntent().getIntExtra("id_colie", -1);
        if (id_colie != -1) {
            Colie colie = db.getColieById2(id_colie);
            moveMarkerToAddress(colie.getLast_position().toString());
        } else {
            Toast.makeText(this, "Colie not found!", Toast.LENGTH_SHORT).show();
            moveMarkerToAddress("Q7FW+JM2, H'raoua, Algérie");
        }
    }

    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // Update location every 10 seconds
        locationRequest.setFastestInterval(5000); // The fastest update interval, in milliseconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // Use high accuracy
    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    gMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                    break; // Get only the first location
                }
            }
        };
    }

    private void getCurrentLocation() {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            gMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                        } else {
                            // If location is null, request location updates
                            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                        }
                    });
        } else {
            // Permission not granted, request it
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            Toast.makeText(this, "Location permission denied!", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to move marker to a specific address
    private void moveMarkerToAddress(String address) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(address, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address location = addresses.get(0);
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                gMap.addMarker(new MarkerOptions().position(latLng).title(address));
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            } else {
                Toast.makeText(this, "Address not found!", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error retrieving address!", Toast.LENGTH_SHORT).show();
        }
    }
    public static LatLng getLatLngFromAddress(Context context, String address) {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(address, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null && !addresses.isEmpty()) {
            Address location = addresses.get(0);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            return new LatLng(latitude, longitude);
        } else {
            return null; // لم يتم العثور على العنوان
        }
    }



    private void updateGPS(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Testmap.this);
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(Testmap.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (!addresses.isEmpty()) {
                                Address address = addresses.get(0);
                                String addressString = address.getAddressLine(0); // هنا يتم الحصول على العنوان
                                // ارسل العنوان كـ String إلى الدالة التي تحتاج إليها
                                processAddressString(addressString);
                            } else {
                                Log.d("Location", "No address found");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.d("Location", "No location found");
                    }
                }
            });
        } else {
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_FINE_LOCATION);
            }
        }
    }

    // الدالة التي تقوم بمعالجة العنوان كـ String
    private void processAddressString(String addressString) {
        Log.d("Location", "Address: " + addressString);
        addressString23 = addressString;

    }

}