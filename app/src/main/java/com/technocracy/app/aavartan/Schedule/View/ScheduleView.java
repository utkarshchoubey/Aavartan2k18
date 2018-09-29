package com.technocracy.app.aavartan.Schedule.View;

import com.technocracy.app.aavartan.Event.Model.Data.Event;

import java.util.List;

public interface ScheduleView {
    void showProgressBar(boolean b);

    void showMessage(String message);

    void showScheduleFromDatabase();

    void showSchedule(List<Event> schedule);

}
