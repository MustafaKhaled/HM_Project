package com.example.mustafakhaled.hm_project.activities;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.example.mustafakhaled.hm_project.Interfaces.ShowProductsInterface;
import com.example.mustafakhaled.hm_project.R;
import com.example.mustafakhaled.hm_project.adapter.ShowProductsAdapter;
import com.example.mustafakhaled.hm_project.helper.RecyclerViewDividerDecoration;
import com.example.mustafakhaled.hm_project.helper.RecyclerViewPagination;
import com.example.mustafakhaled.hm_project.helper.ShowToastMessage;
import com.example.mustafakhaled.hm_project.init.NavActivity;
import com.example.mustafakhaled.hm_project.model.ProductModel;
import com.example.mustafakhaled.hm_project.retrofit.RetrofitClient;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowProducts extends NavActivity {
        Bundle bundle;
        ActionBar toolbar;
        Context mContext;
        AdView mAdView;
        private int page_count=0;

    // The AdLoader used to load ads.
    private AdLoader adLoader;

    // List of MenuItems and native ads that populate the RecyclerView.

    // List of native ads that have been successfully loaded.
    private ArrayList<UnifiedNativeAd> mNativeAds = new ArrayList<>();


    int subCatId;
    ArrayList<Object> saveALL = new ArrayList<>();
    String title;
    int page =0;
    boolean isLoading = false;
    private boolean isLastPage = false;

    ProductModel productModel;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    ShowProductsAdapter showProductsAdapter;
    final private String TAG = "ShowProducts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getExtras();
        title = bundle.getString("subCatTitle");
        subCatId = bundle.getInt("subCatId");
        initiate(title ,R.layout.activity_show_products);
        MobileAds.initialize(this,getString(R.string.app_id_admob_firebase));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        toolbar = getSupportActionBar();
        mContext = this;

//        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.subCategory_recyclerView);
        progressBar=findViewById(R.id.progress);
        Log.e(TAG,"onCreate(): Sub Category Id: "+ subCatId);
        loadFirstPage();



    }

    private void loadFirstPage(){
        Call<ProductModel> apiCall;
        ShowProductsInterface showProductsInterface = RetrofitClient.retrofitClient().create(ShowProductsInterface.class);
        apiCall = showProductsInterface.getProducts(subCatId,page);
        apiCall.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                if (response.isSuccessful()) {
                    productModel = response.body();

                    Log.e(TAG, "onResponse(): " + response.body().toString());
                    if (productModel.getCount() != 0) {
                        page_count = productModel.getCount()/10;
                        Log.e(TAG, "onResponse(): Test Product Model: " + productModel.getSingleProductModels().get(0).getImageUrls());
                        saveALL.addAll(productModel.getSingleProductModels());
                        Log.e(TAG, "ArrayList Size: " + saveALL.size());
                        showProductsAdapter = new ShowProductsAdapter(getApplicationContext(), saveALL, saveALL.size());
                        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(showProductsAdapter);
                        recyclerView.addOnScrollListener(new RecyclerViewPagination(layoutManager,mContext) {
                            @Override
                            protected void loadMoreItems() {
                                Log.e(TAG,"addOnScrollListener(): Load More items");
                                isLoading=true;
                                page+=1;
                                loadNextPage();

                            }
                            @Override
                            public boolean isLastPage(){
                                return isLastPage;

                            }

                            @Override
                            public int getTotalPageCount() {
                                return page_count;
                            }

                            @Override
                            public boolean isLoading() {
                                return isLoading;
                            }


                        });

                    }
                }
                else{
//                    ShowToastMessage.showToast(mContext,getString(R.string.check_your_connection));

                }
            }
            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {

            }
        });
    }
    private void loadNextPage(){
        Call<ProductModel> apiCall;
        ShowProductsInterface showProductsInterface = RetrofitClient.retrofitClient().create(ShowProductsInterface.class);
        apiCall = showProductsInterface.getProducts(subCatId,page);
        apiCall.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                if (response.isSuccessful()) {
                    ProductModel tempModel = new ProductModel();
                    tempModel = response.body();

                    Log.e(TAG, "onResponse(): " + response.body().toString());
                    if (tempModel.getCount() != 0) {
                        Log.e(TAG, "onResponse(): Test Product Model: " + productModel.getSingleProductModels().get(0).getImageUrls());
                        saveALL.addAll(tempModel.getSingleProductModels());
                        Log.e(TAG, "ArrayList Size: " + saveALL.size());
                        showProductsAdapter.notifyDataSetChanged();
                        isLoading=false;
                        progressBar.setVisibility(View.GONE);

                    }
                }
                else{
//                    ShowToastMessage.showToast(mContext,getString(R.string.check_your_connection));

                }
            }
            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {

            }
        });

        if (page != page_count ) {}  // 5
        else isLastPage = true;
    }

}
