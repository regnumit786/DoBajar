package com.dobajar.myapplication.Model;

public class ProductDataModel {
    private String prize;
    private String descreption;
    private String image;

    public ProductDataModel(String prize, String descreption, String image) {
        this.prize = prize;
        this.descreption = descreption;
        this.image = image;
    }

    public ProductDataModel() {
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
