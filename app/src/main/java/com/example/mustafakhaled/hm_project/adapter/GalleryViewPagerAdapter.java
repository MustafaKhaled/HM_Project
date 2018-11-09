package com.example.mustafakhaled.hm_project.adapter;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.activities.ShowProducts;

import java.util.ArrayList;

import static com.example.mustafakhaled.hm_project.Constants.ServerConfig.PRODUCT_BASE_URL;

/*
 * Created by Mustafa Khaled on 11/8/2018.
 *
 */ public class GalleryViewPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> paths = new ArrayList<>();
    int images[];
    LayoutInflater layoutInflater;


    public GalleryViewPagerAdapter(Context context, ArrayList<String> paths) {
        this.context = context;
        this.paths = paths;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.single_item_gallery, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.gallery_image_view);
        imageView.setImageURI(Uri.parse(paths.get(position)));

        container.addView(itemView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
