package com.lewisgreaves.moodtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MoodHistory extends AppCompatActivity {
    private final LinkedList<String> mMoodList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);
        RecyclerView recyclerView = findViewById(R.id.mood_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MoodAdapter adapter = new MoodAdapter(this, mMoodList);
        recyclerView.setAdapter(adapter);

        mMoodList.addLast("Yesterday");
        mMoodList.addLast("The day before yesterday");
        mMoodList.addLast("Two days ago");
        mMoodList.addLast("Three days ago");
        mMoodList.addLast("Four days ago");
        mMoodList.addLast("Five days ago");
        mMoodList.addLast("Six days ago");
    }
}