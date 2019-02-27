package com.example.diaryapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;
import com.example.diaryapp.model.DiaryData;

public class ModifyMyDataActivity extends Activity {

    private DiaryData data;
    private String keyDate = "";
    private String dateString = "";
    private String question = "";

    private TextView date;
    private TextView questionText;
    private EditText t1;
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        date = (TextView) findViewById(R.id.date);
        t1 = (EditText) findViewById(R.id.t1);
        questionText = (TextView) findViewById(R.id.questionText);
        password = (EditText) findViewById(R.id.passwordEdit);

        Intent it = getIntent();

        keyDate = it.getStringExtra("DATE_KEY");
        dateString = it.getStringExtra("DATE_STRING");
        question = it.getStringExtra("QUESTION");

        data = DBHelper.getOneData(this, keyDate);

        date.setText(dateString);
        t1.setText(data.getContent());
        password.setText(data.getPassword());
        questionText.setText(question);
    }

    public void modifyData(View v) {

        if(TextUtils.isEmpty(t1.getText().toString())) { //아무런 일기를 작성하지 않았을경우
            Toast.makeText(this, "일기 내용을 입력 해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!TextUtils.isEmpty(data.getContent())) {   //기존에 작성된 일기가 있을경우 DB 수정
            DBHelper.modifyDB(this, keyDate, t1.getText().toString(), password.getText().toString());
        } else {    //기존에 작성된 일기가 없을경우 새로 저장
            DBHelper.saveDB(this, keyDate, t1.getText().toString(), password.getText().toString());
        }
        finish();
    }

    public void deleteData(View v) {

        if(!TextUtils.isEmpty(data.getContent())) {   //기존에 작성된 일기가 있을경우 삭제
            DBHelper.deleteData(this, keyDate);
            Log.d("adsfdasfasdf", "text = " + keyDate);
        } else {    //기존에 작성된 일기가 없을경우 아무 동작도 하지 않는다
        }
        //화면 종료
        finish();
    }

    public void cancelModifyData(View v) {
        finish();
    }

}
