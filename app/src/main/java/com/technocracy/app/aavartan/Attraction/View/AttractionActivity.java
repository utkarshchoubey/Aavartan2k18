package com.technocracy.app.aavartan.Attraction.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.technocracy.app.aavartan.Attraction.Model.Data.Attraction;
import com.technocracy.app.aavartan.Attraction.Model.RetrofitAttractionProvider;
import com.technocracy.app.aavartan.Attraction.Presenter.AttractionPresenter;
import com.technocracy.app.aavartan.Attraction.Presenter.AttractionPresenterImpl;
import com.technocracy.app.aavartan.Event.View.EventListActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.View.ScheduleActivity;
import com.technocracy.app.aavartan.activity.AccountActivity;
import com.technocracy.app.aavartan.activity.MainActivity;
import com.technocracy.app.aavartan.activity.NotificationsActivity;
import com.technocracy.app.aavartan.helper.BottomNavigationViewHelper;
import com.technocracy.app.aavartan.helper.DatabaseHandler;
import com.technocracy.app.aavartan.helper.Eventkeys;

import java.util.List;

public class AttractionActivity extends AppCompatActivity implements Eventkeys.Attractions, AttractionView {

    private RecyclerView rCyclerView;
    private AttractionAdapter Adap;
    private DatabaseHandler db;
    private ProgressBar progressBar;
    private AttractionPresenter presenter;
    private Intent intent;
    private List<Attraction> attractionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Attractions");
        db = new DatabaseHandler(getApplicationContext());
        rCyclerView = (RecyclerView) findViewById(R.id.rView);
        rCyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setItemBackgroundResource(R.color.lightback);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn1:
                        intent = new Intent(AttractionActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;
                    case R.id.btn2:
                        intent = new Intent(AttractionActivity.this, EventListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;
                    case R.id.btn3:
                        return true;
                    case R.id.btn4:
                        intent = new Intent(AttractionActivity.this, AccountActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return true;
                    case R.id.btn5:
                        intent = new Intent(AttractionActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return true;
                }
                return true;
            }
        });
        Menu menu = bottomNavigationView.getMenu();

        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            if (i == 2)
                item.setChecked(true);
            else
                item.setChecked(false);
        }
        presenter = new AttractionPresenterImpl(new RetrofitAttractionProvider(), this, this);
        presenter.getAttractions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            Intent intent = new Intent(AttractionActivity.this, NotificationsActivity.class);
            AttractionActivity.this.startActivity(intent);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(AttractionActivity.this,MainActivity.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showAttractionsFromDatabase() {
        attractionList = db.getAllAttractions();
        Adap = new AttractionAdapter(this, attractionList);
        rCyclerView.setAdapter(Adap);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.attraction_rel_layout), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showAttractions(List<Attraction> AttractionList) {
        db.deleteAllAttractions();
        attractionList = AttractionList;
        for(Attraction i:attractionList)
            db.addAttraction(i);
        Adap = new AttractionAdapter(this, attractionList);
        rCyclerView.setAdapter(Adap);
    }
}