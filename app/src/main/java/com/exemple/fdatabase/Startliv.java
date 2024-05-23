package com.exemple.fdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Cmd;
import com.exemple.fdatabase.models.Colie;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Startliv extends AppCompatActivity {
    List<Colie> listcolie = new ArrayList<>();
    TextView time,dis;
    String addressString23 ;
    private GoogleMap gMap;
    Cmd cmnd ;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    FusedLocationProviderClient fusedLocationProviderClient;
    Colie colie;
    private Handler mHandler = new Handler();
    String selectedItem = "Delivery Truck";
    private static final int REFRESH_INTERVAL = 3000;
    private boolean mIsRefreshing = false;
    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startliv);
        Intent intent = getIntent();
        MyDatabaseHalper db = new MyDatabaseHalper(this);
        size = intent.getIntExtra("size", -1);
        for (int i = 0; i < size; i++) {
            int id = intent.getIntExtra("id" + i, -1);
            Colie colie = db.getColieById8(id); // Pass the ID directly
            listcolie.add(colie);
        }
        colie = db.getColieByCode(listcolie.get(0).getCode().toString());
        TextView adress,nphone;
        time = findViewById(R.id.sttime);
        dis = findViewById(R.id.stdis);
        adress = findViewById(R.id.stadress);
        nphone = findViewById(R.id.stclientnm);
        cmnd = db.getCmndById(colie.getCmndId());
        if(cmnd != null ) {
        adress.setText(cmnd.getAdresse().toString());
        nphone.setText("0541 39 73 "+cmnd.getNphone());
        }else {
            Toast.makeText(this, "DS", Toast.LENGTH_SHORT).show();
        }
        startRefreshing();
        Button stopliv = findViewById(R.id.stopliv);
        Button continueR = findViewById(R.id.continueR);

        stopliv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueR.setVisibility(View.VISIBLE);
                stopliv.setVisibility(View.GONE);
                stopRefreshing();
            }
        });
        continueR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueR.setVisibility(View.GONE);
                stopliv.setVisibility(View.VISIBLE);
                startRefreshing();
            }
        });
        Button donliv = findViewById(R.id.donliv);
        donliv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateCmdEtat(cmnd.getId(),"livf");
                Intent intent = new Intent(Startliv.this, Select.class);
                intent.putExtra("selectitem",selectedItem);
                startActivity(intent);
            }
        });
    }


    private void startRefreshing() {
        if (!mIsRefreshing) {
            mIsRefreshing = true;
            mHandler.postDelayed(mRefreshRunnable, REFRESH_INTERVAL);
        }
    }

    private void stopRefreshing() {
        if (mIsRefreshing) {
            mIsRefreshing = false;
            mHandler.removeCallbacks(mRefreshRunnable);
        }
    }

    private Runnable mRefreshRunnable = new Runnable() {
        @Override
        public void run() {
            updateGPS();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatLng latLng = getLatLngFromAddress(Startliv.this, addressString23.toString());
                    double startLatitude = latLng.latitude;
                    double startLongitude = latLng.longitude;
                    MyDatabaseHalper db = new MyDatabaseHalper(Startliv.this);
                    LatLng latLng2 = getLatLngFromAddress(Startliv.this, cmnd.getAdresse().toString());
                    double endLatitude = latLng2.latitude;
                    double endLongitude = latLng2.longitude;
                    float[] results = new float[1];
                    Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results);
                    double distanceInKm = results[0] / 1000.0;
                    String formattedDistance = String.format("%.2f", distanceInKm);
                    long roundedNumber = Math.round(distanceInKm);
                    dis.setText(formattedDistance+" km");
                    time.setText(roundedNumber*5+" min");
                    for (int i = 0; i < size; i++) {
                        colie = db.getColieByCode(listcolie.get(i).getCode().toString());
                        db.updateColieLastPosition(colie.getId(),addressString23);
                    }
                }
            }, 1000);
            // Schedule the next refresh
            mHandler.postDelayed(this, REFRESH_INTERVAL);
        }
    };

    private void refreshCode() {
        // Implement the code you want to refresh here
        // This method will be called every 5 seconds
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop refreshing when the activity is destroyed
        stopRefreshing();
    }



    private void updateGPS(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Startliv.this);
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(Startliv.this, Locale.getDefault());
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
}