package com.example.diaryapp.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class DataUtil {

    public static ArrayList<String> stringToArray(String str) {
        ArrayList<String> result = new ArrayList<>();
        if(TextUtils.isEmpty(str))  return result;

        String[] strArr = str.split(", ");
        result.addAll(Arrays.asList(strArr));

        return result;
    }

    public static String arrayToString(ArrayList<String> list) {
        return list.size() > 0 ? TextUtils.join(", ", list) : "";
    }

}
