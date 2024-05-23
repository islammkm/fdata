package com.exemple.fdatabase;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exemple.fdatabase.controler.MyDatabaseHalper;
import com.exemple.fdatabase.models.Colie;
import com.exemple.fdatabase.models.Fourniseur;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import android.Manifest;
import android.widget.Toast;

public class Scanner extends AppCompatActivity {
    String addressString2 ;
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest = new LocationRequest();
    MyDatabaseHalper db = new MyDatabaseHalper(this);
    Button scanbtn;
    Colie colie = new Colie();
    Fourniseur fourn = new Fourniseur();
    String ScanR = "حي صنوبر";
    TextView colieinf ,last_position,datec,cmndid,newposition,fournisseur;
    private String scanResult;
    FrameLayout frameLayout;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        boolean select = getIntent().getBooleanExtra("select", false);
        String selectedItem = getIntent().getStringExtra("selectitem");
        String todayedate = getTodaysDate();
        scanbtn = findViewById(R.id.scanbutton);

            scanbtn.setOnClickListener(v ->
            {
                if(select == false) {
                    updateGPS();
                }else if (selectedItem != null){
                    addressString2 = selectedItem;
                }
                ScanOptions options = new ScanOptions();
                options.setPrompt("Volum up to flash on");
                options.setBeepEnabled(true);
                options.setOrientationLocked(true);
                options.setCaptureActivity(CapturAct.class);
                barLaucher.launch(options);
            });
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            colie = db.getColieByCode(result.getContents());
            if(colie != null) {

                if(addressString2 != null) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Scanner.this);
                    View myview = getLayoutInflater().inflate(R.layout.colieinf, null);
                    builder.setView(myview);
                    final AlertDialog dialog = builder.create();
                    dialog.setCancelable(false);
                    dialog.show();
                    colieinf = myview.findViewById(R.id.colieinf);
                    cmndid = myview.findViewById(R.id.CmndId);
                    last_position = myview.findViewById(R.id.last_position);
                    datec = myview.findViewById(R.id.DateC);
                    newposition = myview.findViewById(R.id.newpostion);
                    fournisseur = myview.findViewById(R.id.fourni);

                    colieinf.setVisibility(View.VISIBLE);
                    cmndid.setVisibility(View.VISIBLE);
                    last_position.setVisibility(View.VISIBLE);
                    datec.setVisibility(View.VISIBLE);
                    newposition.setVisibility(View.VISIBLE);
                    fournisseur.setVisibility(View.VISIBLE);

                    colieinf.setText("Colie N° " + result.getContents());
                    cmndid.setText("ID cmnd" + colie.getCmndId());
                    datec.setText("Date " + colie.getDate());
                    last_position.setText("previous position " + colie.getLast_position());
                    newposition.setText("new position " + addressString2);
                    fourn = db.getFournisseurById(colie.getFournisseurId());
                    fournisseur.setText("fournisseur " + fourn.getNom());
                    db.updateColieEtat(colie.getId(),getTodaysDate());
                    db.updateCmdEtat(colie.getCmndId(),"liv2");
                    db.updateColieLastPosition(colie.getId(), addressString2);
                    myview.findViewById(R.id.buttonntf).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }else {
                    final AlertDialog.Builder builder2 = new AlertDialog.Builder(Scanner.this);
                    View myview2 = getLayoutInflater().inflate(R.layout.googlealert, null);
                    builder2.setView(myview2);
                    final AlertDialog dialog2 = builder2.create();
                    dialog2.setCancelable(false);
                    dialog2.show();
                    myview2.findViewById(R.id.okbtngoogle).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog2.dismiss();
                        }
                    });
                }
            }else{
                Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
            }

        }

    });
private void updateGPS(){
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Scanner.this);
    if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Geocoder geocoder = new Geocoder(Scanner.this, Locale.getDefault());
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
        addressString2 = addressString;
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Scanner.this);
        View myview = getLayoutInflater().inflate(R.layout.alertbox, null);
        builder.setView(myview);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        myview.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finishAndRemoveTask();
            }
        });
        myview.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }
    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }
}