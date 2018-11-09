package com.example.mustafakhaled.hm_project.retrofit;

import android.content.Context;

import com.example.mustafakhaled.hm_project.helper.LocaleHelper;
import com.example.mustafakhaled.hm_project.init.Application;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Created by Mustafa Khaled on 9/14/2018.
 *
 */ public class HeadersInterceptors implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        String lang = LocaleHelper.getLanguage(Application.getContext());
        Request request = chain.request();
        switch (lang){
            case "en":{
                request = request.newBuilder()
                            .removeHeader("lang")
                            .addHeader("lang","en")
                            .build();
                break;
            }
            case "ar":
                request = request.newBuilder()
                        .removeHeader("lang")
                        .addHeader("lang", "ar")
                        .build();
//                Log.e(TAG, "HeadersInterceptor Language Switch: Switched to arabic.");
                break;

            default:
                request = request.newBuilder()
                        .removeHeader("lang")
                        .addHeader("lang", "en")
                        .build();
        }
        Response response = chain.proceed(request);
        return response;
    }
}
