package com.technocracy.app.aavartan.Event.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.technocracy.app.aavartan.Attraction.View.AttractionActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.View.ScheduleActivity;
import com.technocracy.app.aavartan.activity.AccountActivity;
import com.technocracy.app.aavartan.activity.MainActivity;
import com.technocracy.app.aavartan.activity.MapsActivity;
import com.technocracy.app.aavartan.activity.NotificationsActivity;
import com.technocracy.app.aavartan.helper.BottomNavigationViewHelper;

public class EventListActivity extends AppCompatActivity {

    private Intent intent;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.menubar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Events");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_event_set);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EventSetAdapter adapter = new EventSetAdapter(this);
        recyclerView.setAdapter(adapter);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        //bottomNavigationView.setItemBackgroundResource(R.color.white);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn1:
                        intent = new Intent(EventListActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.btn2:
                        break;
                    case R.id.btn3:
                        intent = new Intent(EventListActivity.this, AttractionActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        break;
                    case R.id.btn4:
                        intent = new Intent(EventListActivity.this, AccountActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        break;
                    case R.id.btn5:
                        intent = new Intent(EventListActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                }
                //     updateNavigationBarState(bottomNavigationView,item.getItemId());
                return true;
            }
        });
        Menu menu = bottomNavigationView.getMenu();

        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            if (i == 1)
                item.setChecked(true);
            else
                item.setChecked(false);
        }
    }

    private void updateNavigationBarState(BottomNavigationView bottomNavigationView, int actionId) {
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == actionId);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(EventListActivity.this, MainActivity.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
            Intent intent = new Intent(EventListActivity.this, NotificationsActivity.class);
            EventListActivity.this.startActivity(intent);
        }if (id == R.id.map) {
            Intent intent = new Intent(EventListActivity.this, MapsActivity.class);
            EventListActivity.this.startActivity(intent);
        }
        return false;
    }
}