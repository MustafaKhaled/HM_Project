package com.example.mustafakhaled.hm_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.model.SubCategoryItem;
import com.example.mustafakhaled.hm_project.model.SubCategoryModel;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 10/15/2018.
 *
 */ public class SubCategory_RecyclerView_Adapter extends RecyclerView.Adapter<SubCategory_RecyclerView_Adapter.MyViewHolder> {
    CustomOnClickListner customOnClickListner;
    Context mContext;
    ArrayList<SubCategoryItem> subCategories;

     public interface CustomOnClickListner {
        void OnItemListner(SubCategoryItem subCategoryItem,int position);
    }
    public SubCategory_RecyclerView_Adapter(Context context, ArrayList<SubCategoryItem> subCategories,CustomOnClickListner customOnClickListner)
    {
        this.customOnClickListner = customOnClickListner;
        this.mContext=context;
        this.subCategories=subCategories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sub_category_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(subCategories.get(position),customOnClickListner,position);
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView subCategoryName,subCategoryNewitems;
        public MyViewHolder(View itemView) {
            super(itemView);
            subCategoryName = itemView.findViewById(R.id.sub_cat_textView);
            subCategoryNewitems = itemView.findViewById(R.id.new_item_textview);

        }
        private void bind(final SubCategoryItem subCategoryItem,final CustomOnClickListner clickListner,final int position){
            subCategoryName.setText(subCategoryItem.getName());
            subCategoryNewitems.setText("+"+subCategoryItem.getNewItems()+"");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListner.OnItemListner(subCategoryItem,position);
                }
            });

        }
    }

}
