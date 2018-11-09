package com.example.mustafakhaled.hm_project.room;

import android.content.Context;

/*
 * Created by Mustafa Khaled on 10/25/2018.
 *
 */ public class RoomDatabaseOperations {

    private static ProductEntity addUser(final RoomDatabaseHelper db, ProductEntity  user) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(user.getId());
        productEntity.setUrl(user.getUrl());
        db.pe().insert(productEntity);
        return user;
    }
}
