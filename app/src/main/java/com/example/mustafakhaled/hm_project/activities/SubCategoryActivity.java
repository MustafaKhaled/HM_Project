package com.example.mustafakhaled.hm_project.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.adapter.SubCategory_RecyclerView_Adapter;
import com.example.mustafakhaled.hm_project.init.NavActivity;
import com.example.mustafakhaled.hm_project.model.SubCategoryItem;
import com.example.mustafakhaled.hm_project.model.SubCategoryModel;

import java.util.ArrayList;

import static com.example.mustafakhaled.hm_project.init.Application.getContext;

public class SubCategoryActivity extends NavActivity {
    final private String TAG = "SubCategoryActivity";
    Context mContext;
    ArrayList<SubCategoryItem> subCategoryDetail;
    String categoryName;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        subCategoryDetail = getIntent().getParcelableArrayListExtra("subCategoryDetail");
        categoryName = getIntent().getStringExtra("categoryName");
        initiate(categoryName,R.layout.activity_sub_category);
        recyclerView = findViewById(R.id.subCategoryRecyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        SubCategory_RecyclerView_Adapter subAdapter = new SubCategory_RecyclerView_Adapter(mContext, subCategoryDetail, new SubCategory_RecyclerView_Adapter.CustomOnClickListner() {
            @Override
            public void OnItemListner(SubCategoryItem subCategoryItem, int position) {
                Intent intent = new Intent(SubCategoryActivity.this,ShowProducts.class);
                intent.putExtra("subCatTitle",subCategoryDetail.get(position).getName());
                intent.putExtra("subCatId",subCategoryDetail.get(position).getId());
                startActivity(intent);
//                Toast.makeText(mContext,"Item Clickked",Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(subAdapter);
        Log.e(TAG,"Sub Categories Size is: "+ subCategoryDetail.size());
    }
}
