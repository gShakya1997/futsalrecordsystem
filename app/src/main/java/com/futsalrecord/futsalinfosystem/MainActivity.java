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
    private TabLayout tabLayoutLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPagerLogin = findViewById(R.id.viewPagerLogin);
        tabLayoutLogin = findViewById(R.id.tabLayoutLogin);

        tabLayoutLogin.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        ViewPagerLoginAdapter viewPagerLoginAdapter = new ViewPagerLoginAdapter(getSupportFragmentManager());
        viewPagerLoginAdapter.addFragment(new AdminLoginFragment(),"Admin Login");
        viewPagerLoginAdapter.addFragment(new StaffLoginFragment(),"Staff Login");
        viewPagerLogin.setAdapter(viewPagerLoginAdapter);
        tabLayoutLogin.setupWithViewPager(viewPagerLogin);
    }
}
