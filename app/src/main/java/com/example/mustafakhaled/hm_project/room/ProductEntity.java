package com.example.mustafakhaled.hm_project.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/*
 * Created by Mustafa Khaled on 10/25/2018.
 *
 */
    @Entity(tableName = "save_product")
    public class ProductEntity {

      @PrimaryKey(autoGenerate = false)
      private int id;
      @ColumnInfo(name = "url")
      private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
