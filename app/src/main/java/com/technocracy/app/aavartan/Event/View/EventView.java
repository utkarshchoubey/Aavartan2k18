package com.technocracy.app.aavartan.Event.View;

import java.util.List;

import com.technocracy.app.aavartan.Event.Model.Data.Event;

public interface EventView {
    void showProgressBar(boolean b);

    void showMessage(String string);

    void showEvents(List<Event> body);
    void showEventsFromDatabase();
}
