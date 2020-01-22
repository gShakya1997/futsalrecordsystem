package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalCustomerDataActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalEarningActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalHomeActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalSettingActivity;
import com.futsalrecord.futsalinfosystem.activities.futsal.FutsalStaffDataActivity;

public class FutsalDashboard extends AppCompatActivity {
    CardView cardHome, cardCustomerDetail, cardStaff, cardEarning, cardSetting, cardLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_dashboard);
        binding();
        cardActions();
    }

    private void cardActions() {
        cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, FutsalHomeActivity.class);
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

        cardStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, FutsalStaffDataActivity.class);
                startActivity(intent);
            }
        });

        cardEarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FutsalDashboard.this, FutsalEarningActivity.class);
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
                Intent intent = new Intent(FutsalDashboard.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void binding() {
        cardHome = findViewById(R.id.cardHome);
        cardCustomerDetail = findViewById(R.id.cardCustomerDetail);
        cardStaff = findViewById(R.id.cardStaff);
        cardEarning = findViewById(R.id.cardEarning);
        cardSetting = findViewById(R.id.cardSetting);
        cardLogout = findViewById(R.id.cardLogout);
    }
}
