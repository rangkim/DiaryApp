package com.example.diaryapp.model;

public class DiaryData {

    private String date;
    private String content;
    private String password;

    public DiaryData(String date, String content, String password) {
        this.date = date;
        this.content = content;
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
