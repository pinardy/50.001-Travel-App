package com.example.pin.itinerarytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO: Clear button
    ArrayList<String> arrayList = new ArrayList<String>();
    public void addToItinerary(View v){
        //display the list of locations to visit
        TextView itineraryList = (TextView) findViewById(R.id.itineraryList);

        //store name of location from dropdown into a string
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String locationChoice = mySpinner.getSelectedItem().toString();

        //store the strings into an arraylist
        if (arrayList.contains(locationChoice)) {
        } // if arrayList already has location, do nothing
        else{
            arrayList.add(locationChoice);
        }
        StringBuilder builder = new StringBuilder();
        for (String location:arrayList){
            builder.append(location + "\n");
        }
        itineraryList.setText(builder.toString());
    }

    public void clearItinerary(View v){
        TextView itineraryList = (TextView) findViewById(R.id.itineraryList);
        itineraryList.setText(""); //Clearing the textview
        arrayList.clear(); //Clearing the arraylist
    }
}
