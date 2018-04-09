package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.database.*;
import com.google.firebase.database.ValueEventListener;

public class ViewController {

    private static Uri uncroppedImage;
    private static Bitmap croppedImage;
    private static Flags [] results;
    private static Flags flagResult;
    private static FirebaseDatabase database;
    private static DatabaseReference myRef;
    private static int count;


    public static void setUncroppedImage(Uri input){
        uncroppedImage = input;
    }

    public static Uri getUncroppedImage(){
        return uncroppedImage;
    }

    public static void runFinalImage(Context context, Bitmap image){
        croppedImage = image;
        results = Forum.getImage(context, image);
    }

    public static void setFlag(Flags flag){
        flagResult = flag;
        count = 0;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child("Countries").child(flagResult.getCountry()).child("Weight").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("test", "setFlag: " + dataSnapshot.getValue());
                count++;
                myRef.child("Countries").child(flagResult.getCountry()).child("Weight").setValue(count);

                Log.i("test2","test: " + count);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.i("test2","test: " + count);
    }

    public static Flags getFlag(){
        return flagResult;
    }

    public static Flags [] getResults(){
        return results;
    }

}
