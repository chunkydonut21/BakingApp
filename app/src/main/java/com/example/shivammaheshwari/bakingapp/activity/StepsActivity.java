package com.example.shivammaheshwari.bakingapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.shivammaheshwari.bakingapp.R;
import com.example.shivammaheshwari.bakingapp.fragments.StepFragment;

public class StepsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_activity);

        if (!MainActivity.isTablet) {
            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                StepFragment stepFragment = new StepFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.video_Layout, stepFragment)
                        .commit();
            }
        }
    }
}
