package com.example.diaryapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diaryapp.MainActivity;
import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;
import com.example.diaryapp.db.DBManager;

public class WriteDiaryActivity extends Activity {
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
    }

    public void saveData(View view){//DBHelper로
        String diary_date = et_name.getText().toString();
        String diary_content = et_name2.getText().toString();

        DBHelper.saveDB(this, diary_date, diary_content);

        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

}
