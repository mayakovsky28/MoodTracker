package com.lewisgreaves.moodtracker;

import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.LinkedList;

import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_FIVE;
import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_FOUR;
import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_ONE;
import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_SIX;
import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_THREE;
import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_TWO;
import static com.lewisgreaves.moodtracker.MainActivity.KEY_MOOD_ZERO;

public class MoodHistory extends AppCompatActivity {
    //    create list for Mood objects
    private final LinkedList<Mood> mMoodList = new LinkedList<>();
    private SharedPreferences preferences;

    @Override
    protected void onStart() {
        super.onStart();

//      access Mood objects from shared preferences and add them to the list
        preferences = getSharedPreferences("mySavedMoods", 0);

        Mood moodZero = getMood(KEY_MOOD_ZERO);
        Mood mood = getMood(KEY_MOOD_ONE);
        Mood moodTwo = getMood(KEY_MOOD_TWO);
        Mood moodThree = getMood(KEY_MOOD_THREE);
        Mood moodFour = getMood(KEY_MOOD_FOUR);
        Mood moodFive = getMood(KEY_MOOD_FIVE);
        Mood moodSix = getMood(KEY_MOOD_SIX);

        mMoodList.add(moodZero);
        mMoodList.add(mood);
        mMoodList.add(moodTwo);
        mMoodList.add(moodThree);
        mMoodList.add(moodFour);
        mMoodList.add(moodFive);
        mMoodList.add(moodSix);

        setContentView(R.layout.activity_mood_history);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        RecyclerView recyclerView = findViewById(R.id.mood_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MoodAdapter adapter = new MoodAdapter(this, mMoodList, screenWidth);
        recyclerView.setAdapter(adapter);
    }

    private Mood getMood(String key) {
//      function to get Mood object from shared preferences where it is stored as a json string
        String moodOne = preferences.getString(key, "");
        Gson gson = new Gson();
        if (moodOne.isEmpty()) {
            return new Mood(3, "");
        }
        return gson.fromJson(moodOne, Mood.class);
    }
}