package com.example.mustafakhaled.hm_project.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/*
 * Created by Mustafa Khaled on 9/29/2018.
 *
 */
    public class SubCategoryItem implements Parcelable{
        @SerializedName("id")
        int id;
        @SerializedName("modificationDate")
        String modificationDate;
        @SerializedName("name")
        String name;

    protected SubCategoryItem(Parcel in) {
        id = in.readInt();
        modificationDate = in.readString();
        name = in.readString();
        newItems = in.readInt();
    }

    public static final Creator<SubCategoryItem> CREATOR = new Creator<SubCategoryItem>() {
        @Override
        public SubCategoryItem createFromParcel(Parcel in) {
            return new SubCategoryItem(in);
        }

        @Override
        public SubCategoryItem[] newArray(int size) {
            return new SubCategoryItem[size];
        }
    };

    public int getNewItems() {
        return newItems;
    }

    public void setNewItems(int newItems) {
        this.newItems = newItems;
    }

    @SerializedName("newItems")
        int newItems;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(modificationDate);
        parcel.writeString(name);
        parcel.writeInt(newItems);
    }
}
