package com.example.user.travelapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView t;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn=(Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent anyintent = new Intent(HomeActivity.this, InfoActivity.class);
                startActivity(anyintent);
            }
                               });




        t=(TextView)findViewById(R.id.my_text);
        Typeface my_customfont=Typeface.createFromAsset(getAssets(),"fonts/Questrial.ttf");
        t.setTypeface(my_customfont);

        t=(TextView)findViewById(R.id.my_text2);
        Typeface my_customfont2=Typeface.createFromAsset(getAssets(),"fonts/Questrial.ttf");
        t.setTypeface(my_customfont2);

        t=(TextView)findViewById(R.id.my_text3);
        Typeface my_customfont3=Typeface.createFromAsset(getAssets(),"fonts/Questrial.ttf");
        t.setTypeface(my_customfont3);
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Are you sure you want to exit the application?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}