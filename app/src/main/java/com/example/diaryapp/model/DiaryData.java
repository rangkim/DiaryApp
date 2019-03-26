package com.example.diaryapp.model;

public class DiaryData {

    private String date;
    private String content;
    private String password;
    private String imageUrl;
    private String imageTitle;

    public DiaryData(String date, String content, String password, String imageUrl, String imageTitle) {
        this.date = date;
        this.content = content;
        this.password = password;
        this.imageUrl = imageUrl;
        this.imageTitle = imageTitle;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
}
