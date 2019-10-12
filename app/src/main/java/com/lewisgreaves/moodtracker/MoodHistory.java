package com.lewisgreaves.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.LinkedList;

import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_ONE;
import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_THREE;
import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_TWO;
import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_ZERO;

public class MoodHistory extends AppCompatActivity {
    private final LinkedList<String> mMoodList = new LinkedList<>();
    private SharedPreferences preferences;

    @Override
    protected void onStart() {
        super.onStart();
        preferences = getSharedPreferences("mySavedMoods", 0);
        Mood moodZero = getMood(KEY_MOOD_ZERO);
        Mood mood = getMood(KEY_MOOD_ONE);
        Mood moodTwo = getMood(KEY_MOOD_TWO);
        Mood moodThree = getMood(KEY_MOOD_THREE);
        //get background colour
        //get note
        mMoodList.add("" + moodZero.moodId);
        mMoodList.add(moodZero.moodNote);
        mMoodList.add("" + mood.moodId);
        mMoodList.add(mood.moodNote);
        mMoodList.add("" + moodTwo.moodId);
        mMoodList.add(moodTwo.moodNote);
        mMoodList.add("" + moodThree.moodId);
        mMoodList.add(moodThree.moodNote);

        mMoodList.add("Three days ago");
        mMoodList.add("Four days ago");
        mMoodList.add("Five days ago");
        mMoodList.add("Six days ago");

        setContentView(R.layout.activity_mood_history);
        RecyclerView recyclerView = findViewById(R.id.mood_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MoodAdapter adapter = new MoodAdapter(this, mMoodList);
        recyclerView.setAdapter(adapter);
    }

    private Mood getMood(String key) {
        String moodOne = preferences.getString(key, "");
        Gson gson  = new Gson();
        return gson.fromJson(moodOne, Mood.class);
    }
}