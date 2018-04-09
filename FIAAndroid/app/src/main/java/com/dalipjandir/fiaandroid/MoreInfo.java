package com.dalipjandir.fiaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoreInfo extends AppCompatActivity implements View.OnClickListener{

    private Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        mainMenu = (Button) findViewById(R.id.mainmenu);
        mainMenu.setOnClickListener(this);
    }

    @Override
    public void onClick (View v){
        if (v == mainMenu){
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
