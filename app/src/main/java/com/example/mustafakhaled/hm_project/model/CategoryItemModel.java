package com.example.mustafakhaled.hm_project.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 9/29/2018.
 *
 */
        public class CategoryItemModel implements Parcelable {
        @SerializedName("id")
        int id;
        @SerializedName("modificationDate")
        String modificationDate;
        @SerializedName("name")
        String name;
        @SerializedName("subCategoryDTO")
        ArrayList<SubCategoryItem> subCategoryModels;

    protected CategoryItemModel(Parcel in) {
        id = in.readInt();
        modificationDate = in.readString();
        name = in.readString();
    }

    public static final Creator<CategoryItemModel> CREATOR = new Creator<CategoryItemModel>() {
        @Override
        public CategoryItemModel createFromParcel(Parcel in) {
            return new CategoryItemModel(in);
        }

        @Override
        public CategoryItemModel[] newArray(int size) {
            return new CategoryItemModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubCategoryItem> getSubCategoryModels() {
        return subCategoryModels;
    }

    public void setSubCategoryModels(ArrayList<SubCategoryItem> subCategoryModels) {
        this.subCategoryModels = subCategoryModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(modificationDate);
        parcel.writeString(name);
    }
}
