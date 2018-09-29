package com.technocracy.app.aavartan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.technocracy.app.aavartan.Attraction.View.AttractionActivity;
import com.technocracy.app.aavartan.Event.View.EventListActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.View.ScheduleActivity;
import com.technocracy.app.aavartan.api.MyEvent;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.BottomNavigationViewHelper;
import com.technocracy.app.aavartan.helper.DatabaseHandler;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button button1;
    Button button2;
    Button stay;
    SessionManager sessionManager;
    SQLiteHandler sqLiteHandler;
    User user;
    TextView user_id, first, last;
    TextView first_name;
    TextView email;
    TextView phone;
    TextView college;
    TextView event;
    TextView member_since;
    private Intent intent;
    private SQLiteHandler uesrDB;
    private List<MyEvent> eventlist;
    private DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_user);
        } catch (NullPointerException e) {
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
        uesrDB = new SQLiteHandler(getApplicationContext());
        user = uesrDB.getUser();

        getMyEvents();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.title));
        mToolbar.setTitle("User");
        mToolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sqLiteHandler = new SQLiteHandler(getApplicationContext());
        db = new DatabaseHandler(getApplicationContext());
        eventlist = db.getAllMyEvents();
        int count = eventlist.size();

        user = sqLiteHandler.getUser();
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserActivity.this, MyEventsActivity.class);
                startActivity(intent);
            }
        });
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(getApplicationContext());
                sessionManager.setLogin(false);
                sqLiteHandler.deleteUsers();
                Intent intent = new Intent(UserActivity.this, AccountActivity.class);
                startActivity(intent);
                finish();
            }
        });

        stay = (Button) findViewById(R.id.stay);
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, StayActivity.class);
                startActivity(intent);
            }
        });
        Date notifDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            notifDate = dateFormat.parse(user.getMember_since());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy h:mm:ss a ");

        String outputdate = outputFormat.format(notifDate);


        user_id = (TextView) findViewById(R.id.user_id);
        first_name = (TextView) findViewById(R.id.first_name);
        first = (TextView) findViewById(R.id.first);
        last = (TextView) findViewById(R.id.last);

        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        college = (TextView) findViewById(R.id.college);
        event = (TextView) findViewById(R.id.event);
        member_since = (TextView) findViewById(R.id.member_since);

        user_id.setText(String.valueOf(user.getUser_id()));
        first_name.setText(user.getFirst_name());

        first.setText(user.getFirst_name());
        last.setText(user.getLast_name());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        college.setText(user.getCollege());
        event.setText(String.valueOf(user.getcount_event_registered()));
        member_since.setText(outputdate);


        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn1:
                        intent = new Intent(UserActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.btn2:
                        intent = new Intent(UserActivity.this, EventListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                        break;
                    case R.id.btn3:
                        intent = new Intent(UserActivity.this, AttractionActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                        break;
                    case R.id.btn4:
                        intent = new Intent(UserActivity.this, AccountActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                        break;
                    case R.id.btn5:
                        intent = new Intent(UserActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        break;
                }
                //   updateNavigationBarState(bottomNavigationView,item.getItemId());
                return true;
            }
        });

        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            if (i == 3)
                item.setChecked(true);
            else
                item.setChecked(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void getMyEvents() {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                "https://beta.aavartan.org/app.android.registered.events/" + String.valueOf(user.getUser_id()), new Response.Listener<String>() {
            //String.valueOf(user.getUser_id())
            @Override
            public void onResponse(String response) {
                Log.d("ayush", "MyEvents Response: " + response.toString());
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Log.d("ayush", "success");
                        JSONArray enlist = jsonResponse.getJSONArray("eventList");
                        SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
                        User updatedUser = sqLiteHandler.getUser();
                        updatedUser.setcount_event_registered(enlist.length());
                        sqLiteHandler.updateeventscount(updatedUser);
                        user = updatedUser;
                        db.deleteAllMyEvents();
                        Log.d("ayush", "eventlist length : " + String.valueOf(enlist.length()));
                        for (int i = 0; i < enlist.length(); i++) {
                            JSONObject jsonObject = enlist.getJSONObject(i);
                            JSONObject eventobject = jsonObject.getJSONObject("event");
                            Log.d("ayush", "id : " + eventobject.getString("date"));
                            Log.d("ayush", "here nahi");
                            MyEvent myEvent = new MyEvent(jsonObject.getInt("id"),
                                    eventobject.getString("event_name"), eventobject.getString("date"));
                            db.addMyEvent(myEvent);
                        }
                        eventlist = db.getAllMyEvents();
                    } else {
                        Snackbar.make(findViewById(R.id.relativeLayout), jsonResponse.getString("error_msg"), Snackbar.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    eventlist = db.getAllMyEvents();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                eventlist = db.getAllMyEvents();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}




