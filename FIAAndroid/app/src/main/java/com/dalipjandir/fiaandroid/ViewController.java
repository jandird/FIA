package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.database.*;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewController {

    private static Uri uncroppedImage;
    private static Bitmap croppedImage;
    private static Flags [] results;
    private static Flags flagResult;
    private static Flags prevResultFlag; //flag used in previous results when to go to more info without updating the online database
    private static FirebaseDatabase database;
    private static DatabaseReference myRef;
    private static int count;
    private static double longi, latti;


    public static void setUncroppedImage(Uri input){
        uncroppedImage = input;
    }

    public static Uri getUncroppedImage(){
        return uncroppedImage;
    }

    public static void runFinalImage(Context context, Bitmap image){
        croppedImage = image;
        results = Forum.getImage(context, image, longi, latti);
    }

    //set flag for result including update to database
    public static void setFinalFlag(Flags flag){
        Forum.addPreviousResults(flag.getIndex());
        flagResult = flag;
        count = 0;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child("Countries").child(flagResult.getCountry()).child("Weight").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("test", "setFlag: " + dataSnapshot.getValue());
                count = Integer.parseInt(dataSnapshot.getValue().toString());
                count++;
                myRef.child("Countries").child(flagResult.getCountry()).child("Weight").setValue(Integer.toString(count));

                Log.i("test2","test: " + count);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void setPrevResultFlag(Flags flag){
        flagResult = flag;
    }

    public static Flags getFlag(){
        return flagResult;
    }

    public static Flags [] getResults(){
        return results;
    }

    public static void setLongLat (double lon, double lat){
        longi = lon;
        latti = lat;
    }

}
