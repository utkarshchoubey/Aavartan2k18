package com.technocracy.app.aavartan.Schedule.Model;

import android.os.Handler;

import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;
import com.technocracy.app.aavartan.Schedule.ScheduleCallback;

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
        if (day.equals("7")) {

        } else {
        }
        //mockScheduleData = new ScheduleData(true, "Success", list);
        return mockScheduleData;
    }
}
