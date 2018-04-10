package com.dalipjandir.fiaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class PreviousResultsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mainMenu;
    private ListView listview;
    private List<Flags> prevResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_results);

        mainMenu = (Button) findViewById(R.id.mainmenu);
        mainMenu.setOnClickListener(this);
        prevResults = LocalStorage.readAll();
        Log.i("testing", "onCreate: " + prevResults.size());
    }

    @Override
    public void onClick (View v){
        if (v == mainMenu){
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
