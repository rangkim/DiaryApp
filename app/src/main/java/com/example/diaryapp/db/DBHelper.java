package com.example.diaryapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.diaryapp.model.DiaryData;

import java.util.ArrayList;

public class DBHelper {

    public static void saveDB(Context context, String diaryDate, String diaryContent, String password, String imageUrl, String imageTitle) {
        try{
            DBManager dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getWritableDatabase();
            sdb.execSQL("insert into diaryTB values('"+diaryDate+"','"+diaryContent+"', '"+password+"', '"+imageUrl+"', '"+imageTitle+"');");
            Log.d("Mydata", "saveDB diaryDate : " + diaryDate + " \n\n\n " + diaryContent + "\n" + password);
            dbmgr.close();
        } catch (SQLiteException e){}
    }

    public static void modifyDB(Context context, String date, String content, String password, String imageUrl, String imageTitle) {
        try{
            DBManager dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getWritableDatabase();
            Cursor cursor = sdb.query("diaryTB", null, null, null, null, null, null);
            Log.d("Mydata", "modifyDb diaryDate : " + date + " \n\n\n " + content);
            String sql = String.format("UPDATE diaryTB SET content = '%s', password = '%s', image = '%s', imageTitle = '%s' WHERE date = '%s'", content, password, imageUrl, imageTitle, date);

            sdb.execSQL(sql);
            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e){}
    }

    public static boolean deleteData(Context context, String date) {
        try {
            DBManager dbmgr = new DBManager(context);
            SQLiteDatabase sdb = dbmgr.getWritableDatabase();
            Cursor cursor = sdb.query("diaryTB", null, null, null, null, null, null, null);
            String sql = String.format("DELETE FROM diaryTB WHERE date = '%s'", date);
            Log.d("Mydata", "deleteData diaryDate : " + date);

            sdb.execSQL(sql);
            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    //현재 미사용
//    public static ArrayList<DiaryData> getAllData(Context context) {
//        ArrayList<DiaryData> resultData = new ArrayList<>();
//        DBManager dbmgr = new DBManager(context);
//        SQLiteDatabase sdb = dbmgr.getReadableDatabase();
//        Cursor  cursor = sdb.rawQuery("select * from diaryTB",null);
//
//
//        if (cursor.moveToFirst()) {
//            while (!cursor.isAfterLast()) {
//                resultData.add(new DiaryData(cursor.getString(cursor.getColumnIndex("date")),
//                        cursor.getString(cursor.getColumnIndex("content")), cursor.getString(cursor.getColumnIndex("password"))));
//                Log.d("Mydata", "//////////////");
//                Log.d("Mydata", "str : " + cursor.getColumnIndex("date"));
//                Log.d("Mydata", "str2 : " + cursor.getColumnIndex("content"));
//                Log.d("Mydata", "str3 : " + cursor.getColumnIndex("password"));
//                Log.d("Mydata", "//////////////");
//                cursor.moveToNext();
//            }
//        }
//
//        return resultData;
//    }

    public static DiaryData getOneData(Context context, String key) {
        DBManager dbmgr = new DBManager(context);
        SQLiteDatabase sdb = dbmgr.getReadableDatabase();
        Log.d("Mydata", "getOneData : " + key + " \n");
        Cursor cursor = sdb.rawQuery(String.format("SELECT * FROM diaryTB WHERE date = '%s'", key),null);

        if(cursor != null && cursor.moveToFirst()){
            return new DiaryData(cursor.getString(cursor.getColumnIndex("date")),
                    cursor.getString(cursor.getColumnIndex("content")), cursor.getString(cursor.getColumnIndex("password")),
                    cursor.getString(cursor.getColumnIndex("image")), cursor.getString(cursor.getColumnIndex("imageTitle")));
        } else {
            return new DiaryData("", "", "", "", "");
        }
    }

}