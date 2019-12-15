package com.example.newsfeed;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerviewTest {

    /** Launches {@link NewsFeedActivity} for every test */
    @Rule
    public ActivityTestRule<NewsFeedActivity> activityRule =
            new ActivityTestRule<>(NewsFeedActivity.class);

    /**
     * Test a heading of the recycler view is clickable.
     */
    @Test
    public void testIsClickable() {
        onView(withId(R.id.framelayout_container)).check(matches(isClickable()));
    }

    @Test
    public void clickItemOnSecondPos() {
        onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }

    @Test
    public void performClickOnFirstItem() {
        onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void performClickOnBasedOnText() {
        onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("")), click()));
    }

    @Test
    public void checkTheItemPositionContent() {
        onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(2, click()));
    }

}
