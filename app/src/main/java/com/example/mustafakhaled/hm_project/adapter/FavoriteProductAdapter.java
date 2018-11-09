package com.example.mustafakhaled.hm_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.model.FavoriteModel;
import com.example.mustafakhaled.hm_project.model.SingleProductModel;
import com.example.mustafakhaled.hm_project.rx.RxJavaHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.mustafakhaled.hm_project.Constants.ServerConfig.PRODUCT_BASE_URL;

/*
 * Created by Mustafa Khaled on 11/3/2018.
 *
 */ public class FavoriteProductAdapter extends RecyclerView.Adapter<FavoriteProductAdapter.MyViewHolder> {
     private String TAG = "FavoriteProductAdapter";
     Context mContext;
     List<FavoriteModel> links;
     FavoriteModel temp;
    public FavoriteProductAdapter(Context context, List<FavoriteModel> links) {
        this.mContext = context;
        this.links=links;
        Log.d(TAG,"FavoriteProductAdapter()" + "Favorite List Size: "+ links.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_view_list_item,parent,false);
        return new FavoriteProductAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        temp = links.get(position);
        Glide.with(mContext)
                .load(PRODUCT_BASE_URL+temp.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.app_icon)
                .into(holder.product_imageView);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int newHeight = windowManager.getDefaultDisplay().getHeight()*2/4;
        int newWidth = windowManager.getDefaultDisplay().getWidth()*9/10;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(newWidth, newHeight);
        holder.product_imageView.setLayoutParams(params);
        holder.love.setImageResource(R.drawable.ic_favorite_red_24dp);
        holder.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(links.size()!=0){
                    Log.e(TAG,"onClick()"+ "Remove "+ "Position "+position +
                            "Links Position: "+ links.get(position).getId());

                    RxJavaHelper.removeFromFavorite(mContext,links.get(position).getId());
                    links.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,links.size());
                }



            }
        });

    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView product_imageView, love, share, download,copy;
        public MyViewHolder(View itemView) {
            super(itemView);
            product_imageView = itemView.findViewById(R.id.imageView);
            love = itemView.findViewById(R.id.favorite);
            share = itemView.findViewById(R.id.share);
            download = itemView.findViewById(R.id.download);
            copy = itemView.findViewById(R.id.copy);
        }
    }
}
