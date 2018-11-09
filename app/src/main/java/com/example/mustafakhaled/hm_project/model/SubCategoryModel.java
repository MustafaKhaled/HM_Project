package com.example.mustafakhaled.hm_project.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 9/29/2018.
 *
 */ public class SubCategoryModel implements Parcelable {

     @SerializedName("subCategoryDTO")
     ArrayList<SubCategoryItem> subCategoryItemArrayList;

    protected SubCategoryModel(Parcel in) {
    }

    public static final Creator<SubCategoryModel> CREATOR = new Creator<SubCategoryModel>() {
        @Override
        public SubCategoryModel createFromParcel(Parcel in) {
            return new SubCategoryModel(in);
        }

        @Override
        public SubCategoryModel[] newArray(int size) {
            return new SubCategoryModel[size];
        }
    };

    public ArrayList<SubCategoryItem> getSubCategoryItemArrayList() {
        return subCategoryItemArrayList;
    }

    public void setSubCategoryItemArrayList(ArrayList<SubCategoryItem> subCategoryItemArrayList) {
        this.subCategoryItemArrayList = subCategoryItemArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
