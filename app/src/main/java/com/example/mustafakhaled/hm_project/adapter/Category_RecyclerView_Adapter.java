package com.example.mustafakhaled.hm_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mustafakhaled.hm_project.R;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 10/14/2018.
 *
 */ public class Category_RecyclerView_Adapter extends RecyclerView.Adapter<Category_RecyclerView_Adapter.MyViewHolder> {
    Context mContext;
    ArrayList<String> headers;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String item,int position);
    }
    public Category_RecyclerView_Adapter(Context context, ArrayList<String> headers,OnItemClickListener listener) {
        this.mContext=context;
        this.headers =headers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Category_RecyclerView_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_categories_list_item,parent,false);
        return new Category_RecyclerView_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(headers.get(position),listener,position);

    }

    @Override
    public int getItemCount() {
        return headers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView header_textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            header_textView = itemView.findViewById(R.id.main_cat_textView);
        }

        public void bind(final String cat, final OnItemClickListener listener, final int position){

            header_textView.setText(cat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(cat,position);
                }
            });

        }

    }

}
