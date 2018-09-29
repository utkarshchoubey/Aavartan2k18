package com.technocracy.app.aavartan.Event;


import com.technocracy.app.aavartan.Event.Model.Data.EventData;

public interface EventCallback {

    void onFailure();

    void onSuccess(EventData body);

}
