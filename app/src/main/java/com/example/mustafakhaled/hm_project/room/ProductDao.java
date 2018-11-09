package com.example.mustafakhaled.hm_project.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.mustafakhaled.hm_project.model.FavoriteModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/*
 * Created by Mustafa Khaled on 10/25/2018.
 *
 */
     @Dao
     public interface ProductDao {

     @Query("SELECT * FROM save_product")
     Flowable<List<ProductEntity>> getAll();

     @Query("SELECT id FROM save_product")
     Single<List<Integer>> getAllIds();

     @Query("SELECT url,id FROM save_product")
     Single<List<FavoriteModel>> getAllUrl();

     @Insert( onConflict = OnConflictStrategy.REPLACE)
     void insert(ProductEntity pe);

     @Query("DELETE  FROM  save_product WHERE id = :id")
     void delete(int id);
}
