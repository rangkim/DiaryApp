package com.example.diaryapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBManager extends SQLiteOpenHelper {
    public DBManager (Context context) {
        super(context, "csc", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table diaryTB (date text primary key, content text, password text, image text, imageTitle text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
