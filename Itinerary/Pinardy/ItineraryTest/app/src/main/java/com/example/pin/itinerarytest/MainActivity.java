package com.example.pin.itinerarytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**@param arraylist:
     * stores the strings of the locations the user intends to visit
     */
    ArrayList<String> arrayList = new ArrayList<String>();
    public void addToItinerary(View v){
        //display the list of locations to visit
        TextView itineraryList = (TextView) findViewById(R.id.itineraryList);

        //store name of location from dropdown into a string
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String locationChoice = mySpinner.getSelectedItem().toString();

        //store the strings into an arraylist
        if (arrayList.contains(locationChoice)) {
            Toast.makeText(MainActivity.this,
                    "Location already chosen", Toast.LENGTH_LONG).show();
        } // if arrayList already has location, do nothing (only toast to inform user)
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

    /**
     * @param list_string
     * Convert from ArrayList<String> to ArrayList<Integer>
     *
     * Purpose of this function is to be able to read the user input from
     * the app in the form of integers as the data is in the form of integers
     *
     */
    public ArrayList<Integer> stringToIntegerArrayList(ArrayList<String> list_string){
        //instantiating the output (An arraylist of integers)
        ArrayList<Integer> arrayList_int = new ArrayList<Integer>();


        if (list_string.contains("Singapore Flyer")) {
            arrayList_int.add(1);
        }
        if (list_string.contains("Vivo City")) {
            arrayList_int.add(2);
        }
        if (list_string.contains("Resorts World Sentosa")) {
            arrayList_int.add(3);
        }
        if (list_string.contains("Buddha Tooth Relic Temple")) {
            arrayList_int.add(4);
        }
        if (list_string.contains("Zoo")) {
            arrayList_int.add(5);
        }

        return arrayList_int;
    }

    public void planItinerary(View v){
        // Bring us to the itinerary screen
        Intent myIntent = new Intent(this, ExhaustiveActivity.class);
        startActivity(myIntent);

        /**Data to be passed into algorithm
         * @param arrayList_int (ArrayList<Integer)
         * @param budget  (Double)
         */
        ArrayList<Integer> arrayList_int = stringToIntegerArrayList(arrayList);
        EditText budgetEditText = (EditText) findViewById(R.id.budget);
        Double budget = Double.valueOf(budgetEditText.getText().toString());

        //pass data into algorithm
        TripObject mostOptimal = ExhaustiveEnum.fullFunction(arrayList_int,budget);

        //data obtained
        Integer totalTime = mostOptimal.getTotalTime();
        Double totalCost = mostOptimal.getTotalCost();
        ArrayList<String> tripList = mostOptimal.getTripList();
        ArrayList<String> timeCostList = mostOptimal.getTimeAndCostList();

    }
}
