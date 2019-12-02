package com.futsalrecord.futsalinfosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.futsalrecord.futsalinfosystem.adapter.ViewPagerLoginAdapter;
import com.futsalrecord.futsalinfosystem.fragments.AdminLoginFragment;
import com.futsalrecord.futsalinfosystem.fragments.StaffLoginFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPagerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPagerLogin = findViewById(R.id.viewPagerLogin);

        ViewPagerLoginAdapter viewPagerLoginAdapter = new ViewPagerLoginAdapter(getSupportFragmentManager());
        viewPagerLoginAdapter.addFragment(new StaffLoginFragment(), "User Login");
        viewPagerLoginAdapter.addFragment(new AdminLoginFragment(), "Admin Login");
        viewPagerLogin.setAdapter(viewPagerLoginAdapter);
    }
}
