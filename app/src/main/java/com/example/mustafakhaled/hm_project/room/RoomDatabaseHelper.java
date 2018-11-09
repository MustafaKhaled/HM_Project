package com.example.mustafakhaled.hm_project.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mustafakhaled.hm_project.init.NavActivity;


/*
 * Created by Mustafa Khaled on 10/25/2018.
 *
 *
 */
    @Database(entities = {ProductEntity.class},version = 1)
    public abstract class RoomDatabaseHelper extends  RoomDatabase {
        private static RoomDatabaseHelper INSTANCE;
        public abstract ProductDao pe();

        public static RoomDatabaseHelper getAppDatabase(Context context){
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), RoomDatabaseHelper.class, "save_product")
                                // allow queries on the main thread.
                                // Don't do this on a real app! See PersistenceBasicSample for an example.
                                .allowMainThreadQueries()
                                .build();
            }
            return INSTANCE;

        }
        public static void destroyInstance() {
            INSTANCE = null;
    }
}
