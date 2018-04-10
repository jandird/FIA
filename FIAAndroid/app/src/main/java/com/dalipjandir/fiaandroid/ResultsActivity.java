package com.dalipjandir.fiaandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    //Flag buttons and None button
    private Button buttonNone;

    public Flags[] flags = ViewController.getResults();
    //list of countries and their respective images to be displayed as the results to choose from
    //public final int[] images = new int[]{getResources().openRawResource(R.raw.ad), R.drawable.can, R.drawable.can, R.drawable.can, R.drawable.can};


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
        for (int i = 0; i < flags.length; i++) {
            ListObject item = new ListObject();
            item.setCountry(flags[i].getCountry());
            String flagFile = flags[i].getPngName();
            InputStream in = this.getResources().openRawResource(this.getResources().getIdentifier(flagFile,"raw", this.getPackageName()));
            //InputStream imageStream = this.getResources().openRawResource(R.raw.ad);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            item.setImage(bitmap);
            values.add(item);
        }

        listview = (ListView) findViewById(R.id.list);
        listview.setItemsCanFocus(false);
        SimpleArrayAdapter adapter = new SimpleArrayAdapter(this, R.layout.rowlayout, values);
        listview.setAdapter(adapter);


        //on click of country name
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewController.setFlag(flags[position]);
                Intent myIntent = new Intent(ResultsActivity.this, MoreInfoActivity.class);
                startActivity(myIntent);
            }
        });
    }

        @Override
        public void onClick (View v){
            if (v == buttonNone){
                startActivity(new Intent(this, NoneActivity.class));
            }
    }


}
