package com.example.diaryapp.db;

import java.util.HashMap;

public class QuestionList {

    private static HashMap<String, String> questions = new HashMap<>();

    public static HashMap<String, String> getQuestions(){
        if(questions.size() == 0){
            setQuestions();
        }
        return questions;
    }

    private static void setQuestions(){
        questions.put("0216", "오늘 기분은 어떤가요?");
        questions.put("0217", "날씨는 좋았나요?");
        questions.put("0218", "행복 했나요?");
        questions.put("0219", "흠....");
        questions.put("0220", "안녕?");
        questions.put("0221", "헬로우?");
        questions.put("0222", "엘지는 오늘 잘했나요?");
        questions.put("0223", "오늘도 졌군요?");
        questions.put("0224", "하하하하");
    }

}
