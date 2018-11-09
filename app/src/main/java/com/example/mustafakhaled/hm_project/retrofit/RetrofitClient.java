package com.example.mustafakhaled.hm_project.retrofit;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.mustafakhaled.hm_project.Constants.ServerConfig;
import com.example.mustafakhaled.hm_project.init.Application;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by Mustafa Khaled on 9/14/2018.
 *
 */ public class RetrofitClient {
     public static Retrofit retrofitClient(){
     HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
     ConnectivityInterceptor connectivityInterceptor = new ConnectivityInterceptor(Application.customGetApplicationContext());
     HeadersInterceptors headersInterceptors = new HeadersInterceptors();
     httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

     OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
             .addInterceptor(httpLoggingInterceptor)
             .addInterceptor(headersInterceptors)
             .addInterceptor(connectivityInterceptor)
             .connectTimeout(30,TimeUnit.SECONDS)
             .writeTimeout(30, TimeUnit.SECONDS)
             .readTimeout(30, TimeUnit.SECONDS)
             .build();



     Retrofit retrofit = new Retrofit.Builder()
             .baseUrl(ServerConfig.BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .client(okHttpClient)
             .build();

     return retrofit;
    }
}
