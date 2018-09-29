package com.technocracy.app.aavartan.Schedule.Model.Data;

import com.technocracy.app.aavartan.Event.Model.Data.Event;

import java.util.List;

/**
 * Created by Abhi on 01-Sep-17.
 */

public class ScheduleData {
    private boolean success;
    private String message;
    private List<Event> eventList;

    public ScheduleData(boolean success, String message, List<Event> schedule) {
        this.success = success;
        this.message = message;
        this.eventList = schedule;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Event> getSchedule() {
        return eventList;
    }
}