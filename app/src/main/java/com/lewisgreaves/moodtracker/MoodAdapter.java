package com.lewisgreaves.moodtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    class MoodViewHolder extends RecyclerView.ViewHolder {
        final TextView myMoodView;
        final MoodAdapter mAdapter;

        MoodViewHolder(View moodView, MoodAdapter adapter) {
            super(moodView);
            myMoodView =  moodView.findViewById(R.id.mood);
            this.mAdapter = adapter;
        }
    }

    private final LinkedList<String> mMoodList;
    private LayoutInflater mInflater;
    MoodAdapter(Context context, LinkedList<String> moodList) {
        mInflater = LayoutInflater.from(context);
        this.mMoodList = moodList;
    }


    @NonNull
    @Override
    public MoodAdapter.MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.my_mood_view, parent, false);
        int height = parent.getMeasuredHeight() / 7;
        mItemView.setMinimumHeight(height);
        return new MoodViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodAdapter.MoodViewHolder holder, int position) {
        String mCurrent = mMoodList.get(position);
        holder.myMoodView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mMoodList.size();
    }
}
