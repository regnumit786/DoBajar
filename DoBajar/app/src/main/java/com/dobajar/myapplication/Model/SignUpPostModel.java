package com.dobajar.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class SignUpPostModel {

    @SerializedName("name")
    private String name;

    @SerializedName("phone_number")
    private String phone_number;

    @SerializedName("password")
    private String password;

    public SignUpPostModel(String name, String phone_number, String password) {
        this.name = name;
        this.phone_number = phone_number;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
