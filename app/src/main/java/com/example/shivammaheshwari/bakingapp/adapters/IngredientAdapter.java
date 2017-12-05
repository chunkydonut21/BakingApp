package com.example.shivammaheshwari.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shivammaheshwari.bakingapp.R;
import com.example.shivammaheshwari.bakingapp.model.BakingIngredients;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder> {

    private List<BakingIngredients> mIngredients;
    private Context mContext;

    public IngredientAdapter(Context context, List<BakingIngredients> bakingIngredients) {
        mContext = context;
        mIngredients = bakingIngredients;
    }

    @Override
    public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.ingredient;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        final IngredientHolder viewHolder = new IngredientHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientHolder holder, int position) {

        BakingIngredients bakingIngredients = mIngredients.get(position);

        holder.ingredientsTextVIew.setText(bakingIngredients.getIngredients());
        holder.quantityTextView.setText(bakingIngredients.getQuantity());
        holder.measureTextView.setText(bakingIngredients.getMeasure());

    }

    @Override
    public int getItemCount() {
        if (mIngredients != null) {
            return mIngredients.size();
        } else {
            return 0;
        }
    }

    public class IngredientHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.ingredients)
        TextView ingredientsTextVIew;
        @BindView(R.id.quantity_ingredients)
        TextView quantityTextView;
        @BindView(R.id.measure_ingredients)
        TextView measureTextView;

        public IngredientHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}