package com.lewisgreaves.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.LinkedList;

import static com.lewisgreaves.moodtracker.AtMidnight.KEY_MOOD_ONE;

public class MoodHistory extends AppCompatActivity {
    private final LinkedList<String> mMoodList = new LinkedList<>();
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("mySavedMoods", 0);
        String moodOne = preferences.getString(KEY_MOOD_ONE, "");
        Gson gson  = new Gson();
        Mood mood = gson.fromJson(moodOne, Mood.class);
        //get background colour
        //get note
        mMoodList.add("Yesterday");
        mMoodList.add("" + mood.moodId);
        mMoodList.add(mood.moodNote);
        mMoodList.add("The day before yesterday");
        mMoodList.add("Two days ago");
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
}