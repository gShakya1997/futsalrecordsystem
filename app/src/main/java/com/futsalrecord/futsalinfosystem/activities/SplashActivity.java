package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.bll.LoginBLL;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.futsalrecord.futsalinfosystem.url.Url;

public class SplashActivity extends AppCompatActivity {
    private String futsalName, futsalPassword;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkUserProfile()) {
                    proxForCloseApp();
                    Intent intent = new Intent(SplashActivity.this, FutsalDashboard.class);
                    startActivity(intent);
                    finish();
                } else {
                    proxForCloseApp();
                    Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }

    private Boolean checkUserProfile() {
        SharedPreferences sharedPreferencesUser = this.getSharedPreferences("Futsal", MODE_PRIVATE);
        futsalName = sharedPreferencesUser.getString("FutsalName", null);
        futsalPassword = sharedPreferencesUser.getString("FutsalPassword", null);

        //Night mode
//        SharedPreferences sharedPreferencesMode = getSharedPreferences("nightModePrefs", MODE_PRIVATE);
//        Boolean darkMode = sharedPreferencesMode.getBoolean("isNightMode", false);

        if (futsalName != null && futsalPassword != null) {
            login();
//            if (sharedPreferencesMode.getBoolean("isNightMode", false)) {
//                darkMode.equals(true);
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            } else {
//                darkMode.equals(false);
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            }
            return true;
        } else {
//            if (sharedPreferencesMode.getBoolean("isNightMode", false)) {
//                darkMode.equals(true);
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            } else {
//                darkMode.equals(false);
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            }
            return false;
        }
    }

    private void proxForCloseApp() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener proxListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] <= 1) {
                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory(Intent.CATEGORY_HOME);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null) {
            sensorManager.registerListener(proxListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }

    }

    private void login() {
        String futsalNameLogin = futsalName;
        String futsalPasswordLogin = futsalPassword;

        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkFutsal(futsalNameLogin, futsalPasswordLogin)) {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Token expired", Toast.LENGTH_SHORT).show();
        }
    }
}
