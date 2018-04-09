package com.dalipjandir.fiaandroid;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.net.SocketPermission;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.net.SocketPermission;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating Buttons
    private Button buttonID;
    private Button buttonResults;
    private Button buttonMS;
    private Button buttonTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = (TextView) findViewById(R.id.title);

        //Creating Buttons
        buttonID = (Button) findViewById(R.id.buttonID);
        buttonResults = (Button) findViewById(R.id.buttonResults);
        buttonMS = (Button) findViewById(R.id.buttonMS);
        buttonTutorial = (Button) findViewById(R.id.buttonTutorial);

        //Adding ActionListeners
        buttonID.setOnClickListener(this);
        buttonResults.setOnClickListener(this);
        buttonMS.setOnClickListener(this);
        buttonTutorial.setOnClickListener(this);

        //Getting Title Font
        Typeface titleTF = Typeface.createFromAsset(getAssets(), "font/Cookie-Regular.ttf");
        title.setTypeface(titleTF);

        readflagData();
    }

  // public static List<Flags> flags = new ArrayList<>();



    private void readflagData(){
        InputStream is = getResources().openRawResource(R.raw.flags);
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


    @Override
    public void onClick (View v){
        if (v == buttonID){
            startActivity(new Intent(this, UploadActivity.class));
        }
        if (v == buttonResults){
            startActivity(new Intent(this, ResultsActivity.class));
        }
        if (v == buttonMS){
            startActivity(new Intent(this, MostSearchedActivity.class));
        }
        if (v == buttonTutorial){
            startActivity(new Intent(this, testtutorialActivity.class));
        }
    }
}
