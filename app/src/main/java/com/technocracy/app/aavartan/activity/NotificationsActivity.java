package com.technocracy.app.aavartan.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.adapter.NotificationAdapter;
import com.technocracy.app.aavartan.api.Notifications;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.DatabaseHandler;
import com.technocracy.app.aavartan.helper.SessionManager;
import com.technocracy.app.aavartan.util.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationsActivity extends AppCompatActivity {
    private List<Notifications> notificationsList;
    private RecyclerView recyclerView;
    private NotificationAdapter mAdapter;
    private Toolbar mToolbar;
    private TextView noNotificationTextView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //  mToolbar.setTitleTextColor(Color.);
        mToolbar.setTitle("Notifications");
        setSupportActionBar(mToolbar);
        noNotificationTextView = (TextView) findViewById(R.id.noNotificationsTextView);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new NotificationAdapter(notificationsList, getApplicationContext());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(App.SWIPE_REFRESH_COLORS);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                preparenotificationdata();
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                preparenotificationdata();
            }
        });

    }

    private void preparenotificationdata() {
        String tag_string_req = "req_getNotification";
        swipeRefreshLayout.setRefreshing(true);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                "https://beta.aavartan.org/app.android.notifications", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("notifications");
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        db.deleteAllNotifications();
                        noNotificationTextView.setVisibility(View.INVISIBLE);
                        if (jsonArray.length() == 0)
                            noNotificationTextView.setVisibility(View.VISIBLE);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Notifications notifications = new Notifications();
                            notifications.setId(jsonObject.getInt("id"));
                            notifications.setTitle(jsonObject.getString("title"));
                            notifications.setMessage(jsonObject.getString("message"));
                            notifications.setImageUrl(jsonObject.getString("image_url"));
                            notifications.setCreatedAt(jsonObject.getString("created_at"));
                            db.addNotification(notifications);
                        }
                        notificationsList = db.getAllNotifications();
                        mAdapter = new NotificationAdapter(notificationsList, NotificationsActivity.this);
                        recyclerView.setAdapter(mAdapter);
                    } else {
                        noNotificationTextView.setVisibility(View.VISIBLE);
                        Snackbar.make(findViewById(R.id.relativeLayout), jsonResponse.getString("error_msg"), Snackbar.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(findViewById(R.id.relativeLayout), getResources().getString(R.string.no_internet_error), Snackbar.LENGTH_LONG).show();
                DatabaseHandler db = new DatabaseHandler(NotificationsActivity.this);
                notificationsList = db.getAllNotifications();
                mAdapter = new NotificationAdapter(notificationsList, NotificationsActivity.this);
                recyclerView.setAdapter(mAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    @Override
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
        Intent intent1 = new Intent(NotificationsActivity.this, MainActivity.class);
        startActivity(intent1);
    }

}
