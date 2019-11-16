package com.lewisgreaves.moodtracker;

/*
 * Created by @Mayakovsky28 on 23 10 2019.
 */

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.lewisgreaves.moodtracker.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void swipeChangesMood() {
//        passes
        onView(withId(R.id.homepage)).perform(swipeDown());
        onView(withId(R.id.homepage)).check(matches(withDrawable(R.drawable.smiley_sad)));
    }

    @Test
//    passes
    public void tappingAddNoteBringsUpTextInput() {
        onView(withId(R.id.add_note)).perform(click());
        onView(withResourceName("alertTitle")).check(matches(isDisplayed()));
        onView(ViewMatchers.withText("Cancel")).perform(click());
    }

    @Test
//    passes
    public void tappingViewHistoryChangesActivity() {
        onView(withId(R.id.view_history)).perform(click());
        onView(withId(R.id.mood_recycler_view)).check(matches(isDisplayed()));
    }

//    @Test
//    public void tappingNoteIconInRecyclerViewShowsNoteToast() {
//        onView(withId(R.id.view_history)).perform(click());
//        onView(withId(R.id.noteImageView)).perform(click());
//        onView(withId(R.id.mood_recycler_view)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
//    }

    @Test
//    passes
    public void onCreateTwoButtonsVisible() {
        onView(withId(R.id.view_history)).check(matches(withDrawable(R.drawable.ic_history_black)));
        onView(withId(R.id.add_note)).check(matches(withDrawable(R.drawable.ic_note_add_black)));
    }


    @Test
//    passes
    public void recyclerViewHasSevenViews() {
        onView(withId(R.id.view_history)).perform(click());
        onView(withId(R.id.mood_recycler_view)).check(matches(hasChildCount(7)));
    }
}
