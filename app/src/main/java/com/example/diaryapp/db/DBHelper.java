package com.example.diaryapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.diaryapp.model.DiaryData;

import java.util.ArrayList;

public class DBHelper {

    public static void saveDB(Context context, String diaryDate, String diaryContent) { //WriteDiaryActivity에 있던 함수
        try{
            DBManager dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getWritableDatabase();
            sdb.execSQL("insert into diaryTB values('"+diaryDate+"','"+diaryContent+"');");
            dbmgr.close();
        } catch (SQLiteException e){}
    }

    public static void modifyDB(Context context, int nowData, String str) { //ModifyMyDataActivity
        try{
            DBManager dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getWritableDatabase();
            Cursor cursor = sdb.query("diaryTB", null, null, null, null, null, null);
            cursor.moveToPosition(nowData);
            String diaryDate = cursor.getString(0);

            Log.d("Mydata", "modifyDb diaryDate : " + diaryDate + " \n\n\n " + str);
            String sql = String.format("UPDATE diaryTB SET data2 = '%s' WHERE data1 = '%s'", str, diaryDate);

            sdb.execSQL(sql);
            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e){}
    }

    public static boolean deleteData(Context context, int numberOfData, int nowData) {
        if(numberOfData >= 1) {
            try {
                DBManager dbmgr = new DBManager(context);
                SQLiteDatabase sdb = dbmgr.getWritableDatabase();
                Cursor cursor = sdb.query("diaryTB", null, null, null, null, null, null, null);
                cursor.moveToPosition(nowData);

                String diaryDate = cursor.getString(0);
                String sql = String.format("DELETE FROM diaryTB WHERE data1 = '%s'", diaryDate);

                sdb.execSQL(sql);
                cursor.close();
                dbmgr.close();
            } catch (SQLiteException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static ArrayList<DiaryData> getAllData(Context context) {
        ArrayList<DiaryData> resultData = new ArrayList<>();
        DBManager dbmgr = new DBManager(context);
        SQLiteDatabase sdb = dbmgr.getReadableDatabase();
        Cursor  cursor = sdb.rawQuery("select * from diaryTB",null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                resultData.add(new DiaryData(cursor.getString(0), cursor.getString(1)));
                Log.d("Mydata", "//////////////");
                Log.d("Mydata", "str : " + cursor.getString(0));
                Log.d("Mydata", "str2 : " + cursor.getString(1));
                Log.d("Mydata", "//////////////");
                cursor.moveToNext();
            }
        }

        return resultData;
    }

    public static DiaryData getOneData(Context context, int nowData) {
        DBManager dbmgr = new DBManager(context);
        SQLiteDatabase sdb = dbmgr.getReadableDatabase();
        Cursor cursor = sdb.query("diaryTB", null, null, null, null, null, null);
        cursor.moveToPosition(nowData);
        return new DiaryData(cursor.getString(0), cursor.getString(1));
    }

}