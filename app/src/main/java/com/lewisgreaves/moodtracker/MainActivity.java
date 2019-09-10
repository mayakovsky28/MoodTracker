package com.lewisgreaves.moodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int SWIPE_THRESHOLD = 100;
    private ImageView mHomePage;
    GestureDetector gestureDetector;
    private int currentlyDisplayedMood = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHomePage = findViewById(R.id.homepage);
        gestureDetector = new GestureDetector(this, this);
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

    private void onSwipeDown() {
    if(currentlyDisplayedMood != 0) {
        currentlyDisplayedMood--;
    }
    switch (currentlyDisplayedMood) {
        case 0: mHomePage.setImageResource(R.drawable.smiley_sad);
        break;
        case 1: mHomePage.setImageResource(R.drawable.smiley_disappointed);
        break;
        case 2: mHomePage.setImageResource(R.drawable.smiley_normal);
        break;
        case 3: mHomePage.setImageResource(R.drawable.smiley_happy);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + currentlyDisplayedMood);
    }
        Toast.makeText(this, "You swiped down.", Toast.LENGTH_LONG).show();
    }

    private void onSwipeUp() {
        if(currentlyDisplayedMood < 4) {
            currentlyDisplayedMood++;
        }
        switch (currentlyDisplayedMood) {
            case 1: mHomePage.setImageResource(R.drawable.smiley_disappointed);
            break;
            case 2: mHomePage.setImageResource(R.drawable.smiley_normal);
            break;
            case 3: mHomePage.setImageResource(R.drawable.smiley_happy);
            break;
            case 4: mHomePage.setImageResource(R.drawable.smiley_super_happy);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentlyDisplayedMood);
        }
        Toast.makeText(this, "You swiped up.", Toast.LENGTH_LONG).show();
    }

    private void onSwipeLeft() {
        Toast.makeText(this, "Swipe up or down.", Toast.LENGTH_LONG).show();
    }

    private void onSwipeRight() {
        Toast.makeText(this, "Swipe up or down.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
