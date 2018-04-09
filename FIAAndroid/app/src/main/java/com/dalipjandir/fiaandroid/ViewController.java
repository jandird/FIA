package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class ViewController {

    private static Uri uncroppedImage;
    private static Bitmap croppedImage;
    private static Flags [] results;
    private static Flags flagResult;

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
    }

    public static Flags getFlag(){
        return flagResult;
    }

    public static Flags [] getResults(){
        return results;
    }

}
