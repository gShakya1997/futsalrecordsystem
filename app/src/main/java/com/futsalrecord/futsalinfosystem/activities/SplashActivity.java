package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;

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
                checkUser();
            }
        }, 2000);
    }

    private void checkUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("username", "");
        if (username.equals("admin") && password.equals("admin")){
            Intent intent = new Intent(SplashActivity.this,AdminDashboard.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this,GettingStarted.class);
            startActivity(intent);
            finish();
        }
    }
}
