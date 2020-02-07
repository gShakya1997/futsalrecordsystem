package com.futsalrecord.futsalinfosystem.activities.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.futsalrecord.futsalinfosystem.R;
import com.futsalrecord.futsalinfosystem.activities.GettingStarted;
import com.futsalrecord.futsalinfosystem.bll.Validation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class FutsalRegistration extends AppCompatActivity {
    private TextInputLayout etFutsalName, etFutsalAddress, etFutsalEmail, etFutsalPhone,
            etFutsalPassword, etFutsalCPassword, etFutsalOpeningTime, etFutsalClosingTime,
            etFutsalPrice;
    private Button btnFutsalNext;
    private TextInputEditText futsalOpeningTime, futsalClosingTime;
    private TimePickerDialog timePickerDialog;
    private Calendar calendar;
    private String amPm;
    private Bundle futsalDataBundle = new Bundle();
    private Validation validation = new Validation();
    private String regName;
    private String regAddress;
    private String regEmail;
    private String regPhone;
    private String regPassword;
    private String regCpassword;
    private String regOpeningTime;
    private String regClosingTime;
    private String regPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_registration);
        initialize();
        actionButtons();
    }

    private void actionButtons() {
        btnFutsalNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFutsalName() | !validateAddress() | !validateEmail() | !validatePhone()
                        | !validatePassword() | !validateConfirmPassword() | !validateOpeningTime() |
                        !validateClosingTime() | !validatePrice()) {
                    return;
                }
                Intent intentActivity = new Intent(getApplicationContext(), FutsalProfilePic.class);
                intentActivity.putExtras(futsalDataBundle);
                startActivity(intentActivity);
            }
        });

        futsalOpeningTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(FutsalRegistration.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        futsalOpeningTime.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

        futsalClosingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(FutsalRegistration.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        futsalClosingTime.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });
    }


    //validation
    private boolean validateFutsalName() {
        regName = etFutsalName.getEditText().getText().toString().trim();
        if (!validation.validateFutsalName(regName)) {
            etFutsalName.setError("Required");
            return false;
        } else {
            etFutsalName.setError(null);
            futsalDataBundle.putString("futsalName", regName);
            return true;
        }
    }

    private boolean validateAddress() {
        regAddress = etFutsalAddress.getEditText().getText().toString().trim();
        if (!validation.validateAddress(regAddress)) {
            etFutsalAddress.setError("Required");
            return false;
        } else {
            etFutsalAddress.setError(null);
            futsalDataBundle.putString("futsalAddress", regAddress);
            return true;
        }
    }

    private boolean validateEmail() {
        regEmail = etFutsalEmail.getEditText().getText().toString().trim();
        if (validation.validateEmail(regEmail).equals("required")) {
            etFutsalEmail.setError("Required");
            return false;
        } else if (validation.validateEmail(regEmail).equals("invalidEmail")) {
            etFutsalEmail.setError("Please enter a valid email");
            return false;
        } else {
            etFutsalEmail.setError(null);
            futsalDataBundle.putString("futsalEmail", regEmail);
            return true;
        }
    }

    private boolean validatePhone() {
        regPhone = etFutsalPhone.getEditText().getText().toString().trim();
        if (validation.validatePhone(regPhone).equals("required")) {
            etFutsalPhone.setError("Required");
            return false;
        } else if (validation.validatePhone(regPhone).equals("invalidPhone")) {
            etFutsalPhone.setError("Please enter a valid phone number");
            return false;
        } else {
            etFutsalPhone.setError(null);
            futsalDataBundle.putString("futsalPhone", regPhone);
            return true;
        }
    }

    private boolean validatePassword() {
        regPassword = etFutsalPassword.getEditText().getText().toString().trim();
        if (validation.validatePassword(regPassword).equals("required")) {
            etFutsalPassword.setError("Required");
            return false;
        } else if (validation.validatePassword(regPassword).equals("weakPassword")) {
            etFutsalPassword.setError("Password is too weak");
            return false;
        } else {
            etFutsalPassword.setError(null);
            futsalDataBundle.putString("futsalPassword", regPassword);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        regCpassword = etFutsalCPassword.getEditText().getText().toString().trim();
        if (validation.validateConfirmPassword(regPassword, regCpassword).equals("!Password")) {
            etFutsalCPassword.setError("Password does not match");
            return false;
        } else if (validation.validateConfirmPassword(regPassword, regCpassword).equals("required")) {
            etFutsalCPassword.setError("Required");
            return false;
        } else {
            etFutsalCPassword.setError(null);
            futsalDataBundle.putString("futsalCPassword", regCpassword);
            return true;
        }
    }

    private boolean validateOpeningTime() {
        regOpeningTime = etFutsalOpeningTime.getEditText().getText().toString().trim();
        if (!validation.validateOpeningTime(regOpeningTime)) {
            etFutsalOpeningTime.setError("Required");
            return false;
        } else {
            etFutsalOpeningTime.setError(null);
            futsalDataBundle.putString("futsalOpeningTime", regOpeningTime);
            return true;
        }
    }

    private boolean validateClosingTime() {
        regClosingTime = etFutsalClosingTime.getEditText().getText().toString().trim();
        if (!validation.validateClosingTime(regClosingTime)) {
            etFutsalClosingTime.setError("Required");
            return false;
        } else {
            etFutsalClosingTime.setError(null);
            futsalDataBundle.putString("futsalClosingTime", regClosingTime);
            return true;
        }
    }

    private boolean validatePrice() {
        regPrice = etFutsalPrice.getEditText().getText().toString().trim();
        if (validation.validatePrice(regPrice).equals("required")) {
            etFutsalPrice.setError("Required");
            return false;
        } else if (validation.validatePrice(regPrice).equals("overPriced")) {
            etFutsalPrice.setError("Too expensive");
            return false;
        } else {
            etFutsalPrice.setError(null);
            futsalDataBundle.putString("futsalPrice", regPrice);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FutsalRegistration.this, GettingStarted.class);
        startActivity(intent);
        finish();
    }

    private void initialize() {
        futsalOpeningTime = findViewById(R.id.futsalOpeningTime);
        futsalClosingTime = findViewById(R.id.futsalClosingTime);
        etFutsalName = findViewById(R.id.etFutsalName);
        etFutsalAddress = findViewById(R.id.etFutsalAddress);
        etFutsalEmail = findViewById(R.id.etFutsalEmail);
        etFutsalPhone = findViewById(R.id.etFutsalPhone);
        etFutsalPassword = findViewById(R.id.etFutsalPassword);
        etFutsalCPassword = findViewById(R.id.etFutsalCPassword);
        etFutsalOpeningTime = findViewById(R.id.etFutsalOpeningTime);
        etFutsalClosingTime = findViewById(R.id.etFutsalClosingTime);
        etFutsalPrice = findViewById(R.id.etFutsalPrice);
        btnFutsalNext = findViewById(R.id.btnFutsalNext);
    }
}
