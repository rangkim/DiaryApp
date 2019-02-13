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

public class ShowMyDataActivity extends Activity {
    int nowData = 0;
    Cursor cursor;
    TextView date;
    TextView t1;
    String diary_content;
    String diary_date;
    int numberOfData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        getAllData();

        Log.d("Mydata", "showData");
        date = (TextView) findViewById(R.id.date);
        t1 = (TextView) findViewById(R.id.t1);

        try {
            DBManager dbmgr = new DBManager(this);
            SQLiteDatabase sdb = dbmgr.getReadableDatabase();
            cursor = sdb.query("diaryTB", null, null, null, null, null, null);
            numberOfData = cursor.getCount();
            cursor.moveToFirst();

            if(numberOfData == 0)
                nowData = 0;
            else
                nowData = 1;

            if(cursor.getCount() > 0) {
                diary_content = cursor.getString(0);
                diary_date = cursor.getString(1);
            }

            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e){}

        Log.d("Mydata", "showData");
        date.setText(diary_content);
        t1.setText(diary_date);
    }
    public void nextData(View v){
        nowData += 1;
        if (nowData >= numberOfData)
            nowData = numberOfData;
        DBHelper.nextDB(this, cursor, numberOfData, nowData, diary_content, diary_date, date, t1);
    }

    public void previousData (View v){
        nowData -= 1;
        if (nowData <= 1)
            nowData = 1;
        DBHelper.previousDB(this, cursor, numberOfData, nowData, diary_content, diary_date, date, t1);
    }

    public void deleteData(View v){
        DBHelper.deleteData(this, numberOfData, cursor, nowData, diary_content);
    }

    public void modifyData (View v){
        Intent it  = new Intent();
        it = new Intent(this, ModifyMyDataActivity.class);
        String msg = nowData + "";
        it.putExtra("it_name", msg);

        startActivity(it);
        finish();
    }



    private void getAllData(){
        DBManager dbmgr = new DBManager(this);
        SQLiteDatabase sdb = dbmgr.getReadableDatabase();
        Cursor  cursor = sdb.rawQuery("select * from diaryTB",null);
        Log.d("Mydata", "**********************");

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String str = cursor.getString(0);
                String str2 = cursor.getString(1);

                Log.d("Mydata", "//////////////");
                Log.d("Mydata", "str : " + str );
                Log.d("Mydata", "str2 : " + str2 );
                Log.d("Mydata", "//////////////");

                cursor.moveToNext();
            }
        }
        Log.d("Mydata", "**********************");
    }



}
