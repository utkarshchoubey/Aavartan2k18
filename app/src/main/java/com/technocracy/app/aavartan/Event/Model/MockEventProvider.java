package com.technocracy.app.aavartan.Event.Model;

import android.os.Handler;

import com.technocracy.app.aavartan.Event.EventCallback;
import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Event.Model.Data.EventData;
import com.technocracy.app.aavartan.Event.RegisterEventCallback;

import java.util.ArrayList;
import java.util.List;

public class MockEventProvider implements EventProvider {
    private EventData mockData;


    public void getEvents(String eventSetId, final EventCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockEventData());
            }
        }, 500);
    }

    private EventData getMockEventData() {
        List<Event> eventList = new ArrayList<>();
        Event event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        event = new Event(1, "A", "ABC", "fun", "6-7 Oct", "5-6 PM", "AA jaoo jhaa bhi hai", "http://www.appimage", "5-6-10", "7-8-10");
        eventList.add(event);
        String type ="fun",image_url="urls.com";
        mockData = new EventData(true, "Success!", eventList);
        return mockData;
    }

    @Override
    public void getFunEvent(final EventCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockEventData());
            }
        }, 500);
    }

    @Override
    public void getManagerialEvent(final EventCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockEventData());
            }
        }, 500);

    }

    @Override
    public void getTechEvent(final EventCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockEventData());
            }
        }, 500);
    }

    @Override
    public void getRoboEvent(final EventCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockEventData());
            }
        }, 500);
    }

    @Override
    public void registerEvent(String userId, String eventId, RegisterEventCallback callback) {

    }
}