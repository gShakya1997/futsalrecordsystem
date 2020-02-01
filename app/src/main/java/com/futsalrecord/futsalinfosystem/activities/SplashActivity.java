package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.url.Url;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.finish();
                checkUserProfile();
            }
        }, 2000);
    }

    private void checkUserProfile() {
        SharedPreferences sharedPreferencesUser = this.getSharedPreferences("Futsal", MODE_PRIVATE);
        String username = sharedPreferencesUser.getString("FutsalName", null);
        String password = sharedPreferencesUser.getString("FutsalPassword", null);

        //Night mode
        SharedPreferences sharedPreferencesMode = getSharedPreferences("nightModePrefs", MODE_PRIVATE);
        Boolean darkMode = sharedPreferencesMode.getBoolean("isNightMode", false);

        if (username != null && password != null) {
            if (sharedPreferencesMode.getBoolean("isNightMode", false)) {
                darkMode.equals(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
                startActivity(intent);
                finish();
            }
            Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
            startActivity(intent);
            finish();
        } else {
            if (sharedPreferencesMode.getBoolean("isNightMode", false)) {
                darkMode.equals(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
                startActivity(intent);
                finish();
            }
            Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
            startActivity(intent);
            finish();
        }
    }
}
