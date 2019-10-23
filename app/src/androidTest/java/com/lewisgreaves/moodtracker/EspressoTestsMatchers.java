package com.lewisgreaves.moodtracker;

import android.view.View;

import org.hamcrest.Matcher;

/*
 * Created by @Mayakovsky28 on 23 10 2019.
 */
public class EspressoTestsMatchers {

        public static Matcher<View> withDrawable(final int resourceId) {
            return new DrawableMatcher(resourceId);
        }
}
