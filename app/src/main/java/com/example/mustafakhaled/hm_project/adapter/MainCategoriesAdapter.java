package com.example.mustafakhaled.hm_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.activities.ShowProducts;
import com.example.mustafakhaled.hm_project.model.MainCategoriesModel;
import com.example.mustafakhaled.hm_project.model.SubCategoryItem;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 9/29/2018.
 *
 */ public class MainCategoriesAdapter extends BaseExpandableListAdapter {
    Context mContext;
    MainCategoriesModel categoriesModel;
    ArrayList<String> groups_title = new ArrayList<>();
    ArrayList<SubCategoryItem> subCategoryModel;

    public MainCategoriesAdapter(Context mContext,MainCategoriesModel categoriesModel) {
        this.mContext = mContext;
        this.categoriesModel = categoriesModel;
        for(int i=0;i<categoriesModel.getCount();i++){
            groups_title.add(categoriesModel.getCategoryItemModels().get(i).getName());
        }
    }

    @Override
    public int getGroupCount() {
        return categoriesModel.getCount();
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return categoriesModel.getCategoryItemModels().get(listPosition).getSubCategoryModels().size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return groups_title.get(listPosition);
    }

    @Override
    public Object getChild(int listPosition, int expandPosition) {
        return categoriesModel.getCategoryItemModels().get(listPosition).getSubCategoryModels();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public long getChildId(int listPosition, int expandPosition) {
        return expandPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int listPosition, boolean expanded, View view, ViewGroup viewGroup) {
        String title = (String) getGroup(listPosition);
        ImageView group_icon;
        if(view ==null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.main_categories_list_item,viewGroup,false);
        }
        group_icon = (ImageView) view.findViewById(R.id.group_arrow);
        TextView title_text_view = (TextView) view.findViewById(R.id.main_cat_textView);
        title_text_view.setTypeface(null, Typeface.BOLD);
        title_text_view.setText(title);
        if(expanded){
            group_icon.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
        }
        else {
            group_icon.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
        }
        return view;
    }

    @Override
    public View getChildView(int listPosition, final int expandPosition, boolean b, View view, ViewGroup viewGroup) {
        subCategoryModel = (ArrayList<SubCategoryItem>) getChild(listPosition,expandPosition);
        LayoutInflater layoutInflater  = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.sub_category_list_item,viewGroup,false);
        TextView subCat = (TextView) view.findViewById(R.id.sub_cat_textView);
        TextView newItem = view.findViewById(R.id.new_item_textview);
        subCat.setText(subCategoryModel.get(expandPosition).getName());
        newItem.setText("+"+subCategoryModel.get(expandPosition).getNewItems());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShowProducts.class);
                intent.putExtra("subCatTitle",subCategoryModel.get(expandPosition).getName());
                intent.putExtra("subCatId",subCategoryModel.get(expandPosition).getId());
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
