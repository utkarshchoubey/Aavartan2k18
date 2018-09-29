package com.technocracy.app.aavartan.api;

public class MyEvent {
    private int id;
    private String eventName;
    private String eventDate;

    public MyEvent(int id, String eventName, String eventDate) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }
}
