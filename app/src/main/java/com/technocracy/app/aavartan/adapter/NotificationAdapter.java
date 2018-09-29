package com.technocracy.app.aavartan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.Notifications;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<Notifications> notificationList;
    private Context mContext;
    private String currentDateString;
    private SimpleDateFormat dateFormat;

    public NotificationAdapter(List<Notifications> notificationList, Context mContext) {
        this.notificationList = notificationList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_row, parent, false);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDateString = dateFormat.format(Calendar.getInstance().getTime());

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Notifications notifications = notificationList.get(position);

        holder.title.setText(notifications.getTitle());
        holder.message.setText(notifications.getMessage());

        holder.notificationImage.setImageDrawable(null);
        Log.d("ayush", notifications.getCreatedAt());
        setNotificationTime(holder.createdAt, notifications.getCreatedAt());
        String notificationImageUrl = notifications.getImageUrl();
        if (notificationImageUrl != null && notificationImageUrl.length() > 4) {
            Picasso.with(mContext)
                    .load(notificationImageUrl)
                    .into(holder.notificationImage);
        }
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    private void setNotificationTime(TextView notifTimeTextView, String notifTime) {
        try {
            Date currentDate = dateFormat.parse(currentDateString);
            Date notifDate = dateFormat.parse(notifTime);
            long differenceInMS = currentDate.getTime() - notifDate.getTime();
            long differenceInSecs = TimeUnit.MILLISECONDS.toSeconds(differenceInMS);
            if (differenceInSecs < 60) {
                notifTimeTextView.setText("Just now");
            } else if (differenceInSecs < 3600) {
                long differenceInMins = TimeUnit.SECONDS.toMinutes(differenceInSecs);
                if (differenceInMins == 1)
                    notifTimeTextView.setText(differenceInMins + " minute ago");
                else
                    notifTimeTextView.setText(differenceInMins + " minutes ago");
            } else if (differenceInSecs < 86400) {
                long differenceInHrs = TimeUnit.SECONDS.toHours(differenceInSecs);
                if (differenceInHrs == 1)
                    notifTimeTextView.setText("about an hour ago");
                else
                    notifTimeTextView.setText(differenceInHrs + " hours ago");
            } else if (differenceInSecs < 604800) {
                long differenceInDays = TimeUnit.SECONDS.toDays(differenceInSecs);
                String time = new SimpleDateFormat("h:mm aa").format(notifDate);
                if (differenceInDays == 1)
                    notifTimeTextView.setText("Yesterday at " + time);
                else {
                    Calendar c = Calendar.getInstance();
                    c.setTime(notifDate);
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                    switch (dayOfWeek) {
                        case 1:
                            notifTimeTextView.setText("Mon at " + time);
                            break;
                        case 2:
                            notifTimeTextView.setText("Tue at " + time);
                            break;
                        case 3:
                            notifTimeTextView.setText("Wed at " + time);
                            break;
                        case 4:
                            notifTimeTextView.setText("Thu at " + time);
                            break;
                        case 5:
                            notifTimeTextView.setText("Fri at " + time);
                            break;
                        case 6:
                            notifTimeTextView.setText("Sat at " + time);
                            break;
                        case 7:
                            notifTimeTextView.setText("Sun at " + time);
                            break;
                    }
                }
            } else {
                String time = new SimpleDateFormat("h:mm aa").format(notifDate);
                String date = new SimpleDateFormat("d MMM").format(notifDate);
                notifTimeTextView.setText(date + " at " + time);
            }

        } catch (ParseException e) {
            notifTimeTextView.setText(notifTime);
            e.printStackTrace();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, message, createdAt;
        public ImageView notificationImage;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            message = (TextView) view.findViewById(R.id.message);
            createdAt = (TextView) view.findViewById(R.id.createdAt);
            notificationImage = (ImageView) view.findViewById(R.id.notificationImage);
        }
    }
}