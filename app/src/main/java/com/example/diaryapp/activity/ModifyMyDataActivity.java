package com.example.diaryapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diaryapp.MainActivity;
import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;

public class ModifyMyDataActivity extends Activity {
    private String keyDate = "";
    private String content = "";
    private TextView date;
    private EditText t1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        date = (TextView) findViewById(R.id.date);
        t1 = (EditText) findViewById(R.id.t1);

        Intent it = getIntent();

        keyDate = it.getStringExtra("KEY_DATE");
        content = it.getStringExtra("CONTENT");

        date.setText(keyDate);
        t1.setText(content);
    }

    public void modifyData(View v){

        if(TextUtils.isEmpty(t1.getText().toString())){ //아무런 일기를 작성하지 않았을경우
            Toast.makeText(this, "일기 내용을 입력 해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!TextUtils.isEmpty(content)) {   //기존에 작성된 일기가 있을경우 DB 수정
            DBHelper.modifyDB(this, keyDate, t1.getText().toString());
        } else {    //기존에 작성된 일기가 없을경우 새로 저장
            DBHelper.saveDB(this, keyDate, t1.getText().toString());
        }
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
