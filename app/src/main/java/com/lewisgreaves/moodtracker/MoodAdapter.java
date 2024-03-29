package com.lewisgreaves.moodtracker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    class MoodViewHolder extends RecyclerView.ViewHolder {
        TextView myMoodTextView;
        ImageView myNoteImageView;
        View parentView;

        MoodViewHolder(View moodView) {
            super(moodView);
            myMoodTextView = moodView.findViewById(R.id.mood);
            myNoteImageView = moodView.findViewById(R.id.noteImageView);
            parentView = moodView;
        }
    }

    private final LinkedList<Mood> mMoodList;
    private Context mContext;
    private LayoutInflater mInflater;
    private int screenWidth;

    MoodAdapter(Context context, LinkedList<Mood> moodList, int screenWidth) {
        mInflater = LayoutInflater.from(context);
        mMoodList = moodList;
        mContext = context;
        this.screenWidth = screenWidth;
    }

    @NonNull
    @Override
    public MoodAdapter.MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.my_mood_view, parent, false);
        int height = parent.getMeasuredHeight() / 7;
        mItemView.setMinimumHeight(height);
        return new MoodViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodAdapter.MoodViewHolder holder, int position) {
        final Mood mCurrent = mMoodList.get(position);

        holder.parentView.setBackgroundColor(Color.parseColor(getMoodColour(mCurrent)));

        int width = (screenWidth / 5) * ++mCurrent.moodId;
        holder.parentView.setLayoutParams(new LinearLayout.LayoutParams(width, holder.myMoodTextView.getLayoutParams().height));

        holder.myMoodTextView.setText(getDayText(position));
        if (mCurrent.moodNote.isEmpty()) {
            holder.myNoteImageView.setVisibility(GONE);
        } else {
            holder.myNoteImageView.setVisibility(VISIBLE);
        }

        holder.myNoteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mCurrent.moodNote, Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getDayText(int position) {
        switch (position) {
            case 0:
                return "Yesterday";
            case 1:
                return "Vorgestern";
            case 2:
                return "Vorvorgestern";
            case 3:
                return "Four days ago";
            case 4:
                return "Five days ago";
            case 5:
                return "Six days ago";
            case 6:
                return "Seven days ago";
            default:
                return "New space time dimension reached";
        }
    }

    private String getMoodColour(Mood mCurrent) {
        if (mCurrent == null) {
            return "#ffb8e986";
        }
        switch (mCurrent.moodId) {
            case 0:
                return "#ffde3c50";
            case 1:
                return "#ff9b9b9b";
            case 2:
                return "#a5468ad9";
            case 4:
                return "#fff9ec4f";
            default:
                return "#ffb8e986";
        }
    }

    @Override
    public int getItemCount() {
        return mMoodList.size();
    }
}