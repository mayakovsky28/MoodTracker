package com.lewisgreaves.moodtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

/*
 * Created by @Mayakovsky28 on 9/30/19.
 */

public class AtMidnight extends BroadcastReceiver {

    public static final String KEY_MOOD_ZERO = "KEY_MOOD_ZERO";
    public static final String KEY_MOOD_ONE = "KEY_MOOD_ONE";
    public static final String KEY_MOOD_TWO = "KEY_MOOD_TWO";
    public static final String KEY_MOOD_THREE = "KEY_MOOD_THREE";
    public static final String KEY_MOOD_FOUR = "KEY_MOOD_FOUR";
    public static final String KEY_MOOD_FIVE = "KEY_MOOD_FIVE";
    public static final String KEY_MOOD_SIX = "KEY_MOOD_SIX";

    @Override
    public void onReceive(Context context, Intent intent) {
        // put mood in as json string instead of int
        SharedPreferences preferences = context.getSharedPreferences("mySavedMoods", 0);

        int selectedMood = preferences.getInt(MainActivity.PREFERENCE_SELECTED_MOOD, 0);
        String todayNote = preferences.getString(MainActivity.PREFERENCE_TODAY_NOTE, "");
        Mood mood = new Mood(selectedMood, todayNote);
        String json = mood.toJson();

        //code to run to save mood at end of day
        preferences.edit().putString(KEY_MOOD_SIX, preferences.getString(KEY_MOOD_FIVE, "")).apply();
        preferences.edit().putString(KEY_MOOD_FIVE, preferences.getString(KEY_MOOD_FOUR, "")).apply();
        preferences.edit().putString(KEY_MOOD_FOUR, preferences.getString(KEY_MOOD_THREE, "")).apply();
        preferences.edit().putString(KEY_MOOD_THREE, preferences.getString(KEY_MOOD_TWO, "")).apply();
        preferences.edit().putString(KEY_MOOD_TWO, preferences.getString(KEY_MOOD_ONE, "")).apply();
        preferences.edit().putString(KEY_MOOD_ONE, preferences.getString(KEY_MOOD_ZERO, "")).apply();
        preferences.edit().putString(KEY_MOOD_ZERO, json).apply();
        Log.d("alarm", "The alarm was triggered.");
    }
}
