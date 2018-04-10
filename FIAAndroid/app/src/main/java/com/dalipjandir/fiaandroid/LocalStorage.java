package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jeromicho on 2018-04-09.
 */

public class LocalStorage {

    //public static List<Integer> LocalResult = new ArrayList<>();
    public static File result;


    public static void createLocalFile(Context context) {

        File path = context.getFilesDir();
        result = new File(path, "LocalResult.txt");
    }

    //input the index of the flag and it will add the index to the local file
    public static void addResult(int index) {
        try {

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(result, true)));

            String temp = String.valueOf(index) + "\n";
            out.write(temp);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // return a list of flags from the local file
    public static List<Flags> readAll() {
        List<Integer> LocalResult = new ArrayList<>();
        List<Flags> flags = new ArrayList<>();

        FileInputStream in = null;
        try {
            in = new FileInputStream(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(in, Charset.forName("UTF-8"))
        );
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                    int tmp = Integer.parseInt(line);
                    LocalResult.add(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Flags> temp = Flags_data.getFlags();
        for (int i = 0; i < LocalResult.size(); i++){
            for (Flags flag : temp){
                int match = LocalResult.get(i);
                if (flag.getIndex() == match){
                    Log.d("Read", ""+flag.getIndex());
                    flags.add(flag);
                    break;
                }
            }
        }
        //   Collections.reverse(flags);
        return flags;
    }

    //delete the file
    public static void deleteFile(){
        result.delete();
    }

}
