package com.example.homework1;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework1.tasks.TaskListContent;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskInfoFragment extends Fragment {


    public TaskInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_info, container, false);
    }

    public void displayTask(TaskListContent.Task task) {
        FragmentActivity activity = getActivity();
        TextView taskInfoNameSurname = activity.findViewById(R.id.taskInfoNameSurname);
        ImageView taskInfoImage = activity.findViewById(R.id.taskInfoImage);
        TextView taskInfoPhone = activity.findViewById(R.id.taskInfoPhone);
        TextView taskInfoBirthday = activity.findViewById(R.id.taskInfoBirthday);
        TextView taskInfoSound = activity.findViewById(R.id.taskInfoSound);

        taskInfoNameSurname.setText(task.name + " " + task.surname);
        taskInfoPhone.setText("Phone: "+ task.phone);
        taskInfoBirthday.setText("Birthday: " + task.birthday);
        taskInfoSound.setText("Sound selected: " + task.sPath);

        Drawable taskDrawable;
        Random rnd = new Random();
        int i = rnd.nextInt()%4 +1;
        switch (i) {
            case 1:
                taskDrawable = activity.getResources().getDrawable(R.drawable.first);
                break;
            case 2:
                taskDrawable = activity.getResources().getDrawable(R.drawable.second);
                break;
            case 3:
                taskDrawable = activity.getResources().getDrawable(R.drawable.third);
                break;
            default:
                taskDrawable = activity.getResources().getDrawable(R.drawable.third);
        }
        taskInfoImage.setImageDrawable(taskDrawable);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            TaskListContent.Task receivedTask = intent.getParcelableExtra(MainActivity.taskExtra);
            if(receivedTask != null) {
                displayTask(receivedTask);
            }
        }
    }


}
