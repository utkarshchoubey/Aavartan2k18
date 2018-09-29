package com.technocracy.app.aavartan.Schedule.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.technocracy.app.aavartan.Attraction.View.AttractionActivity;
import com.technocracy.app.aavartan.Event.View.EventListActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.View.tabs.Tab1;
import com.technocracy.app.aavartan.Schedule.View.tabs.Tab2;
import com.technocracy.app.aavartan.SectionsPagerAdapter;
import com.technocracy.app.aavartan.activity.AccountActivity;
import com.technocracy.app.aavartan.activity.MainActivity;
import com.technocracy.app.aavartan.activity.MapsActivity;
import com.technocracy.app.aavartan.activity.NotificationsActivity;
import com.technocracy.app.aavartan.helper.BottomNavigationViewHelper;

public class ScheduleActivity extends ActionBarActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        setUpViewPager();
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn1:
                        intent = new Intent(ScheduleActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                        break;
                    case R.id.btn2:
                        intent = new Intent(ScheduleActivity.this, EventListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                        break;
                    case R.id.btn3:
                        intent = new Intent(ScheduleActivity.this, AttractionActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                        break;
                    case R.id.btn4:
                        intent = new Intent(ScheduleActivity.this, AccountActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                        break;
                    case R.id.btn5:
                        break;
                }
                return true;
            }
        });

        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            if (i == 4)
                item.setChecked(true);
            else
                item.setChecked(false);
        }
    }

    public void onBackPressed() {
        Intent intent1 = new Intent(ScheduleActivity.this, MainActivity.class);
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
        if (id == R.id.action_notification) {
            Intent intent = new Intent(ScheduleActivity.this, NotificationsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        if (id == R.id.map) {
            Intent intent = new Intent(ScheduleActivity.this, MapsActivity.class);
            ScheduleActivity.this.startActivity(intent);
        }
        return false;
    }

    private void setUpViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        Tab1 tab1 = new Tab1();
        adapter.addFragment(tab1);
        adapter.addFragment(new Tab2());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("7 October");
        tabLayout.getTabAt(1).setText("8 October");
    }
}


