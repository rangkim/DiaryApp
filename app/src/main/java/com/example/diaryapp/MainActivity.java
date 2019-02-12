package com.example.diaryapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        tabHost.setup();

        Intent intent = new Intent().setClass(this, ShowMyData.class);

        spec = tabHost.newTabSpec("show").setIndicator("일기보기").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, WriteDiaryActivity.class);
        spec = tabHost.newTabSpec("write").setIndicator("일기쓰기").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, AppHelpActivity.class);
        spec = tabHost.newTabSpec("help").setIndicator("App소개").setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}
