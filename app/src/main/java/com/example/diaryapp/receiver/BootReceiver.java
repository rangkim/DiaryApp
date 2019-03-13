package com.example.diaryapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.diaryapp.db.SharedPreferencesApi;
import com.example.diaryapp.util.AlarmUtil;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.d("DiaryApp", "ACTION_BOOT_COMPLETED!!!!!");
            if(SharedPreferencesApi.isAlarm(context)){
                Log.d("DiaryApp", "alarm is true!!!!!");
                AlarmUtil.setAlarm(context);
            }
        }
    }

}