package com.dalipjandir.fiaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoneActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating Buttons
    private Button buttonMM;
    private Button buttonTut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_none);


        buttonMM = (Button) findViewById(R.id.buttonMM);
        buttonTut = (Button) findViewById(R.id.buttonTut);

        //Adding ActionListeners
        buttonMM.setOnClickListener(this);
        buttonTut.setOnClickListener(this);
    }
    @Override
    public void onClick (View v) {
        if (v == buttonMM) {
            startActivity(new Intent(this, MainActivity.class));
        }
        if (v == buttonTut) {
            startActivity(new Intent(this, testtutorialActivity.class));
        }
    }
}
