package com.example.user.travelapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ItineraryActivity extends AppCompatActivity{

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        ArrayList<Integer> arrayList_int = stringToIntegerArrayList(arrayList);
        EditText budgetEditText = (EditText) findViewById(R.id.budget);


        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.weed);

        btn1=(Button)findViewById(R.id.backItinerary);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                mp1.start();
                Intent anyintent = new Intent(ItineraryActivity.this, HomeActivity.class);
                startActivity(anyintent);
            }
        });
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
            Toast.makeText(ItineraryActivity.this,
                    "Location already chosen", Toast.LENGTH_LONG).show();
        } // if arrayList already has location, do nothing (only toast to inform user)
        else{
            arrayList.add(locationChoice);
        }
        StringBuilder builder = new StringBuilder();
        for (String location:arrayList){
            builder.append("•  "+location + "\n");
        }
        itineraryList.setText(builder.toString());
    }

    public void clearItinerary(View v){
        TextView itineraryList = (TextView) findViewById(R.id.itineraryList);
        itineraryList.setText(""); // Clearing the textview
        arrayList.clear(); // Clearing the arraylist
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

    //get budget, if empty return 0
    private double getBudget() {
        EditText budgetEditText = (EditText) findViewById(R.id.budget);

        double value = Double.parseDouble(budgetEditText.getText().toString().trim());
        return value;
    }

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] output = new int[integers.size()];
        for (int i=0; i < output.length; i++)
        {
            output[i] = integers.get(i).intValue();
        }
        return output;
    }

    public void planItinerary(View v){
        ArrayList<Integer> arrayList_int = stringToIntegerArrayList(arrayList);

        try {
            TripObject mostOptimal = ExhaustiveEnum.fullFunction(arrayList_int, getBudget());
            passOnResults(mostOptimal);
        } catch (NumberFormatException num_e){
            Toast.makeText(ItineraryActivity.this,
                    "Enter your budget", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(ItineraryActivity.this,
                    "Empty Itinerary", Toast.LENGTH_LONG).show();
            Toast.makeText(ItineraryActivity.this,
                    "Choose your location(s)", Toast.LENGTH_LONG).show();
        }
    }

    public void planItinerary_fast(View v){
        ArrayList<Integer> arrayList_int = stringToIntegerArrayList(arrayList);
        int[] fastInput = convertIntegers(arrayList_int);
        FastSolver smart = new FastSolver();

        try {
            if (fastInput.length == 0){
                throw new Exception("Empty Itinerary");
            }
            else {
                smart.setDestination(fastInput);
                smart.setBudget(getBudget());
                smart.computeModeOfTransport();
                passOnResults_fast(smart);
            }
        } catch (NumberFormatException num_e){
            Toast.makeText(ItineraryActivity.this,
                    "Enter your budget", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(ItineraryActivity.this,
                    "Empty Itinerary", Toast.LENGTH_LONG).show();
            Toast.makeText(ItineraryActivity.this,
                    "Choose your location(s)", Toast.LENGTH_LONG).show();
        }


    }

    private void passOnResults(TripObject mostOptimalPath) {
        Intent i = new Intent(getApplicationContext(), ExhaustiveActivity.class);
        i.putExtra("Most Optimal",  mostOptimalPath);
        startActivity(i);
    }

    private void passOnResults_fast(FastSolver fastOptimalPath) {
        Intent i_fast = new Intent(getApplicationContext(), FastActivity.class);
        i_fast.putExtra("Fast Optimal", fastOptimalPath);
        startActivity(i_fast);

    }

}

