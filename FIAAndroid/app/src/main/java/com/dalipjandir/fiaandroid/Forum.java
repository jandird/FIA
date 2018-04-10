package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Forum {

    public static Flags[] getImage(Context context, Bitmap bitmap){
        Flags flag [] = new Flags[6];
        sendFlags(context, bitmap);
        return flag;
    }

    public static void getPreviousResults (){

    }

    private static ArrayList<Flags> sendFlags(Context context, Bitmap bitmap){

        return analyzeResults(Colour.getImage(context, bitmap), Colour.getImage(context, bitmap));
    }

    private static ArrayList<Flags> analyzeResults(ArrayList<Flags> shape, ArrayList<Flags> colour){

        Log.d("FlagShape", ""+shape.size());
        for (Flags flag : colour){
            Log.d("FlagShape", flag.getCountry());
        }
        return new ArrayList<>();
    }


    //adding test flags to past results To be updated when forum is complete
    public static void addPreviousResults(){
        LocalStorage.deleteFile();
        LocalStorage.addResult(1);
    }
}
