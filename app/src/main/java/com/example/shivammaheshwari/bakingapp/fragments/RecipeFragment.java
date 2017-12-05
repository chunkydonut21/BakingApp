package com.example.shivammaheshwari.bakingapp.fragments;


import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shivammaheshwari.bakingapp.R;
import com.example.shivammaheshwari.bakingapp.RecipeLoader;
import com.example.shivammaheshwari.bakingapp.adapters.RecipeAdapter;
import com.example.shivammaheshwari.bakingapp.model.BakingRecipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<BakingRecipe>> {

    private static final String TAG = "RecipeFragment";
    private RecipeAdapter recipeAdapter;
    public String reviewUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.loading_indicator)
    View loadingIndicator;

    public RecipeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            rv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }

        rv.setHasFixedSize(true);
        recipeAdapter = new RecipeAdapter(getContext(), new ArrayList<BakingRecipe>());
        rv.setAdapter(recipeAdapter);

        rv.setVisibility(View.INVISIBLE);
        emptyView.setVisibility(View.VISIBLE);


        //check for connection
        ConnectivityManager connMgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(1, null, this);
        } else {
            loadingIndicator.setVisibility(View.GONE);

            emptyView.setText("No connection");

        }

    }

    @Override
    public android.support.v4.content.Loader<List<BakingRecipe>> onCreateLoader(int id, Bundle args) {
        String finalUri = reviewUrl;
        Uri baseUri = Uri.parse(finalUri);
        Uri.Builder ub = baseUri.buildUpon();

        return new RecipeLoader(getContext(), ub.toString());
    }


    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<BakingRecipe>> loader, List<BakingRecipe> data) {

        loadingIndicator.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {

            recipeAdapter.setRecipeData(data);

            rv.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.INVISIBLE);
        } else {

            emptyView.setText(R.string.nothing_found);
            emptyView.setVisibility(View.VISIBLE);

            rv.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<BakingRecipe>> loader) {
        recipeAdapter.setRecipeData(null);
    }
}
