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

import static com.example.nutrical.Home.getDailyDateTime;


public class MemoBraodcastReset extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent repeating_intent = new Intent(context,Home.class);

        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,1,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Reset")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.logov21), 128, 128, false))
                .setContentTitle("Reset")
                .setContentText("Calories Daily Reset Successful")
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        newdailycaloriesModel newdailycaloriesModel;
        newdailycaloriesModel = new newdailycaloriesModel(-1, dataBaseHelper.getBMR(),
                dataBaseHelper.sumCalories(), getDailyDateTime());

        boolean success = dataBaseHelper.addOneDailyCal(newdailycaloriesModel);
        dataBaseHelper.resetCalories();
        //Home home = new Home();
        //home.updateBMR(dataBaseHelper);
        dataBaseHelper.close();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(250, builder.build());
    }
}
