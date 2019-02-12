package com.example.diaryapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.EditText;

public class DBHelper {
    private DBManager dbmgr;

    public void dbHelper(Context context, EditText et_name, EditText et_name2){
        String diary_date = et_name.getText().toString();
        String diary_content = et_name2.getText().toString();
        try{
            dbmgr = new DBManager(context);
            SQLiteDatabase sdb;
            sdb = dbmgr.getWritableDatabase();
            sdb.execSQL("insert into diaryTB values('"+diary_date+"','"+diary_content+"');");
            dbmgr.close();
        } catch (
                SQLiteException e){}
    }
}