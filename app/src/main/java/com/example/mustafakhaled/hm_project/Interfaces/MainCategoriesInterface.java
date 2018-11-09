package com.example.mustafakhaled.hm_project.Interfaces;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.mustafakhaled.hm_project.Constants.ServerConfig;
import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.init.BaseActivity;
import com.example.mustafakhaled.hm_project.init.NavActivity;
import com.example.mustafakhaled.hm_project.model.MainCategoriesModel;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/*
 * Created by Mustafa Khaled on 9/14/2018.
 *
 */ public interface MainCategoriesInterface {
        @Headers({"lang: ar"})
        @GET(ServerConfig.MAIN_CATEGORIES)
        Call<MainCategoriesModel> getAllCategories();

    }

