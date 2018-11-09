package com.example.mustafakhaled.hm_project.init;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.mustafakhaled.hm_project.helper.LocaleHelper;

public class Application extends android.app.Application {
    public static Application mContext;
    public static Context getApplicationContextInstance;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
        MultiDex.install(this);


    }

    @Override
    public void onCreate() {
        mContext = this;
        getApplicationContextInstance=getApplicationContext();
        super.onCreate();
    }

    public static Application getContext (){
        return mContext;
    }
    public static Context customGetApplicationContext (){ return getApplicationContextInstance;
    }

}
