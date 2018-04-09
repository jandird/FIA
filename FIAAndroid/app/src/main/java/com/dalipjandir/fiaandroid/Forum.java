package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class Forum {

    public static Flags[] getImage(Context context, Bitmap bitmap){
        ArrayList<Flags> temp = Flags_data.getFlags();
        Flags flags [] = {temp.get(6), temp.get(1), temp.get(2), temp.get(3), temp.get(4), temp.get(5)};

        sendFlags(context, bitmap);
        return flags;
    }

    public static void getPreviousResults (){

    }

    private static ArrayList<Flags> sendFlags(Context context, Bitmap bitmap){

        return analyzeResults(Shape.getImage(context, bitmap), Colour.getImage(context, bitmap));
    }

    private static ArrayList<Flags> analyzeResults(ArrayList<Flags> shape, ArrayList<Flags> colour){

        Log.d("FlagShape", ""+colour.size());
        for (Flags flag : colour){
            Log.d("FlagShape", flag.getCountry());
        }
        return new ArrayList<>();
    }
}
