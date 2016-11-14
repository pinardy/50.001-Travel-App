package com.example.lenovo.map1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final EditText addressfield = (EditText) findViewById(R.id.editText);
        final Button launchmapbtn = (Button) findViewById(R.id.button);

        launchmapbtn.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                try {
                    String address = addressfield.getText().toString();
                    address = address.replace(' ', '+');
                    Intent geoIntent = new Intent (android.content.Intent.ACTION_VIEW, Uri.parse ("geo:0,0?q=" + address)); //
                    startActivity(geoIntent); 
                } catch (Exception e){
                }
            }
        });
    }
}
