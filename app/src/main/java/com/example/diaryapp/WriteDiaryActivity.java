package com.example.diaryapp;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WriteDiaryActivity extends Activity implements View.OnClickListener {
    private DBManager dbmgr;
    EditText et_name;
    EditText et_name2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writediary);
        Button btn = (Button) findViewById(R.id.button_store);
        et_name = (EditText)findViewById(R.id.edit_name);
        et_name2 = (EditText)findViewById(R.id.edit_diary);
        btn.setOnClickListener(this);
    }

    public void saveData(){
        String diary_date = et_name.getText().toString();
        String diary_content = et_name2.getText().toString();

        try{
            dbmgr = new DBManager(this);
            SQLiteDatabase sdb;
            sdb = dbmgr.getWritableDatabase();
            sdb.execSQL("insert into diaryTB values('"+diary_date+"','"+diary_content+"');");
            dbmgr.close();
        } catch (SQLiteException e){}

        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }


    @Override
    public void onClick(View view) {
        saveData();
    }
}
