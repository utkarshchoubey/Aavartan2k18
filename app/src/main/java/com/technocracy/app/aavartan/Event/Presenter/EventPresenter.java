package com.technocracy.app.aavartan.Event.Presenter;

public interface EventPresenter {

    void getEvents(String eventSetId);

    void registerEvent(String userId, String eventId);
}
