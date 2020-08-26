package com.dobajar.myapplication.Model.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClint {

    public static Retrofit getRetrifitClint(){
        return new Retrofit.Builder()
                .baseUrl("http://dobajar.regnumit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /*public static Retrofit getFakeRetrifitClint(){
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }*/

}
