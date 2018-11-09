package com.example.mustafakhaled.hm_project.model;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Created by Mustafa Khaled on 9/8/2018.
 *
 */
public class NavigationDrawerModel implements Parcelable{
    public NavigationDrawerModel() {
    }

    public NavigationDrawerModel(Parcel in) {
        item_List = in.readString();
        item_desc = in.readString();
        icon = in.readInt();
    }

    public static final Creator<NavigationDrawerModel> CREATOR = new Creator<NavigationDrawerModel>() {
        @Override
        public NavigationDrawerModel createFromParcel(Parcel in) {
            return new NavigationDrawerModel(in);
        }

        @Override
        public NavigationDrawerModel[] newArray(int size) {
            return new NavigationDrawerModel[size];
        }
    };

    public NavigationDrawerModel(String item_List, String item_desc, int icon) {
        this.item_List = item_List;
        this.item_desc = item_desc;
        this.icon = icon;
    }

    public String getItem_List() {
        return item_List;
    }

    public void setItem_List(String item_List) {
        this.item_List = item_List;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    String item_List;
    String item_desc;
    int icon;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(item_List);
        parcel.writeString(item_desc);
        parcel.writeInt(icon);
    }
}
