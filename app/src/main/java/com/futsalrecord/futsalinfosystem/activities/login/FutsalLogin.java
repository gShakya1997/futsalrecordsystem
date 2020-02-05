package com.futsalrecord.futsalinfosystem.activities.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;
import com.futsalrecord.futsalinfosystem.activities.GettingStarted;
import com.futsalrecord.futsalinfosystem.activities.registration.FutsalRegistration;
import com.futsalrecord.futsalinfosystem.bll.LoginBLL;
import com.futsalrecord.futsalinfosystem.createChannel.CreateNotificationChannel;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.google.android.material.textfield.TextInputLayout;

public class FutsalLogin extends AppCompatActivity {
    private TextInputLayout futsalLoginUsername, futsalLoginPassword;
    private Button btnFutsalLogin, btnFutsalRegister;
    private String futsalname, futsalPassword;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_login);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateNotificationChannel createNotificationChannel = new CreateNotificationChannel(this);
        createNotificationChannel.createChannel();
        initialize();
        actionButtons();
    }

    private void actionButtons() {
        btnFutsalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnFutsalRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalLogin.this, FutsalRegistration.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void displayNotification() {
        Notification notification = new NotificationCompat.Builder
                (this, CreateNotificationChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_lock_open_black_24dp)
                .setContentTitle("Login Successful")
                .setContentText("Welcome")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    private void login() {
        futsalname = futsalLoginUsername.getEditText().getText().toString().trim();
        futsalPassword = futsalLoginPassword.getEditText().getText().toString().trim();

        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkFutsal(futsalname, futsalPassword)) {
            saveSharedPreferences();
            displayNotification();
            Intent intent = new Intent(this, FutsalDashboard.class);
            startActivity(intent);
            finish();
        } else {
            futsalLoginUsername.setError("Enter correct username");
            futsalLoginPassword.setError("Enter correct password");
        }
    }

    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Futsal", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FutsalName", futsalname);
        editor.putString("FutsalPassword", futsalPassword);
//        editor.putString("FutsalToken", Url.token);
        editor.apply();
    }

    private void initialize() {
        futsalLoginUsername = findViewById(R.id.futsalLoginUsername);
        futsalLoginPassword = findViewById(R.id.futsalLoginPassword);
        btnFutsalLogin = findViewById(R.id.btnFutsalLogin);
        btnFutsalRegister = findViewById(R.id.btnFutsalRegister);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, GettingStarted.class);
        startActivity(intent);
        finish();
    }
}
