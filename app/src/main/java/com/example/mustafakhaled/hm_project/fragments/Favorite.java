package com.example.mustafakhaled.hm_project.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mustafakhaled.hm_project.Interfaces.ShowProductsInterface;
import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.adapter.FavoriteProductAdapter;
import com.example.mustafakhaled.hm_project.adapter.ShowProductsAdapter;
import com.example.mustafakhaled.hm_project.helper.LocaleHelper;
import com.example.mustafakhaled.hm_project.helper.RecyclerViewPagination;
import com.example.mustafakhaled.hm_project.init.Application;
import com.example.mustafakhaled.hm_project.init.NavActivity;
import com.example.mustafakhaled.hm_project.model.FavoriteModel;
import com.example.mustafakhaled.hm_project.model.ProductModel;
import com.example.mustafakhaled.hm_project.retrofit.RetrofitClient;
import com.example.mustafakhaled.hm_project.room.RoomDatabaseHelper;
import com.example.mustafakhaled.hm_project.rx.RxJavaHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class Favorite extends Fragment{
    private String TAG= "Favorite";
    LinearLayoutManager linearLayoutManager;
    FavoriteProductAdapter adapter;
    private RecyclerView favorite_rv;
    List<FavoriteModel> list=new ArrayList<>();
    Button change;
    public Favorite() {
        getAllUrl(getActivity());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_favorite,container,false);
        favorite_rv = view.findViewById(R.id.favorite_recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        Log.e(TAG,"Favorite Array Value: "+ Arrays.toString(list.toArray()));
        adapter = new FavoriteProductAdapter(getContext(),list);
        favorite_rv.setLayoutManager(linearLayoutManager);
        favorite_rv.setAdapter(adapter);

        return view;

    }

//    @Override
//    public void onClick(View view) {
//        if(view==change){
//            if(LocaleHelper.getLanguage(getActivity()).equalsIgnoreCase("en")){
//                    LocaleHelper.setLocale(getActivity(),"ar");
//                    getActivity().recreate();
//                }
//                else{
//                    Local   eHelper.setLocale(getActivity(),"en");
//                    getActivity().recreate();
//
//            }
//
//        }
//    }


public List<FavoriteModel> getAllUrl(Context mContext){
    Single<List<FavoriteModel>> single = RoomDatabaseHelper.getAppDatabase(Application.customGetApplicationContext()).pe().getAllUrl();
    list = new ArrayList<>();
    single.observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<List<FavoriteModel>>() {
                @Override
                public void onSubscribe(Disposable d) {
                }
                @Override
                public void onSuccess(List<FavoriteModel> productEntities) {


                    list.addAll(productEntities);
//                    Log.e(TAG,"Favorite Array Value: "+ list.get(3).getUrl());
//                    Log.e(TAG,"Favorite Array Value: "+ list.get(3).getId());
                }

                @Override
                public void onError(Throwable e) {

                }
            });
    return list;
}
}
