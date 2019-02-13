package com.example.diaryapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class DBHelper {


    public static void saveDB(Context context, String diary_date, String diary_content){ //WriteDiaryActivity에 있던 함수
        DBManager dbmgr = new DBManager(context);
        try{
            dbmgr = new DBManager(context);
            SQLiteDatabase sdb;
            sdb = dbmgr.getWritableDatabase();
            sdb.execSQL("insert into diaryTB values('"+diary_date+"','"+diary_content+"');");
            dbmgr.close();
        } catch (
                SQLiteException e){}
    }

    public static void modifyDB(Context context, Cursor cursor, String diary_date, int nowData, EditText t1){ //ModifyMyDataActivity
        DBManager dbmgr = new DBManager(context);
        try{
            dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getWritableDatabase();
            cursor = sdb.query("diaryTB", null, null, null, null, null, null);
            cursor.moveToPosition(nowData - 1);
            diary_date = cursor.getString(0);

            String str_ex = t1.getText().toString();
            Log.d("Mydata",diary_date);
            String sql = String.format("UPDATE diaryTB SET data2 = '%s' WHERE data1 = '%s'", str_ex, diary_date);

            sdb.execSQL(sql);

            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e){}

    }

    public static void nextDB(Context context, Cursor cursor, int numberOfData, int nowData, String diary_content, String diary_date, TextView date
    , TextView t1){ //ShowMyDataActivity
        DBManager dbmgr = new DBManager(context);
        try{
            dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getReadableDatabase();
            cursor = sdb.query("diaryTB", null, null, null, null, null, null);

            if(numberOfData == 0)
                nowData = 0;

            if(cursor.getCount() > 0 && nowData <= numberOfData){
                cursor.moveToPosition(nowData - 1);
                diary_content = cursor.getString(0);
                diary_date = cursor.getString(1);
            }
            Log.d("Mydata", "in sql "+diary_content);

            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e){
            Log.e("Mydata", e.toString());

        }
        Log.d("Mydata", ""+diary_content);
        date.setText(diary_content);
        t1.setText(diary_date);
    }


    public static void previousDB (Context context, Cursor cursor, int numberOfData, int nowData, String diary_content, String diary_date, TextView date, TextView t1){ //ShowMyDataActivity
        DBManager dbmgr = new DBManager(context);
        try{
            dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getReadableDatabase();
            cursor = sdb.query("diaryTB", null, null, null, null, null, null);

            if(numberOfData == 0)
                nowData = 0;

            if(cursor.getCount() > 0 && nowData > 1){
                cursor.moveToPosition(nowData - 1);
                diary_content = cursor.getString(0);
                diary_date = cursor.getString(1);
            }
            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e){}
        date.setText(diary_content);
        t1.setText(diary_date);
    }


    public static void deleteData(Context context, int numberOfData, Cursor cursor, int nowData, String diary_content) {
        if(numberOfData >= 1)
            try{
                DBManager dbmgr = new DBManager(context);
                SQLiteDatabase sdb;

                sdb = dbmgr.getWritableDatabase();
                cursor = sdb.query("diaryTB", null, null, null, null, null, null, null);
                cursor.moveToPosition(nowData - 1);
                diary_content = cursor.getString(0);
                nowData = -1;
                String sql = String.format("DELETE FROM diaryTB WHERE data1 = '%s", diary_content);

                sdb.execSQL(sql);

                cursor.close();
                dbmgr.close();
            } catch (SQLiteException e) {}
    }


}