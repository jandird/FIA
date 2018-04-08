package com.dalipjandir.fiaandroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    //Flag buttons and None button

    private Button buttonF1;

    private Button buttonF2;
    private Button buttonF3;
    private Button buttonF4;
    private Button buttonF5;
    private Button buttonF6;

    private Button buttonNone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        //Creating Buttons

        buttonF1 = (Button) findViewById(R.id.buttonF1);

        buttonF2 = (Button) findViewById(R.id.buttonF2);
        buttonF3 = (Button) findViewById(R.id.buttonF3);
        buttonF4 = (Button) findViewById(R.id.buttonF4);
        buttonF5 = (Button) findViewById(R.id.buttonF5);
        buttonF6 = (Button) findViewById(R.id.buttonF6);

        buttonNone = (Button) findViewById(R.id.buttonNone);


        //Adding ActionListeners

        buttonF1.setOnClickListener(this);

        buttonF2.setOnClickListener(this);
        buttonF3.setOnClickListener(this);
        buttonF4.setOnClickListener(this);
        buttonF5.setOnClickListener(this);
        buttonF6.setOnClickListener(this);

        buttonNone.setOnClickListener(this);
    }

        @Override
        public void onClick (View v){
            if (v == buttonF1){
                startActivity(new Intent(this, NoneActivity.class));
                //System.out.println("buttonF1");
            }
            if (v == buttonF2){
                System.out.println("buttonF2");
            }
            if (v == buttonF3){
                System.out.println("buttonF3");
            }
            if (v == buttonF4){
                System.out.println("buttonF4");
            }
            if (v == buttonF5){
                System.out.println("buttonF5");
                }
            if (v == buttonF6){
                System.out.println("buttonF6");
            }

            if (v == buttonNone){
                System.out.println("buttonF6");
                //startActivity(new Intent(this, UploadActivity.class));
            }
    }
}
