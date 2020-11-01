package com.example.flyhigh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.emsyne.timeslotpicker.utils.SlotPicker;
import com.transferwise.sequencelayout.SequenceStep;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SequenceStep[] steps = new SequenceStep[4];
    int activeStepIndex = 1;
    Button next, btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        next = findViewById(R.id.next);
        // check for first install and intro completed or not and save it in shared preference
        SharedPreferences sharedPref = getSharedPreferences("FirstLaunch", Context.MODE_PRIVATE);
        if (!sharedPref.getBoolean("INTRO_COMPLETED", false)) {
            startActivityForResult(
                    new Intent(getBaseContext(), IntroActivity.class),
                    1
            );
        }
        steps[0] = findViewById(R.id.s1);
        steps[0].setActive(true);
        steps[1] = findViewById(R.id.s2);
        steps[2] = findViewById(R.id.s3);
        steps[3] = findViewById(R.id.s4);

        next.setOnClickListener(v -> {
            ArrayList<String> scheduledtimes = new ArrayList<String>();
            Date d = Calendar.getInstance().getTime();
            String dateSelected = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            //this will indicate that slot is unavailable
            scheduledtimes.add("19:50");
            SlotPicker.pickTime(MainActivity.this, dateSelected, scheduledtimes, 2);
        });
    }

    void setActiveStep(SequenceStep[] steps, int activeStepIndex) {
        for (int i = 0; i < steps.length; i++) {
            if (i == activeStepIndex) {
                steps[i].setActive(true);
                return;
            }
            steps[i].setActive(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            SharedPreferences sharedPref = getSharedPreferences("FirstLaunch", Context.MODE_PRIVATE);
            if (resultCode == Activity.RESULT_OK) {
                sharedPref.edit().putBoolean("INTRO_COMPLETED", true).apply();
            } else {
                sharedPref.edit().putBoolean("INTRO_COMPLETED", false).apply();
            }
        }
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                String date_time = data.getStringExtra("TIME_SELECTED");
                if (date_time != null) {
                    //update progress of step
                    if (activeStepIndex < 4) {
                        steps[activeStepIndex].setTitleTextAppearance(R.style.TextAppearance_AppCompat_Title);
                        setActiveStep(steps, activeStepIndex);
                        steps[activeStepIndex].setSubtitle("Time Slot Booked: " + date_time);
                        activeStepIndex++;
                        Toast.makeText(this, "Slot Booked Successfully", Toast.LENGTH_SHORT).show();
                    }
                    if (activeStepIndex == 4) {
                        next.setVisibility(View.INVISIBLE);
                    }
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                //do something
            }
        }
    }
}