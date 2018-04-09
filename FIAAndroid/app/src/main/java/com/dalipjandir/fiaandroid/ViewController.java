package com.dalipjandir.fiaandroid;

import android.net.Uri;

public class ViewController {

    private static Uri uncroppedImage;
    private static String croppedImage;
    private static Flags [] results;
    private static Flags flagResult;

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

    public static void setFlag(Flags flag){
        flagResult = flag;
    }

    public static Flags getFlag(){
        return flagResult;
    }

    public static Flags [] getResults(){
        return Forum.getImage("temp");
    }

}
