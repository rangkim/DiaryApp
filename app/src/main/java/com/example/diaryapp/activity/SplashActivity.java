package com.example.diaryapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);

        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, ShowDiaryActivity.class));
        finish();
    }
}
