package com.example.diaryapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.diaryapp.MainActivity;
import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;
import com.example.diaryapp.db.DBManager;
import com.example.diaryapp.model.DiaryData;

public class ModifyMyDataActivity extends Activity {
    private int nowData = 0;
    private TextView date;
    private EditText t1;
    private DiaryData diaryData;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        date = (TextView) findViewById(R.id.date);
        t1 = (EditText) findViewById(R.id.t1);

        Intent it = getIntent();

        String str_name = it.getStringExtra("it_name");
        nowData = Integer.parseInt(str_name);
        diaryData = DBHelper.getOneData(this, nowData);

        date.setText(diaryData.getDate());
        t1.setText(diaryData.getContent());
    }

    public void modifyData(View v){//DBHelper로
        DBHelper.modifyDB(this, nowData, t1.getText().toString());
        startMainActivity();
    }

    public void cancelModifyData(View v){
        startMainActivity();
    }

    private void startMainActivity(){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

}
