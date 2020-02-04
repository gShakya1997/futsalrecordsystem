package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;

public class FutsalSettingActivity extends AppCompatActivity {
    private Switch switchDarkMode;
    SharedPreferences sharedPreferences;
    public static final String Key_isnightmode = "isNightMode";
    public static final String MyPreferences = "nightModePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_setting);

        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        binding();
//        checkNightModeActivated();
        actionListeners();
    }

    private void actionListeners() {
//        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                        saveNightModeState(true);
//                        recreate();
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    saveNightModeState(false);
//                    recreate();
//                }
//            }
//        });
    }

    private void binding() {
        switchDarkMode = findViewById(R.id.switchDarkMode);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FutsalSettingActivity.this, FutsalDashboard.class);
        startActivity(intent);
        finish();
    }
}
