package com.example.mustafakhaled.hm_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.activities.ViewPagerGallery;
import com.example.mustafakhaled.hm_project.helper.Util;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 11/6/2018.
 *
 */ public class GridViewAdapter extends BaseAdapter {
    private final String TAG = "GridViewAdapter";
     ImageView imageView;
     ArrayList<String> files_path;
     Context mContext;

    public GridViewAdapter() {
    }

    public GridViewAdapter(ArrayList<String> files_path, Context mContext) {
        this.files_path = files_path;
        this.mContext = mContext;
        Log.d(TAG,"ArrayList Size: "+ files_path.size());
    }

    @Override
    public int getCount() {
        return files_path.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view ==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.grid_layout_item,viewGroup,false);
        }
        imageView = view.findViewById(R.id.image_item);
        imageView.setImageURI(Uri.parse(files_path.get(position)));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Image Selected: "+ files_path.get(position));
                Intent intent = new Intent(mContext,ViewPagerGallery.class);
                intent.putExtra("image_list",files_path);
                intent.putExtra("image_index",position);
                mContext.startActivity(intent);
            }
        });
            return view;
    }

}
