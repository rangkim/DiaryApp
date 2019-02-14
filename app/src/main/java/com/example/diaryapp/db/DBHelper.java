package com.example.diaryapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class DBHelper {

    public static void saveDB(Context context, String diaryDate, String diaryContent){ //WriteDiaryActivity에 있던 함수
        try{
            DBManager dbmgr = new DBManager(context);
            SQLiteDatabase sdb;
            sdb = dbmgr.getWritableDatabase();
            sdb.execSQL("insert into diaryTB values('"+diaryDate+"','"+diaryContent+"');");
            dbmgr.close();
        } catch (SQLiteException e){}
    }

    public static void modifyDB(Context context, int nowData, EditText t1){ //ModifyMyDataActivity
        try{
            DBManager dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getWritableDatabase();
            Cursor cursor = sdb.query("diaryTB", null, null, null, null, null, null);
            cursor.moveToPosition(nowData - 1);
            String diaryDate = cursor.getString(0);

            String strEx = t1.getText().toString();
            Log.d("Mydata", "modifyDb diaryDate : " + diaryDate);
            String sql = String.format("UPDATE diaryTB SET data2 = '%s' WHERE data1 = '%s'", strEx, diaryDate);

            sdb.execSQL(sql);
            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e){}

    }

    public static void nextDB(Context context, int nowData, TextView date, TextView t1){ //ShowMyDataActivity
        try{
            DBManager dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getReadableDatabase();
            Cursor cursor = sdb.query("diaryTB", null, null, null, null, null, null);
            int numberOfData = cursor.getCount();

            if(numberOfData == 0)
                nowData = 0;

            if(cursor.getCount() > 0 && nowData <= numberOfData){
                cursor.moveToPosition(nowData - 1);
                String diaryContent = cursor.getString(0);
                String diaryDate = cursor.getString(1);
                date.setText(diaryContent);
                t1.setText(diaryDate);
                Log.d("Mydata", "nextDB diaryContent "+diaryContent);
            }

            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e){
            Log.e("SQLiteException", e.toString());
        }
    }

    public static void previousDB (Context context, int nowData, TextView date, TextView t1){ //ShowMyDataActivity
        try{
            DBManager dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getReadableDatabase();
            Cursor cursor = sdb.query("diaryTB", null, null, null, null, null, null);
            int numberOfData = cursor.getCount();

            if(numberOfData == 0)
                nowData = 0;

            if(cursor.getCount() > 0 && nowData > 1){
                cursor.moveToPosition(nowData - 1);
                String diaryContent = cursor.getString(0);
                String diaryDate = cursor.getString(1);
                date.setText(diaryContent);
                t1.setText(diaryDate);
                Log.d("Mydata", "previousDB diaryContent "+diaryContent);
            }

            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e){}
    }

    public static void deleteData(Context context, int numberOfData, int nowData) {
        if(numberOfData >= 1) {
            try {
                DBManager dbmgr = new DBManager(context);
                SQLiteDatabase sdb;

                sdb = dbmgr.getWritableDatabase();
                Cursor cursor = sdb.query("diaryTB", null, null, null, null, null, null, null);
                cursor.moveToPosition(nowData - 1);
                String diary_content = cursor.getString(0);
                String sql = String.format("DELETE FROM diaryTB WHERE data1 = '%s'", diary_content);

                sdb.execSQL(sql);
                cursor.close();
                dbmgr.close();
            } catch (SQLiteException e) {}
        }
    }

}