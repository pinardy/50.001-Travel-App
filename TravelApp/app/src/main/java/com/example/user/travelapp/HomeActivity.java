package com.example.user.travelapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView t1,t2,t3;
    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.bruh);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.rn);
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.sound2);

        btn1=(Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Intent anyintent = new Intent(HomeActivity.this, InfoActivity.class);
//                startActivity(anyintent);
                mp1.start();
            }
        });

        btn2=(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Intent anyintent = new Intent(HomeActivity.this, InfoActivity.class);
//                startActivity(anyintent);
                mp2.start();
            }
        });


        btn3=(Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent anyintent = new Intent(HomeActivity.this, InfoActivity.class);
                startActivity(anyintent);
                mp3.start();
            }
                               });


        t1=(TextView)findViewById(R.id.my_text);
        Typeface my_customfont=Typeface.createFromAsset(getAssets(),"fonts/Questrial.ttf");
        t1.setTypeface(my_customfont);

        t2=(TextView)findViewById(R.id.my_text2);
        Typeface my_customfont2=Typeface.createFromAsset(getAssets(),"fonts/Questrial.ttf");
        t2.setTypeface(my_customfont2);

        t3=(TextView)findViewById(R.id.my_text3);
        Typeface my_customfont3=Typeface.createFromAsset(getAssets(),"fonts/Questrial.ttf");
        t3.setTypeface(my_customfont3);

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