package com.example.flyhigh;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.NavigationPolicy;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class IntroActivity extends com.heinrichreimersoftware.materialintro.app.IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullscreen(true);
        setPageScrollDuration(500);
        setButtonBackVisible(true);
        setButtonBackFunction(BUTTON_BACK_FUNCTION_BACK);

        setNavigationPolicy(new NavigationPolicy() {
            @Override
            public boolean canGoForward(int position) {
                return true;
            }

            @Override
            public boolean canGoBackward(int position) {
                return true;
            }
        });


        addSlide(new SimpleSlide.Builder()
                .title("Prepare for your journey ahead of time")
                .description("Book time slot for Luggage Check-in, Security Checking")
                .image(R.drawable.time)
                .background(R.color.slid1)
                .backgroundDark(R.color.colorPrimary)
                .scrollable(false)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Travel Safe")
                .description("App will notify you if you get in contact with COVID-19 positive person")
                .image(R.drawable.distace)
                .background(R.color.slid2)
                .backgroundDark(R.color.colorPrimary)
                .scrollable(false)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Simple Login")
                .description("Use credentials provided with Airline Ticket")
                .image(R.drawable.mail2)
                .background(R.color.slid3)
                .backgroundDark(R.color.colorPrimary)
                .scrollable(false)
                .build());
    }
}