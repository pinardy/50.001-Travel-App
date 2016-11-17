package com.example.user.travelapp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {
    private List<Place> placeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PlaceInfoAdapter pAdapter;
    private int noOfPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);


        pAdapter = new PlaceInfoAdapter(placeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new Divider(this, LinearLayoutManager.VERTICAL));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            public void onClick(View view, int position) {
                Place place = placeList.get(position);
                Toast.makeText(getApplicationContext(), place.getPlace() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            public void onLongClick(View view, int position) {

            }
        }));

        recyclerView.setAdapter(pAdapter);

        preparePlaceData();

        for(int i=0; i<noOfPlaces; i++){
            String webUrl = placeList.get(i).getDetails();



        }
    }

    private void preparePlaceData() {
        Place place = new Place("temple","Buddha Tooth Relic Temple","The Buddha Tooth Relic Temple and Museum is a Buddhist temple and museum complex located in the Chinatown district of Singapore.", "https://www.wikipedia.org/wiki/Buddha_Tooth_Relic_Temple_and_Museum");
        placeList.add(place);

        place = new Place("mbs", "Marina Bay Sands", "Marina Bay Sands is an integrated resort fronting Marina Bay in Singapore. At its opening in 2010, it was billed as the world's most expensive standalone casino property at S$8 billion, including the land cost.", "https://www.wikipedia.org/wiki/Marina_Bay_Sands");
        placeList.add(place);

        place = new Place("rws", "Resorts World Sentosa", "Resorts World Sentosa is an integrated resort on the island of Sentosa, off the southern coast of Singapore. The key attractions include one of Singapore's two casinos, a Universal Studios theme park, Adventure Cove Water Park, and S.E.A. Aquarium, which includes the world's largest oceanarium.", "https://www.wikipedia.org/wiki/Resorts_World_Sentosa");
        placeList.add(place);

        place = new Place("flyer", "Singapore Flyer", "The Singapore Flyer is a giant Ferris wheel in Singapore. Described by its operators as an observation wheel, it opened in 2008, construction having taken about 2½ years. It carried its first paying passengers on 11 February, opened to the public on 1 March, and was officially opened on 15 April. It has 28 air-conditioned capsules, each able to accommodate 28 passengers, and incorporates a three-storey terminal building.", "https://www.wikipedia.org/wiki/Singapore_Flyer");
        placeList.add(place);

        place = new Place("zoo", "Singapore Zoo", "The Singapore Zoo, formerly known as the Singapore Zoological Gardens and commonly known locally as the Mandai Zoo, occupies 28 hectares (69 acres) on the margins of Upper Seletar Reservoir within Singapore's heavily forested central catchment area.", "https://www.wikipedia.org/wiki/Singapore_Zoo");
        placeList.add(place);

        place = new Place("vivo", "VivoCity", "VivoCity (Chinese: 怡丰城) is the largest shopping mall in Singapore. Located in the HarbourFront precinct of Bukit Merah, it was designed by the Japanese architect Toyo Ito. Its name is derived from the word vivacity.", "https://www.wikipedia.org/wiki/VivoCity");
        placeList.add(place);

        noOfPlaces = 6;
        pAdapter.notifyDataSetChanged();
    }
}
