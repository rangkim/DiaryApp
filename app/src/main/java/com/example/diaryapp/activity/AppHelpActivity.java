package com.example.diaryapp.activity;

        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.TextView;

public class AppHelpActivity extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        TextView textview = new TextView(this);
        textview.setTextSize(20);
        textview.setText("일기장 어플입니다.");
        setContentView(textview);
    }
}
