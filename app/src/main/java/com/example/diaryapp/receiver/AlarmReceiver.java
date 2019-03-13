package com.example.diaryapp.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;
import com.example.diaryapp.R;
import com.example.diaryapp.activity.SplashActivity;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "반응!!!!", Toast.LENGTH_SHORT).show();
        Log.d("DiaryApp", "AlarmReceiver!!!!!!");

        Intent aIntent = new Intent(context, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, aIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1234")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Diary App")
                .setContentText("오늘 하루를 정리하는 시간 어때요?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1234, builder.build());

        PowerManager pm = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.SCREEN_DIM_WAKE_LOCK, "DIARYAPP" );
        wakeLock.acquire();
        wakeLock.release();
    }

}
