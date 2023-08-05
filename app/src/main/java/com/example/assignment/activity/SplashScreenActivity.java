package com.example.assignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignment.MainActivity;
import com.example.assignment.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread() {

            public void run() {
                try {
                    sleep(2000);
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                } catch (Exception e) {
                }
            }
        };
        thread.start();
    }
}