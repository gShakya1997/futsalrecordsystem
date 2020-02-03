package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.bll.LoginBLL;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;

public class SplashActivity extends AppCompatActivity {
    private String futsalName, futsalPassword;

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
        futsalName = sharedPreferencesUser.getString("FutsalName", null);
        futsalPassword = sharedPreferencesUser.getString("FutsalPassword", null);

        //Night mode
        SharedPreferences sharedPreferencesMode = getSharedPreferences("nightModePrefs", MODE_PRIVATE);
        Boolean darkMode = sharedPreferencesMode.getBoolean("isNightMode", false);

        if (futsalName != null && futsalPassword != null) {
            login();
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

    private void login() {
        String futsalNameLogin = futsalName;
        String futsalPasswordLogin = futsalPassword;

        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkFutsal(futsalNameLogin, futsalPasswordLogin)) {
            Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Token expired", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
            startActivity(intent);
        }
    }
}
