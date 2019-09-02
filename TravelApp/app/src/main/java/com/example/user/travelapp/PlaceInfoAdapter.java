package com.example.user.travelapp;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlaceInfoAdapter extends RecyclerView.Adapter<PlaceInfoAdapter.MyViewHolder> {

    private List<Place> placesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView place, description, details;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.imageViewer);
            place = (TextView) view.findViewById(R.id.place);
            description = (TextView) view.findViewById(R.id.description);
            details = (TextView) view.findViewById(R.id.details);
        }
    }


    public PlaceInfoAdapter(List<Place> placesList) {
        this.placesList = placesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Place place = placesList.get(position);
        Context context = holder.image.getContext();
        int ResID = context.getResources().getIdentifier(place.getImagePath(), "drawable", context.getPackageName());
        holder.image.setImageResource(ResID);
        holder.place.setText(place.getPlace());
        holder.description.setText(place.getDescription());
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }
}