package com.example.diaryapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;
import com.example.diaryapp.model.DiaryData;

import java.util.ArrayList;

public class ShowMyDataActivity extends Activity {
    private int nowData = 0;
    private TextView date;
    private TextView t1;
    private ArrayList<DiaryData> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        Log.d("Mydata", "showData");
        date = (TextView) findViewById(R.id.date);
        t1 = (TextView) findViewById(R.id.t1);

        data = DBHelper.getAllData(this);

        if(data.size() > 0){
            date.setText(data.get(0).getDate());
            t1.setText(data.get(0).getContent());
        }
    }

    public void nextData(View v){   //다음 버튼 클릭 했을때
        if(data == null || data.isEmpty())  return;
        nowData += 1;
        if (nowData >= data.size()) nowData = data.size()-1;

        date.setText(data.get(nowData).getDate());
        t1.setText(data.get(nowData).getContent());
    }

    public void previousData (View v){  //이전 버튼 클릭 했을때
        if(data == null || data.isEmpty())  return;
        nowData -= 1;
        if (nowData <= 0)
            nowData = 0;

        date.setText(data.get(nowData).getDate());
        t1.setText(data.get(nowData).getContent());
    }

    public void deleteData(View v){ //삭제 버튼 클릭 했을때
        if(data == null || data.isEmpty())  return;
        DBHelper.deleteData(this, data.size(), nowData);
    }

    public void modifyData (View v){    //수정 버튼 클릭 했을때
        Intent it  = new Intent(this, ModifyMyDataActivity.class);
        String msg = nowData + "";
        it.putExtra("it_name", msg);
        startActivity(it);
        finish();
    }

}
