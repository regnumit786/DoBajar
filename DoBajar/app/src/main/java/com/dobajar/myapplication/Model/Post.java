package com.dobajar.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("userId")
    private int userId;
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    public Post(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
