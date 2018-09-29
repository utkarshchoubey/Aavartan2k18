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
        String type ="fun",image_url="urls.com";
        mockData = new EventData(true, "Success!", eventList);
        return mockData;
    }

    @Override
    public void getFunEvent(EventCallback callback) {

    }

    @Override
    public void getManagerialEvent(EventCallback callback) {

    }

    @Override
    public void getTechEvent(EventCallback callback) {

    }

    @Override
    public void getRoboEvent(EventCallback callback) {

    }

    @Override
    public void registerEvent(String userId, String eventId, RegisterEventCallback callback) {

    }
}