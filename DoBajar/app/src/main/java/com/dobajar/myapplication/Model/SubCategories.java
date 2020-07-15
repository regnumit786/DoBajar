package com.dobajar.myapplication.Model;


import com.google.gson.annotations.SerializedName;

public class SubCategories {
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private int image;
    @SerializedName("category_id")
    private String category_id;

    public SubCategories(String name, int image, String category_id) {
        this.name = name;
        this.image = image;
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
