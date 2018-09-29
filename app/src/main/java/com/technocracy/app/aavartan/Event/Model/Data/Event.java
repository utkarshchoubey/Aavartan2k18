package com.technocracy.app.aavartan.Event.Model.Data;


public class Event {
    int id;
    private String type, event_name, description, date,
            time, venue, image_url, created_at, updated_at;

    public Event(int id, String event_name, String description, String type,
                 String date, String time, String venue, String image_url,
                 String created_at, String updated_at) {
        this.id = id;
        this.event_name = event_name;
        this.description = description;
        this.type = type;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.image_url = image_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getEventId() {
        return id;
    }

    public String getName() {
        return event_name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public String getType() {
        return type;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}