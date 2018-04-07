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


public class MostSearchedActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    String countryKey;

    private ListView listview;
    private ArrayList<String> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_searched);


        //GET COUNTRIES with weight greater than or equal to 2
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child("Countries").orderByChild("Weight").startAt("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                    countryKey = childSnapshot.getKey() + "; " + countryKey;
                }
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setText(countryKey);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //GETTING FIELDS OF A COUNTRY AND DISPLAYING IN LISTVIEW
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("Countries").child("Afghantistan");
//
//        listview = (ListView) findViewById(R.id.newListView);
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,details);
//        listview.setAdapter(arrayAdapter);
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String value = dataSnapshot.getValue(String.class);
//                details.add(value);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



        ///////
//        //FUNCTIONING: SETTING AND DISPLAYING VALUE
//
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("Countries").child("Afghanistan").child("Weight");
//        myRef.setValue("2"); //changes the value associated with weight
//
//        //reading from database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override //what you want to do when data is changed
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class); //retrieves weight value
//                TextView textView = (TextView) findViewById(R.id.textView);
//                textView.setText(value);
//
//            }
//
//            @Override //what to do when you fail to read data
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}
