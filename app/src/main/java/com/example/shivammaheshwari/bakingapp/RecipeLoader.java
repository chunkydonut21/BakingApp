package com.example.shivammaheshwari.bakingapp;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.example.shivammaheshwari.bakingapp.model.BakingRecipe;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class RecipeLoader extends AsyncTaskLoader<List<BakingRecipe>> {
    private final String mUrl;

    public RecipeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<BakingRecipe> loadInBackground() {
        List<BakingRecipe> bakingRecipes = null;
        if (mUrl == null) {
            return null;
        }

        try {
            bakingRecipes = Utils.fetchData(mUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bakingRecipes;
    }
}