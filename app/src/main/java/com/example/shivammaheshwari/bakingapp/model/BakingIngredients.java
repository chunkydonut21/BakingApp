package com.example.shivammaheshwari.bakingapp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class BakingIngredients implements Parcelable {

    private String mQuantity;
    private String mMeasure;
    private String mIngredients;

    public BakingIngredients(String quantiy, String measure, String dishIngredients) {
        mQuantity = quantiy;
        mMeasure = measure;
        mIngredients = dishIngredients;

    }

    protected BakingIngredients(Parcel in) {
        mQuantity = in.readString();
        mMeasure = in.readString();
        mIngredients = in.readString();
    }

    public static final Creator<BakingIngredients> CREATOR = new Creator<BakingIngredients>() {
        @Override
        public BakingIngredients createFromParcel(Parcel in) {
            return new BakingIngredients(in);
        }

        @Override
        public BakingIngredients[] newArray(int size) {
            return new BakingIngredients[size];
        }
    };

    public String getQuantity() {
        return mQuantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public String getIngredients() {
        return mIngredients;
    }

    public void setQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public void setMeasure(String mMeasure) {
        this.mMeasure = mMeasure;
    }

    public void setIngredients(String mIngredients) {
        this.mIngredients = mIngredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuantity);
        dest.writeString(mMeasure);
        dest.writeString(mIngredients);
    }
}
