package com.futsalrecord.futsalinfosystem.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.login.FutsalLogin;
import com.futsalrecord.futsalinfosystem.activities.login.UserLogin;
import com.futsalrecord.futsalinfosystem.activities.registration.FutsalRegistration;
import com.futsalrecord.futsalinfosystem.activities.registration.UserRegistration;

public class GettingStarted extends AppCompatActivity {
    private Button btnRegFutsalOwner, btnRegUsers, btnFutsalLogin, btnUserLogin;
    private TextView textLogIn;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        binding();
        actionButtons();
        lightSensorForDarkMode();
    }

    private void actionButtons() {

        btnRegFutsalOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingStarted.this, FutsalRegistration.class);
                startActivity(intent);
            }
        });

        btnRegUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingStarted.this, UserRegistration.class);
                startActivity(intent);
            }
        });

        btnFutsalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingStarted.this, FutsalLogin.class);
                startActivity(intent);
            }
        });

        btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingStarted.this, UserLogin.class);
                startActivity(intent);
            }
        });
    }

    private void lightSensorForDarkMode() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        SensorEventListener lightListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] <= 1.0) { //1.0 = deep twilight
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null) {
            sensorManager.registerListener(lightListener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }

    private void binding() {
        btnRegFutsalOwner = findViewById(R.id.btnRegFutsalOwner);
        btnRegUsers = findViewById(R.id.btnRegUsers);
        textLogIn = findViewById(R.id.textLogIn);
        btnUserLogin = findViewById(R.id.btnUserLogin);
        btnFutsalLogin = findViewById(R.id.btnFutsalLogin);
    }
}
