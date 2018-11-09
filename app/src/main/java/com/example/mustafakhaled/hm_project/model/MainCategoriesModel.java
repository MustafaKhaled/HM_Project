package com.example.mustafakhaled.hm_project.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 9/29/2018.
 *
 */ public class MainCategoriesModel implements Parcelable{
    @SerializedName("count")
    int count;
    @SerializedName("result")
    ArrayList<CategoryItemModel> categoryItemModels;

    protected MainCategoriesModel(Parcel in) {
        count = in.readInt();
    }

    public static final Creator<MainCategoriesModel> CREATOR = new Creator<MainCategoriesModel>() {
        @Override
        public MainCategoriesModel createFromParcel(Parcel in) {
            return new MainCategoriesModel(in);
        }

        @Override
        public MainCategoriesModel[] newArray(int size) {
            return new MainCategoriesModel[size];
        }
    };

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<CategoryItemModel> getCategoryItemModels() {
        return categoryItemModels;
    }

    public void setCategoryItemModels(ArrayList<CategoryItemModel> categoryItemModels) {
        this.categoryItemModels = categoryItemModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(count);
    }
}
