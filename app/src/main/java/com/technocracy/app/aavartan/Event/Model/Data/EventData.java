package com.technocracy.app.aavartan.Event.Model.Data;

import java.util.List;

public class EventData {
    private boolean success;
    private String message;
    private List<Event> eventList;
    public EventData(boolean success, String message, List<Event> eventList) {
        this.success = success;
        this.message = message;
        this.eventList = eventList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Event> getEventList() {
        return eventList;
    }
}
