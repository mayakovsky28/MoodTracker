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

    public static final String KEY_MOOD_ZERO = "PREFERENCE_SELECTED_MOOD";
    public static final String KEY_MOOD_ONE = "KEY_MOOD_ONE";
    public static final String KEY_MOOD_TWO = "KEY_MOOD_TWO";
    public static final String KEY_MOOD_THREE = "KEY_MOOD_THREE";
    public static final String KEY_MOOD_FOUR = "KEY_MOOD_FOUR";
    public static final String KEY_MOOD_FIVE = "KEY_MOOD_FIVE";
    public static final String KEY_MOOD_SIX = "KEY_MOOD_SIX";

    @Override
    public void onReceive(Context context, Intent intent) {
        //code to run to save mood at end of day
        SharedPreferences preferences = context.getSharedPreferences("mySavedMoods", 0);
        preferences.edit().putInt(KEY_MOOD_SIX, preferences.getInt(KEY_MOOD_FIVE, 0)).apply();
        preferences.edit().putInt(KEY_MOOD_FIVE, preferences.getInt(KEY_MOOD_FOUR, 0)).apply();
        preferences.edit().putInt(KEY_MOOD_FOUR, preferences.getInt(KEY_MOOD_THREE, 0)).apply();
        preferences.edit().putInt(KEY_MOOD_THREE, preferences.getInt(KEY_MOOD_TWO, 0)).apply();
        preferences.edit().putInt(KEY_MOOD_TWO, preferences.getInt(KEY_MOOD_ONE, 0)).apply();
        preferences.edit().putInt(KEY_MOOD_ONE, preferences.getInt(KEY_MOOD_ZERO, 0)).apply();
        preferences.edit().putInt(KEY_MOOD_ZERO, preferences.getInt(KEY_MOOD_ZERO, 0)).apply();
        Log.d("alarm", "The alarm was triggered.");
    }
}
