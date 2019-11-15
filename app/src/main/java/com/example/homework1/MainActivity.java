package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.homework1.tasks.TaskListContent;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements
        TaskFragment.OnListFragmentInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener
{

    public static final String NAME = "name" ;
    public static final String SURNAME = "surname" ;
    public static final String BIRTHDAY = "date" ;
    public static final String PHONE = "number";
    public static final String SOUND = "sound";
    public static final String PICTURE = "picture";


    private int currentItemPosition = -1;

    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;
    private int currentSound= - 1;

    public static final String taskExtra = "taskExtra";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sounds = new Uri[4];
//Use parse method of the Uri class to obtain the Uri of a resource
//specified by a string
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring01);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring02);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring03);
        sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring04);
        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        buttonPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @Override
    public void onListFragmentClickInteraction(TaskListContent.Task task, int position) {
        Toast.makeText(this,getString(R.string.item_selected_msg),Toast.LENGTH_SHORT ).show();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            displayTaskInFragment(task);
        } else {
            startSecondActivity(task, position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(TaskListContent.Task task, int position) { // tutaj [raz]
        switch(task.sPath) {
            case "sound 1":
                currentSound = 0;
                break;
            case "sound 2":
                currentSound = 1;
                break;
            case "sound 3":
                currentSound = 2;
                break;
            case "sound 4":
                currentSound = 3;
                break;
            case "default sound":
                currentSound = 3;
            default:
                currentSound = 3;
        }
        Toast.makeText(this,"play element" + position,Toast.LENGTH_SHORT).show();
        buttonPlayer.reset();
        try {
//Set Data source according to the current_sound value
            buttonPlayer.setDataSource(getApplicationContext(),sounds[currentSound]);
        } catch (IOException e) {
            e.printStackTrace();
        }
//Prepare the player asynchronously
        buttonPlayer.prepareAsync();

//No need to call start() since we call with onPreparedListener

    }



    @Override
    public void onListFragmentImageClickInteraction(int position) {
        Toast.makeText(this,getString(R.string.long_click_msg) +position,Toast.LENGTH_SHORT).show();
        showDeleteDialog();
        currentItemPosition = position;
    }

    public void goActivityCreating(View view) {
        Intent intent = new Intent(this,CreatingActivity.class);
        startActivityForResult(intent,1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == 1) {
                TaskListContent.Task task = new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() +1,
                        data.getStringExtra(NAME),
                        data.getStringExtra(SURNAME),
                        data.getStringExtra(BIRTHDAY),
                        data.getStringExtra(PHONE),
                        data.getStringExtra(SOUND),
                        data.getStringExtra(PICTURE)
                        );
                TaskListContent.addItem(task);
                ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
            } else if (resultCode  == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),getText(R.string.back_message),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showDeleteDialog() {
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != -1 && currentItemPosition < TaskListContent.ITEMS.size()) {
            TaskListContent.removeItem(currentItemPosition);
            ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        }

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.taskFragment);
        if (v!=null) {
            Snackbar.make(v,getString(R.string.delete_cancel_msg), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry_msg), new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            showDeleteDialog();
                        }
                    }).show();
        }

    }

    private void startSecondActivity(TaskListContent.Task task,int position) {
        Intent intent = new Intent(this,TaskInfoActivity.class);
        intent.putExtra(taskExtra,task);
        startActivity(intent);
    }


    private void displayTaskInFragment(TaskListContent.Task task) {
        TaskInfoFragment taskInfoFragment  = ((TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(taskInfoFragment != null) {
            taskInfoFragment.displayTask(task);
        }
    }
}
