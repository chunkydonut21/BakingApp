package com.example.shivammaheshwari.bakingapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shivammaheshwari.bakingapp.BakingService;
import com.example.shivammaheshwari.bakingapp.R;
import com.example.shivammaheshwari.bakingapp.activity.MainActivity;
import com.example.shivammaheshwari.bakingapp.activity.StepsActivity;
import com.example.shivammaheshwari.bakingapp.adapters.IngredientAdapter;
import com.example.shivammaheshwari.bakingapp.adapters.StepsAdapter;
import com.example.shivammaheshwari.bakingapp.model.BakingIngredients;
import com.example.shivammaheshwari.bakingapp.model.BakingRecipe;
import com.example.shivammaheshwari.bakingapp.model.BakingSteps;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements StepsAdapter.ListItemClickListener {

    private static final String TAG = "DetailFragment";
    private ArrayList<BakingIngredients> mIngredientList;
    private ArrayList<BakingSteps> mStepsList;

    @BindView(R.id.ingredient_view)
    RecyclerView ingredientRecyclerView;
    @BindView(R.id.steps_view)
    RecyclerView stepsRecyclerView;

    public DetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BakingRecipe bakingRecipe = getArguments().getParcelable("BundleRecipe");
        assert bakingRecipe != null;
        mIngredientList = (ArrayList<BakingIngredients>) bakingRecipe.getIngredientList();
        mStepsList = (ArrayList<BakingSteps>) bakingRecipe.getRecipeList();

        //setting up ingredientRecyclerView
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientRecyclerView.setHasFixedSize(true);
        ingredientRecyclerView.setNestedScrollingEnabled(false);

        IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), mIngredientList);
        ingredientRecyclerView.setAdapter(ingredientAdapter);

        //setting up StepsRecyclerView
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stepsRecyclerView.setHasFixedSize(true);
        stepsRecyclerView.setNestedScrollingEnabled(false);

        StepsAdapter stepsAdapter = new StepsAdapter(getContext(), mStepsList, this);
        stepsRecyclerView.setAdapter(stepsAdapter);


        final ArrayList<String> recipeWidget = new ArrayList<>();

        for (int i = 0; i < mIngredientList.size(); i++) {

            String ingredientName = mIngredientList.get(i).getIngredients();
            String quantity = mIngredientList.get(i).getQuantity();
            String measure = mIngredientList.get(i).getMeasure();

            recipeWidget.add(ingredientName + "\n" + "Quantity: " + quantity + "\n" + "Measure: " + measure + "\n");
        }
//        Log.e("recipe", recipeWidget.toString());

        BakingService.startBakingService(getContext(), recipeWidget);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        if (MainActivity.isTablet) {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            StepFragment stepFragment = new StepFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("StepsDetailArrayList", mStepsList);
            bundle.putInt("index", clickedItemIndex);
            stepFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.video_Layouts, stepFragment)
                    .commit();

        } else {
            Intent intent = new Intent(getActivity(), StepsActivity.class);
            intent.putParcelableArrayListExtra("StepsDetailArrayList", mStepsList);
            intent.putExtra("index", clickedItemIndex);
            startActivity(intent);
        }

    }
}