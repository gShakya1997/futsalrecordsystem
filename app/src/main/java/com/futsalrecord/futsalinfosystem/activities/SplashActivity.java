package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.futsalrecord.futsalinfosystem.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUserProfile();
            }
        }, 2000);
    }

    private void checkUserProfile() {
        //For user
        SharedPreferences sharedPreferencesUser = getSharedPreferences("User", MODE_PRIVATE);
        String username = sharedPreferencesUser.getString("username", "");
        String password = sharedPreferencesUser.getString("username", "");

        //For futsal
        SharedPreferences sharedPreferencesFutsal = getSharedPreferences("Futsal", MODE_PRIVATE);
        String futsalName = sharedPreferencesFutsal.getString("FutsalName", "");
        String futsalPassword = sharedPreferencesFutsal.getString("FutsalPassword", "");

        //Night mode
        SharedPreferences sharedPreferencesMode = getSharedPreferences("nightModePrefs", MODE_PRIVATE);
        Boolean darkMode = sharedPreferencesMode.getBoolean("isNightMode", false);


        if (username.equals("admin") | password.equals("admin")) {
            if (sharedPreferencesMode.getBoolean("isNightMode", false)) {
                darkMode.equals(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
                startActivity(intent);
                finish();
            } else {
                darkMode.equals(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
                startActivity(intent);
                finish();
            }
        } else if (futsalName.equals("") | password.equals("")) {
            if (sharedPreferencesMode.getBoolean("isNightMode", false)) {
                darkMode.equals(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
                startActivity(intent);
                finish();
            } else {
                darkMode.equals(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
                startActivity(intent);
                finish();
            }
        } else {
            Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
            startActivity(intent);
            finish();
        }
    }
}
