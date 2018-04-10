package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Forum {

    public static Flags[] getImage(Context context, Bitmap bitmap, double lon, double lat){
        Flags flag [] = new Flags[6];
        ArrayList<Flags> results = sendFlags(context, bitmap, lon, lat);

        flag[0] = results.get(0);
        flag[1] = results.get(1);
        flag[2] = results.get(2);
        flag[3] = results.get(3);
        flag[4] = results.get(4);
        flag[5] = results.get(5);

        return flag;
    }

    public static void getPreviousResults (){

    }

    private static ArrayList<Flags> sendFlags(Context context, Bitmap bitmap, Double lon, Double lat){

        GPS.analyzeLocation(context, lon, lat);
        return analyzeResults(Shape.getImage(context, bitmap), Colour.getImage(context, bitmap));
    }

    private static ArrayList<Flags> analyzeResults(ArrayList<Flags> shape, ArrayList<Flags> colour){

//        Log.d("FlagShape", ""+shape.size());
//        for (Flags flag : shape){
//            Log.d("FlagShape", flag.getCountry());
//            Log.d("FlagShape", ""+flag.getShapeVal());
//        }
//
//        Log.d("FlagShape", ""+colour.size());
//        for (Flags flag : colour){
//            Log.d("FlagShape", flag.getCountry());
//            Log.d("FlagShape", ""+flag.getColourVal());
//        }

        ArrayList<Flags> combined = colour;

        for (Flags flag : shape){
            boolean done = true;
            for (Flags in : combined){
                if (flag.getCountry().equals(in.getCountry())){
                    combined.get(combined.indexOf(in)).setShapeVal(flag.getShapeVal());
                    done = false;
                    break;
                }
            }

            if (done){
                combined.add(flag);
            }
        }

        Collections.sort(combined, sort);

        for (Flags flag : combined){
            Log.d("FlagShape", flag.getCountry());
            Log.d("FlagShape", ""+flag.getColourVal());
            Log.d("FlagShape", ""+flag.getShapeVal());

        }

        return combined;
    }

    static Comparator<Flags> sort = new Comparator<Flags>() {
        @Override
        public int compare(Flags o1, Flags o2) {
            if (o1.getShapeVal() + o1.getColourVal() > o2.getShapeVal() + o2.getColourVal()) {
                return -1;
            } else if (o1.getShapeVal() + o1.getColourVal() == o2.getShapeVal() + o2.getColourVal()) {
                return 0;
            } else {
                return 1;
            }
        }
    };

    //adding test flags to past results To be updated when forum is complete
    public static void addPreviousResults(int index){
        LocalStorage.addResult(index);
    }
}
