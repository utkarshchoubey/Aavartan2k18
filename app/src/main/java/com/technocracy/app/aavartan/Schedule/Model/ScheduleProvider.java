package com.technocracy.app.aavartan.Schedule.Model;

import com.technocracy.app.aavartan.Schedule.ScheduleCallback;

public interface ScheduleProvider {
    void getSchedule1(ScheduleCallback callback);

    void getSchedule2(ScheduleCallback callback);
}
