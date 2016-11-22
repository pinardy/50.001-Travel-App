package com.example.user.travelapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.text.DecimalFormat;


/**
 * Created by Pin on 14-Nov-16.
 */

public class ExhaustiveActivity extends AppCompatActivity implements Serializable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhaustive);

        // Extracting the data we put into the algorithm in the previous activity

        TripObject mostOptimal = getIntent().getExtras().getParcelable("Most Optimal");

        TextView textView1 = (TextView)findViewById(R.id.textView1);
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        TextView textView3 = (TextView)findViewById(R.id.textView3);
        TextView textView4 = (TextView)findViewById(R.id.textView4);
        TextView textView5 = (TextView)findViewById(R.id.textView5);
        TextView textView6 = (TextView)findViewById(R.id.textView6);
        TextView textView7 = (TextView)findViewById(R.id.textView7);

        ImageView imageView1 = (ImageView)findViewById(R.id.arrow1);
        ImageView imageView2 = (ImageView)findViewById(R.id.arrow2);
        ImageView imageView3 = (ImageView)findViewById(R.id.arrow3);
        ImageView imageView4 = (ImageView)findViewById(R.id.arrow4);
        ImageView imageView5 = (ImageView)findViewById(R.id.arrow5);
        ImageView imageView6 = (ImageView)findViewById(R.id.arrow6);

        // Display the most optimal path, top down in app
        try {
            textView1.setText(String.valueOf((mostOptimal.getTripList()).get(0)));
        } catch (Exception e){
            textView1.setText("");
        }
        try {
            textView2.setText(String.valueOf((mostOptimal.getTripList()).get(1)));
        } catch (Exception e){
            textView2.setText("");
            imageView1.setImageDrawable(null);
        }
        try {
            textView3.setText(String.valueOf((mostOptimal.getTripList()).get(2)));
        } catch (Exception e){
            textView3.setText("");
            imageView2.setImageDrawable(null);
        }
        try {
            textView4.setText(String.valueOf((mostOptimal.getTripList()).get(3)));
        } catch (Exception e){
            textView4.setText("");
            imageView3.setImageDrawable(null);
        }
        try {
            textView5.setText(String.valueOf((mostOptimal.getTripList()).get(4)));
        } catch (Exception e){
            textView5.setText("");
            imageView4.setImageDrawable(null);
        }
        try {
            textView6.setText(String.valueOf((mostOptimal.getTripList()).get(5)));
        } catch (Exception e){
            textView6.setText("");
            imageView5.setImageDrawable(null);
        }
        try {
            textView7.setText(String.valueOf((mostOptimal.getTripList()).get(6)));
        } catch (Exception e){
            textView7.setText("");
            imageView6.setImageDrawable(null);
        }


        /* Initialize the textviews so that we can manipulate them
         based on the outputs from the algorithm */

        TextView info1 = (TextView)findViewById(R.id.first_info);
        TextView info2 = (TextView)findViewById(R.id.second_info);
        TextView info3 = (TextView)findViewById(R.id.third_info);
        TextView info4 = (TextView)findViewById(R.id.fourth_info);
        TextView info5 = (TextView)findViewById(R.id.fifth_info);
        TextView info6 = (TextView)findViewById(R.id.six_info);
        TextView info7 = (TextView)findViewById(R.id.seven_info);

        /* Editing the information displayed depending on the most optimal path
           Displays Mode, Time, Cost from source to destination
         */
        try {
            info1.setText(String.valueOf((mostOptimal.getTimeAndCostList()).get(0)));
        } catch (Exception e){
            info1.setText("");
        }
        try {
            info2.setText(String.valueOf((mostOptimal.getTimeAndCostList()).get(1)));
        } catch (Exception e){
            info2.setText("");
        }
        try {
            info3.setText(String.valueOf((mostOptimal.getTimeAndCostList()).get(2)));
        } catch (Exception e){
            info3.setText("");
        }
        try {
            info4.setText(String.valueOf((mostOptimal.getTimeAndCostList()).get(3)));
        } catch (Exception e){
            info4.setText("");
        }
        try {
            info5.setText(String.valueOf((mostOptimal.getTimeAndCostList()).get(4)));
        } catch (Exception e){
            info5.setText("");
        }
        try {
            info6.setText(String.valueOf((mostOptimal.getTimeAndCostList()).get(5)));
        } catch (Exception e){
            info6.setText("");
        }
        try {
            info7.setText(String.valueOf((mostOptimal.getTimeAndCostList()).get(6)));
        } catch (Exception e){
            info7.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        TripObject mostOptimal = getIntent().getExtras().getParcelable("Most Optimal");

        // Total time
        Integer totalTime = mostOptimal.getTotalTime();
        String totalTime_string = String.valueOf(totalTime);

        // Total cost
        Double totalCost = mostOptimal.getTotalCost();
        DecimalFormat df = new DecimalFormat("#.00");
        String totalCost_string = df.format(totalCost);

        if (res_id == R.id.totaltime) {
            Toast.makeText(getApplicationContext(), "Total time taken: " + totalTime_string + " minutes",
                    Toast.LENGTH_LONG).show();
        }
        if (res_id == R.id.totalcost) {
            Toast.makeText(getApplicationContext(), "Total cost: $" + totalCost_string,
                    Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
