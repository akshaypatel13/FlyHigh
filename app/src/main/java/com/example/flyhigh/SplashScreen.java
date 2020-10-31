package com.example.flyhigh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        //for setting gif in image holder of activity
        ImageView splash_gif = (ImageView) findViewById(R.id.splash);
        Glide.with(getBaseContext()).load(R.drawable.flyhigh).into(splash_gif);
        new Thread(){
            @Override
            public void run() {
                startMainOrIntroActivity();
            }
        }.start();
    }

    public void startMainOrIntroActivity() {
        try {
            Thread.sleep(1500);
            Intent intent = new Intent(getBaseContext(), Login.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.e("SplashScreen", e.getMessage());
            Toast.makeText(this, "An error occurred. Please try again after some time",
                    Toast.LENGTH_LONG).show();
        }
    }
}