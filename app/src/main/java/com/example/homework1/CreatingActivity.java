package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

import static java.lang.System.out;

public class CreatingActivity extends AppCompatActivity {

    EditText NameEditTxt;
    EditText SurnameEditTxt;
    EditText BirtdayEditTxt;
    EditText PhoneEditTxt;
    Spinner rawSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating);
        
    }



    public void addClick(View view) {

        NameEditTxt = findViewById(R.id.NAME);
        SurnameEditTxt = findViewById(R.id.SURNAME);
        BirtdayEditTxt = findViewById(R.id.BIRTHDAY);
        PhoneEditTxt = findViewById(R.id.PHONE);
        rawSpinner = findViewById(R.id.rawSpinner);


        String taskName = NameEditTxt.getText().toString();
        String taskSurname = SurnameEditTxt.getText().toString();
        String taskBirthday = BirtdayEditTxt.getText().toString();
        String taskPhone = PhoneEditTxt.getText().toString();
        String taskSound = rawSpinner.getSelectedItem().toString();


        char[] birtday_array = taskBirthday.toCharArray();
        if (birtday_array.length == 8) {

            char[] phone_array = taskPhone.toCharArray();
            if (phone_array.length == 9 || phone_array.length == 11) {

                    String birtday_day_1 = String.valueOf(birtday_array[0]);
                    String birtday_day_2 = String.valueOf(birtday_array[1]);
                    String birtday_day_3 = String.valueOf(birtday_array[2]);
                    String birtday_day_4 = String.valueOf(birtday_array[3]);
                    String birtday_day_5 = String.valueOf(birtday_array[4]);
                    String birtday_day_6 = String.valueOf(birtday_array[5]);
                    String birtday_day_7 = String.valueOf(birtday_array[6]);
                    String birtday_day_8 = String.valueOf(birtday_array[6]);
                    String day = birtday_day_1 + birtday_day_2;
                    String month = birtday_day_3 + birtday_day_4;
                    String year = birtday_day_5 + birtday_day_6 + birtday_day_7 + birtday_day_8;

                   // int birtday_day = Character.getNumericValue(birtday_array[0] + birtday_array[1]);
                   // int birtday_month = Character.getNumericValue(birtday_array[2] + birtday_array[3]);
                   // int birtday_year_1 = Character.getNumericValue(birtday_array[4]);
                   // int birtday_year_2 = Character.getNumericValue(birtday_array[5]);
                   // int birthday_year_3 = Character.getNumericValue(birtday_array[6]);
                    int birtday_day= Integer.valueOf(day);
                    int birtday_year = Integer.valueOf(year);
                    int birtday_month = Integer.valueOf(month);

                    if (birtday_day > 0 && birtday_day < 32) {
                        if (birtday_month > 0 && birtday_month < 13) {
                            if (birtday_year > 1900  && birtday_year < 2020) {

                                if (taskName.isEmpty())
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
                                int i = rnd.nextInt() % 4 + 1;
                                String taskPicture = String.valueOf(i);

                                if(phone_array.length == 11)
                                    taskPhone = "+" + taskPhone;

                                taskBirthday = day + "/" + month + "/" + year;


                                Intent data = new Intent();
                                data.putExtra(MainActivity.NAME, taskName);
                                data.putExtra(MainActivity.SURNAME, taskSurname);
                                data.putExtra(MainActivity.BIRTHDAY, taskBirthday);
                                data.putExtra(MainActivity.PHONE, taskPhone);
                                data.putExtra(MainActivity.SOUND, taskSound);
                                data.putExtra(MainActivity.PICTURE, taskPicture);

                                setResult(RESULT_OK, data);

                                finish();
                            } else
                                Toast.makeText(getApplicationContext(), getText(R.string.bad_birthday_1), Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getApplicationContext(), getText(R.string.bad_birthday_2), Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), getText(R.string.bad_birthday_3), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getApplicationContext(), getText(R.string.bad_number), Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), getText(R.string.bad_birthday), Toast.LENGTH_SHORT).show();
    }
}
