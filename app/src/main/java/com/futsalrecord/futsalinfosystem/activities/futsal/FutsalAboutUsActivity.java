package com.futsalrecord.futsalinfosystem.activities.futsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.FutsalDashboard;

public class FutsalAboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_about_us);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FutsalAboutUsActivity.this, FutsalDashboard.class);
        startActivity(intent);
        finish();
    }
}
