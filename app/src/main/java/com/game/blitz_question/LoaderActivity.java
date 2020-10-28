package com.game.blitz_question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class LoaderActivity extends AppCompatActivity {

    private boolean isActivityRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        ImageView loading = findViewById(R.id.loading);

        Glide.with(this).load(R.drawable.loading).into(loading);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (isActivityRunning) {
                    startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                }
                finish();
            }
        }, 2500);
    }

    @Override
    protected void onPause() {
        isActivityRunning = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        isActivityRunning = true;
        super.onResume();
    }
}