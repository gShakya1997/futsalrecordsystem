package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Switch;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.ui.home.HomeFragment;

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
        SharedPreferences sharedPreferences2 = getSharedPreferences("nightModePrefs", MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        Boolean darkMode = sharedPreferences2.getBoolean("isNightMode", false);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("username", "");
        if (username.equals("admin") && password.equals("admin")) {
            if (sharedPreferences2.getBoolean("isNightMode", false)) {
                darkMode.equals(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
                startActivity(intent);
            } else {
                darkMode.equals(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
                startActivity(intent);
            }
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
            startActivity(intent);
            finish();
        }
    }
}
