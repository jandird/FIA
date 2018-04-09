package com.dalipjandir.fiaandroid;

import android.net.Uri;

public class ViewController {

    private static Uri uncroppedImage;
    private static String croppedImage;
    private static Flags [] results;

    public static void setUncroppedImage(Uri input){
        uncroppedImage = input;
    }

    public static Uri getUncroppedImage(){
        return uncroppedImage;
    }

    public static void runFinalImage(String image){
        croppedImage = image;
        results = Forum.getImage(image);
    }

    public static Flags [] getResults(){
        return Forum.getImage("temp");
    }

}
