package com.technocracy.app.aavartan.Schedule;

import com.technocracy.app.aavartan.Event.Model.Data.Event;

/**
 * Created by Abhi on 24-Sep-17.
 */

public interface EventByIdCallback {
    void onSuccess(Event body);

    void onFailure();
}
