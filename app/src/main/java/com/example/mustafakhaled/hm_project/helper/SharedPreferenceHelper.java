package com.example.mustafakhaled.hm_project.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mustafakhaled.hm_project.init.Application;

/*
 * Created by Mustafa Khaled on 10/7/2018.
 *
 */ public class SharedPreferenceHelper {
    SharedPreferences.Editor lastLogin = Application.getContext().getSharedPreferences("last_login",Context.MODE_PRIVATE).edit();
    public void editLastEntry(Long timestamp){
        lastLogin.putLong("last_entry",timestamp);
        lastLogin.apply();
    }

 }
