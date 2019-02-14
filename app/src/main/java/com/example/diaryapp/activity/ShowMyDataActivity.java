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

        date = (TextView) findViewById(R.id.date);
        t1 = (TextView) findViewById(R.id.t1);

        data = DBHelper.getAllData(this);

        if(data.size() > 0){
            setTextData();
        }
    }

    public void nextData(View v){   //다음 버튼 클릭 했을때
        if(data == null || data.isEmpty())  return;
        nowData += 1;
        if (nowData >= data.size()) nowData = data.size()-1;

        setTextData();
    }

    public void previousData (View v){  //이전 버튼 클릭 했을때
        if(data == null || data.isEmpty())  return;
        nowData -= 1;
        if (nowData <= 0) nowData = 0;

        setTextData();
    }

    public void deleteData(View v){ //삭제 버튼 클릭 했을때
        if(data == null || data.isEmpty())  return;
        if(DBHelper.deleteData(this, data.size(), nowData)) {
            data.remove(nowData);   //ArrayList의 data도 삭제해준다.

            //아이템이 삭제되었으니 삭제된 애를 빼고 다시 화면을 그려준다.
            if(data.size() == 0){   //마지막 하나 남은 아이템을 삭제했을때
                date.setText("");
                t1.setText("");
                return;
            } else if(data.size() <= nowData){  //가장 마지막 아이템을 삭제했을때
                nowData--;
            }
            setTextData();
        }
    }

    public void modifyData (View v){    //수정 버튼 클릭 했을때
        Intent it  = new Intent(this, ModifyMyDataActivity.class);
        String msg = nowData + "";
        it.putExtra("it_name", msg);
        startActivity(it);
        finish();
    }

    private void setTextData(){
        date.setText(data.get(nowData).getDate());
        t1.setText(data.get(nowData).getContent());
    }
}
