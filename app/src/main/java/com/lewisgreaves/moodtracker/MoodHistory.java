package com.lewisgreaves.moodtracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.LinkedList;

public class MoodHistory extends AppCompatActivity {
    private final LinkedList<String> mMoodList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMoodList.add("Yesterday");
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