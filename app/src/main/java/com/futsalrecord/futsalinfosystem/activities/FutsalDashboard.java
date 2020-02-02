package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalAboutUsActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalCustomerDataActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalEventActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalFeedback;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalProfileActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalSettingActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.GameActivity;
import com.futsalrecord.futsalinfosystem.createChannel.CreateNotificationChannel;

public class FutsalDashboard extends AppCompatActivity {
    private CardView cardFeedback, cardCustomerDetail, cardAboutUs, cardEarning, cardSetting, cardLogout,
            cardEvent, cardProfile;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_dashboard);
        initialize();
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

        notificationManagerCompat.notify(1,notification);
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
                SharedPreferences sharedPreferencesFutsal = getSharedPreferences
                        ("Futsal", MODE_PRIVATE);
                SharedPreferences.Editor editorFutsal = sharedPreferencesFutsal.edit();
                editorFutsal.clear();
                editorFutsal.apply();
                displayNotification();
                Intent intent = new Intent(FutsalDashboard.this, MainActivity.class);
                startActivity(intent);
                finish();
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
