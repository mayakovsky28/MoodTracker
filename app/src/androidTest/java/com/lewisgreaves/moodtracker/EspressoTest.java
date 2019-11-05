package com.lewisgreaves.moodtracker;

/*
 * Created by @Mayakovsky28 on 23 10 2019.
 */

import android.view.View;

import androidx.test.espresso.action.TypeTextAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.text.IsEmptyString;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.lewisgreaves.moodtracker.EspressoTestsMatchers.withDrawable;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void swipeChangesMood() {
//        check that it might be swiping the other way
        onView(withId(R.id.homepage)).perform(swipeDown());
//        onView(withId(R.id.homepage)).check(matches(withDrawable(R.drawable.smiley_sad)));
//        onView(withId(R.id.homepage
    }

    @Test
    public void tappingAddNoteBringsUpTextInput() {
        onView(withId(R.id.add_note)).perform(click());
        onView(ViewMatchers.withText("Cancel")).perform(click());
        onView(withId(R.id.add_note)).perform(click());
//        TODO: onView(withId(R.id.noteEditText)).... how to get the text...https://www.google.com/url?q=https://stackoverflow.com/a/45601564/5066615&sa=D&source=hangouts&ust=1573077736502000&usg=AFQjCNHV_b_OljSD6wzL5DUgev6iNPU_Ug
        onView(ViewMatchers.withText("Describe")).check(matches(isDisplayed())).perform(typeText("Just got a date!"));
        onView(ViewMatchers.withText("OK")).perform(click());
        onView(withId(R.id.homepage)).check(matches(ViewMatchers.withText("")));
        onView(withId(R.id.add_note)).perform(click());
        onView(ViewMatchers.withText("Describe")).check(matches(ViewMatchers.withText("Just got a date!")));
    }

    @Test
    public void tappingViewHistoryChangesActivity() {
        onView(withId(R.id.view_history)).perform(click());
        onView(withId(R.id.mood_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void firstItemStaticTextIsYesterday() {}

    @Test
    public void onCreateThreeImagesVisible() {}

//    TODO: think of some more tests
}
