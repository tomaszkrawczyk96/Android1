package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Random;

public class CreatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating);
    }

    public void addClick(View view) {
        EditText NameEditTxt = findViewById(R.id.NAME);
        EditText SurnameEditTxt = findViewById(R.id.SURNAME);
        EditText BirtdayEditTxt = findViewById(R.id.BIRTHDAY);
        EditText PhoneEditTxt = findViewById(R.id.PHONE);
        Spinner rawSpinner = findViewById(R.id.rawSpinner);

        String taskName = NameEditTxt.getText().toString();
        String taskSurname = SurnameEditTxt.getText().toString();
        String taskBirthday = BirtdayEditTxt.getText().toString();
        String taskPhone = PhoneEditTxt.getText().toString();
        String taskSound = rawSpinner.getSelectedItem().toString();

        if(taskName.isEmpty())
            taskName = "default name";
        if (taskSurname.isEmpty())
            taskSurname = "default surname";
        if (taskBirthday.isEmpty())
            taskBirthday = "default birthday";
        if (taskPhone.isEmpty())
            taskPhone = "default phone";
        if (taskSound.isEmpty())
            taskSound = "default sound";

        Random rnd = new Random();
        int i = rnd.nextInt()%4 +1;
        String taskPicture = String.valueOf(i);


        Intent data = new Intent();
        data.putExtra(MainActivity.NAME,taskName);
        data.putExtra(MainActivity.SURNAME,taskSurname);
        data.putExtra(MainActivity.BIRTHDAY,taskBirthday);
        data.putExtra(MainActivity.PHONE,taskPhone);
        data.putExtra(MainActivity.SOUND,taskSound);
        data.putExtra(MainActivity.PICTURE,taskPicture);

        setResult(RESULT_OK,data);

        finish();


    }
}
