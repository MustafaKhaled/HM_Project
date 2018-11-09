package com.example.mustafakhaled.hm_project.Interfaces;

import com.example.mustafakhaled.hm_project.Constants.ServerConfig;
import com.example.mustafakhaled.hm_project.model.MainCategoriesModel;
import com.example.mustafakhaled.hm_project.model.ProductModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/*
 * Created by Mustafa Khaled on 10/6/2018.
 *
 */ public interface ShowProductsInterface {

    @Headers({"lang: ar"})
    @GET(ServerConfig.SHOW_PRODUCTS)
    Call<ProductModel> getProducts(@Path("subCatId") int subCategory , @Path("page") int page);

}
