package com.example.diaryapp.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diaryapp.R;
import com.example.diaryapp.util.DateUtil;

import java.util.Calendar;

public class ShowDiaryActivity extends Activity implements DatePickerDialog.OnDateSetListener {

    private Calendar cal;

    private TextView dateText;

    private TextView firstDate;
    private TextView secondDate;
    private TextView thirdDate;
    private TextView fourthDate;
    private TextView fifthDate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diary);

        dateText = (TextView) findViewById(R.id.dateText);

        firstDate = (TextView) findViewById(R.id.firstDateText);
        secondDate = (TextView) findViewById(R.id.secondDateText);
        thirdDate = (TextView) findViewById(R.id.thirdDateText);
        fourthDate = (TextView) findViewById(R.id.fourthDateText);
        fifthDate = (TextView) findViewById(R.id.fifthDateText);

        cal = Calendar.getInstance();
        setDate();
    }

    private void setDate(){ //날짜값들 TextView에 입력
        dateText.setText(DateUtil.getDate(cal.getTime(), "yyyy / MM / dd"));

        String MMdd = DateUtil.getDate(cal.getTime(), "MM.dd");
        firstDate.setText("2019."+MMdd);
        secondDate.setText("2020."+MMdd);
        thirdDate.setText("2021."+MMdd);
        fourthDate.setText("2022."+MMdd);
        fifthDate.setText("2023."+MMdd);
    }

    public void nextData(View v){   //다음 버튼 클릭 했을때
        cal.set(Calendar.DATE, cal.get(Calendar.DATE)+1);
        setDate();
    }

    public void previousData (View v){  //이전 버튼 클릭 했을때
        cal.set(Calendar.DATE, cal.get(Calendar.DATE)-1);
        setDate();
    }

    public void selectDate(View v){ //날짜 클릭 했을때
        showDatePicker();
    }

    private void showDatePicker() {
        Calendar minCal = Calendar.getInstance();
        Calendar maxCal = Calendar.getInstance();
        minCal.set(2019, 0, 1); //2019년 1월 1일 ~
        maxCal.set(2023, 11, 31);   //2023년 12월 31일 까지만 선택 가능

        DatePickerDialog dpDialog = new DatePickerDialog(this, this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        dpDialog.getDatePicker().setMinDate(minCal.getTime().getTime());
        dpDialog.getDatePicker().setMaxDate(maxCal.getTime().getTime());
        dpDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        cal.set(year, month, day);
        setDate();
    }

}
