package com.lewisgreaves.moodtracker;

import android.content.SharedPreferences;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
    private final LinkedList<Mood> mMoodList = new LinkedList<>();
    private SharedPreferences preferences;

    @Override
    protected void onStart() {
        super.onStart();
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
//        RelativeLayout relativeLayout = findViewById(R.id.mood_history_parent_layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        int partWidth = relativeLayout.getMeasuredWidth() / 5;
        int partWidth = layoutParams.width / 5;
        RecyclerView recyclerView = findViewById(R.id.mood_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        MoodAdapter adapter = new MoodAdapter(this, mMoodList);
        MoodAdapter adapter = new MoodAdapter(this, mMoodList, partWidth);
        recyclerView.setAdapter(adapter);
    }

    private Mood getMood(String key) {
        String moodOne = preferences.getString(key, "");
        Gson gson  = new Gson();
        return gson.fromJson(moodOne, Mood.class);
    }
}