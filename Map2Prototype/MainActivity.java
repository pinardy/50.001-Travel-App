package com.example.lenovo.map1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    private static String[] Places = new String[]{"Abdul Gaffoor Mosque", "Al-Abrar Mosque", "ArtScience Museum", "Asian Civilisations Museum", "Bright Hill Temple (Khong Meng San Phor Kark See Temple)", "Buddha Tooth Relic Temple", "Bukit Timah Nature Reserve", "Cathedral of the Good Shepherd", "Central Catchment Nature Reserve", "Central Sikh Temple", "Changi Prison Chapel and Museum", "Chijmes", "Chinatown Heritage Centre", "Chinese and Japanese Gardens", "Chinese Methodist Church", "Church of St Gregory the Illuminator", "Crane Dance", "East Coast Park", "Esplanade - Theatres on the Bay", "Eu Yan Sang Chinese Medical Hall", "Fort Canning Park", "Gardens by the Bay", "Geylang Serai Market", "G-Max Reverse Bungy", "Hajjah Fatimah Mosque", "Haw Par Villa", "HortPark", "House of Tan Teng Niah", "Images of Singapore", "Istana", "Jamae Mosque", "Joo Chiat Street", "Jurong Bird Park", "Katong", "Kranji War Memorial", "Kusu Island", "Kwan Im Thong", "Hood Cho Temple", "Lau Pa Sat (Telok Ayer Market)", "Leong San See Temple", "Maghain Aboth Synagogue", "Malay Heritage Centre", "Malay Village",
            "Mandai Orchid Gardens", "Marina Barrage", "Marina Bay Sands Casino", "Marina Bay Sands SkyPark", "Maxwell Road Hawker Centre", "Merlion Park", "Mount Faber Park", "Nagore Durgha Shrine", "National Museum of Singapore", "National Orchid Garden", "NEWater Visitor Centre", "Old Parliament House", "Peranakan Museum", "Pulau Ubin", "Raffles Hotel", "Raffles Place", "Red Dot Design Museum", "Resort World Sentosa Casino", "Sentosa Skyline Luge and Skyride", "Singapore Art Museum", "Singapore Bazaar and Flea Market", "Singapore Botanic Gardens", "Singapore Butterfly and Insect Kingdom", "Singapore Discovery Centre", "Singapore F1 Grand Prix","Singapore Flyer", "Singapore Mint Coin Gallery", "Singapore Navy Museum", "Singapore Night Safari", "Singapore Philatelic Museum", "Singapore River Science Centre", "Singapore", "Singapore Zoo", "Siong Lim Temple", "Sisters' Islands", "Songs of the Sea", "Sri Krishnan Temple", "Sri Mariamman Temple", "Sri Srinivasa Perumal Temple", "Sri Thandayuthapani Temple", "Sri Veeramakaliamman Temple", "St Andrew's Cathedral", "St John's Island", "Statues of Sir Stamford Raffles", "Sultan Mosque", "Sungei Buloh Nature Park",
            "Supreme Court & City Hall", "Temple of 1,000 Lights (Sakyamuni Buddha Gaya Temple)", "The Padang", "The Southern Ridges", "Thian Hock Keng Temple", "Treetop Walk at MacRitchie Reservoir", "Underwater World", "Universal Studios Singapore", "Wonder Full at Marina Bay Sands", "Sentosa", "Skyline"};
    ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Places);
        autoCompleteTextView.setAdapter(adapter);

//        final EditText addressfield = (EditText) findViewById(R.id.editText);
        final Button launchmapbtn = (Button) findViewById(R.id.button);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                Toast toast= Toast.makeText(getApplicationContext(), s + " is clicked", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0); toast.show();
            }
        });

        assert launchmapbtn != null;
        launchmapbtn.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                try {
                    String address = autoCompleteTextView.getText().toString();
                    address = address.replace(' ', '+');
                    Intent geoIntent = new Intent (android.content.Intent.ACTION_VIEW, Uri.parse ("geo:0,0?q=" + address)); //
                    startActivity(geoIntent); 
                } catch (Exception e){
                }

            }
        });
    }
}
