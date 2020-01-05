package com.futsalrecord.futsalinfosystem.ui.appearance;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.futsalrecord.futsalinfosystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppearanceFragment extends Fragment {
    private Switch switchAppearance;
    private static final String keyIsNightMode = "isNightMode";
    private SharedPreferences sharedPreferences;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_appearance, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("nightModePref", Context.MODE_PRIVATE);
        switchAppearance = root.findViewById(R.id.switchAppearance);
        checkNightModeActivated();
        switchAppearance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeState(true);
                    restartActivity();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveNightModeState(false);
                    restartActivity();
                }
            }
        });
        return root;
    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(keyIsNightMode, nightMode);
        editor.apply();
    }

    public void checkNightModeActivated(){
        if (sharedPreferences.getBoolean(keyIsNightMode,false)){
            switchAppearance.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            switchAppearance.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void restartActivity(){
        Intent intent = new Intent(getContext(),AppearanceFragment.class);
        startActivity(intent);
        getActivity().finish();
    }
}
