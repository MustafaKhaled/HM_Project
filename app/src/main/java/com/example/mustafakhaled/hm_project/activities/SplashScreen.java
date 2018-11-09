package com.example.mustafakhaled.hm_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.init.BaseActivity;

/*
 * Created by Mustafa Khaled on 9/9/2018.
 *
 */ public class SplashScreen extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        },3000);
    }
}
