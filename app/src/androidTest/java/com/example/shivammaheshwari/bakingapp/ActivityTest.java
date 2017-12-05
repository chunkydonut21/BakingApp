package com.example.shivammaheshwari.bakingapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.shivammaheshwari.bakingapp.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTest = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void clickTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView = onView(withId(R.id.rv));
        recyclerView.perform(click());


    }

    @Test
    public void scrollingTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView = onView(withId(R.id.rv));
        recyclerView.perform(RecyclerViewActions.scrollToPosition(2))
                .perform(click());


    }
    @Test
    public void checkRecyclerView(){

        onView(withId(R.id.rv)).check(matches(hasDescendant(withText("Nutella Pie"))));
    }

}
