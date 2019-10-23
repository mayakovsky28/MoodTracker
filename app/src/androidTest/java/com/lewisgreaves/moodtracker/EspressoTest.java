package com.lewisgreaves.moodtracker;

/*
 * Created by @Mayakovsky28 on 23 10 2019.
 */

import android.view.View;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.lewisgreaves.moodtracker.EspressoTestsMatchers.withDrawable;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void swipeChangesMood() {
        onView(withId(R.id.homepage)).perform(swipeDown());
        onView(withId(R.id.homepage)).check(matches(withDrawable(R.drawable.smiley_sad)));
    }
}
