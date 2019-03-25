package com.example.diaryapp.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;
import com.example.diaryapp.db.SharedPreferencesApi;
import com.example.diaryapp.model.DiaryData;
import com.example.diaryapp.ui.ImageViewPagerAdapter;
import com.example.diaryapp.util.DataUtil;

import java.util.ArrayList;
import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class ModifyMyDataActivity extends Activity {

    private static int PICK_IMAGE_REQUEST_CODE = 7777;

    private DiaryData data;
    private String keyDate = "";
    private String dateString = "";
    private String question = "";

    private ViewPager pager;
    private ImageViewPagerAdapter adpater;
    private ArrayList<String> imageList = new ArrayList<>();

    private FrameLayout questionLayout;
    private TextView date;
    private TextView questionText;
    private EditText t1;
    private EditText password;

    private TextToSpeech tts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);

        date = (TextView) findViewById(R.id.date);
        t1 = (EditText) findViewById(R.id.t1);
        questionText = (TextView) findViewById(R.id.questionText);
        password = (EditText) findViewById(R.id.passwordEdit);
        pager = (ViewPager) findViewById(R.id.selectImageView);
        questionLayout = (FrameLayout) findViewById(R.id.questionLayout);
        adpater = new ImageViewPagerAdapter(this);
        pager.setAdapter(adpater);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                    //음성 톤
                    tts.setPitch(0.7f);
                    //읽는 속도
                    tts.setSpeechRate(1.2f);
                }
            }
        });

        initData();

    }

    private void initData(){
        Intent it = getIntent();
        keyDate = it.getStringExtra("DATE_KEY");
        dateString = it.getStringExtra("DATE_STRING");
        question = it.getStringExtra("QUESTION");

        data = DBHelper.getOneData(this, keyDate);
        date.setText(dateString);
        t1.setText(data.getContent());
        password.setText(data.getPassword());
        questionText.setText(question);
        questionLayout.setVisibility(SharedPreferencesApi.isQuestion(this) ? View.VISIBLE : View.GONE);
        imageList = DataUtil.stringToArray(data.getImageUrl());
        changeImageView();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(tts != null && tts.isSpeaking()) {
            tts.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(tts != null) {
            tts.shutdown();
        }
    }

    public void modifyData(View v) {

        if(TextUtils.isEmpty(t1.getText().toString())) { //아무런 일기를 작성하지 않았을경우
            Toast.makeText(this, "일기 내용을 입력 해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!TextUtils.isEmpty(data.getContent())) {   //기존에 작성된 일기가 있을경우 DB 수정
            DBHelper.modifyDB(this, keyDate, t1.getText().toString(), password.getText().toString(), DataUtil.arrayToString(imageList));
        } else {    //기존에 작성된 일기가 없을경우 새로 저장
            DBHelper.saveDB(this, keyDate, t1.getText().toString(), password.getText().toString(), DataUtil.arrayToString(imageList));
        }
        finish();
    }

    public void deleteData(View v) {

        if(!TextUtils.isEmpty(data.getContent())) {   //기존에 작성된 일기가 있을경우 삭제
            DBHelper.deleteData(this, keyDate);
        } else {    //기존에 작성된 일기가 없을경우 아무 동작도 하지 않는다
        }
        //화면 종료
        finish();
    }

    public void cancelModifyData(View v) {
        finish();
    }

    public void changeImage(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("사진을 추가 할 수 있어요");
        builder.setMessage("이미지 변경 또는 삭제 하실래요??");
        builder.setPositiveButton("변경",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_REQUEST_CODE);
//                        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);

                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("삭제",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        imageList.clear();
                        changeImageView();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    private void changeImageView() {
        if(imageList.size() > 0) {  //저장된 imageUrl이 있을경우
            pager.setVisibility(View.VISIBLE);  //layout을 보여주고
            adpater.setData(imageList);
        } else {    //image가 없을경우 layout을 없앤다
            pager.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if(data.getClipData() != null) {
                imageList.clear();
                int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                for(int i = 0; i < count; i++){
                    imageList.add(data.getClipData().getItemAt(i).getUri().toString());
                }
            } else if(data.getData() != null) {
                imageList.clear();
                imageList.add(data.getData().toString());
            }

            changeImageView();
        } else {
            Toast.makeText(this, "이미지 변경 취소",Toast.LENGTH_LONG).show();
        }
    }

    public void startTextToSpeech(View v) {
        if(tts.isSpeaking()) {
            tts.stop();
        }

        if(!TextUtils.isEmpty(t1.getText().toString())) {
            tts.speak(t1.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

}
