package com.study.futsalwear;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends WearableActivity {

    private TextView et1, et2;
    private Button btnAdd;
    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(et1.getText().toString());
                num2 = Integer.parseInt(et2.getText().toString());
                int result = num1 + num2;
                Toast.makeText(MainActivity.this, "" + result, Toast.LENGTH_SHORT).show();
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }
}
