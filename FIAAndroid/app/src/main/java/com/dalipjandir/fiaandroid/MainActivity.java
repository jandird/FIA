package com.dalipjandir.fiaandroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    }

    @Override
    public void onClick (View v){
        if (v == buttonID){
            startActivity(new Intent(this, UploadActivity.class));
        }
        if (v == buttonResults){
            System.out.println("buttonResults");
        }
        if (v == buttonMS){
            System.out.println("buttonMS");
        }
        if (v == buttonTutorial){
            System.out.println("buttonTutorial");
        }
    }


}
