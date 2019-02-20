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
        questions.put("0101", "내 삶의 목적은 무엇인가?");
        questions.put("0102", "사람은 변할 수 있을까?");
        questions.put("0103", "현재 읽고 있는 글이나 책이 있다면?");
        questions.put("0104", "내 삶에서 가장 결별하고 싶은 것은?");
        questions.put("0105", "가장 최근에 방문한 식당은? 무엇을 먹었는가?");
        questions.put("0106", "오늘 하루가 힘들었던 이유는 _______이다.");
        questions.put("0107", "나는 운이 좋은 편이라고 생각하는가?");
        questions.put("0108", "계속 머릿속에 맴도는 노래가 있다면?");
        questions.put("0109", "오늘은 평범한 하루였는가? 그렇거나 그렇지 않은 이유는?");
        questions.put("0110", "내 인생을 바꿀 만한 영감을 준 사람이 있다면?");
        questions.put("0111", "나는 오늘 _____을(를) 잃었다.");
        questions.put("0112", "가장 좋아하는 액세서리는?");
        questions.put("0113", "다음에 여행하고 싶은 곳은 어디인가?");
        questions.put("0114", "나는 이끄는 쪽인가 따르는 쪽인가?");
        questions.put("0115", "가장 자신있게 만들 수 있는 요리는?");
        questions.put("0116", "빌리거나 빌려준 돈이 있는가?");
        questions.put("0117", "오늘 입은 옷차림 중에서 가장 오래된 것은?");
        questions.put("0118", "최근 꾼 꿈 중에 가장 인상적인 것은?");
        questions.put("0119", "평생 한 사람만 사랑하면서 살 수 있다고 생각하는가?");
        questions.put("0120", "앙심을 품고 있는 대상이 있는가? 무슨 일 때문인가?");
        questions.put("0121", "만약 창업을 한다면 어떤 일을 하고 싶은가?");
        questions.put("0122", "안전을 추구하는가 모험을 추구하는가?");
        questions.put("0123", "휴식이 필요하다고 느끼는가? 무엇으로부터?");
        questions.put("0124", "몹시 기다려지는 일이 있는가?");
        questions.put("0125", "무엇이 나를 나답게 만드는가?");
        questions.put("0126", "오늘 나는 ______이(가) 좀 더 필요했다.");
        questions.put("0127", "오늘의 나를 예술사조로 표현한다면? (초현실주의, 모더니즘, 다다이즘)");
        questions.put("0128", "집이란 무엇이라고 생각하는가?");
        questions.put("0129", "마지막으로 본  TV 프로그램은?");
        questions.put("0130", "오늘 있었던 일 중에서 지우고 싶은 기억이 있다면?");
        questions.put("0131", "궁극적으로 어떤 사람이 되고 싶은가?");




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
