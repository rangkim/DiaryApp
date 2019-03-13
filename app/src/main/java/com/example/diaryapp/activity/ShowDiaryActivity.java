package com.example.diaryapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;
import com.example.diaryapp.db.QuestionList;
import com.example.diaryapp.model.DiaryData;
import com.example.diaryapp.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;

public class ShowDiaryActivity extends Activity implements DatePickerDialog.OnDateSetListener {

    private static int MY_PERMISSIONS_REQUEST_READ_MEDIA = 4885;

    private Calendar cal;
    private String MMdd = "";
    private String MMddDot = "";

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

    private ImageView firstImageView;
    private ImageView secondImageView;
    private ImageView thirdImageView;
    private ImageView fourthImageView;
    private ImageView fifthImageView;


    private ArrayList<DiaryData> dairyList = new ArrayList<>();

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

        firstImageView = (ImageView) findViewById(R.id.firstImage);
        secondImageView = (ImageView) findViewById(R.id.secondImage);
        thirdImageView = (ImageView) findViewById(R.id.thirdImage);
        fourthImageView = (ImageView) findViewById(R.id.fourthImage);
        fifthImageView = (ImageView) findViewById(R.id.fifthImage);

        cal = Calendar.getInstance();
        permissionCheck();
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
        MMddDot = DateUtil.getDate(cal.getTime(), "MM.dd");
        firstDate.setText("2019."+MMddDot);
        secondDate.setText("2020."+MMddDot);
        thirdDate.setText("2021."+MMddDot);
        fourthDate.setText("2022."+MMddDot);
        fifthDate.setText("2023."+MMddDot);

        //Diary String set
        dairyList.clear();
        checkPassword(firstDiary, firstImageView, DBHelper.getOneData(this, "19"+MMdd));
        checkPassword(secondDiary, secondImageView, DBHelper.getOneData(this, "20"+MMdd));
        checkPassword(thirdDiary, thirdImageView, DBHelper.getOneData(this, "21"+MMdd));
        checkPassword(fourthDiary, fourthImageView, DBHelper.getOneData(this, "22"+MMdd));
        checkPassword(fifthDiary, fifthImageView, DBHelper.getOneData(this, "23"+MMdd));
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

    public void modifyData (String year) {    //수정 버튼 클릭 했을때
        Intent it  = new Intent(this, ModifyMyDataActivity.class);
        String key = year+MMdd;
        String dateString = "20"+year+"."+MMddDot;
        it.putExtra("DATE_KEY", key);
        it.putExtra("DATE_STRING", dateString);
        it.putExtra("QUESTION", questionText.getText().toString());
        startActivity(it);
    }

    public void clickFirst(View v) {    //2019년 아이템 클릭했을때
        showCheckPassword("19", 0);
    }

    public void clickSecond(View v) {    //2020년 아이템 클릭했을때
        showCheckPassword("20", 1);
    }

    public void clickThird(View v) {    //2021년 아이템 클릭했을때
        showCheckPassword("21", 2);
    }

    public void clickFourth(View v) {    //2022년 아이템 클릭했을때
        showCheckPassword("22", 3);
    }

    public void clickFifth(View v) {    //2023년 아이템 클릭했을때
        showCheckPassword("23", 4);
    }

    private void checkPassword(TextView tv, ImageView iv, DiaryData data) {
        dairyList.add(data);    //원래는 여기에 넣기엔 좀 애매하지만, 개발상 편하게 여기에서 data를 add 시켰다.

        if(TextUtils.isEmpty(data.getPassword())){  //비밀번호 안걸려 있으면
            tv.setText(data.getContent());
            if(!TextUtils.isEmpty(data.getImageUrl())) {    //저장한 이미지가 있으면
                iv.setVisibility(View.VISIBLE);
                Glide.with(this)    // 이미지를 불러온다
                        .load("content://media"+data.getImageUrl())
                        .apply(RequestOptions.circleCropTransform())
                        .into(iv);
            } else {
                iv.setImageDrawable(null);
                iv.setVisibility(View.GONE);
            }
        } else {    //비밀번호 걸려 있으면
            tv.setText("비밀 이에요");
            iv.setImageDrawable(null);
            iv.setVisibility(View.GONE);
        }
    }

    private void showCheckPassword(String year, int index)
    {
        DiaryData data = dairyList.get(index);
        if(TextUtils.isEmpty(data.getPassword())){
            modifyData(year);
        } else {
            showPasswordDialog(year, data.getPassword());
        }
    }

    private void showPasswordDialog(final String year, final String password){
        final EditText edittext = new EditText(this);
        edittext.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Password");
        builder.setMessage("Password를 입력 해 주세요.");
        builder.setView(edittext);
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(password.equals(edittext.getText().toString()) ||
                                "$$$$$$".equals(edittext.getText().toString())){
                            modifyData(year);
                        } else {
                            Toast.makeText(ShowDiaryActivity.this, "비밀번호가 틀렸어요", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    private void permissionCheck(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_MEDIA);
        }
    }

    public void goSetting(View v) {
        Intent it  = new Intent(this, SettingActivity.class);
        startActivity(it);
    }
}
