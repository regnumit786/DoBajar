package com.dobajar.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dobajar.myapplication.Adapter.AllFragmentDataAdpr;
import com.dobajar.myapplication.R;


public class PersonalCare extends AppCompatActivity implements AllFragmentDataAdpr.OnItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalcares);

    }

    @Override
    public void OnItemClick(int position) {
        Intent intent= new Intent(this, ProductDetails.class);
        startActivity(intent);
    }
}
