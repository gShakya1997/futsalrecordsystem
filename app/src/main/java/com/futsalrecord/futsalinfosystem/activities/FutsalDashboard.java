package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalAboutUsActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalCustomerDataActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalEventActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalFeedback;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalProfileActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalSettingActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.GameActivity;
import com.futsalrecord.futsalinfosystem.activities.login.FutsalLogin;
import com.futsalrecord.futsalinfosystem.createChannel.CreateNotificationChannel;

public class FutsalDashboard extends AppCompatActivity {
    private CardView cardFeedback, cardCustomerDetail, cardAboutUs, cardEarning, cardSetting, cardLogout,
            cardEvent, cardProfile;
    private NotificationManagerCompat notificationManagerCompat;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_dashboard);
        initialize();
        lightSensorForDarkMode();
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateNotificationChannel createNotificationChannel = new CreateNotificationChannel(this);
        createNotificationChannel.createChannel();
        cardActions();
    }

    private void displayNotification() {
        Notification notification = new NotificationCompat.Builder
                (this, CreateNotificationChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_lock_outline_black_24dp)
                .setContentTitle("Logout Successful")
                .setContentText("Goodbye")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    private void cardActions() {
        cardFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, FutsalFeedback.class);
                startActivity(intent);
            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, FutsalProfileActivity.class);
                startActivity(intent);
            }
        });

        cardCustomerDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, FutsalCustomerDataActivity.class);
                startActivity(intent);
            }
        });

        cardEarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, GameActivity.class);
                startActivity(intent);
            }
        });

        cardSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, FutsalSettingActivity.class);
                startActivity(intent);
            }
        });

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        cardEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, FutsalEventActivity.class);
                startActivity(intent);
            }
        });

        cardAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, FutsalAboutUsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void logout() {
        SharedPreferences sharedPreferencesFutsal = getSharedPreferences
                ("Futsal", MODE_PRIVATE);
        SharedPreferences.Editor editorFutsal = sharedPreferencesFutsal.edit();
        editorFutsal.clear();
        editorFutsal.apply();
        displayNotification();
        Intent intent = new Intent(FutsalDashboard.this, FutsalLogin.class);
        startActivity(intent);
        finish();
    }

    private void lightSensorForDarkMode() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        SensorEventListener lightListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] <= 1.08) { //1.08 = deep twilight
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
            sensorManager.registerListener(lightListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        cardFeedback = findViewById(R.id.cardFeedback);
        cardCustomerDetail = findViewById(R.id.cardCustomerDetail);
        cardAboutUs = findViewById(R.id.cardAboutUs);
        cardEarning = findViewById(R.id.cardEarning);
        cardSetting = findViewById(R.id.cardSetting);
        cardLogout = findViewById(R.id.cardLogout);
        cardProfile = findViewById(R.id.cardProfile);
        cardEvent = findViewById(R.id.cardEvent);
    }
}
