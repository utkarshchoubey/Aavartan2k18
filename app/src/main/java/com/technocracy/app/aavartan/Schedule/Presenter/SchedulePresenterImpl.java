package com.technocracy.app.aavartan.Schedule.Presenter;

import android.content.Context;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;
import com.technocracy.app.aavartan.Schedule.Model.ScheduleProvider;
import com.technocracy.app.aavartan.Schedule.ScheduleCallback;
import com.technocracy.app.aavartan.Schedule.View.ScheduleView;

public class SchedulePresenterImpl implements SchedulePresenter {
    private ScheduleProvider provider;
    private ScheduleView view;
    private Context context;

    public SchedulePresenterImpl(ScheduleProvider provider, ScheduleView view, Context context) {
        this.provider = provider;
        this.view = view;
        this.context = context;
    }

    @Override
    public void getSchedule(String day) {
        view.showProgressBar(true);
        if (day.equals("7")) {
            provider.getSchedule1(new ScheduleCallback() {
                @Override
                public void onSuccess(ScheduleData body) {
                    view.showProgressBar(false);
                    if (body.isSuccess()) {
                        view.showSchedule(body.getSchedule());
                    } else {
                        view.showScheduleFromDatabase();
                        view.showMessage(body.getMessage() + "In Success False!");
                    }
                }

                @Override
                public void onFailure() {
                    view.showProgressBar(false);
                    view.showMessage("Connection Error! Please check your connection");
                    view.showScheduleFromDatabase();
                }
            });
        } else {
            provider.getSchedule2(new ScheduleCallback() {
                @Override
                public void onSuccess(ScheduleData body) {
                    view.showProgressBar(false);
                    if (body.isSuccess()) {
                        view.showSchedule(body.getSchedule());
                    } else {
                        view.showScheduleFromDatabase();
                        view.showMessage(body.getMessage());
                    }
                }

                @Override
                public void onFailure() {
                    view.showProgressBar(false);
                    view.showMessage("Connection Error! Please check your connection");
                    view.showScheduleFromDatabase();
                }
            });
        }
    }
}