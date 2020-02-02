package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.adapter.ViewPagerLoginAdapter;
import com.futsalrecord.futsalinfosystem.fragments.FutsalLoginFragment;
import com.futsalrecord.futsalinfosystem.fragments.UserLoginFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPagerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPagerLogin = findViewById(R.id.viewPagerLogin);

        ViewPagerLoginAdapter viewPagerLoginAdapter = new ViewPagerLoginAdapter(getSupportFragmentManager());
        viewPagerLoginAdapter.addFragment(new UserLoginFragment(), "User Login");
        viewPagerLoginAdapter.addFragment(new FutsalLoginFragment(), "Admin Login");
        viewPagerLogin.setAdapter(viewPagerLoginAdapter);
    }
}
