package com.lewisgreaves.moodtracker;

/*
 * Created by @Mayakovsky28 on 10/2/19.
 */

public class Mood {
    int moodId;
    String moodNote;

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int id) {
        moodId = id;
    }

    public String getMoodNote() {
        return moodNote;
    }

    public void setMoodNote(String note) {
        moodNote = note;
    }
}
