package com.example.user.travelapp;

/**
 * Created by muayanfrost on 17/11/16.
 */

public class Place {
    private String imagePath, place, description, details;

    public Place() {
    }

    public Place(String imagePath, String place, String description, String details) {
        this.imagePath = imagePath;
        this.place = place;
        this.description = description;
        this.details = details;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
