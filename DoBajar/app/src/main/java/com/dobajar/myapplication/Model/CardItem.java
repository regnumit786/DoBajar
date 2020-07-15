package com.dobajar.myapplication.Model;

import android.content.Context;

public class CardItem {
    private Context context;
    private String productImage;
    private String productName;
    private String productPrize;
    private String productNumber;

    public CardItem(Context context, String productImage, String productName, String productPrize, String productNumber) {
        this.context = context;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrize = productPrize;
        this.productNumber = productNumber;
    }

    public CardItem(){

    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrize() {
        return productPrize;
    }

    public void setProductPrize(String productPrize) {
        this.productPrize = productPrize;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }
}
