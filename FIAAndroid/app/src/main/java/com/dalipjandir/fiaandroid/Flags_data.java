package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeromicho on 2018-04-08.
 */

public class Flags_data {
    public static List<Flags> flags = new ArrayList<>();

    public static void readflagData(Context context){
        InputStream is = context.getResources().openRawResource(R.raw.flags);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line;
        int i = 0;
        try {
            while ((line = reader.readLine()) != null){
                //split by ","
                String[] token = line.split(",");

                //Read the data
                Flags flag = new Flags();
                flag.setCountry(token[0]);
                flag.setPngName(token[1]);
                flag.setCapital(token[2]);
                flag.setWiki(token[3]);
                flag.setEdge_Pic(token[4]);
                flag.setIndex(i);
                i++;
                Flags_data.flags.add(flag);

                Log.d("MyActivity", "Just created: " + flag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
