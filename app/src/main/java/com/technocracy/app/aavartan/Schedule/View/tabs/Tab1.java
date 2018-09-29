package com.technocracy.app.aavartan.Schedule.View.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.Model.RetrofitScheduleProvider;
import com.technocracy.app.aavartan.Schedule.Presenter.SchedulePresenter;
import com.technocracy.app.aavartan.Schedule.Presenter.SchedulePresenterImpl;
import com.technocracy.app.aavartan.Schedule.View.ScheduleAdapter;
import com.technocracy.app.aavartan.Schedule.View.ScheduleView;
import com.technocracy.app.aavartan.helper.DatabaseHandler;

import java.util.List;

public class Tab1 extends Fragment implements ScheduleView {
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private View v;
    private List<Event> scheduleList;
    private DatabaseHandler db;
    private ScheduleAdapter adapter;
    private ProgressBar progressBar;
    private SchedulePresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab_1, container, false);
        db = new DatabaseHandler(getActivity());
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar_tab1);
        presenter = new SchedulePresenterImpl(new RetrofitScheduleProvider(), this, getActivity());
        presenter.getSchedule("7");
        return v;
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(v.findViewById(R.id.tab1_rel_layout), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showScheduleFromDatabase() {
        scheduleList = db.getScheduleDay1Items();
        adapter = new ScheduleAdapter(getActivity(), scheduleList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showSchedule(List<Event> schedule) {
        db.deleteScheduleDay1();
        scheduleList = schedule;
        adapter = new ScheduleAdapter(getActivity(), schedule);
        for (Event x : schedule)
            db.addScheduleDay1Item(x);
        recyclerView.setAdapter(adapter);
    }
}