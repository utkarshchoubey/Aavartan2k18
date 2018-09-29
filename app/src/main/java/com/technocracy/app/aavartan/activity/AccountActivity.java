package com.technocracy.app.aavartan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.technocracy.app.aavartan.Attraction.View.AttractionActivity;
import com.technocracy.app.aavartan.Event.View.EventListActivity;
import com.technocracy.app.aavartan.LoginFragment;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.View.ScheduleActivity;
import com.technocracy.app.aavartan.SectionsPagerAdapter;
import com.technocracy.app.aavartan.SignupFragment;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.helper.BottomNavigationViewHelper;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

public class AccountActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 3;
    private Intent intent;
    private Toolbar mtoolbar;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mtoolbar=(Toolbar)findViewById(R.id.loginbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Your Account");
        setUpViewPager();
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
            User user = sqLiteHandler.getUser();
            Intent intent = new Intent(AccountActivity.this, UserActivity.class);
            startActivity(intent);
            finish();
        }
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn1:
                        intent = new Intent(AccountActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.btn2:
                        intent = new Intent(AccountActivity.this, EventListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                        break;
                    case R.id.btn3:
                        intent = new Intent(AccountActivity.this, AttractionActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                        break;
                    case R.id.btn4:
                        break;
                    case R.id.btn5:
                        intent = new Intent(AccountActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        break;
                }
             //   updateNavigationBarState(bottomNavigationView,item.getItemId());
                return true;
            }
        });

        Menu menu=bottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            if(i==3)
                item.setChecked(true);
            else
                item.setChecked(false);
        }
    }
   /* private void updateNavigationBarState(BottomNavigationView bottomNavigationView,int actionId){
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == actionId);
        }
    }*/
    @Override
    public void onBackPressed()
    {
        Intent intent1=new Intent(AccountActivity.this,MainActivity.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    private void setUpViewPager()
    {
        SectionsPagerAdapter adapter=new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment());
        adapter.addFragment(new SignupFragment());
        ViewPager viewPager=(ViewPager)findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Login");
        tabLayout.getTabAt(1).setText("Signup");
    }


}
