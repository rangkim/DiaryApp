package com.example.diaryapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;
import com.example.diaryapp.db.DBManager;
import com.example.diaryapp.model.DiaryData;

import java.util.ArrayList;

public class ShowMyDataActivity extends Activity {
    int nowData = 0;
    Cursor cursor;
    TextView date;
    TextView t1;
    String diary_content;
    String diary_date;
    int numberOfData;
    ArrayList<DiaryData> data = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        Log.d("Mydata", "showData");
        date = (TextView) findViewById(R.id.date);
        t1 = (TextView) findViewById(R.id.t1);

        data = DBHelper.getAllData(this);

        if(data.size() > 0){
            date.setText(diary_content);
            t1.setText(diary_date);
        }
    }
    public void nextData(View v){
        nowData += 1;
        if (nowData >= numberOfData)
            nowData = numberOfData;
        DBHelper.nextDB(this, nowData, date, t1);
    }

    public void previousData (View v){
        nowData -= 1;
        if (nowData <= 1)
            nowData = 1;
        DBHelper.previousDB(this, nowData, date, t1);
    }

    public void deleteData(View v){
        DBHelper.deleteData(this, numberOfData, nowData);
    }

    public void modifyData (View v){
        Intent it  = new Intent();
        it = new Intent(this, ModifyMyDataActivity.class);
        String msg = nowData + "";
        it.putExtra("it_name", msg);

        startActivity(it);
        finish();
    }

}
