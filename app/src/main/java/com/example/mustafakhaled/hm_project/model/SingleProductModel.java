package com.example.mustafakhaled.hm_project.model;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Created by Mustafa Khaled on 10/6/2018.
 *
 */
    public class SingleProductModel implements Parcelable{
        String desc;
        int id;
        String imageUrls;
        long modificationDate;
        String name;
        boolean isLoved;

    protected SingleProductModel(Parcel in) {
        desc = in.readString();
        id = in.readInt();
        imageUrls = in.readString();
        modificationDate = in.readLong();
        name = in.readString();
        isLoved = in.readByte() != 0;
    }

    public static final Creator<SingleProductModel> CREATOR = new Creator<SingleProductModel>() {
        @Override
        public SingleProductModel createFromParcel(Parcel in) {
            return new SingleProductModel(in);
        }

        @Override
        public SingleProductModel[] newArray(int size) {
            return new SingleProductModel[size];
        }
    };

    public boolean isLoved() {
        return isLoved;
    }

    public void setLoved(boolean loved) {
        isLoved = loved;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public long getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(long modificationDate) {
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
        parcel.writeString(desc);
        parcel.writeInt(id);
        parcel.writeString(imageUrls);
        parcel.writeLong(modificationDate);
        parcel.writeString(name);
        parcel.writeByte((byte) (isLoved ? 1 : 0));
    }
}
