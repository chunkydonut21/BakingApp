package com.example.shivammaheshwari.bakingapp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class BakingSteps implements Parcelable {
    private String mRecipeId;
    private String mDescription;
    private String mShortDescription;
    private String mVideoUrl;
    private String mThumbUrl;


    public BakingSteps(String recipeId, String shortDescription, String description, String videoUrl, String thumbUrl) {
        mRecipeId = recipeId;
        mShortDescription = shortDescription;
        mDescription = description;
        mVideoUrl = videoUrl;
        mThumbUrl = thumbUrl;

    }


    protected BakingSteps(Parcel in) {
        mRecipeId = in.readString();
        mDescription = in.readString();
        mShortDescription = in.readString();
        mVideoUrl = in.readString();
        mThumbUrl = in.readString();
    }

    public static final Creator<BakingSteps> CREATOR = new Creator<BakingSteps>() {
        @Override
        public BakingSteps createFromParcel(Parcel in) {
            return new BakingSteps(in);
        }


        @Override
        public BakingSteps[] newArray(int size) {
            return new BakingSteps[size];
        }
    };

    public String getRecipeId() {
        return mRecipeId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public String getThumbUrl() {
        return mThumbUrl;
    }

    public void setRecipeId(String mRecipeId) {
        this.mRecipeId = mRecipeId;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public void setVideoUrl(String mVideoUrl) {
        this.mVideoUrl = mVideoUrl;
    }

    public void setThumbUrl(String mThumbUrl) {
        this.mThumbUrl = mThumbUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mRecipeId);
        dest.writeString(mDescription);
        dest.writeString(mShortDescription);
        dest.writeString(mVideoUrl);
        dest.writeString(mThumbUrl);
    }
}
