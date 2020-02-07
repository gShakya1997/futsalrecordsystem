package com.futsalrecord.futsalinfosystem.activities.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.GettingStarted;
import com.futsalrecord.futsalinfosystem.bll.Validation;
import com.google.android.material.textfield.TextInputLayout;


public class UserRegistration extends AppCompatActivity {
    private TextInputLayout userRegUsername, userRegAddress, userRegEmail, userRegPhone,
            userRegPassword, userRegCPassword;
    private Button btnUserNext;
    private RadioGroup rgRegGender;
    private RadioButton rbRegMale, rbRegFemale, rbRegOthers;
    private Bundle userDataBundle = new Bundle();
    private Validation validation = new Validation();
    private String gender;
    private String regEmail;
    private String regName;
    private String regPhone;
    private String regPassword;
    private String regCpassword;
    private String regAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        initialize();
        actionButtons();
    }

    private void actionButtons() {

        btnUserNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validateEmail() | !validatePassword() |
                        !validatePhone() | !validateConfirmPassword() | !validateAddress()) {
                    return;
                }
                int selectGender = rgRegGender.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectGender);
                gender = radioButton.getText().toString().trim();
                userDataBundle.putString("gender", gender);
                Intent intentActivity = new Intent(getApplicationContext(), UserProfilePic.class);
                intentActivity.putExtras(userDataBundle);
                startActivity(intentActivity);
            }
        });
    }

    //Validation
    private boolean validateEmail() {
        regEmail = userRegEmail.getEditText().getText().toString().trim();
        if (validation.validateEmail(regEmail).equals("required")) {
            userRegEmail.setError("Required");
            return false;
        } else if (validation.validateEmail(regEmail).equals("Please enter a valid email")) {
            userRegEmail.setError("Please enter a valid email");
            return false;
        } else {
            userRegEmail.setError(null);
            userDataBundle.putString("email", regEmail);
            return true;
        }
    }

    private boolean validateUsername() {
        regName = userRegUsername.getEditText().getText().toString().trim();
        if (validation.validateUsername(regName).equals("required")) {
            userRegUsername.setError("Required");
            return false;
        } else if (validation.validateUsername(regName).equals("usernameTooLong")) {
            userRegUsername.setError("Username too long");
            return false;
        } else if (validation.validateUsername(regName).equals("usernameTooShort")) {
            userRegUsername.setError("Username too short (More than 6 characters)");
            return false;
        } else {
            userRegUsername.setError(null);
            userDataBundle.putString("username", regName);
            return true;
        }
    }

    private boolean validatePhone() {
        regPhone = userRegPhone.getEditText().getText().toString().trim();
        if (validation.validatePhone(regPhone).equals("required")) {
            userRegPhone.setError("Required");
            return false;
        } else if (validation.validatePhone(regPhone).equals("invalidPhone")) {
            userRegPhone.setError("Please enter a valid phone number");
            return false;
        } else {
            userRegPhone.setError(null);
            userDataBundle.putString("phone", regPhone);
            return true;
        }
    }

    private boolean validatePassword() {
        regPassword = userRegPassword.getEditText().getText().toString().trim();
        if (validation.validatePassword(regPassword).equals("required")) {
            userRegPassword.setError("Required");
            return false;
        } else if (validation.validatePassword(regPassword).equals("weakPassword")) {
            userRegPassword.setError("Password is too weak");
            return false;
        } else {
            userRegPassword.setError(null);
            userDataBundle.putString("password", regPassword);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        regCpassword = userRegCPassword.getEditText().getText().toString().trim();
        if (validation.validateConfirmPassword(regPassword, regCpassword).equals("!Password")) {
            userRegCPassword.setError("Password does not match");
            return false;
        } else if (validation.validateConfirmPassword(regPassword, regCpassword).equals("required")) {
            userRegCPassword.setError("Required");
            return false;
        } else {
            userRegCPassword.setError(null);
            return true;
        }
    }

    private boolean validateAddress() {
        regAddress = userRegAddress.getEditText().getText().toString().trim();
        if (!validation.validateAddress(regAddress)) {
            userRegAddress.setError("Required");
            return false;
        } else {
            userRegAddress.setError(null);
            userDataBundle.putString("address", regAddress);
            return true;
        }
    }

    private void initialize() {
        userRegUsername = findViewById(R.id.userRegUsername);
        userRegAddress = findViewById(R.id.userRegAddress);
        userRegEmail = findViewById(R.id.userRegEmail);
        userRegPhone = findViewById(R.id.userRegPhone);
        userRegPassword = findViewById(R.id.userRegPassword);
        userRegCPassword = findViewById(R.id.userRegCPassword);
        btnUserNext = findViewById(R.id.btnUserNext);
        rgRegGender = findViewById(R.id.rgRegGender);
        rbRegMale = findViewById(R.id.rbRegMale);
        rbRegFemale = findViewById(R.id.rbRegFemale);
        rbRegOthers = findViewById(R.id.rbRegOthers);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UserRegistration.this, GettingStarted.class);
        startActivity(intent);
        finish();
    }
}
