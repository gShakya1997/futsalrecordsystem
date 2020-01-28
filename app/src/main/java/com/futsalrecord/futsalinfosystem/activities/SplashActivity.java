package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.model.Futsal;
import com.futsalrecord.futsalinfosystem.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        //Night mode
        SharedPreferences sharedPreferencesMode = getSharedPreferences("nightModePrefs", MODE_PRIVATE);
        Boolean darkMode = sharedPreferencesMode.getBoolean("isNightMode", false);

        if (sharedPreferencesMode.getBoolean("isNightMode", false)) {
            darkMode.equals(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
            startActivity(intent);
            finish();
        } else {
            darkMode.equals(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
            startActivity(intent);
            finish();
        }
    }
}
