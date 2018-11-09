package com.example.mustafakhaled.hm_project.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.example.mustafakhaled.hm_project.helper.ShowToastMessage;
import com.example.mustafakhaled.hm_project.helper.Util;
import com.example.mustafakhaled.hm_project.init.Application;
import com.example.mustafakhaled.hm_project.rx.RxJavaHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.mustafakhaled.hm_project.init.Application.customGetApplicationContext;
import static com.example.mustafakhaled.hm_project.init.Application.getContext;

/*
 * Created by Mustafa Khaled on 11/3/2018.
 *
 */ public class ConnectivityInterceptor implements Interceptor {
    Context mContext;
    ShowToastMessage showToastMessage;
    public ConnectivityInterceptor(Context context) {
        this.mContext =context;
        showToastMessage = new ShowToastMessage();

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(!Util.isNetworkAvailable())
            showToastMessage.showOnUIToast((customGetApplicationContext()), "Please check you internet connection");

        Request.Builder builder =  chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
