package com.technocracy.app.aavartan.Schedule.Model;

import android.os.Handler;

import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;
import com.technocracy.app.aavartan.Schedule.ScheduleCallback;

import java.util.ArrayList;

public class MockScheduleProvider implements ScheduleProvider {

    private ScheduleData mockScheduleData;
    private Event event;
    @Override
    public void getSchedule1(final ScheduleCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockScheduleData("7"));
            }
        }, 500);
    }
    @Override
    public void getSchedule2(final ScheduleCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockScheduleData("8"));
            }
        }, 500);
    }

    private Event getMockEventByIdData(String eventId) {
        return event;
    }

    public ScheduleData getMockScheduleData(String day) {
        Event event;
        ArrayList<Event> eventList = new ArrayList<>();
        event = new Event(1, "A", "ABC", "tech", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "tech", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "tech", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "tech", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "tech", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "tech", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "tech", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "tech", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "tech", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
//        if (day.equals("7")) {
//        } else {
//        }
        mockScheduleData = new ScheduleData(true, "Success", eventList);
        return mockScheduleData;
    }
}
