package com.technocracy.app.aavartan.fcm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.technocracy.app.aavartan.activity.NotificationsActivity;
import com.technocracy.app.aavartan.api.Notifications;
import com.technocracy.app.aavartan.helper.DatabaseHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {


    private static final String TAG = "ayush";
    private NotificationUtils notificationUtils;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("ayush", "onMessageReceived");
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d("ayush",remoteMessage.getData().get("created_at"));
        Log.d("ayush",remoteMessage.getData().get("image_url"));
        Log.d("ayush",remoteMessage.getData().get("title"));
        String date = null;
        try {
            
            JSONObject jsonObject = new JSONObject( remoteMessage.getData().get("created_at"));
            date = jsonObject.getString("date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Notifications notification = new Notifications(Integer.parseInt(remoteMessage.getData().get("id")),
                remoteMessage.getData().get("title"), remoteMessage.getData().get("message"),
                remoteMessage.getData().get("image_url"),
                date);

        db.addNotification(notification);

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Title: " + notification.getTitle());
        Log.d(TAG, "message: " + notification.getMessage());
        Log.d(TAG, "image: " + notification.getImageUrl());
        Log.d(TAG, "timestamp: " + notification.getCreatedAt());

        Intent resultIntent = new Intent(getApplicationContext(), NotificationsActivity.class);
        resultIntent.putExtra("message", notification.getMessage());
        resultIntent.putExtra("title", notification.getTitle());
        resultIntent.putExtra("image_url", notification.getImageUrl());
        resultIntent.putExtra("created_at", notification.getCreatedAt());

        showNotificationMessage(getApplicationContext(), notification.getTitle(),
        notification.getMessage(), notification.getCreatedAt(), resultIntent,
        notification.getImageUrl());
    }

   private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | IntentCompat.FLAG_ACTIVITY_TASK_ON_HOME);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }
}