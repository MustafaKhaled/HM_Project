package com.example.mustafakhaled.hm_project.init;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.mustafakhaled.hm_project.helper.LocaleHelper;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

}