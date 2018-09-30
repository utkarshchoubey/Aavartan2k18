package com.technocracy.app.aavartan.Event.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;
import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Event.Model.MockEventProvider;
import com.technocracy.app.aavartan.Event.Presenter.EventPresenter;
import com.technocracy.app.aavartan.Event.Presenter.EventPresenterImpl;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.helper.DatabaseHandler;

import java.util.List;

public class EventActivity extends AppCompatActivity implements EventView {
    private DatabaseHandler db;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ParallaxRecyclerAdapter<Event> adapter;
    private EventPresenter presenter;
    private String eventSetId;
    private String toolbarTitle = "Events";
    private List<Event> eventList;
    private View view, recyclerHeader;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        eventSetId = data.getString("event_selected");

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        recyclerHeader = inflater.inflate(R.layout.event_recycler_header_fun, null);
        ImageView headerImage = (ImageView) recyclerHeader.findViewById(R.id.header_img);
//        recyclerHeader = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_event, viewGroup, false);
        if (eventSetId.equals("1")) {
            eventSetId = "fun";
            headerImage.setImageResource(R.drawable.civil1);
            toolbarTitle = "   Fun";
        } else if (eventSetId.equals("2")) {
            eventSetId = "manager";
            toolbarTitle = "   Managerial";
        } else if (eventSetId.equals("3")) {
            eventSetId = "tech";
            toolbarTitle = "  Technical";
        } else {
            eventSetId = "robo";
            toolbarTitle = "   Robotics";
        }
        Log.d("event_set_id", eventSetId);
        setContentView(R.layout.activity_event);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(toolbarTitle);

        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_event);
        recyclerView = (RecyclerView) findViewById(R.id.event_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new EventPresenterImpl(this, new MockEventProvider(), this);
        db = new DatabaseHandler(getApplicationContext());
        presenter.getEvents(eventSetId);
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

    private ParallaxRecyclerAdapter<Event> createAdapter(final List<Event> eventList) {
        ParallaxRecyclerAdapter<Event> stringAdapter = new ParallaxRecyclerAdapter<Event>(eventList) {
            @Override
            public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter parallaxRecyclerAdapter, int i) {
                MyViewHolder holder = (MyViewHolder) viewHolder;
                final Event event = eventList.get(i);
                String time = event.getTime();
                String venue = event.getVenue();
                String date = event.getDate();
                if (time == null)
                    time = " 9:00AM-5:15PM ";
                if (venue == null)
                    venue = " NIT Raipur";
                if (date == null)
                    date = " 6-7 Oct";
                holder.txt.setText(event.getName());
                holder.txt1.setText("Date : " + date + "\nTime:" + time +
                        "\nVenue:" + venue);
                Picasso.with(EventActivity.this).load(event.getImage_url()).placeholder(R.drawable.avartan_logo100).into(holder.img);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(EventActivity.this, EventDetailsActivity.class);
                        i.putExtra("id", event.getEventId());
                        i.putExtra("event_name", event.getName());
                        i.putExtra("event_description", event.getDescription());
                        startActivity(i);
                    }
                });
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter parallaxRecyclerAdapter, int i) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_event, viewGroup, false);
                return new MyViewHolder(view);
            }

            @Override
            public int getItemCountImpl(ParallaxRecyclerAdapter parallaxRecyclerAdapter) {
                return eventList.size();
            }
        };
        if (eventSetId.equals("fun"))
            stringAdapter.setParallaxHeader(getLayoutInflater().inflate(R.layout.event_recycler_header_fun, recyclerView, false), recyclerView);
        else if (eventSetId.equals("robo"))
            stringAdapter.setParallaxHeader(getLayoutInflater().inflate(R.layout.event_recycler_header_robo, recyclerView, false), recyclerView);
        else if (eventSetId.equals("fun"))
            stringAdapter.setParallaxHeader(getLayoutInflater().inflate(R.layout.event_recycler_header_tech, recyclerView, false), recyclerView);
        else if (eventSetId.equals("manager"))
            stringAdapter.setParallaxHeader(getLayoutInflater().inflate(R.layout.event_recycler_header_managerial, recyclerView, false), recyclerView);
        stringAdapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                //TODO: implement toolbar alpha. See README for details
            }
        });
        return stringAdapter;
    }


    @Override
    public void showEvents(List<Event> body) {
        db.deleteAllEvent(eventSetId);
        for (int i = 0; i < body.size(); i++)
            db.addEvents(body.get(i), eventSetId);
        adapter = createAdapter(body);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void showEventsFromDatabase() {
        eventList = db.getAllEvents(eventSetId);
        Log.d("abhi", eventList.size() + "");
        adapter = createAdapter(eventList);
        recyclerView.setAdapter(adapter);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView txt;
        public final TextView txt1;
        public final ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txt = (TextView) itemView.findViewById(R.id.event_name);
            img = (ImageView) itemView.findViewById(R.id.icon1);
            txt1 = (TextView) itemView.findViewById(R.id.date_venue_time);
        }
    }

}