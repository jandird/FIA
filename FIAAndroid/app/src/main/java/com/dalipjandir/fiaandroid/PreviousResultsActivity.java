package com.dalipjandir.fiaandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PreviousResultsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mainMenu;
    private ListView listview;
    private List<Flags> prevResults;
    List<ListObject> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_results);

        mainMenu = (Button) findViewById(R.id.mainmenu);
        mainMenu.setOnClickListener(this);
        prevResults = LocalStorage.readAll();
        Log.i("testing", "onCreate: " + prevResults.size());

        TextView textView = (TextView) findViewById(R.id.textView);
        values = new ArrayList<ListObject>();
        for (int i = prevResults.size()-1; i >= 0 ; i--) {
            ListObject item = new ListObject();
            item.setCountry(prevResults.get(i).getCountry());
            String flagFile = prevResults.get(i).getPngName();
            InputStream in = this.getResources().openRawResource(this.getResources().getIdentifier(flagFile,"raw", this.getPackageName()));
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            item.setImage(bitmap);
            values.add(item);
        }

        listview = (ListView) findViewById(R.id.list);
        listview.setItemsCanFocus(false);
        SimpleArrayAdapter adapter2 = new SimpleArrayAdapter(this, R.layout.rowlayout, values);
        listview.setAdapter(adapter2);

        //on click of country name
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewController.setPrevResultFlag(prevResults.get(prevResults.size() - position - 1));
                Intent myIntent = new Intent(PreviousResultsActivity.this, MoreInfoActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public void onClick (View v){
        if (v == mainMenu){
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
