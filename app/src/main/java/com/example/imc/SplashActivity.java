package com.example.imc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private ImageView imageSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageSplash = findViewById(R.id.imageSplash);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2500);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }
        };
        timerThread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }
}