package com.technocracy.app.aavartan.Attraction.Model.Data;

public class Attraction {
    private String name, description, img_url, date, venue;

    public Attraction(String name, String date, String venue, String description, String img_url) {
        this.name = name;
        this.description = description;
        this.img_url = img_url;
        this.date = date;
        this.venue = venue;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return img_url;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }
}