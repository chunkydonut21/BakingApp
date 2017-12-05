package com.example.shivammaheshwari.bakingapp.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shivammaheshwari.bakingapp.R;
import com.example.shivammaheshwari.bakingapp.model.BakingSteps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.RecipeHolder> {

    final private ListItemClickListener mOnClickListener;
    private Context mContext;

    private List<BakingSteps> mSteps;

    public StepsAdapter(Context context, List<BakingSteps> bakingSteps, ListItemClickListener listItemClickListener) {
        mContext = context;
        mSteps = bakingSteps;
        mOnClickListener = listItemClickListener;

    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.steps;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        final RecipeHolder viewHolder = new RecipeHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {

        final BakingSteps bakingSteps = mSteps.get(position);

        holder.descriptionTextView.setText(bakingSteps.getShortDescription());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!MainActivity.isTablet) {
//                    Intent intent = new Intent(v.getContext(), StepsActivity.class);
//                    intent.putExtra("Video", bakingSteps);
//                    v.getContext().startActivity(intent);
//                }
//            }
//        });

    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public int getItemCount() {
        if (mSteps != null) {
            return mSteps.size();
        } else {
            return 0;
        }
    }

    public class RecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.short_description)
        TextView descriptionTextView;

        public RecipeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

}