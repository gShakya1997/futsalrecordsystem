package com.futsalrecord.futsalinfosystem.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.GettingStarted;
import com.futsalrecord.futsalinfosystem.activities.registration.UserRegistration;
import com.futsalrecord.futsalinfosystem.activities.user.UserDashboard;
import com.futsalrecord.futsalinfosystem.bll.LoginBLL;
import com.futsalrecord.futsalinfosystem.strictMode.StrictModeClass;
import com.google.android.material.textfield.TextInputLayout;

public class UserLogin extends AppCompatActivity {
    private TextInputLayout userLoginUsername, userLoginPassword;
    private Button btnUserLogin, btnUserRegister;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initialize();
        actionButtons();
    }

    private void actionButtons() {
        btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, UserRegistration.class);
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        userLoginUsername = findViewById(R.id.userLoginUsername);
        userLoginPassword = findViewById(R.id.userLoginPassword);
        btnUserLogin = findViewById(R.id.btnUserLogin);
        btnUserRegister = findViewById(R.id.btnUserRegister);
    }

    private void login() {
        username = userLoginUsername.getEditText().getText().toString().trim();
        password = userLoginPassword.getEditText().getText().toString().trim();

        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(username, password)) {
            saveSharedPreferences();
            Intent intent = new Intent(this, UserDashboard.class);
            startActivity(intent);
        } else {
            userLoginUsername.setError("Enter correct username");
            userLoginPassword.setError("Enter correct password");
        }
    }


    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, GettingStarted.class);
        startActivity(intent);
        finish();
    }
}
