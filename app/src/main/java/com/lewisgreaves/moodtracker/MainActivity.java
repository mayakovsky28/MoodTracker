package com.lewisgreaves.moodtracker;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    public static final String KEY_MOOD_ZERO = "KEY_MOOD_ZERO";

    //    arbitrary values governing swipe recognition
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int SWIPE_THRESHOLD = 100;
    //    initialising variables
    private ImageView mHomePage;
    GestureDetector gestureDetector;
    private String todayNote;
    //    happy face shown when app is first opened
    private int currentlySelectedMood = 3;
    //    set up shared preferences
    public SharedPreferences preferences;
    public static final String PREFERENCE_SELECTED_MOOD = "PREFERENCE_SELECTED_MOOD";
    public static final String PREFERENCE_TODAY_NOTE = "PREFERENCE_TODAY_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHomePage = findViewById(R.id.homepage);
        gestureDetector = new GestureDetector(this, this);

//        access shared preferences
        preferences = getSharedPreferences("mySavedMoods", 0);
        currentlySelectedMood = preferences.getInt(PREFERENCE_SELECTED_MOOD, 3);
        todayNote = preferences.getString(PREFERENCE_TODAY_NOTE, "");

//        get the current mood and call corresponding display method
        switch (currentlySelectedMood) {
            case 0:
                toSad();
                break;
            case 1:
                toDisappointed();
                break;
            case 2:
                toNormal();
                break;
            case 3:
                toHappy();
                break;
            case 4:
                toSuperHappy();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentlySelectedMood);
        }
//        trigger broadcast receiver at midnight
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AtMidnight.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            //horizontal swipe
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                result = true;
            }
        } else {
            //vertical swipe
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeDown();
                } else {
                    onSwipeUp();
                }
                result = true;
            }
        }
        return result;
    }

    public void onSwipeDown() {
//        user selects sadder mood by swiping down
        if (currentlySelectedMood != 0) {
            currentlySelectedMood--;
            preferences.edit().putInt(PREFERENCE_SELECTED_MOOD, currentlySelectedMood).apply();
        }
        switch (currentlySelectedMood) {
            case 0:
                toSad();
                break;
            case 1:
                toDisappointed();
                break;
            case 2:
                toNormal();
                break;
            case 3:
                toHappy();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + PREFERENCE_SELECTED_MOOD);
        }
        Toast.makeText(this, "You swiped down.", Toast.LENGTH_LONG).show();
    }

    void onSwipeUp() {
//        user selects happier mood by swiping up
        if (currentlySelectedMood < 4) {
            currentlySelectedMood++;
            preferences.edit().putInt(PREFERENCE_SELECTED_MOOD, currentlySelectedMood).apply();
        }
        switch (currentlySelectedMood) {
            case 1:
                toDisappointed();
                break;
            case 2:
                toNormal();
                break;
            case 3:
                toHappy();
                break;
            case 4:
                toSuperHappy();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentlySelectedMood);
        }
        Toast.makeText(this, "You swiped up.", Toast.LENGTH_LONG).show();
    }

    private void onSwipeLeft() {
        Toast.makeText(this, "Swipe up or down.", Toast.LENGTH_LONG).show();
    }

    private void onSwipeRight() {
        Toast.makeText(this, "Swipe up or down.", Toast.LENGTH_LONG).show();
    }

    //    display functions
    private void toSad() {
        mHomePage.setImageResource(R.drawable.smiley_sad);
        mHomePage.setBackgroundColor(Color.parseColor("#ffde3c50"));
    }

    private void toDisappointed() {
        mHomePage.setImageResource(R.drawable.smiley_disappointed);
        mHomePage.setBackgroundColor(Color.parseColor("#ff9b9b9b"));
    }

    private void toNormal() {
        mHomePage.setImageResource(R.drawable.smiley_normal);
        mHomePage.setBackgroundColor(Color.parseColor("#a5468ad9"));
    }

    private void toHappy() {
        mHomePage.setImageResource(R.drawable.smiley_happy);
        mHomePage.setBackgroundColor(Color.parseColor("#ffb8e986"));
    }

    private void toSuperHappy() {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.gleam);
        mp.start();
        mHomePage.setImageResource(R.drawable.smiley_super_happy);
        mHomePage.setBackgroundColor(Color.parseColor("#fff9ec4f"));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void viewHistory(View view) {

//        put elements of Mood class into key for use in mood history activity
        SharedPreferences preferences = this.getSharedPreferences("mySavedMoods", 0);
        int selectedMood = preferences.getInt(PREFERENCE_SELECTED_MOOD, 3);
        String todayNote = preferences.getString(PREFERENCE_TODAY_NOTE, "");
        Mood mood = new Mood(selectedMood, todayNote);
        String json = mood.toJson();
        preferences.edit().putString(KEY_MOOD_ZERO, json).apply();

//        switch to mood history activity
        Intent intent = new Intent(this, MoodHistory.class);
        startActivity(intent);
    }

    public void addNote(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Describe your mood today.");
        view.setId(R.id.describe);

// Set up the input
        final EditText input = new EditText(this);
        input.setId(R.id.noteEditText);

// Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(todayNote);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                todayNote = input.getText().toString();
                // put into shared preferencess here
                preferences.edit().putString(PREFERENCE_TODAY_NOTE, todayNote).apply();
                Log.d("UserInput", preferences.getString(PREFERENCE_TODAY_NOTE, ""));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}