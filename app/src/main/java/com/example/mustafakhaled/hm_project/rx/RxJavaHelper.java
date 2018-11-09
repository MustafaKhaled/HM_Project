package com.example.mustafakhaled.hm_project.rx;

import android.content.Context;
import android.util.Log;

import com.example.mustafakhaled.hm_project.helper.ShowToastMessage;
import com.example.mustafakhaled.hm_project.init.Application;
import com.example.mustafakhaled.hm_project.model.TestModel;
import com.example.mustafakhaled.hm_project.room.ProductEntity;
import com.example.mustafakhaled.hm_project.room.RoomDatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

/*
 * Created by Mustafa Khaled on 10/28/2018.
 *
 */ public  class RxJavaHelper {
    static List<String> ids;
    TestModel testModel = new TestModel();
     public static void insertRowtoDatabase(Context mContext , ProductEntity productEntity){
         Completable.fromAction(() -> RoomDatabaseHelper.getAppDatabase(mContext).pe().insert(productEntity))
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new CompletableObserver() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onComplete() {
//                         ShowToastMessage.showToast(mContext,"Row added sucessfully");
                     }

                     @Override
                     public void onError(Throwable e) {
                         Log.e(TAG,"insertUsingRxJava",e);
//                         ShowToastMessage.showToast(mContext,"Errorrrr");

                     }
                 });

     }

     public static void removeFromFavorite(Context mContext,int postId){
         Completable.fromAction(()-> RoomDatabaseHelper.getAppDatabase(mContext).pe().delete(postId))
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new CompletableObserver() {
                     @Override
                     public void onSubscribe(Disposable d) {
                         Log.d(TAG,"onSubscribe(): Subscribing");

                     }

                     @Override
                     public void onComplete() {
                        Log.d(TAG,"onComplete(): Delete Completed");
                     }

                     @Override
                     public void onError(Throwable e) {
                        Log.e(TAG,"onError(): "+e.getCause());
                     }
                 });

     }
//     public static List<String> getAllUrl(Context mContext){
//         Single<List<String>> single = RoomDatabaseHelper.getAppDatabase(mContext).pe().getAllUrl();
//         ids = new ArrayList<>();
//         single.observeOn(Schedulers.io())
//                 .observeOn(AndroidSchedulers.mainThread())
//                 .subscribe(new SingleObserver<List<String>>() {
//                     @Override
//                     public void onSubscribe(Disposable d) {
//                     }
//                     @Override
//                     public void onSuccess(List<String> productEntities) {
//
//                         ids.addAll(productEntities);
//                         Log.e(TAG,"Show Array Value: "+ Arrays.toString(ids.toArray()));
//                     }
//
//                     @Override
//                     public void onError(Throwable e) {
//
//                     }
//                 });
//         return ids;
//     }
     }

