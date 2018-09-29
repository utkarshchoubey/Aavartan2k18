package com.technocracy.app.aavartan.Schedule.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Event.View.EventDetailsActivity;
import com.technocracy.app.aavartan.R;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.LeaderboardViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private final List<Event> scheduleArrayList;
    private final Context mContext;
    private View view;

    public ScheduleAdapter(Context context, List<Event> scheduleArrayList) {
        mContext = context;
        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        this.scheduleArrayList = scheduleArrayList;
    }

    @Override
    public LeaderboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row, parent, false);
        return new LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeaderboardViewHolder holder, int position) {
        Event event = scheduleArrayList.get(position);
        holder.scheduleCard.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
        holder.eventNameTv.setText(event.getName());
        String time = event.getTime();
        String venue = event.getVenue();
        if (time == null)
            time = "7-8 October";
        if (venue == null)
            venue = "NIT Raipur";
        holder.eventTimeTv.setText(time);
        holder.eventVenueTv.setText(venue);
        String eventImageUrl = event.getImage_url();
        Log.d("abhi", "" + event.getName() + " " + event.getVenue());
        Picasso.with(mContext)
                .load(eventImageUrl).placeholder(R.drawable.ic_logo_small)
                .into(holder.eventImage);
    }

    @Override
    public int getItemCount() {
        return scheduleArrayList.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CardView scheduleCard;
        public final TextView eventNameTv;
        public final TextView eventTimeTv;
        public final TextView eventVenueTv;
        public final ImageView eventImage;

        public LeaderboardViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            scheduleCard = (CardView) itemView.findViewById(R.id.leaderboard_card);
            eventNameTv = (TextView) itemView.findViewById(R.id.ListHead);
            eventTimeTv = (TextView) itemView.findViewById(R.id.ListTime);
            eventVenueTv = (TextView) itemView.findViewById(R.id.ListVenue);
            eventImage = (ImageView) itemView.findViewById(R.id.eventsImage);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(mContext, EventDetailsActivity.class);
                    Event event = scheduleArrayList.get(pos);
                    i.putExtra("id", event.getEventId());
                    i.putExtra("event_name", event.getName());
                    i.putExtra("event_description", event.getDescription());
                    Log.d("abhi", "" + event.getName() + " " + event.getVenue());
                    mContext.startActivity(i);
                }
            });
        }
    }
}