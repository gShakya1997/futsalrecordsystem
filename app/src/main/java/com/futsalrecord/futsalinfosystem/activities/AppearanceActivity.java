package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.futsalrecord.futsalinfosystem.R;

public class AppearanceActivity extends AppCompatActivity {
    private Switch switchDarkMode;
    SharedPreferences sharedPreferences;
    public static final String Key_isnightmode = "isNightMode";
    public static final String MyPreferences = "nightModePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appearance);
        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        switchDarkMode = findViewById(R.id.switchDarkMode);
        checkNightModeActivated();
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeState(true);
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveNightModeState(false);
                    recreate();
                }
            }
        });
    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Key_isnightmode, nightMode);
        editor.apply();
    }

    private void checkNightModeActivated() {
        if (sharedPreferences.getBoolean(Key_isnightmode, false)) {
            switchDarkMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            switchDarkMode.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
