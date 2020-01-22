package com.futsalrecord.futsalinfosystem.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.CustomersAdapter;
import com.futsalrecord.futsalinfosystem.model.Customers;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView customerRecyclerView;
    private Switch switchDarkMode;
    SharedPreferences sharedPreferences;
    public static final String Key_isnightmode = "isNightMode";
    public static final String MyPreferences = "nightModePrefs";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        customerRecyclerView = root.findViewById(R.id.customerRecyclerView);
        List<Customers> customersList = new ArrayList<>();
        customersList.add(new Customers("Gunjan Shakya","gunjan.shakya3@gmail.com","9860915753","Male","Kathmandu",R.drawable.noimage));
        customersList.add(new Customers("Yunish Shakya","gunjan.shakya3@gmail.com","9860915753","Male","Patan",R.drawable.noimage));
        CustomersAdapter customersAdapter = new CustomersAdapter(getActivity(),customersList);
        customerRecyclerView.setAdapter(customersAdapter);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sharedPreferences = this.getActivity().getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        switchDarkMode = root.findViewById(R.id.switchDarkMode);
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
        return root;
    }

    private void recreate() {
        getFragmentManager().beginTransaction()
                .detach(HomeFragment.this)
                .attach(HomeFragment.this)
                .commit();
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