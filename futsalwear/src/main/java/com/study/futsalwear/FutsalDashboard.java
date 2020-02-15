package com.study.futsalwear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class FutsalDashboard extends WearableActivity {

    private TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_dashboard);

        greeting = (TextView) findViewById(R.id.greeting);
        String futsalName = getIntent().getStringExtra("futsalName");
        greeting.setText("Welcome "+futsalName);

        // Enables Always-on
        setAmbientEnabled();
    }
}
