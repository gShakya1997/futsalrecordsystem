package com.futsalrecord.futsalinfosystem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.futsalrecord.futsalinfosystem.R;

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
