package com.dalipjandir.fiaandroid;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.*;


import java.util.ArrayList;
import java.util.List;


public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    //Flag buttons and None button

    private Button buttonNone;

    //list of countries and their respective images to be displayed as the results to choose from
    public static final String[] countries = new String[] {"Germany", "USA", "Great Britain", "Japan", "Korea"};
    public static final int[] images = new int[]{R.drawable.can, R.drawable.can, R.drawable.can, R.drawable.can, R.drawable.can};

    private ListView listview;
    List<ListObject> values;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        //Creating Buttons

        buttonNone = (Button) findViewById(R.id.buttonF1);

        buttonNone.setOnClickListener(this);

        TextView textView = (TextView) findViewById(R.id.textView);
        values = new ArrayList<ListObject>();
        for (int i = 0; i < countries.length; i++) {
            ListObject item = new ListObject();
            item.setCountry(countries[i]);
            item.setImage(images[i]);
            values.add(item);
        }

        listview = (ListView) findViewById(R.id.list);
        listview.setItemsCanFocus(false);
        SimpleArrayAdapter adapter = new SimpleArrayAdapter(this, R.layout.rowlayout, values);
        listview.setAdapter(adapter);

        //on click of country name NEEDS TO BE CHANGED TO GO TO A MORE INFO PAGE
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

        @Override
        public void onClick (View v){
            if (v == buttonNone){
                startActivity(new Intent(this, NoneActivity.class));
                //System.out.println("buttonF1");
            }
    }


}
