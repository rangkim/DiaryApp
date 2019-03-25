package com.example.diaryapp.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class SharedPreferencesApi {
    private static final String TAG = "SharedPreferencesApi";

    private static String KEY_IS_ALARM = "KEY_IS_ALARM";
    private static String KEY_ALARM_HOUR = "KEY_ALARM_HOUR";
    private static String KEY_ALARM_MIN = "KEY_ALARM_MIN";
    private static String KEY_IS_QUESTION = "KEY_IS_QUESTION";

    private static SharedPreferences mPref;

    private static SharedPreferences getPrefEditor(Context context) {
        if(mPref == null)   {
            mPref = context.getApplicationContext().getSharedPreferences("diary_app_shred_pref", Context.MODE_PRIVATE);
        }
        return mPref;
    }

    public static void setIsAlarm(Context context, boolean isAlarm) {
        getPrefEditor(context).
                edit()
                .putBoolean(KEY_IS_ALARM, isAlarm)
                .apply();
    }

    public static boolean isAlarm(Context context) {
        return getPrefEditor(context).getBoolean(KEY_IS_ALARM, false);
    }

    public static void setAlarmHour(Context context, int hour) {
        getPrefEditor(context).
                edit()
                .putInt(KEY_ALARM_HOUR, hour)
                .apply();
    }

    public static int getAlarmHour(Context context) {
        return getPrefEditor(context).getInt(KEY_ALARM_HOUR, 19);
    }

    public static void setAlarmMin(Context context, int hour) {
        getPrefEditor(context).
                edit()
                .putInt(KEY_ALARM_MIN, hour)
                .apply();
    }

    public static int getAlarmMin(Context context) {
        return getPrefEditor(context).getInt(KEY_ALARM_MIN, 30);
    }

    public static void setIsQuestion(Context context, boolean isQuestion) {
        getPrefEditor(context).
                edit()
                .putBoolean(KEY_IS_QUESTION, isQuestion)
                .apply();
    }

    public static boolean isQuestion(Context context) {
        return getPrefEditor(context).getBoolean(KEY_IS_QUESTION, true);
    }

}

