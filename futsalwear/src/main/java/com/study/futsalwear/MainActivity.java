package com.study.futsalwear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends WearableActivity {

    private TextView etFutsalLogin, etFutsalPassword;
    private Button btnLogin;
    private String futsalname, futsalPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFutsalLogin = findViewById(R.id.etFutsalLogin);
        etFutsalPassword = findViewById(R.id.etFutsalPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }

    private void login() {
        futsalname = etFutsalLogin.getText().toString().trim();
        futsalPassword = etFutsalPassword.getText().toString().trim();


        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkFutsal(futsalname, futsalPassword)) {
            Intent intent = new Intent(this, FutsalDashboard.class);
            intent.putExtra("futsalName",futsalname);
            startActivity(intent);
            finish();
        } else {
            etFutsalLogin.setError("Enter correct username");
            etFutsalPassword.setError("Enter correct password");
        }
    }
}
