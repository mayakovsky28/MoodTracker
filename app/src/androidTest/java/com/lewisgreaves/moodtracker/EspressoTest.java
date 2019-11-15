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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.lewisgreaves.moodtracker.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    Matcher<View> textViewHasValue() {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("The TextView/EditText has value");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView) && !(view instanceof EditText)) {
                    return false;
                }
                if (view != null) {
                    String text;
                    if (view instanceof TextView) {
                        text = ((TextView) view).getText().toString();
                    } else {
                        text = ((EditText) view).getText().toString();
                    }

                    return (!TextUtils.isEmpty(text));
                }
                return false;
            }
        };
    }

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void swipeChangesMood() {
        onView(withId(R.id.homepage)).perform(swipeDown());
        onView(withId(R.id.homepage)).check(matches(withDrawable(R.drawable.smiley_sad)));
    }

    @Test
    public void tappingAddNoteBringsUpTextInput() {
        onView(withId(R.id.add_note)).perform(click());
        onView(ViewMatchers.withText("Cancel")).perform(click());
        onView(withId(R.id.add_note)).perform(click());
        onView(withId(R.id.noteEditText)).check(matches(textViewHasValue()));
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
    public void tappingNoteIconInRecyclerViewShowsNoteToast(){
        onView(withId(R.id.view_history)).perform(click());
        onView(allOf(withId(R.id.noteImageView))).perform(click());
        onView(withId(R.id.mood_recycler_view)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void firstItemStaticTextIsYesterday() {
        onView(withId(R.id.view_history)).perform(click());
        onView(withId(R.id.mood_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.mood_recycler_view)).check(matches(ViewMatchers.withText("Yesterday")));
    }

    @Test
    public void onCreateThreeImagesVisible() {
        onView(withId(R.id.homepage)).check(matches(withChild(withId(R.id.add_note))));
        onView(withId(R.id.homepage)).check(matches(withChild(withId(R.id.view_history))));
        onView(withId(R.id.homepage)).check(matches(withChild(withDrawable(R.drawable.smiley_happy))));
    }
}
