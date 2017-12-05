package com.example.shivammaheshwari.bakingapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shivammaheshwari.bakingapp.R;
import com.example.shivammaheshwari.bakingapp.activity.DetailActivity;
import com.example.shivammaheshwari.bakingapp.model.BakingRecipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.BakingViewHolder> {

    private List<BakingRecipe> mRecipes;
    private Context mContext;


    public RecipeAdapter(Context context, List<BakingRecipe> bakingRecipes) {
        mRecipes = bakingRecipes;
        mContext = context;
    }


    @Override
    public BakingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.display_name;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        final BakingViewHolder viewHolder = new BakingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BakingViewHolder holder, final int position) {

        final BakingRecipe bakingRecipe = mRecipes.get(position);

        holder.nameTextView.setText(bakingRecipe.getRecipeName());

        String imagePath = bakingRecipe.getRecipeSteps();

        if (imagePath.isEmpty()) {
            holder.mImageView.setImageResource(R.drawable.recipe);
        } else {
            Picasso.with(mContext).load(imagePath).
                    placeholder(R.drawable.recipe).
                    into(holder.mImageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("ParcelRecipe", bakingRecipe);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class BakingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recipeName)
        TextView nameTextView;
        @BindView(R.id.recipeImage)
        ImageView mImageView;

        public BakingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }

    }

    public void setRecipeData(List<BakingRecipe> bakingRecipe) {
        mRecipes = bakingRecipe;
        notifyDataSetChanged();
    }

}

