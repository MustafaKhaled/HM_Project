package com.example.mustafakhaled.hm_project.Constants;

import retrofit2.http.GET;

/*
 * Created by Mustafa Khaled on 9/14/2018.
 *
 */ public class ServerConfig {


     public static final String BASE_URL = "http://31.220.56.98:8080/rest/";
     public static final String PRODUCT_BASE_URL = "http://31.220.56.98:8080/rest/prod/products/image/";
     public static final String MAIN_CATEGORIES="main/categories";
     public static final String SHOW_PRODUCTS = "prod/products/{subCatId}/{page}";
     public static final int BACK_TIME_INTERVAL = 2000;

}
