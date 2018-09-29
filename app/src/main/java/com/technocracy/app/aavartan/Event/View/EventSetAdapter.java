package com.technocracy.app.aavartan.Event.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.helper.App;

public class EventSetAdapter extends RecyclerView.Adapter<EventSetAdapter.EventTypeHolder> {


    private static Context mContext;
    private int img[] = {R.drawable.fun_1, R.drawable.manage_1, R.drawable.coding_events1, R.drawable.robo_1};
    private String events[] = {"Fun", "Managerial", "Technical", "Robotics"};
    private Intent intent;
    private View view;

    public EventSetAdapter(Context context) {
        mContext = context;
    }

    public EventTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.event_set_item, parent, false);
        return new EventTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventTypeHolder holder, final int position) {
        int width = (int) App.getScreenWidth(mContext);
        holder.imageView.getLayoutParams().height = width / 2;
        holder.imageView.getLayoutParams().width = width;
        holder.imageView.requestLayout();
        Picasso.with(mContext).load("www.abhi.co.in").placeholder(img[position]).
                into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
        holder.name.setText(events[position]);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0) {
                    intent = new Intent(mContext, EventActivity.class);
                    intent.putExtra("event_selected", "1");
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                } else if (position == 1) {
                    intent = new Intent(mContext, EventActivity.class);
                    intent.putExtra("event_selected", "2");
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                } else if (position == 2) {
                    intent = new Intent(mContext, EventActivity.class);
                    intent.putExtra("event_selected", "3");
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                } else if (position == 3) {
                    intent = new Intent(mContext, EventActivity.class);
                    intent.putExtra("event_selected", "4");
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public static class EventTypeHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name;
        private Intent intent;

        public EventTypeHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.event_type);
            imageView = (ImageView) view.findViewById(R.id.event_set_img);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position == 0) {
                        intent = new Intent(mContext, EventActivity.class);
                        intent.putExtra("event_selected", "1");
                        mContext.startActivity(intent);
                        ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    } else if (position == 1) {
                        intent = new Intent(mContext, EventActivity.class);
                        intent.putExtra("event_selected", "2");
                        mContext.startActivity(intent);
                        ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    } else if (position == 2) {
                        intent = new Intent(mContext, EventActivity.class);
                        intent.putExtra("event_selected", "3");
                        mContext.startActivity(intent);
                        ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    } else if (position == 3) {
                        intent = new Intent(mContext, EventActivity.class);
                        intent.putExtra("event_selected", "4");
                        mContext.startActivity(intent);
                        ((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                    }
                }
            });
        }
    }
}