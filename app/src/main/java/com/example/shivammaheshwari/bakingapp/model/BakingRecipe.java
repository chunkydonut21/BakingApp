package com.example.shivammaheshwari.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BakingRecipe implements Parcelable {

    public static final Creator<BakingRecipe> CREATOR = new Creator<BakingRecipe>() {
        @Override
        public BakingRecipe createFromParcel(Parcel in) {
            return new BakingRecipe(in);
        }

        @Override
        public BakingRecipe[] newArray(int size) {
            return new BakingRecipe[size];
        }
    };

    private String mRecipeName, mRecipeId, mRecipeServings, mRecipeImage;
    private List<BakingIngredients> mIngredientList;
    private List<BakingSteps> mRecipeList;

    public BakingRecipe(String recipeName, String recipeId, String recipeServings, String recipeImage,
                        List<BakingIngredients> ingredientList, List<BakingSteps> recipeList) {
        mRecipeName = recipeName;
        mRecipeId = recipeId;
        mRecipeServings = recipeServings;
        mRecipeImage = recipeImage;
        mIngredientList = ingredientList;
        mRecipeList = recipeList;


    }


    protected BakingRecipe(Parcel in) {
        mRecipeName = in.readString();
        mRecipeId = in.readString();
        mRecipeServings = in.readString();
        mRecipeImage = in.readString();
        if (in.readByte() == 0x01) {
            mIngredientList = new ArrayList<>();
            in.readList(mIngredientList, BakingIngredients.class.getClassLoader());
        } else {
            mIngredientList = null;
        }
        if (in.readByte() == 0x01) {
            mRecipeList = new ArrayList<>();
            in.readList(mRecipeList, BakingSteps.class.getClassLoader());

        } else {
            mRecipeList = null;
        }
    }


    public String getRecipeName() {
        return mRecipeName;
    }

    public String getRecipeId() {
        return mRecipeId;
    }


    public String getRecipeServings() {
        return mRecipeServings;
    }

    public String getRecipeSteps() {
        return mRecipeImage;
    }

    public List<BakingIngredients> getIngredientList() {
        return mIngredientList;
    }

    public List<BakingSteps> getRecipeList() {
        return mRecipeList;
    }


    public void setRecipeName(String mRecipeName) {
        this.mRecipeName = mRecipeName;
    }

    public void setRecipeId(String mRecipeId) {
        this.mRecipeId = mRecipeId;
    }

    public void setRecipeServings(String mRecipeServings) {
        this.mRecipeServings = mRecipeServings;
    }

    public void setRecipeSteps(String mRecipeSteps) {
        this.mRecipeImage = mRecipeSteps;
    }

    public void setIngredientList(List<BakingIngredients> mIngredientList) {
        this.mIngredientList = mIngredientList;
    }

    public void setRecipeList(List<BakingSteps> mRecipeList) {
        this.mRecipeList = mRecipeList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mRecipeName);
        dest.writeString(mRecipeId);
        dest.writeString(mRecipeServings);
        dest.writeString(mRecipeImage);
        if (mIngredientList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mIngredientList);
        }
        if (mRecipeList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mRecipeList);
        }
    }
}


