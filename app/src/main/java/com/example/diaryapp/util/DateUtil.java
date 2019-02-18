package com.example.diaryapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDate(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }
}
