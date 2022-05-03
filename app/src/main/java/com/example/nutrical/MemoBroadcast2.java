package com.example.nutrical;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MemoBroadcast2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent repeating_intent = new Intent(context,Home.class);

        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,2,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Notification2")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.logov21), 128, 128, false))
                .setContentTitle("Your calorie count for today!")
                .setContentText(String.format("%.2f/%.2f kCal", dataBaseHelper.sumCalories(), dataBaseHelper.getBMR()))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(300, builder.build());
    }
}
