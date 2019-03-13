package com.example.diaryapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "반응!!!!", Toast.LENGTH_SHORT).show();
        Log.d("DiaryApp", "AlarmReceiver!!!!!!");
    }

}
