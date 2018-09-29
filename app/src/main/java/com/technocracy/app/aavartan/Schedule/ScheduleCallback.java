package com.technocracy.app.aavartan.Schedule;

import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;

public interface ScheduleCallback {
    void onSuccess(ScheduleData body);

    void onFailure();

}
