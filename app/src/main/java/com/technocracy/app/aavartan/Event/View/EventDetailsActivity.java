package com.technocracy.app.aavartan.Event.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technocracy.app.aavartan.Event.Model.RetrofitEventProvider;
import com.technocracy.app.aavartan.Event.Presenter.EventPresenter;
import com.technocracy.app.aavartan.Event.Presenter.EventPresenterImpl;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.activity.AccountActivity;
import com.technocracy.app.aavartan.activity.NotificationsActivity;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

public class EventDetailsActivity extends AppCompatActivity implements RegisterEventView {

    SQLiteHandler sqLiteHandler;
    User user;
    private TextView eventDetailsTextView;
    private String eventDetail;
    private String event_id;
    private DrawerLayout drawer;
    private Button registerEventButton;
    private Toolbar toolbar;
    private EventPresenter presenter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Bundle data = getIntent().getExtras();
        eventDetail = data.getString("event_description");
        String title = data.getString("event_name");
        event_id = "" + data.getInt("id");
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.title));
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        eventDetailsTextView = (TextView) findViewById(R.id.event_details_textView);
        eventDetailsTextView.setText(eventDetail);

        registerEventButton = (Button) findViewById(R.id.registerbutton);
        if (data.getInt("id") == 6)
            registerEventButton.setVisibility(View.GONE);

        registerEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                if (sessionManager.isLoggedIn()) {
                    sqLiteHandler = new SQLiteHandler(getApplicationContext());
                    user = sqLiteHandler.getUser();
                    final String user_id = String.valueOf(user.getUser_id());
                    presenter = new EventPresenterImpl(EventDetailsActivity.this, new RetrofitEventProvider(), EventDetailsActivity.this);
                    presenter.registerEvent(user_id, event_id);
                } else {
                    Snackbar.make(findViewById(R.id.drawer_layout), "Please Login First!", Snackbar.LENGTH_LONG)
                            .setAction("Login", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(EventDetailsActivity.this, AccountActivity.class);
                                    startActivity(i);
                                }
                            }).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            case R.id.action_notification:
                Intent intent = new Intent(EventDetailsActivity.this, NotificationsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
        Snackbar.make(findViewById(R.id.drawer_layout), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
/*
    public void registerEvent(final String id, final String user_id) {
        String tag_string_req = "req_registerEvent";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                App.REGISTER_EVENT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean error = jsonResponse.getBoolean("error");
                    if (!error) {

                        Snackbar.make(findViewById(R.id.drawer_layout), "Registration Successful!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(findViewById(R.id.drawer_layout), jsonResponse.getString("error_msg"), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(findViewById(R.id.drawer_layout), getResources().getString(R.string.no_internet_error), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", user_id);
                params.put("event_id", id);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }*/
}

