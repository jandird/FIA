package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class GPS {

    public static String analyzeLocation(Context context, double lat, double lon) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
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
}
