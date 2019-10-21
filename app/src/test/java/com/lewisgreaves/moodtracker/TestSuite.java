package com.lewisgreaves.moodtracker;

/*
 * Created by @Mayakovsky28 on 20 10 2019.
 */

import android.content.SharedPreferences;

import org.junit.Test;

import static com.lewisgreaves.moodtracker.MainActivity.PREFERENCE_SELECTED_MOOD;


public class TestSuite {

    public SharedPreferences preferences;

    @Test
    public void currentlySelectedMoodIsStoredInSharedPreferences() {
        preferences.contains(PREFERENCE_SELECTED_MOOD);
    }

    @Test
    public void currentlySelectedMoodIsThree() {
        MainActivity mainActivity = new MainActivity();
    }
}
