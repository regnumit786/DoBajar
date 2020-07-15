package com.dobajar.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dobajar.myapplication.Loged.Login;
import com.dobajar.myapplication.Loged.Register;
import com.dobajar.myapplication.R;

public class Splash_screen_activity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2500;
    private TextView textView1, textView2;
    private ImageView imageView;
    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_activity);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        textView1 = findViewById(R.id.textview_1);
        textView2 = findViewById(R.id.textview_2);
        imageView= findViewById(R.id.splash_imageview);

        top= AnimationUtils.loadAnimation(this, R.anim.top);
        bottom= AnimationUtils.loadAnimation(this, R.anim.bottom);

        imageView.setAnimation(top);
        textView1.setAnimation(bottom);
        textView2.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash_screen_activity.this, Login.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
