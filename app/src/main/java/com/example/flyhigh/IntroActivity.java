package com.example.flyhigh;

import android.Manifest;
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
                .title("First Slide")
                .description("Need Description")
                .image(R.drawable.mi_ic_finish)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .scrollable(false)
//                .permission(Manifest.permission.CAMERA)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Second Slide")
                .description("Second Description")
                .image(R.drawable.mi_ic_skip)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .scrollable(false)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Third Slide")
                .description("Third Description")
                .image(R.drawable.splash_gif)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .scrollable(false)
                .build());
    }
}