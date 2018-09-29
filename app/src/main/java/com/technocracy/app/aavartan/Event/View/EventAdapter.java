package com.technocracy.app.aavartan.Event.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private Context context;
    private List<Event> eventList;
    private View view;

    public EventAdapter(Context context, List<Event> eventList) {
//        Log.d("AAVARTAN17","IN ADAPTER CONSTRUCTOR");
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Log.d("AAVARTAN17","IN BIND VIEW HOLDER"+position);
        final Event event = eventList.get(position);
        String time = event.getTime();
        String venue = event.getVenue();
        String date = event.getDate();
        if (time == null)
            time = " 9:00AM-5:15PM ";
        if (venue == null)
            venue = " NIT Raipur";
        if (date == null)
            date = " 7-8 Oct";

        holder.txt.setText(event.getName());
        holder.txt1.setText("Date : " + date + "\nTime:" + time +
                "\nVenue:" + venue);
        Picasso.with(context).load(event.getImage_url()).placeholder(R.drawable.avartan_logo100).into(holder.img);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EventDetailsActivity.class);
                i.putExtra("id", event.getEventId());
                i.putExtra("event_name", event.getName());
                i.putExtra("event_description", event.getDescription());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
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
