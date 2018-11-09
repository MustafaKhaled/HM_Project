package com.example.mustafakhaled.hm_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.model.NavigationDrawerModel;

import java.util.ArrayList;
import java.util.List;


/*
 * Created by Mustafa Khaled on 9/8/2018.
 *
 */ public class NavigationDrawerAdapter extends ArrayAdapter<NavigationDrawerModel> {
     ArrayList<NavigationDrawerModel> modelArrayList;
    public NavigationDrawerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<NavigationDrawerModel> objects) {
        super(context, resource, objects);
        this.modelArrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.navigation_drawer_list_item,null,false);
        TextView item_title = (TextView) view.findViewById(R.id.item_text);
        TextView item_desc = (TextView) view.findViewById(R.id.item_desc);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_icon);

        item_title.setText(modelArrayList.get(position).getItem_List());
        item_desc.setText(modelArrayList.get(position).getItem_desc());
        imageView.setImageResource(modelArrayList.get(position).getIcon());

        return view;

    }
}
