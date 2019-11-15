package com.example.homework1;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework1.TaskFragment.OnListFragmentInteractionListener;
import com.example.homework1.tasks.TaskListContent.Task;

import java.util.List;
import java.util.Random;

import static com.example.homework1.tasks.TaskListContent.ITEMS;
import static com.example.homework1.tasks.TaskListContent.ITEM_MAP;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Task} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTaskRecyclerViewAdapter(List<Task> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Task task = mValues.get(position);
        holder.mItem = task;
        holder.mContentView.setText(task.name);
        Context context = holder.mView.getContext();
        Drawable taskDrawable;
        String i = task.picPath;
        switch (i) {
            case "1":
                taskDrawable = context.getResources().getDrawable(R.drawable.first);
                break;
            case "2":
                taskDrawable = context.getResources().getDrawable(R.drawable.second);
                break;
            case "3":
                taskDrawable = context.getResources().getDrawable(R.drawable.third);
                break;
            default:
                taskDrawable = context.getResources().getDrawable(R.drawable.third);
        }
        holder.mItemImageView.setImageDrawable(taskDrawable);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                    mListener.onListFragmentLongClickInteraction(holder.mItem, position); //tutaj [raz]
                    return true;
            }
        });


        holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentImageClickInteraction(position); // musi być (jeszcze w main activity)
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public Task mItem;
        public ImageButton mImageButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView = view.findViewById(R.id.item_image);
            mContentView =  view.findViewById(R.id.content);
            mImageButton = view.findViewById(R.id.button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
