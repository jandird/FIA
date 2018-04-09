package com.dalipjandir.fiaandroid;

import java.util.ArrayList;
import java.util.Collections;

public class Forum {

    public static Flags[] getImage(String path){
        ArrayList<Flags> temp = Flags_data.getFlags();
        Flags flags [] = {temp.get(6), temp.get(1), temp.get(2), temp.get(3), temp.get(4), temp.get(5)};

        //sendFlags(path);
        return flags;
    }

    public static void getPreviousResults (){

    }

    private static ArrayList<Flags> sendFlags(String path){

        return analyzeResults(Shape.getImage(path), Colour.getImage(path));
    }

    private static ArrayList<Flags> analyzeResults(ArrayList<Flags> shape, ArrayList<Flags> colour){
        return new ArrayList<>();
    }
}
