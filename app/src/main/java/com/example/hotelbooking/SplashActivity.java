package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    TextView txt_title, txt_slogan, txt_name, txt_subtitle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        txt_slogan = findViewById(R.id.txt_slogan);
        txt_name = findViewById(R.id.txt_name);
        txt_title = findViewById(R.id.txt_title);
        txt_subtitle = findViewById(R.id.txt_subtitle);
        txt_title.setTranslationY(1000f);
        txt_slogan.setTranslationY(1000f);
        txt_name.setTranslationY(1000f);
        txt_subtitle.setTranslationY(1000f);


        txt_title.animate().translationY(0f).setDuration(1500).setStartDelay(0);
        txt_slogan.animate().translationY(0f).setDuration(1500).setStartDelay(0);
        txt_name.animate().translationY(0f).setDuration(2400).setStartDelay(0);
        txt_subtitle.animate().translationY(0f).setDuration(3600).setStartDelay(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity2.class));
            }
        }, 4000);
    }
}