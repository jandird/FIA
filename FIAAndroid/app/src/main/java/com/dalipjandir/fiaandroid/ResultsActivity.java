package com.dalipjandir.fiaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;
import java.util.ArrayList;

import com.google.firebase.database.*;
import org.w3c.dom.Text;


public class ResultsActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    String countryKey= "countries ";

    private ListView listview;
    private ArrayList<String> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);



//

    }


}
