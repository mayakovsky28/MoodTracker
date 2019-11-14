package com.lewisgreaves.moodtracker;

/*
 * Created by @Mayakovsky28 on 10/2/19.
 */

import com.google.gson.Gson;

public class Mood {

//    set up the Mood class
    int moodId;
    String moodNote;

    public Mood(int moodId, String moodNote) {
        this.moodId = moodId;
        this.moodNote = moodNote;
    }

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

    public String toJson() {
        return new Gson().toJson(this);
    }
}
