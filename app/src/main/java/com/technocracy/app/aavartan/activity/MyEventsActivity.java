package com.technocracy.app.aavartan.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.MyEvent;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.DatabaseHandler;
import com.technocracy.app.aavartan.helper.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MyEventsActivity extends AppCompatActivity {

    private static final String TAG = MyEventsActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private List<MyEvent> eventlist;
    private EventsAdapter eventsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SQLiteHandler uesrDB;
    private DatabaseHandler db;
    private User user;
    private TextView noEventsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_myevents);
        } catch (NullPointerException e) {
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.title));
        mToolbar.setTitle("My Events");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        uesrDB = new SQLiteHandler(getApplicationContext());
        user = uesrDB.getUser();

        db = new DatabaseHandler(getApplicationContext());

        noEventsTextView = (TextView) findViewById(R.id.no_events_yet_textView);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        eventlist = db.getAllMyEvents();
        eventsAdapter = new EventsAdapter(MyEventsActivity.this, eventlist);
        recyclerView.setAdapter(eventsAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(App.SWIPE_REFRESH_COLORS);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyEvents(String.valueOf(user.getUser_id()));
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getMyEvents(String.valueOf(user.getUser_id()));
            }
        });
    }

    private void getMyEvents(final String userID) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        swipeRefreshLayout.setRefreshing(true);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                "https://beta.aavartan.org/app.android.registered.events/" + String.valueOf(user.getUser_id()), new Response.Listener<String>() {
            //String.valueOf(user.getUser_id())
            @Override
            public void onResponse(String response) {
                Log.d("ayush", "MyEvents Response: " + response.toString());
                swipeRefreshLayout.setRefreshing(false);
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
                        if (enlist.length() == 0)
                            noEventsTextView.setVisibility(View.VISIBLE);
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
                        eventsAdapter = new EventsAdapter(MyEventsActivity.this, eventlist);
                        recyclerView.setAdapter(eventsAdapter);
                    } else {
                        Snackbar.make(findViewById(R.id.relativeLayout), jsonResponse.getString("error_msg"), Snackbar.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    eventlist = db.getAllMyEvents();
                    eventsAdapter = new EventsAdapter(MyEventsActivity.this, eventlist);
                    recyclerView.setAdapter(eventsAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                eventlist = db.getAllMyEvents();
                eventsAdapter = new EventsAdapter(MyEventsActivity.this, eventlist);
                recyclerView.setAdapter(eventsAdapter);
                Snackbar.make(findViewById(R.id.relativeLayout), getResources().getString(R.string.no_internet_error), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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

    class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.LeaderboardViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        List<MyEvent> eventslists;

        public EventsAdapter(Context context, List<MyEvent> eventslists) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            this.eventslists = eventslists;
        }

        @Override
        public LeaderboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.items_myevents, parent, false);
            return new LeaderboardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final LeaderboardViewHolder holder, final int position) {
            holder.boundMyEvent = eventslists.get(position);
            holder.myEventCard.setBackgroundColor(getResources().getColor(R.color.blue));
            holder.event_name.setText(Html.fromHtml(holder.boundMyEvent.getEventName()));
            holder.event_date.setText(Html.fromHtml(holder.boundMyEvent.getEventDate()));
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }

            });
        }

        @Override
        public int getItemCount() {
            Log.d("ayush", String.valueOf(eventslists.size()));
            return eventslists.size();
        }

        public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final CardView myEventCard;
            public final TextView event_name;
            public final TextView event_date;
            public MyEvent boundMyEvent;

            public LeaderboardViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                myEventCard = (CardView) itemView.findViewById(R.id.leaderboard_card);
                event_name = (TextView) itemView.findViewById(R.id.username_leaderboard);
                event_date = (TextView) itemView.findViewById(R.id.user_level_leaderboard);
            }
        }
    }
}