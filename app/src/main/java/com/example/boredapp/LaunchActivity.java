package com.example.boredapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    private static final String PREF_IS_FIRST_LAUNCH = "is_first_launch";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences =   getSharedPreferences("pref", Context.MODE_PRIVATE);
        boolean isFirstLaunch = sharedPreferences.getBoolean(PREF_IS_FIRST_LAUNCH,true);
        if (isFirstLaunch){
            sharedPreferences.edit().putBoolean(PREF_IS_FIRST_LAUNCH,false).apply();
            IntroActivity.start(this);
        }else {
            MainActivity.start(this);
        }
        finish();
    }
}
