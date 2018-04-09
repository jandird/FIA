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

    //gps stuff
    private Button buttonGPS;
    private TextView gpsResults;
    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        readflagData();

        //gps stuff
        buttonGPS = (Button) findViewById(R.id.buttonGPS);
        buttonGPS.setOnClickListener(this);
        gpsResults = (TextView) findViewById(R.id.gpsResults);




        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                gpsResults.setText(getCountryName(location.getLatitude(),location.getLongitude()));
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

    // gps - get country name using the long and lat using Geocoder
    // this is very ugly, but works
    public String getCountryName(double lat, double lon) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        int countryCount = 0;
        int numberOfResults = 6;
        double newLat;
        double newLon;
        String[] countryNames = new String[numberOfResults];
        String current;
        String smushedNames = ""; // just used now for testing purposes

        // This code checks nodes surrounding the user in a square like fassion
        // the squares grow by one long and lat point with each iteration
        // the algorithm stops searching at a radius of 5 long points
        // and returns the countries found
        for(int i = 0;i<5 && countryCount!=numberOfResults;i++){

            if(countryCount < numberOfResults)
            for(int k = 0 ;k<4;k++){
                newLat = lat;
                newLon = lon;
                if(k==0)
                    newLon += i;
                if(k==1)
                    newLon -= i;
                if(k==2)
                    newLat += i;
                if(k==3)
                    newLat -= i;
                if(k==4) {
                    newLon += i;
                    newLat += i;
                }
                if(k==5) {
                    newLon += i;
                    newLat -= i;
                }
                if(k==6) {
                    newLon -= i;
                    newLat += i;
                }
                if(k==7) {
                    newLon -= i;
                    newLat -= i;
                }


                // This uses geocoder to submit the coordinates, and return the country which falls
                // within those cordinates
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(newLat, newLon, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        current = addresses.get(0).getCountryName();
                        // if the initial country hasn't been set, do that here
                        if(countryCount == 0){
                            countryNames[0] = current;
                            countryCount++;
                        }
                        // store adj cities as they become avail
                        else if(!Arrays.asList(countryNames).contains(current)) {

                            countryNames[countryCount] = current;
                            countryCount++;
                        }
                    }
                    //return null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // stores results in a string for returning
        for(int i = 0; i < countryCount; ++i){
            smushedNames += countryNames[i] + " ";
        }

        return smushedNames;
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



    private void readflagData(){
        InputStream is = getResources().openRawResource(R.raw.flags);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line;
        int i = 0;
        try {
            while ((line = reader.readLine()) != null){
                //split by ","
                String[] token = line.split(",");

                //Read the data
                Flags flag = new Flags();
                flag.setCountry(token[0]);
                flag.setPngName(token[1]);
                flag.setCapital(token[2]);
                flag.setWiki(token[3]);
                flag.setEdge_Pic(token[4]);
                flag.setIndex(i);
                i++;
                Flags_data.flags.add(flag);

                Log.d("MyActivity", "Just created: " + flag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick (View v){
        if (v == buttonID){
            startActivity(new Intent(this, UploadActivity.class));
        }
        if (v == buttonResults){
            startActivity(new Intent(this, ResultsActivity.class));
        }
        if (v == buttonMS){
            startActivity(new Intent(this, MostSearchedActivity.class));
        }
        if (v == buttonTutorial){
            startActivity(new Intent(this, testtutorialActivity.class));
        }
        if (v == buttonGPS){
            getGPS();
        }
    }
}
