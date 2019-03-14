package com.example.diaryapp.activity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.diaryapp.R;
import com.example.diaryapp.db.SharedPreferencesApi;
import com.example.diaryapp.util.AlarmUtil;

import java.util.Calendar;

public class SettingActivity extends Activity {

    private TextView alarmTime;
    private Switch alarmSwitch;

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_setting);

        alarmTime = (TextView) findViewById(R.id.alarmTimeText);

        alarmSwitch = (Switch) findViewById(R.id.alarmSwitch);
        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                // Toast.makeText(SettingActivity.this, "체크상태 = " + isChecked, Toast.LENGTH_SHORT).show();
                SharedPreferencesApi.setIsAlarm(SettingActivity.this, isChecked);
                if (isChecked) {
                    Toast.makeText(SettingActivity.this, "알람 설정", Toast.LENGTH_SHORT).show();
                    AlarmUtil.setAlarm(SettingActivity.this);
                } else {
                    Toast.makeText(SettingActivity.this, "알람 해제", Toast.LENGTH_SHORT).show();
                    AlarmUtil.cancelAlarm(SettingActivity.this);
                }
            }
        });

        setData();
    }

    private void setData() {
        alarmTime.setText(SharedPreferencesApi.getAlarmHour(this) + " : " + SharedPreferencesApi.getAlarmMin(this));
        alarmSwitch.setChecked(SharedPreferencesApi.isAlarm(this));
    }

    public void setTime(View v) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(SettingActivity.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                SharedPreferencesApi.setAlarmHour(SettingActivity.this, timePicker.getHour());
                SharedPreferencesApi.setAlarmMin(SettingActivity.this, timePicker.getMinute());
                Log.d("DiaryApp", "set Time : " + timePicker.getHour() + " : " + timePicker.getMinute());
                if(alarmSwitch.isChecked()) AlarmUtil.setAlarm(SettingActivity.this);
                setData();
            }
        }, hour, minute, true);

        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
