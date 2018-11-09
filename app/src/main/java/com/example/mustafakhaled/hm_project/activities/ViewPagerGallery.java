package com.example.mustafakhaled.hm_project.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.adapter.GalleryViewPagerAdapter;
import com.example.mustafakhaled.hm_project.init.NavActivity;

import java.util.ArrayList;

public class ViewPagerGallery extends AppCompatActivity {
    ViewPager vp;
    int images[] = {R.drawable.app_icon, R.drawable.app_icon, R.drawable.app_icon, R.drawable.app_icon};

    private String TAG = "ViewPagerGallery";
    Bundle bundle;
    ArrayList<String> images_path;
    GalleryViewPagerAdapter pagerAdapter ;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_pager_gallery);
        setContentView(R.layout.activity_view_pager_gallery);
        bundle = getIntent().getExtras();
        images_path= bundle.getStringArrayList("image_list");
        index = bundle.getInt("image_index");
        Log.d(TAG,"Images List Size: "+ images_path.size());

        vp = findViewById(R.id.viewpager_gallery);

        pagerAdapter= new GalleryViewPagerAdapter(this,images_path);
        vp.setAdapter(pagerAdapter);
        vp.setCurrentItem(index);

    }
}
