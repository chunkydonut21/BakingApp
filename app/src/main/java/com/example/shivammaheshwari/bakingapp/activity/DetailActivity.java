package com.example.shivammaheshwari.bakingapp.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.shivammaheshwari.bakingapp.R;
import com.example.shivammaheshwari.bakingapp.fragments.DetailFragment;
import com.example.shivammaheshwari.bakingapp.model.BakingRecipe;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        BakingRecipe bakingRecipe = getIntent().getParcelableExtra("ParcelRecipe");

        if (savedInstanceState == null) {
            if (MainActivity.isTablet) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                DetailFragment detailFragment = new DetailFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable("BundleRecipe", bakingRecipe);
                detailFragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .add(R.id.recipeLayout, detailFragment)
                        .commit();


            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DetailFragment detailFragment = new DetailFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable("BundleRecipe", getIntent().getParcelableExtra("ParcelRecipe"));
                detailFragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .add(R.id.recipeLayout, detailFragment)
                        .commit();
            }
        }


    }
}
