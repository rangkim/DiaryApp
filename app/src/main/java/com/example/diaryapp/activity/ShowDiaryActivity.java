package com.example.diaryapp.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;
import com.example.diaryapp.db.QuestionList;
import com.example.diaryapp.util.DateUtil;

import java.util.Calendar;

public class ShowDiaryActivity extends Activity implements DatePickerDialog.OnDateSetListener {

    private Calendar cal;
    private String MMdd = "";

    private TextView dateText;
    private TextView questionText;

    private TextView firstDate;
    private TextView secondDate;
    private TextView thirdDate;
    private TextView fourthDate;
    private TextView fifthDate;

    private TextView firstDiary;
    private TextView secondDiary;
    private TextView thirdDiary;
    private TextView fourthDiary;
    private TextView fifthDiary;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diary);

        dateText = (TextView) findViewById(R.id.dateText);
        questionText = (TextView) findViewById(R.id.questionText);

        firstDate = (TextView) findViewById(R.id.firstDateText);
        secondDate = (TextView) findViewById(R.id.secondDateText);
        thirdDate = (TextView) findViewById(R.id.thirdDateText);
        fourthDate = (TextView) findViewById(R.id.fourthDateText);
        fifthDate = (TextView) findViewById(R.id.fifthDateText);

        firstDiary = (TextView) findViewById(R.id.firstDiaryText);
        secondDiary = (TextView) findViewById(R.id.secondDiaryText);
        thirdDiary = (TextView) findViewById(R.id.thirdDiaryText);
        fourthDiary = (TextView) findViewById(R.id.fourthDiaryText);
        fifthDiary = (TextView) findViewById(R.id.fifthDiaryText);

        cal = Calendar.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        setAllDate();
    }

    private void setAllDate() { //날짜값들 TextView에 입력
        dateText.setText(DateUtil.getDate(cal.getTime(), "yyyy / MM / dd"));
        MMdd = DateUtil.getDate(cal.getTime(), "MMdd"); //오늘 날짜값 변수에 저장해둠

        setQuestion();  //질문 변경

        //날짜 String set
        String MMddDot = DateUtil.getDate(cal.getTime(), "MM.dd");
        firstDate.setText("2019."+MMddDot);
        secondDate.setText("2020."+MMddDot);
        thirdDate.setText("2021."+MMddDot);
        fourthDate.setText("2022."+MMddDot);
        fifthDate.setText("2023."+MMddDot);

        //Diary String set
        firstDiary.setText(DBHelper.getOneData(this, "19"+MMdd).getContent());
        secondDiary.setText(DBHelper.getOneData(this, "20"+MMdd).getContent());
        thirdDiary.setText(DBHelper.getOneData(this, "21"+MMdd).getContent());
        fourthDiary.setText(DBHelper.getOneData(this, "22"+MMdd).getContent());
        fifthDiary.setText(DBHelper.getOneData(this, "23"+MMdd).getContent());
    }

    private void setQuestion() {    //질문 set
        String question = QuestionList.getQuestions().get(MMdd);
        questionText.setText(question);
    }

    public void nextData(View v) {   //다음 버튼 클릭 했을때
        cal.set(Calendar.DATE, cal.get(Calendar.DATE)+1);
        setAllDate();
    }

    public void previousData (View v) {  //이전 버튼 클릭 했을때
        cal.set(Calendar.DATE, cal.get(Calendar.DATE)-1);
        setAllDate();
    }

    public void selectDate(View v) { //날짜 클릭 했을때
        showDatePicker();
    }

    private void showDatePicker() {     //달력을 보여준다
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
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {    //달력에서 유저가 날짜를 클릭했을때
        cal.set(year, month, day);
        setAllDate();
    }

    public void modifyData (String year, String content) {    //수정 버튼 클릭 했을때
        Intent it  = new Intent(this, ModifyMyDataActivity.class);
        String msg = year+MMdd;
        it.putExtra("KEY_DATE", msg);
        it.putExtra("CONTENT", content);
        it.putExtra("QUESTION", questionText.getText().toString());
        startActivity(it);
    }

    public void clickFirst(View v) {    //2019년 아이템 클릭했을때
        modifyData("19", firstDiary.getText().toString());
    }

    public void clickSecond(View v) {    //2020년 아이템 클릭했을때
        modifyData("20", secondDiary.getText().toString());
    }

    public void clickThird(View v) {    //2021년 아이템 클릭했을때
        modifyData("21", thirdDiary.getText().toString());
    }

    public void clickFourth(View v) {    //2022년 아이템 클릭했을때
        modifyData("22", fourthDiary.getText().toString());
    }

    public void clickFifth(View v) {    //2023년 아이템 클릭했을때
        modifyData("23", fifthDiary.getText().toString());
    }

}
