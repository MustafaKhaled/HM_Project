package com.example.mustafakhaled.hm_project.adapter;

import android.app.Application;
import android.app.DownloadManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.MainThread;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.helper.ShowToastMessage;
import com.example.mustafakhaled.hm_project.helper.Util;
import com.example.mustafakhaled.hm_project.model.SingleProductModel;
import com.example.mustafakhaled.hm_project.model.TestModel;
import com.example.mustafakhaled.hm_project.room.ProductEntity;
import com.example.mustafakhaled.hm_project.room.RoomDatabaseHelper;
import com.example.mustafakhaled.hm_project.rx.RxJavaHelper;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;
import static com.example.mustafakhaled.hm_project.Constants.ServerConfig.PRODUCT_BASE_URL;
import static com.example.mustafakhaled.hm_project.room.RoomDatabaseHelper.getAppDatabase;

/*
 * Created by Mustafa Khaled on 10/6/2018.
 *
 */ public class ShowProductsAdapter extends RecyclerView.Adapter<ShowProductsAdapter.MyViewHolder> {
    int size;
    List<Integer> ids = new ArrayList<>();
    SingleProductModel temp;
    DownloadManager downloadManager;
    final private String TAG="ShowProductAdapter";
    Context mContext;
    ArrayList<Object> productModel;
    public ShowProductsAdapter(Context mContext, ArrayList<Object> productModel,int size) {
        this.mContext = mContext;
        this.productModel = productModel;
        this.size = size;
        Log.e(TAG,"Array List Size: "+ids.size());
        getAllData(mContext);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.recycler_view_list_item,parent,false);
                 downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

                Log.e(TAG,"Array List Size: "+ids.size());



        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder  holder, final int position) {
                temp = (SingleProductModel) productModel.get(position);
                Glide.with(mContext)
                .load(PRODUCT_BASE_URL+temp.getImageUrls())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.app_icon)
                .into(holder.product_imageView);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int newHeight = windowManager.getDefaultDisplay().getHeight()*2/3;
        int newWidth = windowManager.getDefaultDisplay().getWidth()*9/10;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(newWidth, newHeight);
        holder.product_imageView.setLayoutParams(params);
        boolean check=ids.contains(((SingleProductModel) productModel.get(position)).getId());
        Log.e(TAG,"OnClick(): Id of the appeared post "+ Integer.toString(((SingleProductModel) productModel.get(position)).getId()));
        if(check || ((SingleProductModel) productModel.get(position)).isLoved()){
            Log.e(TAG,"onBindViewHolder(): Check if TRUE");
            holder.love.setImageResource(R.drawable.ic_favorite_red_24dp);
            ((SingleProductModel) productModel.get(position)).setLoved(true);
        }
        else {
            Log.e(TAG,"onBindViewHolder(): Check if FALSE");
            holder.love.setImageResource(R.drawable.ic_favorite_border_black_dp);
//            ((SingleProductModel) productModel.get(position)).setLoved(false);

        }

        holder.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((SingleProductModel) productModel.get(position)).isLoved()==false){
//                    ShowToastMessage.showToast(mContext,"Added to favorite");
                    ((SingleProductModel) productModel.get(position)).setLoved(true);
                    holder.love.setImageResource(R.drawable.ic_favorite_red_24dp);
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setId(((SingleProductModel) productModel.get(position)).getId());
                    productEntity.setUrl(((SingleProductModel) productModel.get(position)).getImageUrls());
                    RxJavaHelper.insertRowtoDatabase(mContext,productEntity);
//                    RxJavaHelper.getAllData(mContext);
//                    ShowToastMessage.showToast(mContext,Arrays.toString(ls.toArray()));

                }
                else{
//                    ShowToastMessage.showToast(mContext,"Removed from favorite");
                    ((SingleProductModel) productModel.get(position)).setLoved(false);
                    holder.love.setImageResource(R.drawable.ic_favorite_border_black_dp);
                    RxJavaHelper.removeFromFavorite(mContext,((SingleProductModel) productModel.get(position)).getId());
                }
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.downloadFile(PRODUCT_BASE_URL+((SingleProductModel) productModel.get(holder.getAdapterPosition())).getImageUrls(),
                        ((SingleProductModel) productModel.get(holder.getAdapterPosition())).getImageUrls());
                }
        });
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.copyToClipboard(PRODUCT_BASE_URL+ ((SingleProductModel) productModel.get(holder.getAdapterPosition())).getImageUrls());
            }
        });


    }

    @Override
    public int getItemCount() {
        return productModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView product_imageView, love, share, download,copy,facebook;

        public MyViewHolder(View itemView) {
            super(itemView);
            product_imageView = itemView.findViewById(R.id.imageView);
            love = itemView.findViewById(R.id.favorite);
            share = itemView.findViewById(R.id.share);
            download = itemView.findViewById(R.id.download);
            copy = itemView.findViewById(R.id.copy);
            facebook = itemView.findViewById(R.id.facebook);

        }
    }

    public void getAllData(Context mContext){
        Single<List<Integer>> single = RoomDatabaseHelper.getAppDatabase(mContext).pe().getAllIds();
        single.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribeWith(new SingleObserver<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe(): Subscribing now");
                    }

                    @Override
                    public void onSuccess(List<Integer> productEntities) {
                        ids.addAll(productEntities);
//                        Log.d(TAG,"onSuccess(): List size: "+productEntities.size());
                        Log.d(TAG,"onSuccess(): Custom ArrayList Size: "+Arrays.toString(ids.toArray()));

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"onError(): Error now");

                    }
                });
    }

}
