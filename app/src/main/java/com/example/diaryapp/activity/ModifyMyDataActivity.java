package com.example.diaryapp.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.diaryapp.R;
import com.example.diaryapp.db.DBHelper;
import com.example.diaryapp.model.DiaryData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ModifyMyDataActivity extends Activity {

    private static int PICK_IMAGE_REQUEST_CODE = 7777;

    private DiaryData data;
    private String keyDate = "";
    private String dateString = "";
    private String question = "";
    private String imageUrl = "";

    private TextView date;
    private TextView questionText;
    private EditText t1;
    private EditText password;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        date = (TextView) findViewById(R.id.date);
        t1 = (EditText) findViewById(R.id.t1);
        questionText = (TextView) findViewById(R.id.questionText);
        password = (EditText) findViewById(R.id.passwordEdit);
        imageView = (ImageView) findViewById(R.id.selectImageView);

        Intent it = getIntent();

        keyDate = it.getStringExtra("DATE_KEY");
        dateString = it.getStringExtra("DATE_STRING");
        question = it.getStringExtra("QUESTION");

        data = DBHelper.getOneData(this, keyDate);

        date.setText(dateString);
        t1.setText(data.getContent());
        password.setText(data.getPassword());
        questionText.setText(question);
        changeImageView(data.getImageUrl());
    }

    public void modifyData(View v) {

        if(TextUtils.isEmpty(t1.getText().toString())) { //아무런 일기를 작성하지 않았을경우
            Toast.makeText(this, "일기 내용을 입력 해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!TextUtils.isEmpty(data.getContent())) {   //기존에 작성된 일기가 있을경우 DB 수정
            DBHelper.modifyDB(this, keyDate, t1.getText().toString(), password.getText().toString(), imageUrl);
        } else {    //기존에 작성된 일기가 없을경우 새로 저장
            DBHelper.saveDB(this, keyDate, t1.getText().toString(), password.getText().toString(), imageUrl);
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

    public void changeImage(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("사진을 추가 할 수 있어요");
        builder.setMessage("이미지 변경 또는 삭제 하실래요??");
        builder.setPositiveButton("변경",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST_CODE);

                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("삭제",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        changeImageView("");
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    private void changeImageView(String imgUrl) {
        imageUrl = imgUrl;

        if(!TextUtils.isEmpty(imageUrl)) {  //저장된 imageUrl이 있을경우
            imageView.setVisibility(View.VISIBLE);  //layout을 보여주고
            Glide.with(this)    // 이미지를 불러온다
                    .load("content://media"+imgUrl)
                    .into(imageView);
        } else {    //image가 없을경우 layout을 없앤다
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            final String imageUri = data.getData().getPath();
            changeImageView(imageUri);
        } else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

}
