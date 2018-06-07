package com.imaginationunlimited.sniper.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jiaxu on 2018-06-07
 */

public class HomeSlidingItemImagesEntity implements Parcelable {
    private String id;
    private String pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.pic);
    }

    public HomeSlidingItemImagesEntity() {
    }

    protected HomeSlidingItemImagesEntity(Parcel in) {
        this.id = in.readString();
        this.pic = in.readString();
    }

    public static final Parcelable.Creator<HomeSlidingItemImagesEntity> CREATOR = new Parcelable.Creator<HomeSlidingItemImagesEntity>() {
        @Override
        public HomeSlidingItemImagesEntity createFromParcel(Parcel source) {
            return new HomeSlidingItemImagesEntity(source);
        }

        @Override
        public HomeSlidingItemImagesEntity[] newArray(int size) {
            return new HomeSlidingItemImagesEntity[size];
        }
    };
}
