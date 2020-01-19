package com.futsalrecord.futsalinfosystem.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.registration.AdminRegistration;
import com.futsalrecord.futsalinfosystem.registration.UserRegistration;

public class GettingStarted extends AppCompatActivity {
    private Button btnRegFutsalOwner, btnRegUsers;
    private TextView textLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        binding();
        actionButtons();
    }

    private void actionButtons() {
        String text = "Have an account already? Log in";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(GettingStarted.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.BLUE);
            }
        };

        ss.setSpan(clickableSpan,26,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textLogIn.setText(ss);
        textLogIn.setMovementMethod(LinkMovementMethod.getInstance());

        btnRegFutsalOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingStarted.this, AdminRegistration.class);
                startActivity(intent);
            }
        });

        btnRegUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingStarted.this, UserRegistration.class);
                startActivity(intent);
            }
        });
    }

    private void binding(){
        btnRegFutsalOwner = findViewById(R.id.btnRegFutsalOwner);
        btnRegUsers = findViewById(R.id.btnRegUsers);
        textLogIn = findViewById(R.id.textLogIn);
    }
}
