package com.example.nutrical;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Home extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {

    Button homebtn_profile, homebtn_info;
    ImageButton homehistory, homechart;
    TextView  home_name, todayCal, targetCal, leftCal, percent;
    ProgressBar bmrProgress;
    ImageButton homeadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("  NutriCal");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logov21_32x32);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(Home.this);
        homebtn_profile = findViewById(R.id.homebtn_profile);
        homebtn_info = findViewById(R.id.homebtn_info);
        homeadd = findViewById(R.id.homeadd);
        //homecalText = findViewById(R.id.homecaltext);
        percent = findViewById(R.id.percent);
        todayCal = findViewById(R.id.todayCal);
        targetCal = findViewById(R.id.targetCal);
        leftCal = findViewById(R.id.leftCal);
        home_name = findViewById(R.id.home_name);
        bmrProgress = findViewById(R.id.bmrProgress);
        homehistory = findViewById(R.id.homehistory);
        homechart = findViewById(R.id.homechart);

        NotificationChannel();
        NotificationChannel2();
        NotificationChannel3();
        NotificationChannelReset();
        updateBMR(dataBaseHelper);

        homeadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();




            }
        });

        homehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_one = new Intent(Home.this, History.class);
                startActivity(intent_one);
                overridePendingTransition(0, 0);
            }
        });

        homechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_one = new Intent(Home.this, CalorieChart.class);
                startActivity(intent_one);
                overridePendingTransition(0, 0);
            }
        });

        homebtn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_one = new Intent(Home.this, Profile.class);
                startActivity(intent_one);
                overridePendingTransition(0, 0);
            }
        });

        homebtn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_one = new Intent(Home.this, Info.class);
                startActivity(intent_one);
                overridePendingTransition(0, 0);
            }
        });
    }

    /////////////////////////////////////////////////////
    ///////////////////NOTIFICATIONS////////////////////
    ////////////////////////////////////////////////////
    private void NotificationChannel() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(Home.this, MemoBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence memo = "NutriCal";
            String description = "Nutrical's Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification", memo, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void NotificationChannel2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(Home.this, MemoBroadcast2.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence memo = "NutriCal";
            String description = "Nutrical's Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification2", memo, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void NotificationChannel3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(Home.this, MemoBroadcast3.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence memo = "NutriCal";
            String description = "Nutrical's Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification3", memo, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void NotificationChannelReset() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 32);
        calendar.set(Calendar.SECOND, 30);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(Home.this, MemoBraodcastReset.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence memo = "NutriCal Reset";
            String description = "Daily Reset";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Reset", memo, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    /////////////////////////////////////////////////////
    ///////////////////NOTIFICATIONS////////////////////
    ////////////////////////////////////////////////////

    public void updateBMR(DataBaseHelper dataBaseHelper) {
        double percentCal = percentCal(dataBaseHelper.sumCalories(),dataBaseHelper.getBMR());
        String perStr = String.format("%.0f", percentCal);

        bmrProgress.setProgress((int) dataBaseHelper.sumCalories());
        bmrProgress.setMax((int) dataBaseHelper.getBMR());

        home_name.setText(dataBaseHelper.firstWord("") + "'s daily calorie counter");
        percent.setText(perStr+"%");
        leftCal.setText(String.format("%.0f kCal", dataBaseHelper.getBMR()-dataBaseHelper.sumCalories()));
        todayCal.setText(String.format("%.0f\nkCal", dataBaseHelper.sumCalories()));
        targetCal.setText(String.format("%.0f kCal", dataBaseHelper.getBMR()));

        if(dataBaseHelper.getBMR()-dataBaseHelper.sumCalories()<=0)
            leftCal.setText("0 kCal");
        else
            leftCal.setText(String.format("%.0f kCal", dataBaseHelper.getBMR()-dataBaseHelper.sumCalories()));

        if (dataBaseHelper.sumCalories() > dataBaseHelper.getBMR()) {
            bmrProgress.setProgress((int) dataBaseHelper.sumCalories());
            bmrProgress.setMax((int) dataBaseHelper.getBMR());
            todayCal.setTextColor(Color.parseColor("#ffff4444"));
            percent.setTextColor(Color.parseColor("#ffff4444"));

        } else {
            bmrProgress.setProgress((int) dataBaseHelper.sumCalories());
            bmrProgress.setMax((int) dataBaseHelper.getBMR());
            todayCal.setTextColor(Color.WHITE);
            percent.setTextColor(Color.WHITE);
        }
    }

    public String getDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yy hh:mm a");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        return dateTime;
    }

    public static String getDailyDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        return dateTime;
    }

    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyText(double calories, String notes) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(Home.this);
        newcaloriesModel newcaloriesModel;

        newcaloriesModel = new newcaloriesModel(-1, calories, getDateTime(),notes);
        Toast.makeText(Home.this, newcaloriesModel.toString(), Toast.LENGTH_SHORT).show();

        boolean success = dataBaseHelper.addOneCal(newcaloriesModel);
        updateBMR(dataBaseHelper);
        dataBaseHelper.close();
    }
    public double percentCal(double todayCal, double targetCal){

        return (todayCal/targetCal) * 100;
    }
}