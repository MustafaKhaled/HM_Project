package com.example.mustafakhaled.hm_project.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 10/6/2018.
 *
 */ public class ProductModel implements Parcelable {
    public ProductModel() {
    }

    @SerializedName("count")
     int count;
     @SerializedName("result")
     ArrayList<SingleProductModel> singleProductModels;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<SingleProductModel> getSingleProductModels() {
        return singleProductModels;
    }

    public void setSingleProductModels(ArrayList<SingleProductModel> singleProductModels) {
        this.singleProductModels = singleProductModels;
    }

    public static Creator<ProductModel> getCREATOR() {
        return CREATOR;
    }

    protected ProductModel(Parcel in) {
        count = in.readInt();
        singleProductModels = in.createTypedArrayList(SingleProductModel.CREATOR);
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(count);
        parcel.writeTypedList(singleProductModels);
    }
}
