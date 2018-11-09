package com.example.mustafakhaled.hm_project.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;

import com.example.mustafakhaled.hm_project.Constants.ServerConfig;
import com.example.mustafakhaled.hm_project.Interfaces.MainCategoriesInterface;
import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.fragments.Download;
import com.example.mustafakhaled.hm_project.fragments.Favorite;
import com.example.mustafakhaled.hm_project.fragments.MenuFragment;
import com.example.mustafakhaled.hm_project.helper.ShowToastMessage;
import com.example.mustafakhaled.hm_project.init.NavActivity;
import com.example.mustafakhaled.hm_project.retrofit.RetrofitClient;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends NavActivity {
    AdView mAdView;
    private long mBackPressed;
    private  static final int TIME_INTERVAL = 2000;
    Fragment menuFragment;
    private android.support.v7.app.ActionBar toolbar;
    private String TAG ="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        initiate(getString(R.string.app_name),R.layout.activity_main);
        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new MenuFragment());

        MobileAds.initialize(this,getString(R.string.app_id_admob_firebase));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//        change_btn = (Button) findViewById(R.id.change_lang);
//        Log.e("MainActivity","Current Lang: "+ LocaleHelper.getLanguage(MainActivity.this));
//        change_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(LocaleHelper.getLanguage(getApplicationContext()).equalsIgnoreCase("en")){
//                    LocaleHelper.setLocale(getApplicationContext(),"ar");
//                    recreate();
//                }
//                else{
//                    LocaleHelper.setLocale(getApplicationContext(),"en");
//                    recreate();
//                }
//
////                Intent intent = new Intent(getBaseContext(),MainActivity.class);
////                finish();
////                startActivity(intent);
//            }
//        });


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    toolbar.setTitle(getString(R.string.fragment1_text));
                    fragment = new MenuFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_gifts:
                    toolbar.setTitle(getString(R.string.fragment2_text));
                    fragment = new Favorite();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    toolbar.setTitle(getString(R.string.fragment3_download));
                    fragment = new Download();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
//        ft.remove(fragment);
        ft.commit();
        fm.popBackStack();


    }

    @Override
    public void onBackPressed() {
        if(ServerConfig.BACK_TIME_INTERVAL+mBackPressed > System.currentTimeMillis()){
            super.onBackPressed();
            return;

        }
        else {
            ShowToastMessage.showToast(this,getString(R.string.press_once_again));
            mBackPressed = System.currentTimeMillis();
        }

    }
}
