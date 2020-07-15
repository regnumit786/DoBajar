package com.dobajar.myapplication.CheckOut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dobajar.myapplication.R;
import com.dobajar.myapplication.activity.MainActivity;

public class Checkout extends AppCompatActivity {

    private Button gotoHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        gotoHome= findViewById(R.id.goto_home);

        gotoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Checkout.this, MainActivity.class));
                finish();
            }
        });
    }
}
