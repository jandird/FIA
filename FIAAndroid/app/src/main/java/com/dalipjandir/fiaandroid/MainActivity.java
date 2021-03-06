package com.dalipjandir.fiaandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating Buttons
    private Button buttonID;
    private Button buttonResults;
    private Button buttonMS;
    private Button buttonTutorial;

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Flags_data.readflagData(getApplicationContext());
        LocalStorage.createLocalFile(getApplicationContext());

        TextView title = (TextView) findViewById(R.id.title);

        //Creating Buttons
        buttonID = (Button) findViewById(R.id.buttonID);
        buttonResults = (Button) findViewById(R.id.buttonResults);
        buttonMS = (Button) findViewById(R.id.buttonMS);
        buttonTutorial = (Button) findViewById(R.id.buttonTutorial);

        //Adding ActionListeners
        buttonID.setOnClickListener(this);
        buttonResults.setOnClickListener(this);
        buttonMS.setOnClickListener(this);
        buttonTutorial.setOnClickListener(this);

        //Getting Title Font
        Typeface titleTF = Typeface.createFromAsset(getAssets(), "font/Cookie-Regular.ttf");
        title.setTypeface(titleTF);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                getGPS();
                break;
            default:
                break;
        }
    }

    void getGPS(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
                //noinspection MissingPermission
        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
    }



    // public static List<Flags> flags = new ArrayList<>();

    @Override
    public void onClick (View v){
        if (v == buttonID){
            startActivity(new Intent(this, UploadActivity.class));
        }
        if (v == buttonResults){
            startActivity(new Intent(this, PreviousResultsActivity.class));
        }
        if (v == buttonMS){
            startActivity(new Intent(this, MostSearchedActivity.class));
        }
        if (v == buttonTutorial){
            startActivity(new Intent(this, testtutorialActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
    }
}
