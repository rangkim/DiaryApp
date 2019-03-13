package com.example.diaryapp.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.diaryapp.db.SharedPreferencesApi;
import com.example.diaryapp.receiver.AlarmReceiver;

import java.util.Calendar;

public class AlarmUtil {

    public static void setAlarm(Context context) {
        cancelAlarm(context);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, SharedPreferencesApi.getAlarmHour(context));   //ex) 14
        calendar.set(Calendar.MINUTE, SharedPreferencesApi.getAlarmMin(context)+1);

        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 1994, intent, 0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        AlarmManager alarmMgr = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    public static void cancelAlarm(Context context) {
        AlarmManager alarmMgr = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1994, intent, 0);
        alarmMgr.cancel(pendingIntent);
    }

}
