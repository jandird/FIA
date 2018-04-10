package com.dalipjandir.fiaandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MoreInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mainMenu;
    private Button prevResults;
    private Flags flagResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        mainMenu = (Button) findViewById(R.id.mainmenu);
        mainMenu.setOnClickListener(this);

        prevResults = (Button) findViewById(R.id.prevResults);
        prevResults.setOnClickListener(this);

        flagResult = ViewController.getFlag();

        TextView countryView = (TextView) findViewById(R.id.country);
        countryView.setText(flagResult.getCountry());

        TextView capitalView = (TextView) findViewById(R.id.capital);
        capitalView.setText(flagResult.getCapital());

        String flagFile = flagResult.getPngName();
        InputStream in = this.getResources().openRawResource(this.getResources().getIdentifier(flagFile,"raw", this.getPackageName()));
        Bitmap bitmap = BitmapFactory.decodeStream(in);

        ImageView image = (ImageView) findViewById(R.id.picture);
        image.setImageBitmap(bitmap);

        Button btn = (Button) findViewById(R.id.wiki);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(flagResult.getWiki()));
                startActivity(myWebLink);
            }
        });
    }

    @Override
    public void onClick (View v){
        if (v == mainMenu){
            startActivity(new Intent(this, MainActivity.class));
        }

        if (v == prevResults){
            startActivity(new Intent(this, PreviousResultsActivity.class));
        }

    }
}
