package com.technocracy.app.aavartan.Schedule.Api;

import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;
import com.technocracy.app.aavartan.helper.App;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ScheduleApi {
    @GET(App.SCHEDULE1)
    Call<ScheduleData> getSchedule1();

    @GET(App.SCHEDULE2)
    Call<ScheduleData> getSchedule2();
}
