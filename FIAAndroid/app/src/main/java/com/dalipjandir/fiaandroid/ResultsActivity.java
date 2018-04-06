package com.dalipjandir.fiaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import org.w3c.dom.Text;


public class ResultsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("0/Country"); //field = test

        //reading from database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override //what you want to do when data is changed
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(value);

            }

            @Override //what to do when you fail to read data
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void writeToDatabase(){
        //writing to database

        myRef.setValue("hello there"); //value = hello there
    }



}
